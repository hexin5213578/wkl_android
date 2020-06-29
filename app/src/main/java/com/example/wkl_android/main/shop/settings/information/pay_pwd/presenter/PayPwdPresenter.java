package com.example.wkl_android.main.shop.settings.information.pay_pwd.presenter;

import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.http.bean.BaseBean;
import com.example.wkl_android.http.callback.impl.JsonCallBack;
import com.example.wkl_android.http.callback.impl.StringCallBack;
import com.example.wkl_android.main.shop.settings.information.pay_pwd.model.PayPwdModel;
import com.example.wkl_android.main.shop.settings.information.pay_pwd.ui.IPayPwdView;
import com.example.wkl_android.main.shop.settings.safe_pwd.model.RefreshTokenModel;

/**
 * Created by szx
 * on 2020/1/8/008
 */
public class PayPwdPresenter extends BasePresenter<IPayPwdView> {
    private PayPwdModel model;
    private RefreshTokenModel pwdSafeModel;

    public PayPwdPresenter(boolean isReset) {
        model = new PayPwdModel(isReset);
        pwdSafeModel = new RefreshTokenModel();
    }

    @Override
    public void cancel() {
        model.cancel();
        pwdSafeModel.cancel();
    }

    public void setPwd(String pwd, String pwdAgain) {
        if (unViewAttachedOrNetworkDisconnected()) {
            return;
        }
        if (isTokenNull()) {
            getView().toLogin();
            return;
        }
        model.setPwd(pwd, pwdAgain,new StringCallBack<BaseBean>() {
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
                        public void onSuccess(String json) throws Exception {
                            try {
                                Common.handleTokenOverFiled(json);
                                setPwd(pwd, pwdAgain);
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
