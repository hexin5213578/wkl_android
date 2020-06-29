package com.example.wkl_android.main.shop.address.select.model;

import com.example.wkl_android.base.model.IModel;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.http.HttpUtils;
import com.example.wkl_android.http.callback.ICallBack;
import com.example.wkl_android.utils.C;

/**
 * Created by szx
 * on 2020/1/6/006
 */
public class SelectModel implements IModel {
    @Override
    public void cancel() {
        HttpUtils.getInstance().cancel(this);
    }

    public void getSite(String pId, ICallBack callBack) {
        String url = C.USER + "/site/china/" + pId;
        HttpUtils.getInstance().doGet(url, Common.getToken(), this, callBack);
    }
}
