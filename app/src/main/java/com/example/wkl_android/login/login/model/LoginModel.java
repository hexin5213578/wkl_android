package com.example.wkl_android.login.login.model;

import com.example.wkl_android.base.model.IModel;
import com.example.wkl_android.http.HttpUtils;
import com.example.wkl_android.http.callback.ICallBack;
import com.example.wkl_android.utils.C;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by szx
 * on 2019/12/30/030
 */
public class LoginModel implements IModel {
    @Override
    public void cancel() {
        HttpUtils.getInstance().cancel(this);
    }

    /**
     * 登录
     *
     * @param name 账户名
     * @param pwd  密码
     */
    public void doLogin(String name, String pwd, ICallBack callBack) {
        String url = C.BASE_URL + "/logon/userLogin";
        ConcurrentHashMap<String, String> params = new ConcurrentHashMap<>();
        params.put("userInfoName", name);
        params.put("userInfoPassword", pwd);
        HttpUtils.getInstance().doPost(url, params, this, callBack);
    }
}
