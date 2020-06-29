package com.example.wkl_android.base.view;

/**
 * @author li
 * @since 2019-05-08
 */
public interface IBaseView {

    /**
     * 展示 loading 弹框
     */
    void showLoading();

    /**
     * 关闭 loading 弹框
     */
    void dismissLoading();

    void toast(CharSequence msg);

    void toast(int stringRes);

    void showCodeMessage(String code, String message);

    void toLogin();

}
