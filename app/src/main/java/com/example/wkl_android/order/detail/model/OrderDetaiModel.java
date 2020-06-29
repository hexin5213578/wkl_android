package com.example.wkl_android.order.detail.model;

import com.example.wkl_android.base.model.IModel;
import com.example.wkl_android.http.HttpUtils;
import com.example.wkl_android.utils.C;

public class OrderDetaiModel implements IModel {
    @Override
    public void cancel() {
        HttpUtils.getInstance().cancel(this);
    }

    /**
     * 取消订单
     */
    public void cancelOrder() {
        String url = C.BASE_URL+"/cancel";

    }
}
