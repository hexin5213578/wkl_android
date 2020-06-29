package com.example.wkl_android.http.callback.impl;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;

import com.example.wkl_android.http.callback.ICallBack;

import java.io.File;

/**
 * @author li
 * @since 2019-07-19
 */
public abstract class BitmapCallBack implements ICallBack {
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
    public void onDownloading(int progress) {

    }

    @Override
    public void onDownloadSuccess(File file) {

    }

    @Override
    public void onDownloadFailed(Exception e) {

    }

    @Override
    public void onBitmapFailedIO(final Exception e) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                onBitmapFailed(e);
            }
        });
    }

    @Override
    public void onBitmapSuccessIO(final Bitmap bitmap) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    onBitmapSuccess(bitmap);
                } catch (Exception e) {
                    onBitmapFailed(e);
                }
            }
        });
    }

    public abstract void onBitmapFailed(Exception e);

    public abstract void onBitmapSuccess(Bitmap bitmap);

    private Handler handler = new Handler(Looper.getMainLooper());
}
