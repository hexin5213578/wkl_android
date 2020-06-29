package com.example.wkl_android.main.shop.main.ui;

import com.example.wkl_android.base.view.IBaseView;
import com.example.wkl_android.main.shop.settings.information.main.model.bean.UserInfo;


/**
 * Created by szx
 * on 2020/1/14/014
 */
public interface IShopView extends IBaseView {
    public void handleUserInfo(UserInfo info);
}
