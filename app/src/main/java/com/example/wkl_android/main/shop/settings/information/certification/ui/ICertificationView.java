package com.example.wkl_android.main.shop.settings.information.certification.ui;

import com.example.wkl_android.base.view.IBaseView;

/**
 * Created by szx
 * on 2020/1/3/003
 */
public interface ICertificationView extends IBaseView {
    void handleUploadSuccess(String netWorkUrl, boolean front);

    void handleSaveSuccess(String message);
}
