package com.example.wkl_android.order.main.presenter;

import android.text.TextUtils;

import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.good.model.GoodsListBean;
import com.example.wkl_android.http.HttpUtils;
import com.example.wkl_android.http.callback.impl.JsonCallBack;
import com.example.wkl_android.order.main.ui.IOrderView;
import com.example.wkl_android.order.main.ui.bean.OrderInfo;
import com.example.wkl_android.utils.C;
import com.example.wkl_android.utils.ToastUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by szx
 * on 2020/1/2/002
 */
public class OrderPresenter extends BasePresenter<IOrderView> {
    @Override
    public void cancel() {

    }

    public void getData(int page, String status) {
        HttpUtils.getInstance().doGet(C.ORDER_LIST + page + "/10" + "?status=" + status, Common.getToken(), this, new JsonCallBack() {
            @Override
            public void onSuccess(String json) throws Exception {

                if (!isViewAttached()) {
                    return;
                }
                Gson gson = new Gson();
                JSONObject object = new JSONObject(json);

                ArrayList<OrderInfo> data = new Gson().fromJson(object.optJSONObject("data").optString("list"), new TypeToken<ArrayList<OrderInfo>>() {
                }.getType());

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
