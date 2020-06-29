package com.example.wkl_android.main.shop.settings.information.login_pwd.model;

import com.example.wkl_android.base.model.IModel;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.http.HttpUtils;
import com.example.wkl_android.http.callback.ICallBack;
import com.example.wkl_android.main.shop.settings.information.login_pwd.model.bean.UploadLoginPwd;
import com.example.wkl_android.utils.C;
import com.google.gson.Gson;

/**
 * Created by szx
 * on 2020/1/8/008
 */
public class UpdateModel implements IModel {
    @Override
    public void cancel() {
        HttpUtils.getInstance().cancel(this);
    }

    /**
     * 修改登录密码
     *
     * @param oldPwd   老密码
     * @param pwd      新密码
     * @param pwdAgain 重复密码
     * @param callBack 回调
     */
    public void update(String oldPwd, String pwd, String pwdAgain, ICallBack callBack) {
        String url = C.BASE_URL + "/logon/logon/editUserPassword";
        UploadLoginPwd bean = new UploadLoginPwd();
        bean.setNewPassword(pwd);
        bean.setOldPassword(oldPwd);
        bean.setNewPassword2(pwdAgain);
        String json = new Gson().toJson(bean);
        HttpUtils.getInstance().doPutJson(url, json, Common.getToken(), this, callBack);
    }
}
