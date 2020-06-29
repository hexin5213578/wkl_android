package com.example.wkl_android.searchshop.presenter;

import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.good.model.GoodsListBean;
import com.example.wkl_android.http.HttpUtils;
import com.example.wkl_android.http.callback.impl.JsonCallBack;
import com.example.wkl_android.login.login.model.LoginModel;
import com.example.wkl_android.searchshop.ISearchShopView;
import com.example.wkl_android.searchshop.bean.ShopVo;
import com.example.wkl_android.utils.C;
import com.example.wkl_android.utils.ToastUtil;
import com.example.wkl_android.wholesale_market.bean.MarketInfo;
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
public class SearchShopPresenter extends BasePresenter<ISearchShopView> {

    private LoginModel model;

    @Override
    public void cancel() {
        model.cancel();
    }

    public void search(int page, int sale, String msg) {

        HttpUtils.getInstance().doGet(C.SEARCH_SHOP + page + "/10/" + "?key=" + msg + "&sales=" + sale, Common.getToken(), this, new JsonCallBack() {
            @Override
            public void onSuccess(String json) throws Exception {

                if (!isViewAttached()) {
                    return;
                }
                Gson g = new Gson();

                Type type = new TypeToken<List<ShopVo>>() {
                }.getType();
                JSONObject object = new JSONObject(json);
                ArrayList<ShopVo> data = g.fromJson(object.optString("data"), type);
                getView().showData(data);

                if (page == 1 && (data == null || data.size() == 0)) {
                    ToastUtil.show("没的搜索到相关商品");
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
