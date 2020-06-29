package com.example.wkl_android.main.shop.settings.safe_pwd.ui;

import com.example.wkl_android.base.view.IBaseView;
import com.example.wkl_android.main.shop.settings.safe_pwd.model.bean.FindList;

import java.util.List;

/**
 * Created by szx
 * on 2020/1/4/004
 */
public interface IPwdSafeView extends IBaseView {
    void handleFindList(List<FindList> list);

    void handleSaveSuccess(String message);
}
