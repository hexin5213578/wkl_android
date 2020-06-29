package com.example.wkl_android.address;

import android.util.Log;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.Poi;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

import java.util.List;

public class MyLocationListener extends BDAbstractLocationListener {
    BaiduMap mMapView;
    MapView mapView;
    Listener listener;

    public MyLocationListener(MapView mapView, Listener listener) {

        this.mapView = mapView;
        mMapView = mapView.getMap();
        this.listener = listener;
    }

    @Override
    public void onReceiveLocation(BDLocation location) {
        //mapView 销毁后不在处理新接收的位置
        if (location == null || mapView == null) {
            return;
        }
        List<Poi> poiList = location.getPoiList();


        if (poiList != null && poiList.size() > 0) {
            for (Poi poi : poiList) {
                String poiName = poi.getName();    //获取POI名称
                String poiAddr = poi.getAddr();    //获取POI地址 //获取周边POI信息

                Log.e("ZYY", "name--------AAAAAAAAA---------" + poiName + "--" + poiAddr);


            }
        }

        Log.e("ZYY", "--------AAAAAAAAA---------" + location.toString() + "--" + location.getCity());
        MyLocationData locData = new MyLocationData.Builder()
                .accuracy(location.getRadius())
                // 此处设置开发者获取到的方向信息，顺时针0-360
                .direction(location.getDirection()).latitude(location.getLatitude())
                .longitude(location.getLongitude()).build();
        listener.setLatLng(new LatLng(location.getLatitude(), location.getLongitude()));
       /* LatLng address = new LatLng(location.getLatitude(), location.getLongitude());
        //上海为地图中心
        MapStatusUpdate status2 = MapStatusUpdateFactory.newLatLng(address);

        mMapView.setMapStatus(status2);*/
    }

    interface Listener {
        void setLatLng(LatLng latLng);
    }
}