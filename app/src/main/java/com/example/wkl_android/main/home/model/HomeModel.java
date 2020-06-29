package com.example.wkl_android.main.home.model;

import com.example.wkl_android.base.model.IModel;
import com.example.wkl_android.http.HttpUtils;
import com.example.wkl_android.http.callback.impl.JsonCallBack;
import com.example.wkl_android.utils.C;

/**
 * 处理主页商品
 */
public class HomeModel implements IModel {
    @Override
    public void cancel() {

    }

    /**
     * 取得商品列表
     *
     * @param callBack
     */
    public void getGoodsList(int pageIndex, JsonCallBack callBack) {
        int pageSize = 20;
        String url = C.HOME_PAGE + "/" + pageIndex + "/" + pageSize;

        HttpUtils.getInstance().doGet(url, this, callBack);
    }

    /**
     * 取得轮播图列表
     *
     * @param callBack
     */
    public void getBannerList(JsonCallBack callBack) {
        String url = C.GET_SLIDE;

        HttpUtils.getInstance().doGet(url, this, callBack);
    }

    /**
     * 取得
     *
     * @param callBack
     */
    public void getMenuList(JsonCallBack callBack) {
        String url = C.GET_PLATE;

        HttpUtils.getInstance().doGet(url, this, callBack);
    }
}
