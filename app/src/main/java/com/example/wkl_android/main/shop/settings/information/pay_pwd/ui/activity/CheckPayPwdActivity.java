package com.example.wkl_android.main.shop.settings.information.pay_pwd.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.main.shop.settings.information.bind_phone.ui.activity.CheckPhoneActivity;
import com.example.wkl_android.main.shop.settings.information.pay_pwd.presenter.CheckPresenter;
import com.example.wkl_android.main.shop.settings.information.pay_pwd.ui.ICheckView;

import butterknife.BindView;

/**
 * 修改支付密码(校验密码)
 * Created by szx
 * on 2020/1/8/008
 */
public class CheckPayPwdActivity extends BaseActivity<ICheckView, CheckPresenter>
        implements ICheckView, View.OnClickListener {
    @BindView(R.id.ivLeft)
    View back;
    @BindView(R.id.tvTitle)
    TextView title;
    @BindView(R.id.etPwd)
    EditText etPwd;
    @BindView(R.id.tvSubmit)
    View tvSubmit;
    @BindView(R.id.tvFindPwd)
    View tvFindPwd;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_check_pay_pwd;
    }

    @Override
    protected CheckPresenter createPresenter() {
        return new CheckPresenter();
    }

    @Override
    protected void initViews() {
        back.setOnClickListener(this);
        title.setText("修改支付密码");
        tvSubmit.setOnClickListener(this);
        tvFindPwd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvSubmit:
                check();
                break;
            case R.id.tvFindPwd:
                startActivity(new Intent(APP, CheckPhoneActivity.class)
                        .putExtra("origin","payPwd"));
                finish();
                break;
        }
    }

    /**
     * 校验旧密码
     */
    private void check() {
        String pwd = etPwd.getText().toString().trim();
        if (TextUtils.isEmpty(pwd)) {
            toast("请输入旧密码");
            return;
        }
        presenter.check(pwd);
    }

    /**
     * 校验旧密码成功处理
     */
    @Override
    public void handleSuccess() {
        startActivity(new Intent(APP, SetPayPwdActivity.class).putExtra("isReset", true));
        finish();
    }
}
