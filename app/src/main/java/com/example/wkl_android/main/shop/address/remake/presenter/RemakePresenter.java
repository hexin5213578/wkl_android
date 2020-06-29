package com.example.wkl_android.main.shop.address.remake.presenter;

import android.text.TextUtils;

import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.http.bean.BaseBean;
import com.example.wkl_android.http.callback.impl.JsonCallBack;
import com.example.wkl_android.http.callback.impl.StringCallBack;
import com.example.wkl_android.main.shop.address.remake.model.RemakeModel;
import com.example.wkl_android.main.shop.address.remake.ui.IRemakeView;
import com.example.wkl_android.main.shop.settings.safe_pwd.model.RefreshTokenModel;

/**
 * Created by szx
 * on 2020/1/7/007
 */
public class RemakePresenter extends BasePresenter<IRemakeView> {
    private RemakeModel model;
    private RefreshTokenModel pwdSafeModel;

    public RemakePresenter() {
        model = new RemakeModel();
        pwdSafeModel = new RefreshTokenModel();
    }

    @Override
    public void cancel() {
        model.cancel();
        pwdSafeModel.cancel();
    }

    public void deleteAddress(String id) {
        if (unViewAttachedOrNetworkDisconnected()) {
            return;
        }
        if (TextUtils.isEmpty(Common.getToken())) {
            getView().toLogin();
            return;
        }
        model.deleteAddress(id, new StringCallBack<BaseBean>() {
            @Override
            public void onSuccess(BaseBean bean) {
                if (!isViewAttached()) {
                    return;
                }
                if (bean.isState()) {
                    getView().handleDelete(bean.getMessage());
                    return;
                }
                if (bean.getTokenFailed()) {
                    pwdSafeModel.refresh(new JsonCallBack() {
                        @Override
                        public void onSuccess(String json) {
                            try {
                                Common.handleTokenOverFiled(json);
                                deleteAddress(id);
                            } catch (Exception e) {
                                getView().toast("数据异常");
                            }
                        }
                    });
                    return;
                }
                getView().toast(bean.getMessage());
            }
        });
    }

    public void save(String name, String phone, String detail, String site1, String site2,
                     String site3, boolean swichChecked, String id) {
        if (unViewAttachedOrNetworkDisconnected()) {
            return;
        }
        if (TextUtils.isEmpty(Common.getToken())) {
            getView().toLogin();
            return;
        }
        model.save(name, phone, detail, site1, site2, site3, swichChecked, id, new StringCallBack<BaseBean>() {
            @Override
            public void onStart() {
                super.onStart();
                if (!isViewAttached()) {
                    return;
                }
                getView().showLoading();
            }

            @Override
            public void onSuccess(BaseBean bean) {
                if (!isViewAttached()) {
                    return;
                }
                if (bean.isState()) {
                    getView().handleSaveSuccess();
                    return;
                }
                if (bean.getTokenFailed()) {
                    pwdSafeModel.refresh(new JsonCallBack() {
                        @Override
                        public void onSuccess(String json) {
                            try {
                                Common.handleTokenOverFiled(json);
                                save(name, phone, detail, site1, site2, site3, swichChecked, id);
                            } catch (Exception e) {
                                getView().toast("数据异常");
                            }
                        }
                    });
                    return;
                }
                getView().toast(bean.getMessage());
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
