package com.example.wkl_android.address;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.Projection;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.wkl_android.R;
import com.example.wkl_android.databinding.ActivityMapBinding;
import com.example.wkl_android.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

public class MapActivity extends AppCompatActivity implements BaseQuickAdapter.OnItemClickListener {
    ActivityMapBinding binding;
    LocationClient mLocationClient;
    MyLocationListener locationListener;
    private GeoCoder mSearch;//地理编码
    private LatLng mapCenterLatLng;
    private BaiduMap map;
    private Point mScreenCenterPoint;
    private BitmapDescriptor mbitmap = BitmapDescriptorFactory.fromResource(R.drawable.icon_gcoding);
    private PoiSearch poiSearch;
    SearchAdapter mAdapter;
    ArrayList<PoiInfo> data = new ArrayList<>();

    String tag = "全部";
    private boolean isFirstLocation = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_map);
        initLocationClient();
        initRecycleView();
        initAction();
        initMap();

    }

    private void initMap() {
        poiSearch = PoiSearch.newInstance();
        map = binding.map.getMap();
        map.setMyLocationEnabled(true);

        map.setOnMapTouchListener(new BaiduMap.OnMapTouchListener() {
            @Override
            public void onTouch(MotionEvent motionEvent) {
                switch(motionEvent.getAction()){
                    case MotionEvent.ACTION_UP:
                        mapCenterLatLng = map.getMapStatus().target;
                        Log.d("wfs",mapCenterLatLng.latitude+"_"+mapCenterLatLng.longitude);
                        getAddressData(mapCenterLatLng);
                        break;
                }
            }
        });

        map.setOnMapStatusChangeListener(new BaiduMap.OnMapStatusChangeListener() {
            @Override
            public void onMapStatusChangeStart(MapStatus mapStatus) {

            }

            @Override
            public void onMapStatusChangeStart(MapStatus mapStatus, int i) {

            }

            @Override
            public void onMapStatusChange(MapStatus mapStatus) {

            }

            @Override
            public void onMapStatusChangeFinish(MapStatus mapStatus) {
                if(isFirstLocation){
                    mapCenterLatLng = mapStatus.target;
                    getAddressData(mapCenterLatLng);

                }
            }
        });
        map.setOnMapLoadedCallback(new BaiduMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                if (null != map.getMapStatus()) {
                    MapStatus status = map.getMapStatus();
                    mapCenterLatLng = status.target;
                    Projection projection = map.getProjection();
                    mScreenCenterPoint = projection.toScreenLocation(mapCenterLatLng);
                    MarkerOptions markerOptionsF = new MarkerOptions().position(mapCenterLatLng).icon(mbitmap).perspective(true)
                            .fixedScreenPosition(mScreenCenterPoint);
                    Marker marker = (Marker) (map.addOverlay(markerOptionsF));
                    Log.e("ZYY", "AAA");
                }

                //开启定位
                if(mLocationClient != null &&  !mLocationClient.isStarted()){
                    mLocationClient.start();
                }
            }
        });


