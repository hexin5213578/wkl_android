package com.example.wkl_android.main.shop.settings.information.pay_pwd.presenter;

import android.os.CountDownTimer;

import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.http.bean.BaseBean;
import com.example.wkl_android.http.callback.impl.JsonCallBack;
import com.example.wkl_android.http.callback.impl.StringCallBack;
import com.example.wkl_android.main.shop.settings.information.pay_pwd.model.CheckCodeModel;
import com.example.wkl_android.main.shop.settings.information.pay_pwd.ui.IStep1View;
import com.example.wkl_android.main.shop.settings.safe_pwd.model.RefreshTokenModel;

/**
 * Created by szx
 * on 2020/1/8/008
 */
public class Step1Presenter extends BasePresenter<IStep1View> {
    private CheckCodeModel model;
    private RefreshTokenModel pwdSafeModel;

    public Step1Presenter() {
        model = new CheckCodeModel();
        pwdSafeModel = new RefreshTokenModel();
    }

    @Override
    public void cancel() {
        model.cancel();
        pwdSafeModel.cancel();
        if (timer != null) {
            timer.cancel();
        }
    }

    private CountDownTimer timer = new CountDownTimer(60_000, 1_000) {
        @Override
        public void onTick(long millisUntilFinished) {
            if (!isViewAttached()) {
                return;
            }
            getView().setBtnTextAndEnable("重新获取(" + millisUntilFinished / 1000 + ")秒", false);
        }

        @Override
        public void onFinish() {
            if (!isViewAttached()) {
                return;
            }
            getView().setBtnTextAndEnable("重新获取", true);
        }
    };

    public void getCode() {
        if (unViewAttachedOrNetworkDisconnected()) {
            return;
        }
        if (isTokenNull()) {
            getView().toLogin();
            return;
        }
        model.getCode(new StringCallBack<BaseBean>() {
            @Override
            public void onStart() {
                super.onStart();
                if (!isViewAttached()) {
                    return;
                }
                getView().showLoading();
                timer.start();
            }

            @Override
            public void onSuccess(BaseBean bean) {
                if (!isViewAttached()) {
                    return;
                }
                if (bean.isState()) {
                    return;
                }
                if (bean.getTokenFailed()) {
                    pwdSafeModel.refresh(new JsonCallBack() {
                        @Override
                        public void onSuccess(String json) {
                            if (!isViewAttached()) {
                                return;
                            }
                            Common.handleTokenOverFiled(json);
                            getCode();
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

    /**
     * 校验验证码
     *
     * @param code 验证码
     */
    public void toNext(String code) {
        if (unViewAttachedOrNetworkDisconnected()) {
            return;
        }
        if (isTokenNull()) {
            getView().toLogin();
            return;
        }
        model.toNext(code, new StringCallBack<BaseBean>() {
            @Override
            public void onSuccess(BaseBean bean) {
                if (!isViewAttached()) {
                    return;
                }
                if (bean.isState()) {
                    getView().handleCheckCodeSuccess();
                    return;
                } else {
                    getView().toast(bean.getMessage());
                }
                if (bean.getTokenFailed()) {
                    pwdSafeModel.refresh(new JsonCallBack() {
                        @Override
                        public void onSuccess(String json) {
                            if (!isViewAttached()) {
                                return;
                            }
                            Common.handleTokenOverFiled(json);
                            toNext(code);
                        }
                    });
                    return;
                }
                getView().toast(bean.getMessage());
            }
        });
    }
}
