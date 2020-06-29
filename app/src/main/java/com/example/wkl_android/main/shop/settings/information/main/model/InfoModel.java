package com.example.wkl_android.main.shop.settings.information.main.model;

import com.example.wkl_android.base.model.IModel;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.http.HttpUtils;
import com.example.wkl_android.http.callback.ICallBack;
import com.example.wkl_android.main.shop.settings.information.main.model.bean.UpdateInformation;
import com.example.wkl_android.utils.C;
import com.google.gson.Gson;

/**
 * Created by szx
 * on 2020/1/7/007
 */
public class InfoModel implements IModel {
    @Override
    public void cancel() {
        HttpUtils.getInstance().cancel(this);
    }

    /**
     * 获取用户信息
     *
     * @param callBack 回调
     */
    public void getUserInfo(ICallBack callBack) {
        String url = C.USER + "/user/getUser";
        HttpUtils.getInstance().doPostJson(url, "", Common.getToken(), this, callBack);
    }

    /**
     * 设置生日
     *
     * @param date 日期
     */
    public void setBirthday(String date, ICallBack callBack) {
        String url = C.USER + "/user/edit";
        UpdateInformation bean = new UpdateInformation();
        bean.setUserBirthday(date);
        String json = new Gson().toJson(bean);
        HttpUtils.getInstance().doPutJson(url, json, Common.getToken(), this, callBack);
    }

    /**
     * 设置头像
     *
     * @param imgUrl 图片链接
     */
    public void setHead(String imgUrl, ICallBack callBack) {
        String url = C.USER + "/user/edit";
        UpdateInformation bean = new UpdateInformation();
        bean.setUserImage(imgUrl);
        String json = new Gson().toJson(bean);
        HttpUtils.getInstance().doPutJson(url, json, Common.getToken(), this, callBack);
    }
}
