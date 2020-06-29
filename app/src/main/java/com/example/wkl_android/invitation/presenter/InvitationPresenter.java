package com.example.wkl_android.invitation.presenter;

import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.good.model.GoodsListBean;
import com.example.wkl_android.http.HttpUtils;
import com.example.wkl_android.http.callback.impl.JsonCallBack;
import com.example.wkl_android.invitation.IInvitationView;
import com.example.wkl_android.login.login.model.LoginModel;
import com.example.wkl_android.utils.C;
import com.example.wkl_android.utils.ToastUtil;
import com.google.gson.Gson;

/**
 * Created by szx
 * on 2019/12/30/030
 */
public class InvitationPresenter extends BasePresenter<IInvitationView> {

    private LoginModel model;

    @Override
    public void cancel() {
        model.cancel();
    }

    public void search(int page, int sale, String msg) {

        HttpUtils.getInstance().doGet(C.SEARCH + page + "/10/" + "?key=" + msg + "&sales=" + sale, Common.getToken(), this, new JsonCallBack() {
            @Override
            public void onSuccess(String json) throws Exception {

                if (!isViewAttached()) {
                    return;
                }
                Gson g = new Gson();

                GoodsListBean goodsListBean = g.fromJson(json, GoodsListBean.class);
                getView().showData(goodsListBean);

                if (page == 1 && (goodsListBean.getData() == null || goodsListBean.getData().size() == 0)) {
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

    public void searchMarket(int page, int sale, String classifyId , String marketId) {

        HttpUtils.getInstance().doGet(C.SEARCH + page + "/10/" + "?classifyId=" + classifyId + "&marketId=" + marketId, Common.getToken(), this, new JsonCallBack() {
            @Override
            public void onSuccess(String json) throws Exception {

                if (!isViewAttached()) {
                    return;
                }
                Gson g = new Gson();

                GoodsListBean goodsListBean = g.fromJson(json, GoodsListBean.class);
                getView().showData(goodsListBean);

                if (page == 1 && (goodsListBean.getData() == null || goodsListBean.getData().size() == 0)) {
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
