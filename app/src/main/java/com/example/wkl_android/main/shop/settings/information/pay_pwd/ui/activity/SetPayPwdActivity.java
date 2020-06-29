package com.example.wkl_android.main.shop.settings.information.pay_pwd.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.main.shop.settings.information.pay_pwd.presenter.PayPwdPresenter;
import com.example.wkl_android.main.shop.settings.information.pay_pwd.ui.IPayPwdView;

import butterknife.BindView;

/**
 * 修改/设置支付密码
 * Created by szx
 * on 2019/12/31/031
 */
public class SetPayPwdActivity extends BaseActivity<IPayPwdView, PayPwdPresenter>
        implements IPayPwdView, View.OnClickListener {
    @BindView(R.id.tvTitle)
    TextView title;
    @BindView(R.id.ivLeft)
    View back;
    @BindView(R.id.etPwd)
    EditText etPwd;
    @BindView(R.id.etPwdAgain)
    EditText etPwdAgain;
    @BindView(R.id.tvSubmit)
    View tvSubmit;

    private boolean isReset;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_set_pay_pwd;
    }

    @Override
    protected PayPwdPresenter createPresenter() {
        Intent intent = getIntent();
        if (intent != null) {
            isReset = intent.getBooleanExtra("isReset", false);
        }
        return new PayPwdPresenter(isReset);
    }

    @Override
    protected void initViews() {
        if(isReset){
            title.setText("修改支付密码");
        }else{
            title.setText("设置支付密码");
        }
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
                submit();
                break;
        }
    }

    private void submit() {
        String pwd = etPwd.getText().toString().trim();
        if (TextUtils.isEmpty(pwd)) {
            toast("请输入支付密码");
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
        presenter.setPwd(pwd, pwdAgain);
    }

    /**
     * 提交成功操作
     *
     * @param message 返回信息
     */
    @Override
    public void handleSuccess(String message) {
        toast(message);
        finish();
    }
}
