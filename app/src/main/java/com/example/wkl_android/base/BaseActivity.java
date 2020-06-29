package com.example.wkl_android.base;

import android.content.DialogInterface;
import android.content.Intent;

import androidx.appcompat.app.AlertDialog;

import com.example.wkl_android.R;
import com.example.wkl_android.base.activity.BaseMVPActivity;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.base.view.IBaseView;
import com.example.wkl_android.common.WebCommonActivity;
import com.example.wkl_android.login.login.ui.activity.LoginActivity;

import butterknife.BindColor;

/**
 * @author li
 * @since 2019-05-20
 */
public abstract class BaseActivity<V extends IBaseView, P extends BasePresenter<V>>
        extends BaseMVPActivity<V, P> {

    @BindColor(R.color.theme)
    int font_theme;
    @BindColor(R.color.font_black_9)
    int font_black_9;

    /**
     * 跳转登录页面
     */
    @Override
    public void toLogin() {
        startActivity(new Intent(APP, LoginActivity.class));
        finish();
    }

    /**
     * 跳转Web页面
     * {@link WebCommonActivity}不能实现的时候不能使用这个方法
     *
     * @param title 标题
     * @param url   链接
     */
    public void startWeb(String title, String url) {
        Intent intent = new Intent(this, WebCommonActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("url", url);
        startActivity(intent);
    }

}
