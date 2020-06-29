package com.example.wkl_android.main.shop.settings.main.model;

import com.example.wkl_android.base.model.IModel;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.http.HttpUtils;
import com.example.wkl_android.http.callback.ICallBack;
import com.example.wkl_android.utils.C;

/**
 * Created by szx
 * on 2020/1/4/004
 */
public class SettingsModel implements IModel {
    @Override
    public void cancel() {
        HttpUtils.getInstance().cancel(this);
    }

    /**
     * 判断用户是否设置密保问题
     *
     * @param callBack 回调
     */
    public void isExist(ICallBack callBack) {
        String url = C.BASE_URL + "/user/safetyAnswer/isExist";
        HttpUtils.getInstance().doPostJson(url, "", Common.getToken(), this, callBack);
    }

    /**
     * 获取用户手机号码
     *
     * @param callBack 回调
     */
    public void getUserPhone(ICallBack callBack) {
        String url = C.BASE_URL + "/logon/logon/getUserPhoneNumber";
        HttpUtils.getInstance().doPostJson(url, "", Common.getToken(), this, callBack);
    }

    /**
     * 判断用户是否有支付密码
     *
     * @param callBack 回调
     */
    public void getIsPayPwd(ICallBack callBack) {
        String url = C.BASE_URL + "/user/paymentCode/isExist";
        HttpUtils.getInstance().doPostJson(url, "", Common.getToken(), this, callBack);
    }

    /**
     * 判断用户是否实名
     *
     * @param callBack 回调
     */
    public void getIsCertification(ICallBack callBack) {
        String url = C.BASE_URL + "/user/certification/isExist";
        HttpUtils.getInstance().doPostJson(url, "", Common.getToken(), this, callBack);
    }
}
