package com.example.wkl_android.main.shop.address.location;

import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.offline.MKOfflineMapListener;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.model.inner.GeoPoint;
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
import com.baidu.mapapi.search.poi.PoiSortType;
import com.baidu.mapapi.utils.CoordinateConverter;
import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.main.shop.address.location.adapter.PoiLittleAdapter;
import com.example.wkl_android.main.shop.address.location.bean.PoiLocation;
import com.example.wkl_android.utils.LogUtils;
import com.example.wkl_android.widget.rv.decoration.CustomDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 定位地址
 */
public class LocationAddressActivity extends BaseActivity
        implements View.OnClickListener, OnGetPoiSearchResultListener, OnGetGeoCoderResultListener {
    @BindView(R.id.ivLeft)
    View back;
    @BindView(R.id.tvTitle)
    TextView title;
    @BindView(R.id.rvAddress)
    RecyclerView rvAddress;
    @BindView(R.id.mapView)
    MapView mapView;
    @BindView(R.id.etKeyWords)
    EditText etKeyWords;
    @BindView(R.id.tvSearch)
    View tvSearch;
    @BindView(R.id.ivMapIcon)
    View ivMapIcon;
    @BindView(R.id.rlMap)
    View rlMap;
    private BaiduMap baiduMap;
    private PoiSearch search;
    private List<PoiLocation> nameList;
    //防止每次定位都重新设置中心点和marker
    private boolean isFirstLocation = true;
    //初始化LocationClient定位类
    //BDAbstractLocationListener为7.2版本新增的Abstract类型的监听接口，原有BDLocationListener接口
    private BDLocationListener myListener = new MyLocationListener();
    private LocationClient mLocationClient;
    private PoiNearbySearchOption nearbySearchOption;
    private StringBuilder addressReult = new StringBuilder();
    private double lat;
    private double lon;
    private LatLng latLng;
    private String province;
    private String city;
    private String district;
    private float radius = 50f;
    private PoiLittleAdapter adapter;
    private GeoCoder geoCoder;
    private int flag;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_location_address;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViews() {
        Intent intent = getIntent();
        if (intent != null) {
            boolean isHome = intent.getBooleanExtra("isHome", false);
            if (isHome) {
                title.setText("我的地址");
                rvAddress.setVisibility(View.GONE);
                dismissLoading();
            } else {
                title.setText("定位地址");
            }
        }
        back.setOnClickListener(this);
        tvSearch.setOnClickListener(this);
        nameList = new ArrayList<>();
        // 3、创建GeoCoder实例对象
        geoCoder = GeoCoder.newInstance();
        // 设置查询结果监听者   ####这里很重要该回调接口有两个方法
        geoCoder.setOnGetGeoCodeResultListener(this);
        initMap();
        initLocation();
        initMapChanged();
    }

    private void initMap() {
        baiduMap = mapView.getMap();
        // 开启定位图层
        baiduMap.setMyLocationEnabled(true);
        //声明LocationClient类
        mLocationClient = new LocationClient(this);
        //注册监听函数
        mLocationClient.registerLocationListener(myListener);
        //开始定位
        mLocationClient.start();
        search = PoiSearch.newInstance();
        search.setOnGetPoiSearchResultListener(this);
        nearbySearchOption = new PoiNearbySearchOption();
    }

    private void mapStartSearch() {
        showLoading();
        String key = etKeyWords.getText().toString();
        nearbySearchOption.location(latLng);
        nearbySearchOption.keyword(key);
        nearbySearchOption.radius(100000);// 检索半径，单位是米 最小1000
        nearbySearchOption.sortType(PoiSortType.distance_from_near_to_far);//搜索类型，从近至远
        nearbySearchOption.pageCapacity(20);//设置每页查询的个数，默认10个
        search.searchNearby(nearbySearchOption);// 发起附近检索请求
    }

    private void initMapChanged() {
        baiduMap.setOnMapStatusChangeListener(new BaiduMap.OnMapStatusChangeListener() {
            //地图状态开始改变。
            public void onMapStatusChangeStart(MapStatus status) {

            }

            @Override
            public void onMapStatusChangeStart(MapStatus mapStatus, int i) {

            }

            //地图状态改变结束
            public void onMapStatusChangeFinish(MapStatus status) {
                //改变结束之后，获取地图可视范围的中心点坐标
                if (flag == 0) {
                    showLoading();
                    latLng = status.target;
                    //地理编码--通过坐标获取周边
                    geoCoder.reverseGeoCode(new ReverseGeoCodeOption().location(latLng));
                }
            }

            //地图状态变化中
            public void onMapStatusChange(MapStatus status) {

            }
        });
    }

    /**
     * 配置定位参数
     */
    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认gcj02，设置返回的定位结果坐标系
        option.setCoorType("bd09ll");
        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        int span = 5000;
        option.setScanSpan(span);
        //可选，设置是否需要地址信息，默认不需要
        option.setIsNeedAddress(true);
        //可选，默认false,设置是否使用gps
        option.setOpenGps(true);
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setLocationNotify(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationDescribe(true);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIsNeedLocationPoiList(true);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.setIgnoreKillProcess(false);
        //可选，默认false，设置是否收集CRASH信息，默认收集
        option.SetIgnoreCacheException(false);
        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        option.setEnableSimulateGps(false);
        mLocationClient.setLocOption(option);
    }

    @Override
    public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {

    }

    /**
     * 地理编码返回结果处理
     *
     * @param result 返回数据
     */
    @Override
    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
        dismissLoading();
        if (result == null) {
            return;
        }
        nameList.clear();
        List<PoiInfo> poiList = result.getPoiList();
        if (poiList == null) {
            return;
        }
        for (int i = 0; i < poiList.size(); i++) {
            PoiInfo poi = poiList.get(i);
            String name = poi.getName();
            String address = poi.getAddress();
            nameList.add(new PoiLocation(name, address, latLng));
        }
        if (adapter == null) {
            return;
        }
        adapter.notifyDataSetChanged();
    }

    /**
     * 实现定位监听 位置一旦有所改变就会调用这个方法
     * 可以在这个方法里面获取到定位之后获取到的一系列数据
     */
    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            lat = location.getLatitude();
            lon = location.getLongitude();
            latLng = new LatLng(lat, lon);
            List<Poi> poiList = location.getPoiList();
            for (int i = 0; i < poiList.size(); i++) {
                Poi poi = poiList.get(i);
                String name = poi.getName();
                String address = poi.getAddr();
                nameList.add(new PoiLocation(name, address, latLng));
            }
            adapter = new PoiLittleAdapter(LocationAddressActivity.this, nameList);
            rvAddress.setAdapter(adapter);
            CustomDecoration customDecoration = new CustomDecoration(LocationAddressActivity.this,
                    CustomDecoration.VERTICAL, R.drawable.shape_ll_divider_gray);
            adapter.setListener((name, address, location1) -> {
                Intent intent = new Intent();
                intent.putExtra("latitude", location1.latitude);
                intent.putExtra("longitude", location1.longitude);
                intent.putExtra("name", name);
                intent.putExtra("province", province);
                intent.putExtra("city", city);
                intent.putExtra("district", district);
                setResult(RESULT_OK, intent);
                finish();
            });
            rvAddress.addItemDecoration(customDecoration);
            //获取省份
            province = location.getProvince();
            //获取城市
            city = location.getCity();
            //获取区县
            district = location.getDistrict();
            addressReult.append(province).append(city).append(district);

            //这个判断是为了防止每次定位都重新设置中心点和marker
            if (isFirstLocation) {
                isFirstLocation = false;
                //设置并显示中心点
                setPosition2Center(baiduMap, lat, lon, true);
            }
        }
    }

    /**
     * 设置中心点和添加marker
     *
     * @param map
     * @param
     * @param isShowLoc
     */
    public void setPosition2Center(BaiduMap map, double latitude, double longitude, Boolean isShowLoc) {
        MyLocationData locData = new MyLocationData.Builder()
                .accuracy(radius)
                .direction(radius).latitude(latitude)
                .longitude(longitude).build();
        map.setMyLocationData(locData);

        if (isShowLoc) {
            LatLng ll = new LatLng(latitude, longitude);
            MapStatus.Builder builder = new MapStatus.Builder();
            builder.target(ll).zoom(18.0f);
            map.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        // 退出时销毁定位
        mLocationClient.unRegisterLocationListener(myListener);
        mLocationClient.stop();
        // 关闭定位图层
        baiduMap.setMyLocationEnabled(false);
        mapView.onDestroy();
        mapView = null;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvSearch:
                flag = 1;
                mapStartSearch();
                rlMap.setVisibility(View.GONE);
                break;
        }
    }

    /**
     * 定位周边小区返回结果回调
     *
     * @param result 结果
     */
    @Override
    public void onGetPoiResult(PoiResult result) {
        dismissLoading();
        if (result == null || result.error == SearchResult.ERRORNO.RESULT_NOT_FOUND) {
            if (result != null) {
                LogUtils.d(result.error);
            } else {
                LogUtils.d("result为null");
            }
            return;
        }

        nameList.clear();
        List<PoiInfo> list = result.getAllPoi();
        if (list == null) {
            return;
        }
        PoiInfo poiInfo1 = list.get(0);
        setPosition2Center(baiduMap, poiInfo1.getLocation().latitude,
                poiInfo1.getLocation().longitude, true);
        for (int i = 0; i < list.size(); i++) {
            PoiInfo poiInfo = list.get(i);
            String name = poiInfo.getName();
            String address = poiInfo.getAddress();
            LatLng location = poiInfo.getLocation();
            nameList.add(new PoiLocation(name, address, location));
        }
        if (adapter == null) {
            return;
        }
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

    }

    @Override
    public void onGetPoiDetailResult(PoiDetailSearchResult poiDetailSearchResult) {

    }

    @Override
    public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

    }
}
