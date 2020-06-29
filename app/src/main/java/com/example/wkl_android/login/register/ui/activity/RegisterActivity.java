package com.example.wkl_android.login.register.ui.activity;

import android.content.Intent;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.login.login.ui.activity.LoginActivity;
import com.example.wkl_android.login.register.presenter.RegisterPresenter;
import com.example.wkl_android.login.register.ui.IRegisterView;
import com.example.wkl_android.utils.ViewBgUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 注册
 *
 * @author szx
 */
public class RegisterActivity extends BaseActivity<IRegisterView, RegisterPresenter> implements
        IRegisterView, View.OnClickListener {
    @BindView(R.id.tvTitle)
    TextView title;
    @BindView(R.id.ivLeft)
    View back;
    @BindView(R.id.tvRight)
    TextView right;
    @BindView(R.id.etMobile)
    EditText etMobile;
    @BindView(R.id.etPwd)
    EditText etPwd;
    @BindView(R.id.etPwdAgain)
    EditText etPwdAgain;
    @BindView(R.id.etInvitation)
    EditText etInvitation;
    @BindView(R.id.etSmsCode)
    EditText etSmsCode;
    @BindView(R.id.tvGetCode)
    TextView tvGetCode;
    @BindView(R.id.tvSubmit)
    View tvSubmit;
    @BindView(R.id.iv_pass_eye)
    ImageView iv_pass_eye;
    @BindView(R.id.iv_again_pass_eye)
    ImageView iv_again_eye;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_register;
    }

    @Override
    protected RegisterPresenter createPresenter() {
        return new RegisterPresenter();
    }

    @Override
    protected void initViews() {
        setTitleBarWithe();
        title.setText("注册");
        back.setVisibility(View.GONE);
        right.setVisibility(View.VISIBLE);
        right.setText("登录");
        right.setOnClickListener(this);
        tvGetCode.setOnClickListener(this);
        tvSubmit.setOnClickListener(this);

        ViewBgUtils.setBg(tvGetCode, "#ff453b", 4);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvRight:
                startActivity(new Intent(APP, LoginActivity.class));
                finish();
                break;
            case R.id.tvGetCode:
                getCode();
                break;
            case R.id.tvSubmit:
                register();
                break;
        }
    }

    /**
     * 注册操作
     */
    private void register() {
        String mobile = etMobile.getText().toString().trim();
        if (TextUtils.isEmpty(mobile)) {
            toast("请输入手机号");
            return;
        }
        if (!Common.checkIsPhone(mobile)) {
            toast("请填写正确格式的手机号");
            return;
        }
        String code = etSmsCode.getText().toString().trim();
        if (TextUtils.isEmpty(code)) {
            toast("请输入验证码");
            return;
        }
        String pwd = etPwd.getText().toString().trim();
        if (TextUtils.isEmpty(pwd)) {
            toast("请输入密码");
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
        String invitation = etInvitation.getText().toString().trim();
        presenter.register(mobile, code, pwd, invitation);
    }

    /**
     * 获取验证码
     */
    private void getCode() {
        String mobile = etMobile.getText().toString().trim();
        if (TextUtils.isEmpty(mobile)) {
            toast("请输入手机号");
            return;
        }
        if (!Common.checkIsPhone(mobile)) {
            toast("请填写正确格式的手机号");
            return;
        }
        presenter.getCode(mobile);
    }

    @Override
    public void showBtnTextAndEnabled(String btnText, boolean b) {
        tvGetCode.setText(btnText);
        tvGetCode.setEnabled(b);
    }

    /**
     * 处理注册成功
     */
    @Override
    public void handleRegisterSuccess() {
        startActivity(new Intent(APP, LoginActivity.class));
        finish();
    }


    boolean show = false;
    @OnClick(R.id.iv_pass_eye)
    public void onEyeClick() {
        show = !show;
        if (show) {
            etPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            iv_pass_eye.setImageResource(R.mipmap.open_eye);
        } else {
            etPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
            iv_pass_eye.setImageResource(R.mipmap.close_eye);
        }
    }

    boolean againShow = false;
    @OnClick(R.id.iv_again_pass_eye)
    public void onAgainEyeClick() {
        againShow = !againShow;
        if (againShow) {
            etPwdAgain.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            iv_again_eye.setImageResource(R.mipmap.open_eye);
        } else {
            etPwdAgain.setTransformationMethod(PasswordTransformationMethod.getInstance());
            iv_again_eye.setImageResource(R.mipmap.close_eye);
        }
    }
}
