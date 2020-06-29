package com.example.wkl_android.http.callback.impl;

import android.graphics.Bitmap;

import com.example.wkl_android.http.callback.ICallBack;


/**
 * @author li
 * @since 2019/4/1
 */
public abstract class DownCallBack implements ICallBack {
    @Override
    public void onSuccessIO(String responseBody) {

    }

    @Override
    public void onFailedIO(Exception e) {

    }

    @Override
    public void onStartIO() {

    }

    @Override
    public void onFinished() {

    }

    @Override
    public void onBitmapFailedIO(Exception e) {

    }

    @Override
    public void onBitmapSuccessIO(Bitmap bitmap) {

    }
}
