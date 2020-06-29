package com.example.wkl_android.http.callback.impl;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;

import com.example.wkl_android.base.app.BaseApp;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.http.callback.ICallBack;
import com.example.wkl_android.utils.LogUtils;

import java.io.File;

/**
 * Created by szx
 * on 2020/1/4/004
 */
public abstract class JsonCallBack implements ICallBack {

    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public void onSuccessIO(String jsonResult) {
        LogUtils.d(jsonResult);
        handler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    onSuccess(jsonResult);
                } catch (Exception e) {
                    onFailed(e);
                } finally {
                    onFinished();
                }
            }
        });
    }


    @Override
    public void onFailedIO(final Exception e) {
        e.printStackTrace();
        handler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    onFailed(e);
                } finally {
                    onFinished();
                }
            }
        });
    }

    @Override
    public void onStartIO() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                onStart();
            }
        });
    }

    //切换主线程
    public void onStart() {
    }

    //主线程
    public abstract void onSuccess(String json) throws Exception;

    //主线程
    public void onFailed(Exception e) {
        e.printStackTrace();
    }

    @Override
    public void onFinished() {
    }

    @Override
    public void onDownloading(int progress) {
        // do nothing
    }

    @Override
    public void onDownloadSuccess(File file) {
        // do nothing
    }

    @Override
    public void onDownloadFailed(Exception e) {
        // do nothing
    }

    @Override
    public void onBitmapFailedIO(Exception e) {
        // do nothing
    }

    @Override
    public void onBitmapSuccessIO(Bitmap bitmap) {
        // do nothing
    }
}
