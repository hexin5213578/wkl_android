package com.example.wkl_android.main.shop.settings.information.bind_phone.ui;

import com.example.wkl_android.base.view.IBaseView;

/**
 * Created by szx
 * on 2020/1/8/008
 */
public interface ICheckView extends IBaseView {
    void setBtnTextAndEnabled(String s, boolean b);

    void handleCheckCodeSuccess();
}
