package com.example.wkl_android.main.shop.settings.information.pay_pwd.ui;

import com.example.wkl_android.base.view.IBaseView;

/**
 * Created by szx
 * on 2020/1/8/008
 */
public interface IStep1View extends IBaseView {
    void handleCheckCodeSuccess();

    void setBtnTextAndEnable(String s, boolean b);
}