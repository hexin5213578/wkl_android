package com.example.wkl_android.main.shop.address.main.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.wkl_android.base.model.IModel;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.http.HttpUtils;
import com.example.wkl_android.http.callback.ICallBack;
import com.example.wkl_android.utils.C;

/**
 * Created by szx
 * on 2020/1/6/006
 */
public class AddressModel implements IModel {
    @Override
    public void cancel() {
        HttpUtils.getInstance().cancel(this);
    }

    public void getList(ICallBack callBack) {
        String url = C.USER + "/address/findList";
        HttpUtils.getInstance().doPostJson(url, "", Common.getToken(), this, callBack);
    }

    public void setDefault(String id, ICallBack callBack) {
        JSONObject object = new JSONObject();
        object.put("data", id);
        HttpUtils.getInstance().doPutJson(C.SET_DEFAULT_ADDRESS, object.toJSONString(), Common.getToken(), this, callBack);
    }

    public void delAddress(String id, ICallBack callBack) {
        JSONObject object = new JSONObject();
        object.put("data", id);
        HttpUtils.getInstance().doPutJson(C.DEL_ADDRESS, object.toJSONString(), Common.getToken(), this, callBack);
    }
}
