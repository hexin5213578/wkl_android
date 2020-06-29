package com.example.wkl_android.main.shop.settings.information.bind_phone.ui.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.main.shop.settings.information.bind_phone.presenter.UpdatePresenter;
import com.example.wkl_android.main.shop.settings.information.bind_phone.ui.IUpdateView;

import butterknife.BindView;

/**
 * 修改手机号
 * Created by szx
 * on 2020/1/8/008
 */
public class UpdatePhoneActivity extends BaseActivity<IUpdateView, UpdatePresenter>
        implements IUpdateView, View.OnClickListener {
    @BindView(R.id.tvTitle)
    TextView title;
    @BindView(R.id.ivLeft)
    View back;
    @BindView(R.id.etPhone)
    EditText etPhone;
    @BindView(R.id.etCode)
    EditText etCode;
    @BindView(R.id.tvGetCode)
    TextView tvGetCode;
    @BindView(R.id.tvSubmit)
    View tvSubmit;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_update_phone;
    }

    @Override
    protected UpdatePresenter createPresenter() {
        return new UpdatePresenter();
    }

    @Override
    protected void initViews() {
        title.setText("修改手机号");
        back.setOnClickListener(this);
        tvGetCode.setOnClickListener(this);
        tvSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvGetCode:
                getCode();
                break;
            case R.id.tvSubmit:
                submit();
                break;
        }
    }

    /**
     * 提交
     */
    private void submit() {
        String phone = etPhone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            toast("请输入手机号");
            return;
        }
        if (!Common.checkIsPhone(phone)) {
            toast("请填写正确格式的手机号");
            return;
        }
        String code = etCode.getText().toString().trim();
        if (TextUtils.isEmpty(code)) {
            toast("请输入验证码");
            return;
        }
        presenter.submit(phone, code);
    }

    /**
     * 获取验证码
     */
    private void getCode() {
        String phone = etPhone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            toast("请输入手机号");
            return;
        }
        if (!Common.checkIsPhone(phone)) {
            toast("请填写正确格式的手机号");
            return;
        }
        presenter.getCode(phone);
    }

    /**
     * 设置按钮文字和状态
     *
     * @param s 文字
     * @param b 状态
     */
    @Override
    public void setBtnTextAndEnabled(String s, boolean b) {
        tvGetCode.setText(s);
        tvGetCode.setEnabled(b);
    }

    /**
     * 请求成功处理
     *
     * @param msg 返回信息
     */
    @Override
    public void handleSuccess(String msg) {
        toast(msg);
        finish();
    }
}
