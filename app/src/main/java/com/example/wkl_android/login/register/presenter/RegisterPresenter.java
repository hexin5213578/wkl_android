package com.example.wkl_android.login.register.presenter;

import android.os.CountDownTimer;

import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.http.bean.BaseBean;
import com.example.wkl_android.http.callback.impl.StringCallBack;
import com.example.wkl_android.login.register.model.GetCodeModel;
import com.example.wkl_android.login.register.ui.IRegisterView;

/**
 * @Author szx
 * @Date: 2019-12-30
 */
public class RegisterPresenter extends BasePresenter<IRegisterView> {
    private GetCodeModel model;

    public RegisterPresenter() {
        model = new GetCodeModel();
    }

    @Override
    public void cancel() {
        model.cancel();
        if (timer != null) {
            timer.cancel();
        }
    }

    private CountDownTimer timer = new CountDownTimer(60_000, 1_000) {
        @Override
        public void onTick(long millisUntilFinished) {
            String btnText = "重新获取(" + millisUntilFinished / 1000 + "秒)";
            getView().showBtnTextAndEnabled(btnText, false);
        }

        @Override
        public void onFinish() {
            getView().showBtnTextAndEnabled("重新获取", true);
        }
    };

    /**
     * 获取验证码
     *
     * @param phone 手机号
     */
    public void getCode(String phone) {
        if (unViewAttachedOrNetworkDisconnected()) {
            return;
        }
        model.getCode(phone, new StringCallBack<BaseBean>() {
            @Override
            public void onStart() {
                super.onStart();
                timer.start();
            }

            @Override
            public void onSuccess(BaseBean bean) {
                if (!isViewAttached()) {
                    return;
                }
                if (bean.isState()) {
                    getView().toast("获取验证码成功");
                    return;
                }
                getView().toast(bean.getMessage());
            }
        });
    }

    public void register(String mobile, String code, String pwd, String invitation) {
        if (unViewAttachedOrNetworkDisconnected()) {
            return;
        }
        model.regist(mobile, code, pwd, invitation, new StringCallBack<BaseBean>() {
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
                    getView().handleRegisterSuccess();
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
