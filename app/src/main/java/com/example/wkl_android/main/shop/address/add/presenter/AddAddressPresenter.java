package com.example.wkl_android.main.shop.address.add.presenter;

import android.text.TextUtils;

import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.http.HttpUtils;
import com.example.wkl_android.http.bean.BaseBean;
import com.example.wkl_android.http.callback.impl.JsonCallBack;
import com.example.wkl_android.http.callback.impl.StringCallBack;
import com.example.wkl_android.main.shop.address.add.model.AddAddressModel;
import com.example.wkl_android.main.shop.address.add.model.bean.SaveAddressBean;
import com.example.wkl_android.main.shop.address.add.ui.IAddView;
import com.example.wkl_android.main.shop.settings.safe_pwd.model.RefreshTokenModel;
import com.example.wkl_android.utils.C;
import com.google.gson.Gson;

/**
 * Created by szx
 * on 2020/1/6/006
 */
public class AddAddressPresenter extends BasePresenter<IAddView> {
    private AddAddressModel model;
    private RefreshTokenModel pwdSafeModel;

    public AddAddressPresenter() {
        model = new AddAddressModel();
        pwdSafeModel = new RefreshTokenModel();
    }

    @Override
    public void cancel() {
        model.cancel();
        pwdSafeModel.cancel();
    }

    /**
     * 保存收货地址
     *
     * @param name         收货人
     * @param phone        手机号
     * @param detail       详细地址
     * @param site1        省
     * @param site2        市
     * @param site3        区
     * @param swichChecked 开关状态
     */
    public void save(String id, String name, String phone, String detail, String site1, String site2,
                     String site3, boolean swichChecked) {
        if (unViewAttachedOrNetworkDisconnected()) {
            return;
        }
        if (TextUtils.isEmpty(Common.getToken())) {
            getView().toLogin();
            return;
        }
        model.save(id, name, phone, detail, site1, site2, site3, swichChecked, new StringCallBack<BaseBean>() {
            @Override
            public void onSuccess(BaseBean bean) {
                if (isViewAttached()) {
                    return;
                }
                if (bean.isState()) {
                    getView().handleSaveSuccess(bean.getMessage());
                    return;
                }
                if (bean.getTokenFailed()) {
                    pwdSafeModel.refresh(new JsonCallBack() {
                        @Override
                        public void onSuccess(String json) {
                            Common.handleTokenOverFiled(json);
                            save(id, name, phone, detail, site1, site2, site3, swichChecked);
                        }
                    });
                    return;
                }
                getView().toast(bean.getMessage());
            }
        });
    }

    public void save(SaveAddressBean src) {
        if (unViewAttachedOrNetworkDisconnected()) {
            return;
        }
        if (TextUtils.isEmpty(Common.getToken())) {
            getView().toLogin();
            return;
        }



        if (!TextUtils.isEmpty(src.getAddressId())) {
             HttpUtils.getInstance().doPutJson(C.ADDRESS_EDIT, new Gson().toJson(src), Common.getToken(), "", new JsonCallBack() {
                @Override
                public void onSuccess(String json) throws Exception {
                    getView().handleSaveSuccess("成功");
                }
            });
        }else {
            HttpUtils.getInstance().doPostJson(C.ADDRESS_SAVE, new Gson().toJson(src), Common.getToken(), "", new JsonCallBack() {
                @Override
                public void onSuccess(String json) throws Exception {
                    getView().handleSaveSuccess("成功");
                }
            });
        }



    }
}
