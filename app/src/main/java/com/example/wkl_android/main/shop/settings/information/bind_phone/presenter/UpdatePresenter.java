package com.example.wkl_android.main.shop.settings.information.bind_phone.presenter;

import android.os.CountDownTimer;

import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.http.bean.BaseBean;
import com.example.wkl_android.http.callback.impl.StringCallBack;
import com.example.wkl_android.login.register.model.GetCodeModel;
import com.example.wkl_android.main.shop.settings.information.bind_phone.model.UpdateModel;
import com.example.wkl_android.main.shop.settings.information.bind_phone.ui.IUpdateView;

/**
 * Created by szx
 * on 2020/1/8/008
 */
public class UpdatePresenter extends BasePresenter<IUpdateView> {
    private UpdateModel model;
    private GetCodeModel getCodeModel;

    public UpdatePresenter() {
        model = new UpdateModel();
        getCodeModel = new GetCodeModel();
    }

    @Override
    public void cancel() {
        model.cancel();
        getCodeModel.cancel();
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

    public void getCode(String phone) {
        if (unViewAttachedOrNetworkDisconnected()) {
            return;
        }
        getCodeModel.getCode(phone, new StringCallBack<BaseBean>() {
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

    /**
     * 提交修改手机号
     *
     * @param phone 手机号
     * @param code  验证码
     */
    public void submit(String phone, String code) {
        if (unViewAttachedOrNetworkDisconnected()) {
            return;
        }
        if (isTokenNull()) {
            getView().toLogin();
            return;
        }
        model.submit(phone, code, new StringCallBack<BaseBean>() {
            @Override
            public void onSuccess(BaseBean bean) {
                if(!isViewAttached()){
                    return;
                }
                if(bean.isState()){
                    getView().handleSuccess(bean.getMessage());
                }
            }
        });
    }
}
