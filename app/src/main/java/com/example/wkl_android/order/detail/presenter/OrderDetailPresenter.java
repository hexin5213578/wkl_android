package com.example.wkl_android.order.detail.presenter;

import android.text.TextUtils;

import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.http.HttpUtils;
import com.example.wkl_android.http.callback.impl.JsonCallBack;
import com.example.wkl_android.order.detail.model.OrderDetaiModel;
import com.example.wkl_android.order.detail.model.OrderDetailInfo;
import com.example.wkl_android.order.detail.ui.IOrderDetailView;
import com.example.wkl_android.order.main.ui.bean.OrderInfo;
import com.example.wkl_android.utils.C;
import com.example.wkl_android.utils.ToastUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.IDN;
import java.util.ArrayList;

public class OrderDetailPresenter extends BasePresenter<IOrderDetailView> {
    private OrderDetaiModel model;

    public OrderDetailPresenter() {
        model = new OrderDetaiModel();
    }

    @Override
    public void cancel() {
        model.cancel();
    }


    public void getOrderData(String id) {
        HttpUtils.getInstance().doGet(C.ORDER_DETAIL + id, Common.getToken(), this, new JsonCallBack() {
            @Override
            public void onSuccess(String json) throws Exception {

                if (!isViewAttached()) {
                    return;
                }
                JSONObject object = new JSONObject(json);
                OrderDetailInfo data = new Gson().fromJson(object.optString("data"), OrderDetailInfo.class);

                getView().setData(data);


            }

            @Override
            public void onFinished() {
                super.onFinished();
                if (!isViewAttached()) {
                    return;
                }
                getView().dismissLoading();
            }
        });
    }

    public void cancelOrder(String id) {

        JSONObject object = new JSONObject();
        String json = "";
        try {
            object.put("data", id);
            json = object.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return;
        }


        HttpUtils.getInstance().doPutJson(C.CANCEL_ORDER, json, Common.getToken(), this, new JsonCallBack() {
            @Override
            public void onSuccess(String json) throws Exception {

                if (!isViewAttached()) {
                    return;
                }

                JSONObject jsonObject = new JSONObject(json);
                if (jsonObject.optBoolean("status")) {
                    getView().cancelOrder();
                } else {
                    ToastUtil.show(jsonObject.optString("message"));
                }


            }

            @Override
            public void onFinished() {
                super.onFinished();
                if (!isViewAttached()) {
                    return;
                }
                getView().dismissLoading();
            }
        });
    }
}
