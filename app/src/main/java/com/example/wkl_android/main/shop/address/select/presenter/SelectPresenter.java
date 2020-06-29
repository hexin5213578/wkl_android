package com.example.wkl_android.main.shop.address.select.presenter;

import android.text.TextUtils;

import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.http.callback.impl.JsonCallBack;
import com.example.wkl_android.main.shop.address.select.model.SelectModel;
import com.example.wkl_android.main.shop.address.select.model.bean.SelectSites;
import com.example.wkl_android.main.shop.address.select.ui.ISelectView;
import com.example.wkl_android.main.shop.settings.information.certification.model.bean.ImageBean;
import com.example.wkl_android.main.shop.settings.safe_pwd.model.RefreshTokenModel;
import com.example.wkl_android.utils.ConvertUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by szx
 * on 2020/1/6/006
 */
public class SelectPresenter extends BasePresenter<ISelectView> {
    private SelectModel model;
    private RefreshTokenModel refreshTokenModel;

    public SelectPresenter() {
        model = new SelectModel();
        refreshTokenModel = new RefreshTokenModel();
    }

    @Override
    public void cancel() {
        model.cancel();
        refreshTokenModel.cancel();
    }

    /**
     * 获取地区列表
     */

    public void getProvince(String pId) {
        if (unViewAttachedOrNetworkDisconnected()) {
            return;
        }
        if (TextUtils.isEmpty(Common.getToken())) {
            getView().toLogin();
            return;
        }
        model.getSite(pId, new JsonCallBack() {
            @Override
            public void onStart() {
                super.onStart();
                if (!isViewAttached()) {
                    return;
                }
                getView().showLoading();
            }

            @Override
            public void onSuccess(String json) {
                if (!isViewAttached()) {
                    return;
                }
                ConvertUtil<SelectSites> util = new ConvertUtil<>();
                int convert = util.convert(new SelectSites(), json);
                if (convert == 1) {
                    Type type = new TypeToken<List<SelectSites>>() {
                    }.getType();
                    List<SelectSites> list = new Gson().fromJson(json, type);
                    getView().handleSites(list);
                    return;
                }
                if (convert == 2) {
                    if (handleConvert2(json)) {
                        refreshTokenModel.refresh(new JsonCallBack() {
                            @Override
                            public void onSuccess(String json) {
                                Common.handleTokenOverFiled(json);
                                getProvince(pId);
                            }
                        });
                    }
                    return;
                }
                if (convert == 3) {
                    handleCovert3(json);
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
