package com.example.wkl_android.http.callback;

import android.graphics.Bitmap;

import java.io.File;

/**
 * @author li
 * @since 2019/3/20
 */
public interface ICallBack {

    void onSuccessIO(String responseBody);

    void onFailedIO(Exception e);

    /**
     * 网络请求开始回调
     * 有可能发生在IO线程
     */
    void onStartIO();

    //完成
    void onFinished();

    void onDownloading(int progress);

    void onDownloadSuccess(File file);

    void onDownloadFailed(Exception e);

    void onBitmapSuccessIO(Bitmap bitmap);

    void onBitmapFailedIO(Exception e);
}
