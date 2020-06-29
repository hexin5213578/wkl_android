package com.example.wkl_android.main.shop.settings.information.bind_phone.presenter;

import android.os.CountDownTimer;

import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.http.bean.BaseBean;
import com.example.wkl_android.http.callback.impl.JsonCallBack;
import com.example.wkl_android.http.callback.impl.StringCallBack;
import com.example.wkl_android.main.shop.settings.information.bind_phone.ui.ICheckView;
import com.example.wkl_android.main.shop.settings.information.pay_pwd.model.CheckCodeModel;
import com.example.wkl_android.main.shop.settings.safe_pwd.model.RefreshTokenModel;

/**
 * Created by szx
 * on 2020/1/8/008
 */
public class CheckPresenter extends BasePresenter<ICheckView> {
    private CheckCodeModel checkCodeModel;
    private RefreshTokenModel refresh;

    public CheckPresenter() {
        checkCodeModel = new CheckCodeModel();
        refresh = new RefreshTokenModel();
    }

    @Override
    public void cancel() {
        checkCodeModel.cancel();
        refresh.cancel();
    }

    private CountDownTimer timer = new CountDownTimer(60_000, 1_000) {
        @Override
        public void onTick(long millisUntilFinished) {
            if (!isViewAttached()) {
                return;
            }
            getView().setBtnTextAndEnabled("重新获取(" + (millisUntilFinished / 1000) + ")秒", false);
        }

        @Override
        public void onFinish() {
            if (!isViewAttached()) {
                return;
            }
            getView().setBtnTextAndEnabled("重新获取", true);
        }
    };

    /**
     * 获取验证码
     */
    public void getCode() {
        if (unViewAttachedOrNetworkDisconnected()) {
            return;
        }
        if (isTokenNull()) {
            getView().toLogin();
            return;
        }
        checkCodeModel.getCode(new StringCallBack<BaseBean>() {
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
                    getView().toast(bean.getMessage());
                    return;
                }
                if (bean.getTokenFailed()) {
                    refresh.refresh(new JsonCallBack() {
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
        checkCodeModel.toNext(code, new StringCallBack<BaseBean>() {
            @Override
            public void onSuccess(BaseBean bean) {
                if (!isViewAttached()) {
                    return;
                }
                if (bean.isState()) {
                    getView().handleCheckCodeSuccess();
                    return;
                }
                if ("0x10026".equals(bean.getCode())) {
                    getView().toast(bean.getMessage());
                    return;
                }
                if (bean.getTokenFailed()) {
                    refresh.refresh(new JsonCallBack() {
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
