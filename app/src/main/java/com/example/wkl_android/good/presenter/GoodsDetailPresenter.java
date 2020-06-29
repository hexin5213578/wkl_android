package com.example.wkl_android.good.presenter;

import com.bumptech.glide.Glide;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.good.model.GoodsModel;
import com.example.wkl_android.good.ui.GoodsActivity;
import com.example.wkl_android.good.model.GoodsBean;
import com.example.wkl_android.good.ui.IGoodsView;
import com.example.wkl_android.http.HttpUtils;
import com.example.wkl_android.http.callback.impl.JsonCallBack;
import com.example.wkl_android.utils.C;
import com.example.wkl_android.utils.ToastUtil;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by max
 * on 2020/4/25/025
 */
public class GoodsDetailPresenter extends BasePresenter<IGoodsView> {
    private GoodsModel goodsModel;

    public GoodsDetailPresenter() {
        goodsModel = new GoodsModel();
    }

    @Override
    public void cancel() {

    }

    /**
     * 取得商品详情
     *
     * @param type
     * @param id
     */
    public void getDateil(String type, String id) {
        if (unViewAttachedOrNetworkDisconnected()) {
            return;
        }


        goodsModel.getDetail(type, id, new JsonCallBack() {
            @Override
            public void onSuccess(String json) throws Exception {
                if (!isViewAttached()) {
                    return;
                }
                GoodsBean bean = new Gson().fromJson(json, GoodsBean.class);
                getView().handleGoodsDetail(bean);
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

    /**
     * 关注商品
     */
    public void collectGoods(String id) {
        if (unViewAttachedOrNetworkDisconnected()) {
            return;
        }


        goodsModel.collect(id, new JsonCallBack() {
            @Override
            public void onSuccess(String json) throws Exception {
                if (!isViewAttached()) {
                    return;
                }
                 getView().collectSuccess();
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


    /**
     * 加购物车
     */
    public void addCar(String id) {
        if (unViewAttachedOrNetworkDisconnected()) {
            return;
        }

        goodsModel.addCar(id, new JsonCallBack() {
            @Override
            public void onSuccess(String json) throws Exception {
                if (!isViewAttached()) {
                    return;
                }
                ToastUtil.show("添加购物车成功");
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
