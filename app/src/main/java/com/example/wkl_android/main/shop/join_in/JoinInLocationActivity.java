package com.example.wkl_android.main.shop.join_in;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.poi.PoiSortType;
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

public class JoinInLocationActivity extends BaseActivity
        implements View.OnClickListener, OnGetPoiSearchResultListener {
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

    @Override
    public int getLayoutRes() {
        return R.layout.activity_join_in_location;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViews() {
        title.setText("定位地址");
        back.setOnClickListener(this);
        tvSearch.setOnClickListener(this);
        nameList = new ArrayList<>();
        initMap();
        initLocation();
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
        nearbySearchOption.radius(1000);// 检索半径，单位是米 最小1000
        nearbySearchOption.sortType(PoiSortType.distance_from_near_to_far);//搜索类型，从近至远
        nearbySearchOption.pageCapacity(20);//设置每页查询的个数，默认10个
        search.searchNearby(nearbySearchOption);// 发起附近检索请求
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

    /**
     * 实现定位监听 位置一旦有所改变就会调用这个方法
     * 可以在这个方法里面获取到定位之后获取到的一系列数据
     */
    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            String province = location.getProvince();    //获取省份
            //获取城市
            String city = location.getCity();
            String district = location.getDistrict();    //获取区县
            addressReult.append(province).append(city).append(district);
            //经纬度
            lat = location.getLatitude();
            lon = location.getLongitude();
            latLng = new LatLng(lat, lon);
            //这个判断是为了防止每次定位都重新设置中心点和marker
            if (isFirstLocation) {
                isFirstLocation = false;
                //设置并显示中心点
                setPosition2Center(baiduMap, location, true);
            }
        }
    }

    /**
     * 设置中心点和添加marker
     *
     * @param map
     * @param bdLocation
     * @param isShowLoc
     */
    public void setPosition2Center(BaiduMap map, BDLocation bdLocation, Boolean isShowLoc) {
        MyLocationData locData = new MyLocationData.Builder()
                .accuracy(bdLocation.getRadius())
                .direction(bdLocation.getRadius()).latitude(bdLocation.getLatitude())
                .longitude(bdLocation.getLongitude()).build();
        map.setMyLocationData(locData);

        if (isShowLoc) {
            LatLng ll = new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude());
            MapStatus.Builder builder = new MapStatus.Builder();
            builder.target(ll).zoom(18.0f);
            map.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvSearch:
                mapStartSearch();
                break;
        }
    }

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
        for (int i = 0; i < list.size(); i++) {
            String name = list.get(i).getName();
            String address = list.get(i).getAddress();
            nameList.add(new PoiLocation(name, address));
        }
        PoiLittleAdapter adapter = new PoiLittleAdapter(this, nameList);
        rvAddress.setAdapter(adapter);
        CustomDecoration customDecoration = new CustomDecoration(this,
                CustomDecoration.VERTICAL, R.drawable.shape_ll_divider_gray);
        rvAddress.addItemDecoration(customDecoration);
        adapter.setListener(new PoiLittleAdapter.OnClickListener() {
            @Override
            public void onClick(String address, String city, LatLng location) {
                Intent intent = new Intent();
                intent.putExtra("address", address);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
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

}
