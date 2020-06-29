package com.example.wkl_android.main.home.presenter;

import android.text.TextUtils;
import android.util.Log;

import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.good.model.GoodsListBean;
import com.example.wkl_android.http.callback.impl.JsonCallBack;
import com.example.wkl_android.main.home.model.HomeModel;
import com.example.wkl_android.main.home.ui.IHomeBlankView;
import com.example.wkl_android.utils.LogUtils;
import com.example.wkl_android.utils.SPUtils;
import com.google.gson.Gson;

import java.util.ArrayList;

public class HomeBlankPresenter extends BasePresenter<IHomeBlankView> {
    private HomeModel homeModel;

    public HomeBlankPresenter() {
        homeModel = new HomeModel();
    }

    @Override
    public void cancel() {

    }

    /**
     * 获取商品数据
     */
    public void getGoodsData(int pageIndex, boolean isTop) {

        if (pageIndex == 1) {
            String data = SPUtils.getInstance().getString("home_data", "");
            if (!TextUtils.isEmpty(data)) {
                try {
                    GoodsListBean bean = new Gson().fromJson(data, GoodsListBean.class);
                    if (bean.getData() != null) {
                        getView().handleGoodsList(isTop, bean, bean.getGoodsSlideshowVOList(), bean.getGoodsPlateVOList());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }

        homeModel.getGoodsList(pageIndex, new JsonCallBack() {
            @Override
            public void onSuccess(String json) {
                if (!isViewAttached()) {
                    return;
                }
                Log.d("wfs",json);
                GoodsListBean bean = new Gson().fromJson(json, GoodsListBean.class);
                SPUtils.getInstance().putString("home_data", json);


                if (bean.getData() == null) {
                    bean.setData(new ArrayList<>());
                }
                getView().handleGoodsList(isTop, bean, bean.getGoodsSlideshowVOList(), bean.getGoodsPlateVOList());
            }
        });
    }

}
