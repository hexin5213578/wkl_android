package com.example.wkl_android.login.register.ui;

import com.example.wkl_android.base.view.IBaseView;

/**
 * @Author szx
 * @Date: 2019-12-30
 */
public interface IRegisterView extends IBaseView {
    void showBtnTextAndEnabled(String btnText, boolean b);

    void handleRegisterSuccess();
}
