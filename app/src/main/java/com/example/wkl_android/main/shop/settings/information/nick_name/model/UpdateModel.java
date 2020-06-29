package com.example.wkl_android.main.shop.settings.information.nick_name.model;

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
public class UpdateModel implements IModel {
    @Override
    public void cancel() {
        HttpUtils.getInstance().cancel(this);
    }

    /**
     * 保存操作
     *
     * @param nickName 昵称
     * @param callBack 回调
     */
    public void save(String nickName, ICallBack callBack) {
        String url = C.USER + "/user/edit";
        UpdateInformation bean = new UpdateInformation();
        bean.setUserNickname(nickName);
        String json = new Gson().toJson(bean);
        HttpUtils.getInstance().doPutJson(url, json, Common.getToken(), this, callBack);
    }
}
