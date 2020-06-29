package com.example.wkl_android.select_city.model;

import com.example.wkl_android.base.model.IModel;
import com.example.wkl_android.http.HttpUtils;

public class SelectCityModel implements IModel {
    @Override
    public void cancel() {
        HttpUtils.getInstance().cancel(this);
    }

    public void getData() {
    }
}
