package com.example.wkl_android.login.register.ui.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseFragment;
import com.example.wkl_android.base.preseter.BasePresenter;

import butterknife.BindView;

/**
 * 注册_普通注册
 *
 * @Author szx
 * @Date: 2019-12-30
 */
public class NormalRegisterFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.etPwd)
    EditText etPwd;
    @BindView(R.id.etPwdAgain)
    EditText etPwdAgain;
    @BindView(R.id.tvSubmit)
    View tvSubmit;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_normal_register;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViews() {
        tvSubmit.setOnClickListener(this);
    }

    public static NormalRegisterFragment newInstance() {

        Bundle args = new Bundle();

        NormalRegisterFragment fragment = new NormalRegisterFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvSubmit:
                submit();
                break;
            default:
                break;
        }
    }

    private void submit() {
        String name = etName.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            toast("用户名不能为空");
            return;
        }
        String pwd = etPwd.getText().toString().trim();
        if (TextUtils.isEmpty(pwd)) {
            toast("密码不能为空");
            return;
        }
        String pwdAgain = etPwdAgain.getText().toString().trim();
        if (TextUtils.isEmpty(pwdAgain)) {
            toast("请输入确认密码");
            return;
        }
        if (!pwd.equals(pwdAgain)) {
            toast("两次密码输入不一致,请重新输入");
            return;
        }

    }
}
