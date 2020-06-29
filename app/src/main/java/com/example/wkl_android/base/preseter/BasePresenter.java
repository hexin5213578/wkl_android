package com.example.wkl_android.base.preseter;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.example.wkl_android.base.app.BaseApp;
import com.example.wkl_android.base.view.IBaseView;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.utils.ConvertResult;
import com.example.wkl_android.utils.GatewayResult;
import com.example.wkl_android.utils.LogUtils;

import java.lang.ref.WeakReference;

/**
 * @author li
 * @since 2019-05-08
 */
public abstract class BasePresenter<V extends IBaseView> {
    /**
     * 弱引用,持有一个{@link IBaseView} 的子类对象
     */
    private WeakReference<V> weakReference;

    /**
     * 建立关联
     *
     * @param view {@link V}
     */
    public void attachView(V view) {
        weakReference = new WeakReference<>(view);
    }

    /**
     * 获取{@link V}
     *
     * @return {@link V}
     */
    protected V getView() {
        return weakReference != null ? weakReference.get() : null;
    }

    /**
     * UI展示相关的操作需要判断一下 Activity 是否已经 finish.
     * <p>
     * 只有当 isViewAttached 返回true时才可以执行与Activity相关的操作,
     * 比如 弹出Dialog、Window、跳转Activity等操作.
     *
     * @return 布尔值
     */
    protected boolean isViewAttached() {
        return getView() != null;
    }

    /**
     * 解除关联
     */
    public void detachView() {
        weakReference.clear();
        cancel();
    }

    public abstract void cancel();

    /**
     * 判断是否联网
     *
     * @return true:联网 false:未联网
     */
    protected boolean isNetworkConnected() {
        return BaseApp.APP.isNetworkConnected();
    }

    protected boolean unViewAttachedOrNetworkDisconnected() {
        return !isViewAttached() || !isNetworkConnected();
    }

    /**
     * 判断当前网络是否是wifi网络
     * if(activeNetInfo.getType()==ConnectivityManager.TYPE_MOBILE) //判断3G网
     *
     * @return true:Wi-Fi连接 false:非Wi-Fi连接
     */
    public boolean isWifi() {
        return BaseApp.APP.isWifi();
    }

    /**
     * 判断token是否失效
     *
     * @param json json数据
     * @return 是否失效
     */
    protected boolean handleConvert2(String json) {
        GatewayResult bean = JSON.parseObject(json, GatewayResult.class);
        if (!bean.getTokenFailed()) {
            getView().toast(bean.getMessage());
        }
        return bean.getTokenFailed();
    }

    /**
     * 其他错误返回处理--解析吐司即可
     *
     * @param json 返回数据
     */
    public void handleCovert3(String json) {
        if (!isViewAttached()) {
            return;
        }
        ConvertResult bean = JSON.parseObject(json, ConvertResult.class);
        getView().toast(bean.getMessage());
    }

    protected boolean isTokenFailed(int code) {
        return 403 == code;
    }

    /**
     * 检查token是否为空
     */
    protected boolean isTokenNull() {
        return TextUtils.isEmpty(Common.getToken());
    }

}
