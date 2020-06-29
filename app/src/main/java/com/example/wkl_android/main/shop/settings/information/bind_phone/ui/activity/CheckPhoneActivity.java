package com.example.wkl_android.main.shop.settings.information.bind_phone.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.main.shop.settings.information.bind_phone.VerifyIdentityActivity;
import com.example.wkl_android.main.shop.settings.information.bind_phone.presenter.CheckPresenter;
import com.example.wkl_android.main.shop.settings.information.bind_phone.ui.ICheckView;
import com.example.wkl_android.main.shop.settings.information.login_pwd.ui.activity.UpdateLoginPwdActivity;
import com.example.wkl_android.main.shop.settings.information.pay_pwd.ui.activity.SetPayPwdActivity;

import butterknife.BindView;

/**
 * 验证手机号
 * Created by szx
 * on 2019/12/31/031
 */
public class CheckPhoneActivity extends BaseActivity<ICheckView, CheckPresenter>
        implements ICheckView, View.OnClickListener {
    @BindView(R.id.tvTitle)
    TextView title;
    @BindView(R.id.ivLeft)
    View back;
    @BindView(R.id.tvGetCode)
    TextView tvGetCode;
    @BindView(R.id.tvVerifyIdentity)
    View tvUpdatePhone;
    @BindView(R.id.etCode)
    EditText etCode;
    @BindView(R.id.tvNext)
    View tvNext;
    @BindView(R.id.tvPhone)
    TextView tvPhone;
    private String origin;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_check_phone;
    }

    @Override
    protected CheckPresenter createPresenter() {
        return new CheckPresenter();
    }

    @Override
    protected void initViews() {
        Intent intent = getIntent();
        if (intent != null) {
            origin = intent.getStringExtra("origin");
        }
        title.setText("验证手机号");
        back.setOnClickListener(this);
        tvGetCode.setOnClickListener(this);
        tvUpdatePhone.setOnClickListener(this);
        tvNext.setOnClickListener(this);

        tvPhone.setText(Common.getUserPhone());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvGetCode:
                presenter.getCode();
                break;
            case R.id.tvVerifyIdentity:
                /*
                用户如果已实名,进入实名验证页面;用户填写过密保问题,进入密保问题页面;
                都没有的话,提示不能更换
                 */
                startActivity(new Intent(APP, VerifyIdentityActivity.class));
                break;
            case R.id.tvNext:
                checkCode();
                break;
        }
    }

    /**
     * 校验验证码
     */
    private void checkCode() {
        String code = etCode.getText().toString().trim();
        if (TextUtils.isEmpty(code)) {
            toast("请输入验证码");
            return;
        }
        presenter.toNext(code);
    }

    /**
     * 设置按钮文字和点击状态
     *
     * @param s 文字
     * @param b 点击状态
     */
    @Override
    public void setBtnTextAndEnabled(String s, boolean b) {
        tvGetCode.setText(s);
        tvGetCode.setEnabled(b);
    }

    /**
     * 校验验证码成功,进行下一步
     */
    @Override
    public void handleCheckCodeSuccess() {
        //如果从支付密码跳转,则调到修改支付密码页面
        if ("payPwd".equals(origin)) {
            startActivity(new Intent(APP, SetPayPwdActivity.class)
                    .putExtra("isReset", true));
        }else if("loginPwd".equals(origin)){
            startActivity(new Intent(APP, UpdateLoginPwdActivity.class)
                    .putExtra("isCheck", true));
        }
        else {
            startActivity(new Intent(APP, UpdatePhoneActivity.class));
        }
        finish();
    }
}


