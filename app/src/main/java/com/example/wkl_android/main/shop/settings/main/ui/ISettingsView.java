package com.example.wkl_android.main.shop.settings.main.ui;

import com.example.wkl_android.base.view.IBaseView;

/**
 * Created by szx
 * on 2020/1/4/004
 */
public interface ISettingsView extends IBaseView {
    void handleIsExists(boolean isExist);

    void handlePayPwdIsExist(boolean isExist);

    void handleIsCertification(boolean exist);
}
