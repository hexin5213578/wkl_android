package com.example.wkl_android.main.shop.main.presenter;

import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.http.callback.impl.JsonCallBack;
import com.example.wkl_android.main.shop.main.ui.IShopView;
import com.example.wkl_android.main.shop.settings.information.main.model.InfoModel;
import com.example.wkl_android.main.shop.settings.information.main.model.bean.UserInfo;
import com.example.wkl_android.utils.ConvertUtil;
import com.example.wkl_android.utils.SPUtils;
import com.google.gson.Gson;

/**
 * Created by szx
 * on 2020/1/14/014
 */
public class ShopPresenter extends BasePresenter<IShopView> {
    @Override
    public void cancel() {

    }

    /**
     * 获取用户信息
     */
    public void getUserInfo() {
        if (unViewAttachedOrNetworkDisconnected()) {
            return;
        }
        if (isTokenNull()) {
            getView().toLogin();
            return;
        }
        InfoModel model = new InfoModel();
        model.getUserInfo(new JsonCallBack() {
            @Override
            public void onSuccess(String json) {
                if (!isViewAttached()) {
                    return;
                }
                ConvertUtil<UserInfo> util = new ConvertUtil<>();
                int convert = util.convert(new UserInfo(), json);

                SPUtils.getInstance().putString(SPUtils.USER_INFO, json);
                UserInfo bean = new Gson().fromJson(json, UserInfo.class);
                getView().handleUserInfo(bean);


            }
        });
    }
}
