package com.example.wkl_android.forget.presenter;

import android.os.CountDownTimer;

import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.forget.IForgetView;
import com.example.wkl_android.good.model.GoodsListBean;
import com.example.wkl_android.http.HttpUtils;
import com.example.wkl_android.http.bean.BaseBean;
import com.example.wkl_android.http.callback.impl.JsonCallBack;
import com.example.wkl_android.http.callback.impl.StringCallBack;
import com.example.wkl_android.login.login.model.LoginModel;
import com.example.wkl_android.login.register.model.GetCodeModel;
import com.example.wkl_android.utils.C;
import com.example.wkl_android.utils.ToastUtil;
import com.google.gson.Gson;

/**
 * Created by szx
 * on 2019/12/30/030
 */
public class ForgetPresenter extends BasePresenter<IForgetView> {

    private GetCodeModel model;

    public ForgetPresenter() {
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


    public void forget(String mobile, String code, String pwd) {
        if (unViewAttachedOrNetworkDisconnected()) {
            return;
        }
        model.forget(mobile, code, pwd, new StringCallBack<BaseBean>() {
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
