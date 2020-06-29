package com.example.wkl_android.login.register.model;

import com.example.wkl_android.base.model.IModel;
import com.example.wkl_android.http.HttpUtils;
import com.example.wkl_android.http.callback.ICallBack;
import com.example.wkl_android.login.register.model.bean.ForgetBean;
import com.example.wkl_android.login.register.model.bean.UploadRegister;
import com.example.wkl_android.main.shop.address.remake.model.bean.DataBean;
import com.example.wkl_android.utils.C;
import com.google.gson.Gson;

/**
 * @Author szx
 * @Date: 2019-12-30
 */
public class GetCodeModel implements IModel {
    @Override
    public void cancel() {
        HttpUtils.getInstance().cancel(this);
    }

    /**
     * 获取验证码
     *
     * @param phone    手机号
     * @param callBack 对调
     */
    public void getCode(String phone, ICallBack callBack) {
        String url = C.BASE_URL + "/user/sms/send";
        DataBean bean = new DataBean();
        bean.setData(phone);
        String json = new Gson().toJson(bean);
        HttpUtils.getInstance().doPostJson(url, json, this, callBack);
    }

    /**
     * 注册
     *
     * @param mobile     手机号
     * @param code       验证码
     * @param pwd        密码
     * @param invitation 邀请码
     * @param callBack   回调
     */
    public void regist(String mobile, String code, String pwd, String invitation, ICallBack callBack) {
        String url = C.BASE_URL + "/logon/logon/registerByPhone";
        UploadRegister bean = new UploadRegister();
        bean.setAuthCode(code);
        bean.setInvitationCode(invitation);
        bean.setPhoneNumber(mobile);
        bean.setUserPassword(pwd);
        String json = new Gson().toJson(bean);
        HttpUtils.getInstance().doPostJson(url, json, this, callBack);
    }

    /**
     * 忘记密码
     *
     * @param num
     * @param code
     * @param pass
     * @param callBack
     */
    public void forget(String num, String code, String pass, ICallBack callBack) {
        String url = C.BASE_URL + "/logon/logon/forgetPassword";
        ForgetBean bean = new ForgetBean();
        bean.setPhoneNumber(num);
        bean.setAuthCode(code);
        bean.setNewPassword(pass);
        String json = new Gson().toJson(bean);
        HttpUtils.getInstance().doPostJson(url, json, this, callBack);
    }
}
