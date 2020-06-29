package com.example.wkl_android.utils;

import com.example.wkl_android.base.app.BaseApp;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;

public class MyIUiListener implements IUiListener {

    @Override
    public void onComplete(Object o) {
        BaseApp.APP.toast("分享成功");
    }

    @Override
    public void onError(UiError uiError) {
        BaseApp.APP.toast(uiError.errorMessage);
    }

    @Override
    public void onCancel() {
        BaseApp.APP.toast("分享取消");
    }
}