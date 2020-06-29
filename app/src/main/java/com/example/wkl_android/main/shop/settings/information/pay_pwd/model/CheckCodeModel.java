package com.example.wkl_android.main.shop.settings.information.pay_pwd.model;

import com.example.wkl_android.base.model.IModel;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.http.HttpUtils;
import com.example.wkl_android.http.callback.ICallBack;
import com.example.wkl_android.main.shop.settings.information.pay_pwd.model.bean.PayPwdCheckCode;
import com.example.wkl_android.utils.C;
import com.google.gson.Gson;

/**
 * Created by szx
 * on 2020/1/8/008
 */
public class CheckCodeModel implements IModel {
    @Override
    public void cancel() {
        HttpUtils.getInstance().cancel(this);
    }

    /**
     * 获取验证码
     *
     * @param callBack 回调
     */
    public void getCode(ICallBack callBack) {
        String url = C.USER + "/sms/sendByUser";
        HttpUtils.getInstance().doPostJson(url, "", Common.getToken(), this, callBack);
    }

    /**
     * 校验验证码
     *
     * @param code     验证码
     * @param callBack 回调
     */
    public void toNext(String code, ICallBack callBack) {
        String url = C.BASE_URL + "/logon/logon/verifyUserAuthCode";
        PayPwdCheckCode bean = new PayPwdCheckCode();
        bean.setData(code);
        String json = new Gson().toJson(bean);
        HttpUtils.getInstance().doPostJson(url, json, Common.getToken(), this, callBack);
    }
}
