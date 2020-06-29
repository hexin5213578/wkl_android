package com.example.wkl_android.order.confirm.presenter;

import android.text.TextUtils;
import android.util.Log;

import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.couponselect.bean.CouponInfo;
import com.example.wkl_android.http.HttpUtils;
import com.example.wkl_android.http.callback.impl.JsonCallBack;
import com.example.wkl_android.order.confirm.model.ConfirmOrder;
import com.example.wkl_android.order.confirm.model.CreateOrder;
import com.example.wkl_android.order.confirm.model.SubmitGoodsInfo;
import com.example.wkl_android.order.confirm.model.SubmitInfo;
import com.example.wkl_android.order.confirm.model.SubmitShopInfo;
import com.example.wkl_android.order.confirm.ui.IPayView;
import com.example.wkl_android.utils.C;
import com.example.wkl_android.utils.ToastUtil;
import com.google.gson.Gson;

import org.json.JSONObject;

public class PayPresenter extends BasePresenter<IPayView> {
    @Override
    public void cancel() {

    }


    public void confirmOrder(ConfirmOrder confirmOrder) {

        String json = new Gson().toJson(confirmOrder);

        HttpUtils.getInstance().doPostJson(C.SUBMIT_ORDER, json, Common.getToken(), "", new JsonCallBack() {
            @Override
            public void onSuccess(String json) throws Exception {
                JSONObject object = new JSONObject(json);

                try {
                        if(!TextUtils.equals(object.optString("code") , "0x10035")){
                            ToastUtil.show(object.optString("message"));
                            return;
                        }


                    SubmitInfo data = new Gson().fromJson(object.optString("data"), SubmitInfo.class);

                    if (data.getUserCoupon() != null && !TextUtils.isEmpty(data.getUserCoupon().getUserCouponId())) {
                        for (CouponInfo info : data.getUserCouponList()) {
                            if (TextUtils.equals(info.getUserCouponId(), data.getUserCoupon().getUserCouponId())) {
                                info.setSelect(true);
                            }
                        }

                        for (SubmitShopInfo shopInfo : data.getOrderSubmitMasterVOList()) {
                            if (shopInfo.getUserCoupon() != null && !TextUtils.isEmpty(shopInfo.getUserCoupon().getUserCouponId())) {
                                for (CouponInfo info : shopInfo.getUserCouponList()) {
                                    if (TextUtils.equals(info.getUserCouponId(), shopInfo.getUserCoupon().getUserCouponId())) {
                                        info.setSelect(true);
                                    }
                                }

                            }

                            for (SubmitGoodsInfo goodsInfo : shopInfo.getOrderSubmitSlaveVOList()) {
                                if (goodsInfo.getUserCoupon() != null && !TextUtils.isEmpty(goodsInfo.getUserCoupon().getUserCouponId())) {
                                    for (CouponInfo info : goodsInfo.getUserCouponList()) {
                                        if (TextUtils.equals(info.getUserCouponId(), goodsInfo.getUserCoupon().getUserCouponId())) {
                                            info.setSelect(true);
                                        }
                                    }
                                }
                            }
                        }
                    }


                    getView().setData(data);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }


    public void createOrder(CreateOrder confirmOrder) {

        String json = new Gson().toJson(confirmOrder);

        HttpUtils.getInstance().doPostJson(C.CREATE_ORDER, json, Common.getToken(), "", new JsonCallBack() {
            @Override
            public void onSuccess(String json) throws Exception {
                JSONObject object = new JSONObject(json);
                String payId = object.optString("payId");
                if(!TextUtils.isEmpty(payId))
                {
                    getView().payOrder(payId);
                }
             }
        });

    }
}
