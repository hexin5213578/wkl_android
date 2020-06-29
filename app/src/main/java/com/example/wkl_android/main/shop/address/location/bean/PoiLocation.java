package com.example.wkl_android.main.shop.address.location.bean;

import com.baidu.mapapi.model.LatLng;

public class PoiLocation {
    private String name;
    private String address;
    private LatLng location;

    public PoiLocation(String name, String address, LatLng location) {
        this.name = name;
        this.address = address;
        this.location = location;
    }

    public PoiLocation(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public LatLng getLocation() {
        return location;
    }

    public void setLocation(LatLng location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
