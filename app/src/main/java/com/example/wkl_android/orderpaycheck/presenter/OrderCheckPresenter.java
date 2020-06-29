package com.example.wkl_android.orderpaycheck.presenter;

import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.good.model.GoodsListBean;
import com.example.wkl_android.http.HttpUtils;
import com.example.wkl_android.http.callback.impl.JsonCallBack;
import com.example.wkl_android.login.login.model.LoginModel;
import com.example.wkl_android.order.main.ui.bean.OrderInfo;
import com.example.wkl_android.orderpaycheck.IOrderCheckView;
import com.example.wkl_android.orderpaycheck.bean.OrderCheckGroup;
import com.example.wkl_android.orderpaycheck.bean.OrderCheckVo;
import com.example.wkl_android.utils.C;
import com.example.wkl_android.utils.ToastUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by szx
 * on 2019/12/30/030
 */
public class OrderCheckPresenter extends BasePresenter<IOrderCheckView> {

    private LoginModel model;

    @Override
    public void cancel() {
        model.cancel();
    }


    public void getData(String id) {
        HttpUtils.getInstance().doGet(C.ORDER_CHECK + id, Common.getToken(), this, new JsonCallBack() {
            @Override
            public void onSuccess(String json) throws Exception {

                if (!isViewAttached()) {
                    return;
                }
                JSONObject object = new JSONObject(json);

//                ArrayList<OrderCheckVo> data = new Gson().fromJson(object.optString("data"), new TypeToken<ArrayList<OrderCheckVo>>() {
//                }.getType());

                OrderCheckGroup data = new Gson().fromJson(object.optString("data") , OrderCheckGroup.class);
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

}
