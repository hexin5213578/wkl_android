package com.example.wkl_android.main.shop.settings.information.login_pwd.presenter;

import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.http.bean.BaseBean;
import com.example.wkl_android.http.callback.impl.JsonCallBack;
import com.example.wkl_android.http.callback.impl.StringCallBack;
import com.example.wkl_android.main.shop.settings.information.login_pwd.model.UpdateModel;
import com.example.wkl_android.main.shop.settings.information.login_pwd.ui.IUpdateView;
import com.example.wkl_android.main.shop.settings.safe_pwd.model.RefreshTokenModel;

/**
 * Created by szx
 * on 2020/1/8/008
 */
public class UpdatePresenter extends BasePresenter<IUpdateView> {
    private UpdateModel model;
    private RefreshTokenModel refreshTokenModel;

    public UpdatePresenter() {
        model = new UpdateModel();
        refreshTokenModel = new RefreshTokenModel();
    }

    @Override
    public void cancel() {
        model.cancel();
        refreshTokenModel.cancel();
    }

    /**
     * 修改登录密码
     *
     * @param oldPwd   旧密码
     * @param pwd      新密码
     * @param pwdAgain 确认密码
     */
    public void update(String oldPwd, String pwd, String pwdAgain) {
        if (unViewAttachedOrNetworkDisconnected()) {
            return;
        }
        if (isTokenNull()) {
            getView().toLogin();
            return;
        }
        model.update(oldPwd, pwd, pwdAgain, new StringCallBack<BaseBean>() {
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
                    refreshTokenModel.refresh(new JsonCallBack() {
                        @Override
                        public void onSuccess(String json) {
                            try {
                                Common.handleTokenOverFiled(json);
                                update(oldPwd, pwd, pwdAgain);
                            } catch (Exception e) {
                                getView().toast(bean.getMessage());
                            }
                        }
                    });
                    return;
                }
                getView().toast(bean.getMessage());
            }
        });
    }
}
