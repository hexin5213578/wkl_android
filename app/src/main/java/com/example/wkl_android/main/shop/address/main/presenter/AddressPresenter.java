package com.example.wkl_android.main.shop.address.main.presenter;

import android.text.TextUtils;
import android.util.Log;

import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.http.callback.impl.JsonCallBack;
import com.example.wkl_android.main.shop.address.main.model.AddressModel;
import com.example.wkl_android.main.shop.address.main.model.bean.Address;
import com.example.wkl_android.main.shop.address.main.model.bean.AddressListBean;
import com.example.wkl_android.main.shop.address.main.ui.IAddressView;
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
public class AddressPresenter extends BasePresenter<IAddressView> {
    private AddressModel model;
    private RefreshTokenModel refreshTokenModel;

    public AddressPresenter() {
        model = new AddressModel();
        refreshTokenModel = new RefreshTokenModel();
    }

    @Override
    public void cancel() {
        model.cancel();
        refreshTokenModel.cancel();
    }

    private List<Address> list;

    public void getList() {
        if (unViewAttachedOrNetworkDisconnected()) {
            return;
        }

        if (TextUtils.isEmpty(Common.getToken())) {
            getView().toLogin();
            return;
        }

        model.getList(new JsonCallBack() {
            @Override
            public void onSuccess(String json) {

                Log.d("wfs","addressList:"+json);

                if (!isViewAttached()) {
                    return;
                }

                ConvertUtil<Address> util = new ConvertUtil<>();
                list = new Gson().fromJson(json, AddressListBean.class).getData();
                getView().handleAddressList(list);

            }
        });
    }

    public void setDefault(String id){
        if (unViewAttachedOrNetworkDisconnected()) {
            return;
        }
        if (TextUtils.isEmpty(Common.getToken())) {
            getView().toLogin();
            return;
        }
        model.setDefault( id , new JsonCallBack() {
            @Override
            public void onSuccess(String json) {
                if (!isViewAttached()) {
                    return;
                }
                getList();
            }
        });
    }

    public void delAddress(String id){
        if (unViewAttachedOrNetworkDisconnected()) {
            return;
        }
        if (TextUtils.isEmpty(Common.getToken())) {
            getView().toLogin();
            return;
        }
        model.delAddress( id , new JsonCallBack() {
            @Override
            public void onSuccess(String json) {
                if (!isViewAttached()) {
                    return;
                }
                getList();
            }
        });
    }
}
