package com.example.wkl_android.main.shop.settings.information.pay_pwd.presenter;

import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.http.bean.BaseBean;
import com.example.wkl_android.http.callback.impl.JsonCallBack;
import com.example.wkl_android.http.callback.impl.StringCallBack;
import com.example.wkl_android.main.shop.settings.information.pay_pwd.model.CheckModel;
import com.example.wkl_android.main.shop.settings.information.pay_pwd.ui.ICheckView;
import com.example.wkl_android.main.shop.settings.safe_pwd.model.RefreshTokenModel;

/**
 * Created by szx
 * on 2020/1/8/008
 */
public class CheckPresenter extends BasePresenter<ICheckView> {
    private CheckModel model;
    private RefreshTokenModel pwdSafeModel;

    public CheckPresenter() {
        model = new CheckModel();
        pwdSafeModel = new RefreshTokenModel();
    }

    @Override
    public void cancel() {
        model.cancel();
        pwdSafeModel.cancel();
    }

    /**
     * 校验旧密码
     *
     * @param pwd 旧密码
     */
    public void check(String pwd) {
        if (unViewAttachedOrNetworkDisconnected()) {
            return;
        }
        if (isTokenNull()) {
            getView().toLogin();
            return;
        }
        model.check(pwd, new StringCallBack<BaseBean>() {
            @Override
            public void onSuccess(BaseBean bean) {
                if (!isViewAttached()) {
                    return;
                }
                if (bean.isState()) {
                    getView().handleSuccess();
                    return;
                }
                if (bean.getTokenFailed()) {
                    pwdSafeModel.refresh(new JsonCallBack() {
                        @Override
                        public void onSuccess(String json) {
                            if (!isViewAttached()) {
                                return;
                            }
                            try {
                                Common.handleTokenOverFiled(json);
                                check(pwd);
                            } catch (Exception e) {
                                getView().toast(bean.getMessage());
                            }
                        }
                    });
                    return;
                }
                if ("0x10036".equals(bean.getCode())) {
                    getView().toast("密码错误");
                    return;
                }
                getView().toast(bean.getMessage());
            }
        });
    }
}
