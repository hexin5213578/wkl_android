package com.example.wkl_android.base.all;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.wkl_android.R;
import com.jaeger.library.StatusBarUtil;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseAvtivity<P extends BasePresenter> extends AppCompatActivity implements BaseView  {
    private P presenter;
    private Unbinder bind;
    Dialog mLoadingDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getResId());

        StatusBarUtil.setTransparent(this);

        presenter = initPresenter();
        bind = ButterKnife.bind(this);
        getData();
    }
    public void showDialog() {
        if (mLoadingDialog == null) {
            mLoadingDialog = new Dialog(this);
            mLoadingDialog.setCancelable(false);
            View v = View.inflate(this, R.layout.dialog_loading, null);

            mLoadingDialog.addContentView(v,
                    new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT));
        }

        mLoadingDialog.show();
    }

    public void hideDialog() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }
    }
    public P getPresenter() {
        return presenter;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(presenter!=null){
            presenter.detachView();
            presenter=null;
        }
        bind.unbind();
    }

    protected abstract int getResId();
    protected abstract void getData();
    protected abstract P initPresenter();
}
