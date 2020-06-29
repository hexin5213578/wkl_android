package com.example.wkl_android.base.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import com.example.wkl_android.base.activity.BaseAppCompatActivity;
import com.example.wkl_android.base.app.BaseApp;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.base.view.IBaseView;

import butterknife.ButterKnife;

/**
 * @author li
 * @since 2019-05-21
 */
public abstract class BaseMVPDialog<V extends IBaseView, P extends BasePresenter<V>>
        extends Dialog implements IBaseView {

    protected P presenter;

    public BaseMVPDialog(Context context) {
        super(context);
    }

    public BaseMVPDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public BaseMVPDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        ButterKnife.bind(this);

        presenter = createPresenter();
        if (presenter != null)
            presenter.attachView((V) this);

        initViews();
    }

    protected abstract int getLayoutResId();

    protected abstract void initViews();

    protected abstract P createPresenter();

    @Override
    public void dismiss() {
        super.dismiss();
        if (presenter != null) {
            presenter.cancel();
            presenter.detachView();
        }
    }

    @Override
    public void showLoading() {
        Context context = getContext();
        if (context instanceof BaseAppCompatActivity) {
            ((BaseAppCompatActivity) context).showLoading();
        }
    }

    @Override
    public void dismissLoading() {
        Context context = getContext();
        if (context instanceof BaseAppCompatActivity) {
            ((BaseAppCompatActivity) context).dismissLoading();
        }
    }

    @Override
    public void toast(CharSequence msg) {
        BaseApp.APP.toast(msg);
    }

    @Override
    public void toast(int stringRes) {
        BaseApp.APP.toast(stringRes);
    }

    @Override
    public void showCodeMessage(String code, String message) {
        Context context = getContext();
        if (context instanceof BaseAppCompatActivity) {
            ((BaseAppCompatActivity) context).showCodeMessage(code, message);
        }
    }

    @Override
    public void toLogin() {
        throw new UnsupportedOperationException();
    }

}
