package com.example.wkl_android.orderpay.presenter;

import android.text.TextUtils;

import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.couponselect.bean.CouponInfo;
import com.example.wkl_android.good.model.GoodsListBean;
import com.example.wkl_android.http.HttpUtils;
import com.example.wkl_android.http.callback.impl.JsonCallBack;
import com.example.wkl_android.login.login.model.LoginModel;
import com.example.wkl_android.order.confirm.model.SubmitGoodsInfo;
import com.example.wkl_android.order.confirm.model.SubmitInfo;
import com.example.wkl_android.order.confirm.model.SubmitShopInfo;
import com.example.wkl_android.orderpay.IOrderPayView;
import com.example.wkl_android.orderpay.bean.WXPreOrderInfo;
import com.example.wkl_android.utils.C;
import com.example.wkl_android.utils.ToastUtil;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by szx
 * on 2019/12/30/030
 */
public class OrderPayPresenter extends BasePresenter<IOrderPayView> {

    private LoginModel model;

    @Override
    public void cancel() {
        model.cancel();
    }


    public void getWeChat(String id) {
        try {
            JSONObject object = new JSONObject();
            object.put("data", id);
            String json = object.toString();

            HttpUtils.getInstance().doPostJson(C.WECHAT_PAY, json, Common.getToken(), "", new JsonCallBack() {
                @Override
                public void onSuccess(String json) throws Exception {
                    JSONObject object = new JSONObject(json);
                    WXPreOrderInfo info = new Gson().fromJson(object.optString("data"), WXPreOrderInfo.class);

                    getView().wxPay(info);

                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public void getAlipay(String id) {
        try {
            JSONObject object = new JSONObject();
            object.put("data", id);
            String json = object.toString();

            HttpUtils.getInstance().doPostJson(C.ALI_PAY, json, Common.getToken(), "", new JsonCallBack() {
                @Override
                public void onSuccess(String json) throws Exception {
                    JSONObject object = new JSONObject(json);

                    getView().alipay(object.optString("data"));

                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
