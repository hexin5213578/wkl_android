package com.example.wkl_android.main.shop.settings.information.login_pwd.ui.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.main.shop.settings.information.login_pwd.presenter.UpdatePresenter;
import com.example.wkl_android.main.shop.settings.information.login_pwd.ui.IUpdateView;

import butterknife.BindView;

/**
 * 修改登录密码
 * Created by szx
 * on 2019/12/31/031
 */
public class UpdateLoginPwdActivity extends BaseActivity<IUpdateView, UpdatePresenter>
        implements IUpdateView, View.OnClickListener {
    @BindView(R.id.tvTitle)
    TextView title;
    @BindView(R.id.ivLeft)
    View back;
    @BindView(R.id.etOldPwd)
    EditText etOldPwd;
    @BindView(R.id.etPwd)
    EditText etPwd;
    @BindView(R.id.etPwdAgain)
    EditText etPwdAgain;
    @BindView(R.id.tvSubmit)
    View tvSubmit;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_update_login_pwd;
    }

    @Override
    protected UpdatePresenter createPresenter() {
        return new UpdatePresenter();
    }

    @Override
    protected void initViews() {
        title.setText("修改登录密码");
        back.setOnClickListener(this);
        tvSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvSubmit:
                update();
                break;
        }
    }

    /**
     * 修改登录密码
     */
    private void update() {
        String oldPwd = etOldPwd.getText().toString().trim();
        if (TextUtils.isEmpty(oldPwd)) {
            toast("旧密码不能为空");
            return;
        }
        String pwd = etPwd.getText().toString().trim();
        if (TextUtils.isEmpty(pwd)) {
            toast("请输入新密码");
            return;
        }
        String pwdAgain = etPwdAgain.getText().toString().trim();
        if (TextUtils.isEmpty(pwdAgain)) {
            toast("请输入确认密码");
            return;
        }
        presenter.update(oldPwd, pwd, pwdAgain);
    }

    /**
     * 更新登录密码成功处理
     *
     * @param message 返回信息
     */
    @Override
    public void handleSuccess(String message) {
        toast(message);
        toLogin();
    }
}
