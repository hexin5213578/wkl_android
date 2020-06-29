package com.example.wkl_android.main.shop.settings.information.bind_phone.model;

import com.example.wkl_android.base.model.IModel;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.http.HttpUtils;
import com.example.wkl_android.http.callback.ICallBack;
import com.example.wkl_android.main.shop.settings.information.bind_phone.model.bean.UploadPhone;
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
     * 提交修改手机号
     *
     * @param phone    手机号
     * @param code     验证码
     * @param callBack 回调
     */
    public void submit(String phone, String code, ICallBack callBack) {
        String url = C.BASE_URL + "/logon/logon/editUserPhone";
        UploadPhone bean = new UploadPhone();
        bean.setPhoneNumber(phone);
        bean.setVerifyCode(code);
        String json = new Gson().toJson(bean);
        HttpUtils.getInstance().doPutJson(url, json, Common.getToken(), this, callBack);
    }
}
