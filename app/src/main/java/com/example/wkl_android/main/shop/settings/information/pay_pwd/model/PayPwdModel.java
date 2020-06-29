package com.example.wkl_android.main.shop.settings.information.pay_pwd.model;

import com.example.wkl_android.base.model.IModel;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.http.HttpUtils;
import com.example.wkl_android.http.callback.ICallBack;
import com.example.wkl_android.main.shop.settings.information.pay_pwd.model.bean.UploadPayPwd;
import com.example.wkl_android.utils.C;
import com.google.gson.Gson;

/**
 * Created by szx
 * on 2020/1/8/008
 */
public class PayPwdModel implements IModel {
    private String baseUrl;
    private boolean isReset;

    public PayPwdModel(boolean isReset) {
        this.isReset = isReset;
        if (isReset) {
            baseUrl = C.USER + "/paymentCode/editPayCode";
        } else {
            baseUrl = C.USER + "/paymentCode/setPayCode";
        }
    }

    @Override
    public void cancel() {
        HttpUtils.getInstance().cancel(this);
    }

    /**
     * 设置支付密码
     *
     * @param pwd      密码
     * @param pwdAgain 确认密码
     * @param callBack 回调
     */
    public void setPwd(String pwd, String pwdAgain, ICallBack callBack) {
        UploadPayPwd bean = new UploadPayPwd();
        bean.setPpaymentCode(pwd);
        bean.setPpaymentCode2(pwdAgain);
        String json = new Gson().toJson(bean);
        if (isReset) {
            HttpUtils.getInstance().doPutJson(baseUrl, json, Common.getToken(), this, callBack);
        } else {
            HttpUtils.getInstance().doPostJson(baseUrl, json, Common.getToken(), this, callBack);
        }
    }
}
