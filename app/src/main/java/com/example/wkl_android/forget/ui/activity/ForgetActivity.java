package com.example.wkl_android.forget.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.base.Constant;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.forget.IForgetView;
import com.example.wkl_android.forget.presenter.ForgetPresenter;
import com.example.wkl_android.good.model.GoodsListBean;
import com.example.wkl_android.login.login.ui.activity.LoginActivity;
import com.example.wkl_android.main.home.ui.adapter.HomeGoodsItemAdapter;
import com.example.wkl_android.utils.KeyBoardUtils;
import com.example.wkl_android.utils.SPUtils;
import com.example.wkl_android.utils.ToastUtil;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 忘记密码
 *
 * @author szx
 */
public class ForgetActivity extends BaseActivity<IForgetView, ForgetPresenter>
        implements IForgetView {


    @BindView(R.id.tvTitle)
    TextView tvTitle;

    @BindView(R.id.etMobile)
    EditText etMobile;

    @BindView(R.id.etSmsCode)
    EditText etSmsCode;

    @BindView(R.id.tvGetCode)
    TextView tvGetCode;

    @BindView(R.id.etPwd)
    EditText etPwd;

    @BindView(R.id.iv_pass_eye)
    ImageView iv_pass_eye;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_forget;
    }

    @Override
    protected ForgetPresenter createPresenter() {
        return new ForgetPresenter();
    }

    @Override
    protected void initViews() {
        setTitleBarWithe();

        tvTitle.setText("忘记密码");
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

    @OnClick(R.id.ivLeft)
    public void onBackClick() {
        finish();
    }

    @OnClick(R.id.tvSubmit)
    public void onSubmitClick() {

        String phone = etMobile.getText().toString();
        String code = etSmsCode.getText().toString();
        String pwd = etPwd.getText().toString();

        if(TextUtils.isEmpty(phone) || phone.length() < 11){
            ToastUtil.show("请输入正确的手机号");
            return;
        }

        if(TextUtils.isEmpty(code)){
            ToastUtil.show("请输入验证码");
            return;
        }

        if(TextUtils.isEmpty(pwd)){
            ToastUtil.show("请输入密码");
            return;
        }

        presenter.forget(phone , code , pwd);
    }


    @OnClick(R.id.tvGetCode)
    public void onGetCodeClick() {
        getCode();
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

    @Override
    public void showBtnTextAndEnabled(String btnText, boolean b) {
        tvGetCode.setText(btnText);
        tvGetCode.setEnabled(b);
    }

    /**
     * 重置密码成功
     */
    @Override
    public void handleRegisterSuccess() {
        startActivity(new Intent(APP, LoginActivity.class));
        finish();
    }
}
