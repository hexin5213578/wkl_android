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
public class CheckModel implements IModel {
    @Override
    public void cancel() {
        HttpUtils.getInstance().cancel(this);
    }

    /**
     * 校验旧密码
     *
     * @param pwd      旧密码
     * @param callBack 回调
     */
    public void check(String pwd, ICallBack callBack) {
        String url = C.USER + "/paymentCode/compareCode";
        PayPwdCheckCode bean = new PayPwdCheckCode();
        bean.setData(pwd);
        String json = new Gson().toJson(bean);
        HttpUtils.getInstance().doPostJson(url, json, Common.getToken(), this, callBack);

    }
}