//        map.setOnMapDrawFrameCallback(new BaiduMap.OnMapDrawFrameCallback() {
//            @Override
//            public void onMapDrawFrame(GL10 gl10, MapStatus mapStatus) {
//
//            }
//
//            @Override
//            public void onMapDrawFrame(MapStatus mapStatus) {
//                mapCenterLatLng = mapStatus.target;
//                //binding.tvAddress.setText(mapStatus.toString());
//            }
//        });
    }

    private void initLocationClient() {
        double lat = 0;//getIntent().getDoubleExtra("lat",0);
        double lng = getIntent().getDoubleExtra("lng",0);

        if(lat!=0 && lng!=0){
            LatLng serverLatLng = new LatLng(lat,lng);
            MapActivity.this.mapCenterLatLng = serverLatLng;
            updateMap();
            getAddressData(serverLatLng);

        }else {
            locationListener = new MyLocationListener(binding.map, new MyLocationListener.Listener() {
                @Override
                public void setLatLng(LatLng latLng) {
                    MapActivity.this.mapCenterLatLng = latLng;
                    updateMap();
                    getAddressData(latLng);
                    mLocationClient.unRegisterLocationListener(locationListener);
                }
            });
            mLocationClient = new LocationClient(this);
            LocationClientOption option = new LocationClientOption();
            option.setOpenGps(true); // 打开gps
            option.setCoorType("bd09ll"); // 设置坐标类型
            option.setScanSpan(1000);
            mLocationClient.setLocOption(option);
            mLocationClient.registerLocationListener(locationListener);
            mLocationClient.start();
        }
    }

    private void initAction() {
        binding.tvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (TextUtils.isEmpty(binding.etKeyWords.getText().toString())) {
                    ToastUtil.show("请输入搜索内容");
                    return;
                }
                doSearch();

            }
        });

 /*       binding.tvAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tag = "全部";
                handerSearch = true;
                search(binding.etKeyWords.getText().toString(), tag);

                binding.tvAll.setTextColor(ContextCompat.getColor(MapActivity.this, R.color.color_ff5438));
                binding.tvWord.setTextColor(ContextCompat.getColor(MapActivity.this, R.color.color_414141));
                binding.tvQu.setTextColor(ContextCompat.getColor(MapActivity.this, R.color.color_414141));
                binding.tvSholl.setTextColor(ContextCompat.getColor(MapActivity.this, R.color.color_414141));


            }
        });

        binding.tvWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tag = "写字楼";
                handerSearch = true;
                search(binding.etKeyWords.getText().toString(), tag);

                binding.tvAll.setTextColor(ContextCompat.getColor(MapActivity.this, R.color.color_414141));
                binding.tvWord.setTextColor(ContextCompat.getColor(MapActivity.this, R.color.color_ff5438));
                binding.tvQu.setTextColor(ContextCompat.getColor(MapActivity.this, R.color.color_414141));
                binding.tvSholl.setTextColor(ContextCompat.getColor(MapActivity.this, R.color.color_414141));

            }
        });

        binding.tvQu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tag = "小区";
                handerSearch = true;
                search(binding.etKeyWords.getText().toString(), tag);

                binding.tvAll.setTextColor(ContextCompat.getColor(MapActivity.this, R.color.color_414141));
                binding.tvWord.setTextColor(ContextCompat.getColor(MapActivity.this, R.color.color_414141));
                binding.tvQu.setTextColor(ContextCompat.getColor(MapActivity.this, R.color.color_ff5438));
                binding.tvSholl.setTextColor(ContextCompat.getColor(MapActivity.this, R.color.color_414141));

            }
        });

        binding.tvSholl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tag = "学校";
                handerSearch = true;
                search(binding.etKeyWords.getText().toString(), tag);

                binding.tvAll.setTextColor(ContextCompat.getColor(MapActivity.this, R.color.color_414141));
                binding.tvWord.setTextColor(ContextCompat.getColor(MapActivity.this, R.color.color_414141));
                binding.tvQu.setTextColor(ContextCompat.getColor(MapActivity.this, R.color.color_414141));
                binding.tvSholl.setTextColor(ContextCompat.getColor(MapActivity.this, R.color.color_ff5438));

            }
        });*/

        binding.etKeyWords.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    //点击搜索的时候隐藏软键盘
                    doSearch();
                    return true;
                }

                return false;
            }
        });

        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                data.putExtra("lat", mapCenterLatLng.latitude);
                data.putExtra("lon", mapCenterLatLng.longitude);
                data.putExtra("data", mapCenterLatLng);
                setResult(RESULT_OK, data);
                finish();
            }
        });

        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void doSearch() {
        searchListener();
        handerSearch = true;
        search(binding.etKeyWords.getText().toString(), tag);
    }

    private void initRecycleView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.rvList.setLayoutManager(manager);
        mAdapter = new SearchAdapter(data);
        binding.rvList.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
    }

    private void search(String txt, String tag) {
//        data.clear();
//        mAdapter.notifyDataSetChanged();
        poiSearch.searchNearby(new PoiNearbySearchOption().radius(20000).pageCapacity(10).keyword(txt).location(mapCenterLatLng));
        hideKeyboard(binding.tvSearch.getWindowToken());
    }


    /**
     * 获取InputMethodManager，隐藏软键盘
     *
     * @param token
     */
    private void hideKeyboard(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

        PoiInfo info = data.get(position);

        Intent data = new Intent();
        data.putExtra("lat", info.getLocation().latitude);
        data.putExtra("lon", info.getLocation().longitude);
        data.putExtra("name", info.getName());
        data.putExtra("address",info.getAddress());
        data.putExtra("data", info.getLocation());
        setResult(RESULT_OK, data);
        finish();
    }


    public void updateMap() {
        if (mapCenterLatLng != null) {
            MapStatus mapStatus = map.getMapStatus();
            binding.tvAddress.setText(mapStatus.toString());
            MapStatus mMapStatus = new MapStatus.Builder()
                    .target(mapCenterLatLng)
                    .zoom(16)
                    .build();
            MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
            map.setMapStatus(mMapStatusUpdate);



        }

    }

    boolean handerSearch = false;



    private void searchListener() {
        OnGetPoiSearchResultListener listener = new OnGetPoiSearchResultListener() {
            @Override
            public void onGetPoiResult(PoiResult poiResult) {

                Log.e("ZYY", "//////" + poiResult.error);

                if (poiResult == null || poiResult.error == SearchResult.ERRORNO.RESULT_NOT_FOUND) {
                    ToastUtil.show("未搜索到结果");
                    return;
                } else if (poiResult.error == SearchResult.ERRORNO.NO_ERROR && poiResult.getAllPoi() != null && poiResult.getAllPoi().size() > 0) {
                    List<PoiInfo> poiInfoList = poiResult.getAllPoi();

                    if(poiInfoList!=null){
                        getAddressData(poiInfoList.get(0).location);

                    }
//                    data.clear();
//                    data.addAll(poiResult.getAllPoi());
//                    mAdapter.notifyDataSetChanged();

                    binding.llList.setVisibility(View.VISIBLE);

                    mapCenterLatLng = map.getMapStatus().target;


                    if (handerSearch) {
                        MapActivity.this.mapCenterLatLng = poiInfoList.get(0).location;
                        updateMap();
                    }
                    for (PoiInfo addrInfo : poiResult.getAllPoi()) {
                        Log.e("ZYY", addrInfo.name + "  " + addrInfo.location + "  " + addrInfo);
                    }
                }
            }

            @Override
            public void onGetPoiDetailResult(PoiDetailSearchResult poiDetailSearchResult) {
                Log.e("ZYY", "搜索成功。。。。。。。。。。1" + poiDetailSearchResult.getPoiDetailInfoList().size());
            }

            @Override
            public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {
                Log.e("ZYY", "搜索成功。。。。。。。。。。2");
            }

            //废弃
            @Override
            public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

            }
        };
        poiSearch.setOnGetPoiSearchResultListener(listener);
    }

    //获取周边的数据(周围的建筑物)
    private void getAddressData(LatLng latlng){
        data.clear();
        coordinateToAddress(latlng);
    }

    //逆地理编码（即坐标转地址）
    private void coordinateToAddress(LatLng ptCenter){
        mSearch = GeoCoder.newInstance();
        OnGetGeoCoderResultListener listener = new OnGetGeoCoderResultListener() {
            //获取地理编码结果
            public void onGetGeoCodeResult(GeoCodeResult result) {
                if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                    //没有检索到结果
                };
            }
            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
                //获取反向地理编码结果
                if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                    //没有找到检索结果
                }

                List<PoiInfo> poiList = result.getPoiList();

                if(isFirstLocation) {
                    isFirstLocation = false;
                    PoiInfo poiInfo = new PoiInfo();
                    poiInfo.address = result.getAddress();
                    poiInfo.location = result.getLocation();
                    poiList.add(0,poiInfo);
                }

                if(poiList!=null) {
                    data.addAll(poiList);
                    mAdapter.notifyDataSetChanged();
                    for(PoiInfo pi: poiList){
                        Log.e("ZYY",pi.getName()+"_"+pi.getAddress());
                    }
                }
            }
        };
        mSearch.setOnGetGeoCodeResultListener(listener);
        mSearch.reverseGeoCode(new ReverseGeoCodeOption().location(ptCenter));

    }

    @Override
    protected void onResume() {
        binding.map.onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        binding.map.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if(mLocationClient!=null) {
            mLocationClient.stop();
        }
        binding.map.getMap().setMyLocationEnabled(false);
        binding.map.onDestroy();
        super.onDestroy();
    }


}
