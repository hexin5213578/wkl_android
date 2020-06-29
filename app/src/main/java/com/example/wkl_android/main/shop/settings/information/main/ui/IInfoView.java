package com.example.wkl_android.main.shop.settings.information.main.ui;

import com.example.wkl_android.base.view.IBaseView;
import com.example.wkl_android.main.shop.settings.information.main.model.bean.UserInfo;

/**
 * Created by szx
 * on 2020/1/2/002
 */
public interface IInfoView extends IBaseView {
    void handleUserInfo(UserInfo bean);

    void handleSuccess(String message);
}
