package com.example.wkl_android.wholesale_market.presenter;

import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.good.model.GoodsListBean;
import com.example.wkl_android.http.HttpUtils;
import com.example.wkl_android.http.callback.impl.JsonCallBack;
import com.example.wkl_android.login.login.model.LoginModel;
import com.example.wkl_android.main.shop.address.select.model.bean.SelectSites;
import com.example.wkl_android.utils.C;
import com.example.wkl_android.utils.ToastUtil;
import com.example.wkl_android.wholesale_market.IWholesaleView;
import com.example.wkl_android.wholesale_market.bean.WholesaleMarketInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by szx
 * on 2019/12/30/030
 */
public class WholesaleMarketPresenter extends BasePresenter<IWholesaleView> {

    private LoginModel model;

    @Override
    public void cancel() {
        model.cancel();
    }

    public void getData() {

        HttpUtils.getInstance().doGet(C.MARKET_CLASSIFY , Common.getToken(), this, new JsonCallBack() {
            @Override
            public void onSuccess(String json) throws Exception {

                if (!isViewAttached()) {
                    return;
                }


                Type type = new TypeToken<List<WholesaleMarketInfo>>() {
                }.getType();
                Gson g = new Gson();

                JSONObject object = new JSONObject(json);


                ArrayList<WholesaleMarketInfo> data = g.fromJson(object.optString("data"), type);
                getView().showData(data);

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
