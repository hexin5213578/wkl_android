package com.example.wkl_android.base;


import android.content.Intent;

import com.example.wkl_android.base.fragment.BaseMVPFragment;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.base.view.IBaseView;
import com.example.wkl_android.common.WebCommonActivity;
import com.example.wkl_android.login.login.ui.activity.LoginActivity;

/**
 * @author li
 * @since 2019-05-21
 */
public abstract class BaseFragment<V extends IBaseView, P extends BasePresenter<V>>
        extends BaseMVPFragment<V, P> {

    /**
     * 跳转登录页面
     */
    @Override
    public void toLogin() {
        startActivity(new Intent(APP, LoginActivity.class));
    }


    /**
     * 跳转Web页面
     * {@link WebCommonActivity}不能实现的时候不能使用这个方法
     *
     * @param title 标题
     * @param url   链接
     */
    public void startWeb(String title, String url) {
        Intent intent = new Intent(APP, WebCommonActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("url", url);
        startActivity(intent);
    }
}
