package com.example.wkl_android.utils;

import android.text.TextUtils;

import com.example.wkl_android.Event.LoginEvent;
import com.example.wkl_android.login.login.model.bean.LoginBean;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

public class UserUtil {
    public static void saveUser(LoginBean user) {
        EventBus.getDefault().post(new LoginEvent());
        SPUtils.getInstance().putString(SPUtils.KEY_USER, new Gson().toJson(user));
    }

    public static void quitLogin(){
        SPUtils.getInstance().putString(SPUtils.KEY_USER, "");
    }

    public static LoginBean getUser() {
        String user_json = SPUtils.getInstance().getString(SPUtils.KEY_USER, "");
        LoginBean bean = new Gson().fromJson(user_json, LoginBean.class);
        return bean;
    }

    public static boolean isLogin() {
        if (getUser() == null || TextUtils.isEmpty(getUser().getToken())) {
            return false;
        }

        return true;
    }
}
