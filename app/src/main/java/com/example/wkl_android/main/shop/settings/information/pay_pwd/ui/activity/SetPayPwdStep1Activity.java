package com.example.wkl_android.main.shop.settings.information.pay_pwd.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.main.shop.settings.information.pay_pwd.presenter.Step1Presenter;
import com.example.wkl_android.main.shop.settings.information.pay_pwd.ui.IStep1View;

import butterknife.BindView;

/**
 * Created by szx
 * on 2020/1/8/008
 */
public class SetPayPwdStep1Activity extends BaseActivity<IStep1View, Step1Presenter>
        implements IStep1View, View.OnClickListener {
    @BindView(R.id.ivLeft)
    View back;
    @BindView(R.id.tvTitle)
    TextView title;
    @BindView(R.id.tvGetCode)
    TextView tvGetCode;
    @BindView(R.id.tvNext)
    View tvNext;
    @BindView(R.id.tvPhone)
    TextView tvPhone;
    @BindView(R.id.etCode)
    EditText etCode;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_check_phone;
    }

    @Override
    protected Step1Presenter createPresenter() {
        return new Step1Presenter();
    }

    @Override
    protected void initViews() {
        back.setOnClickListener(this);
        title.setText("设置支付密码");
        tvGetCode.setOnClickListener(this);
        tvNext.setOnClickListener(this);
        String userPhone = Common.getUserPhone();
        tvPhone.setText(userPhone);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvNext:
                toNext();
                break;
            case R.id.tvGetCode:
                presenter.getCode();
                break;
        }
    }

    /**
     * 校验验证码,跳转设置支付密码
     */
    private void toNext() {
        String code = etCode.getText().toString();
        if (TextUtils.isEmpty(code)) {
            toast("请输入验证码");
            return;
        }
        presenter.toNext(code);
    }

    /**
     * 校验验证码成功处理
     */
    @Override
    public void handleCheckCodeSuccess() {
        startActivity(new Intent(APP, SetPayPwdActivity.class));
        finish();
    }

    @Override
    public void setBtnTextAndEnable(String s, boolean b) {
        tvGetCode.setText(s);
        tvGetCode.setEnabled(b);
    }
}
