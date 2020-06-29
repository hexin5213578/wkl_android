package com.example.wkl_android.WholesaleMarketDetail.presenter;

import android.app.job.JobScheduler;

import com.example.wkl_android.WholesaleMarketDetail.IWholesaleMarketDetailView;
import com.example.wkl_android.WholesaleMarketDetail.bean.MarketDetailVo;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.good.model.GoodsListBean;
import com.example.wkl_android.http.HttpUtils;
import com.example.wkl_android.http.callback.impl.JsonCallBack;
import com.example.wkl_android.login.login.model.LoginModel;
import com.example.wkl_android.utils.C;
import com.example.wkl_android.utils.ToastUtil;
import com.google.gson.Gson;

import org.json.JSONObject;

/**
 * Created by szx
 * on 2019/12/30/030
 */
public class WholesaleMarketDetailPresenter extends BasePresenter<IWholesaleMarketDetailView> {

    private LoginModel model;

    @Override
    public void cancel() {
        model.cancel();
    }

    public void getData(String id) {

        HttpUtils.getInstance().doGet(C.MARKET_HOME + id, Common.getToken(), this, new JsonCallBack() {
            @Override
            public void onSuccess(String json) throws Exception {

                if (!isViewAttached()) {
                    return;
                }
                Gson g = new Gson();

                JSONObject object = new JSONObject(json);

                MarketDetailVo marketDetailVo = g.fromJson(object.optString("data"), MarketDetailVo.class);
                getView().showData(marketDetailVo);


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

    public void search(int page, int sale, String id) {

        HttpUtils.getInstance().doGet(C.SEARCH + page + "/10/" + "?marketId=" + id + "&sales=" + sale, Common.getToken(), this, new JsonCallBack() {
            @Override
            public void onSuccess(String json) throws Exception {

                if (!isViewAttached()) {
                    return;
                }
                Gson g = new Gson();

                GoodsListBean goodsListBean = g.fromJson(json, GoodsListBean.class);
                getView().setGoods(goodsListBean);

                if (page == 1 && (goodsListBean.getData() == null || goodsListBean.getData().size() == 0)) {
                    ToastUtil.show("没有相关商品");
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

