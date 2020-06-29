package com.example.wkl_android.main.shop.settings.information.nick_name.presenter;

import android.text.TextUtils;

import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.http.bean.BaseBean;
import com.example.wkl_android.http.callback.impl.JsonCallBack;
import com.example.wkl_android.http.callback.impl.StringCallBack;
import com.example.wkl_android.main.shop.settings.information.nick_name.model.UpdateModel;
import com.example.wkl_android.main.shop.settings.information.nick_name.ui.IUpdateView;
import com.example.wkl_android.main.shop.settings.safe_pwd.model.RefreshTokenModel;

/**
 * Created by szx
 * on 2020/1/7/007
 */
public class UpdatePresenter extends BasePresenter<IUpdateView> {
    private UpdateModel model;
    private RefreshTokenModel pwdSafeModel;

    public UpdatePresenter() {
        model = new UpdateModel();
        pwdSafeModel = new RefreshTokenModel();
    }

    @Override
    public void cancel() {
        model.cancel();
        pwdSafeModel.cancel();
    }

    /**
     * 提交用户昵称
     *
     * @param nickName 昵称
     */
    public void save(String nickName) {
        if (unViewAttachedOrNetworkDisconnected()) {
            return;
        }
        if (TextUtils.isEmpty(Common.getToken())) {
            getView().toLogin();
            return;
        }
        model.save(nickName, new StringCallBack<BaseBean>() {
            @Override
            public void onSuccess(BaseBean bean) {
                if (!isViewAttached()) {
                    return;
                }
                if (bean.isState()) {
                    getView().handleSuccess(bean.getMessage());
                    return;
                }
                if (bean.getTokenFailed()) {
                    pwdSafeModel.refresh(new JsonCallBack() {
                        @Override
                        public void onSuccess(String json) {
                            try {
                                Common.handleTokenOverFiled(json);
                                save(nickName);
                            } catch (Exception e) {
                                getView().toast(bean.getMessage());
                            }

                        }
                    });
                }
            }
        });
    }
}
