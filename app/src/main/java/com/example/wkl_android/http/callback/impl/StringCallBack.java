package com.example.wkl_android.http.callback.impl;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;

import com.example.wkl_android.http.HttpUtils;
import com.example.wkl_android.http.bean.BaseBean;
import com.example.wkl_android.http.callback.ICallBack;
import com.example.wkl_android.utils.LogUtils;

import java.io.File;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author li
 * @since 2019/4/1
 */
public abstract class StringCallBack<T extends BaseBean> implements ICallBack {

    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public void onSuccessIO(String jsonResult) {
        LogUtils.d(jsonResult);
        try {
            Type type = analysisTypeInfo();
            final T t = HttpUtils.gson.fromJson(jsonResult, type);
            if (t == null) {
                throw new Exception("解析对象为空");
            }
            handler.post(new Runnable() {
                @Override
                public void run() {
                    try {
                        onSuccess(t);
                    } catch (Exception e){
                        onFailed(e);
                    } finally {
                        onFinished();
                    }
                }
            });
        } catch (Exception e) {
            onFailedIO(e);
        }
    }

    /**
     * 获取泛型类{@link T}的Type
     *
     * @return {@link Type}
     */
    private Type analysisTypeInfo() {
        Type genType = getClass().getGenericSuperclass();
        if (genType instanceof ParameterizedType) {
            Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
            if (params.length > 0) {
                return params[0];
            }
        }
        return null;
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
    public abstract void onSuccess(T bean) throws Exception;

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
