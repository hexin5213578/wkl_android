package com.example.wkl_android.login.login.presenter;

import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.http.callback.impl.JsonCallBack;
import com.example.wkl_android.login.login.model.LoginModel;
import com.example.wkl_android.login.login.model.bean.LoginBean;
import com.example.wkl_android.login.login.ui.ILoginView;
import com.example.wkl_android.utils.SPUtils;
import com.example.wkl_android.utils.UserUtil;
import com.google.gson.Gson;

/**
 * Created by szx
 * on 2019/12/30/030
 */
public class LoginPresenter extends BasePresenter<ILoginView> {
    private LoginModel model;

    public LoginPresenter() {
        model = new LoginModel();
    }

    @Override
    public void cancel() {
        model.cancel();
    }

    public void doLogin(String name, String pwd) {
        if (unViewAttachedOrNetworkDisconnected()) {
            return;
        }
        model.doLogin(name, pwd, new JsonCallBack() {
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
                getView().dismissLoading();
                try {
                    LoginBean bean = new Gson().fromJson(json, LoginBean.class);
                    if (bean.isState()) {
                        SPUtils.getInstance().putString(SPUtils.KEY_TOKEN, bean.getToken());
                        SPUtils.getInstance().putString(SPUtils.KEY_REFRESH_TOKEN,
                                bean.getRefreshToken());
                        getView().toMain();
                        UserUtil.saveUser(bean);
                    } else {
                        getView().toast(bean.getMessage());
                    }
                } catch (Exception e) {
                    getView().toast("登录失败");
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
