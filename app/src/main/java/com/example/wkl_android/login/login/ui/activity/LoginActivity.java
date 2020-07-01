package com.example.wkl_android.login.login.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.forget.ui.activity.ForgetActivity;
import com.example.wkl_android.login.login.presenter.LoginPresenter;
import com.example.wkl_android.login.login.ui.ILoginView;
import com.example.wkl_android.login.register.ui.activity.RegisterActivity;
import com.example.wkl_android.main.MainActivity;
import com.example.wkl_android.main.shop.settings.information.bind_phone.ui.activity.CheckPhoneActivity;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 登录页面
 *
 * @author szx
 */
public class LoginActivity extends BaseActivity<ILoginView, LoginPresenter>
        implements ILoginView, View.OnClickListener {
    @BindView(R.id.tvTitle)
    TextView title;
    @BindView(R.id.ivLeft)
    View back;
    @BindView(R.id.etPhone)
    EditText etPhone;
    @BindView(R.id.etPwd)
    EditText etPwd;
    @BindView(R.id.cbAgreement)
    CheckBox cbAgreement;
    @BindView(R.id.tvSubmit)
    View tvSubmit;
    @BindView(R.id.tvToRegister)
    View tvToRegister;
    @BindView(R.id.tvFindPwd)
    View tvFindPwd;
    @BindView(R.id.ivWxLogin)
    View ivWxLogin;
    @BindView(R.id.ivQqLogin)
    View ivQqLogin;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;


    private IWXAPI api;
    private Tencent tencent;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_login;
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected void initViews() {
        setTitleBarWithe(ContextCompat.getColor(this, R.color.color_EB3C34));
        tencent = Tencent.createInstance(Common.APP_ID_QQ, this.getApplicationContext());
        title.setText("登录");
        back.setVisibility(View.GONE);
        tvSubmit.setOnClickListener(this);
        tvToRegister.setOnClickListener(this);
        tvFindPwd.setOnClickListener(this);
        ivWxLogin.setOnClickListener(this);
        ivQqLogin.setOnClickListener(this);
        regToWx();

        rlTitle.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent));
    }

    private void regToWx() {
        String appId = Common.APP_ID;
        api = WXAPIFactory.createWXAPI(this, appId, false);
        api.registerApp(appId);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvSubmit:
                login();
                break;
            case R.id.tvToRegister:
                startActivity(new Intent(APP, RegisterActivity.class));
                finish();
                break;
            case R.id.tvFindPwd:
                //startActivity(new Intent(APP,));
                startActivity(new Intent(APP, ForgetActivity.class));
                break;
            case R.id.ivWxLogin:
                wxLogin();
                break;
            case R.id.ivQqLogin:
                qqLogin();
                break;
        }
    }

    /**
     * qq登录
     */
    private void qqLogin() {
        if (!tencent.isQQInstalled(this)) {
            toast("您还未安装qq");
            return;
        }
        tencent.login(this, "get_user_info", new IUiListener() {
            @Override
            public void onComplete(Object o) {
                toast("登录成功");
            }

            @Override
            public void onError(UiError uiError) {
                toast("登录失败，错误码：" + uiError.errorMessage);
            }

            @Override
            public void onCancel() {
                toast("取消登录");
            }
        });

    }

    /**
     * 微信登录
     */
    private void wxLogin() {
        if (!api.isWXAppInstalled()) {
            toast("您的设备未安装微信客户端");
        } else {
            final SendAuth.Req req = new SendAuth.Req();
            req.scope = "snsapi_userinfo";
            req.state = "wechat_sdk_demo_test";
            api.sendReq(req);
        }
    }

    /**
     * 登录操作
     */
    private void login() {
        String phone = etPhone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            toast("请输入账号");
            return;
        }
        String pwd = etPwd.getText().toString().trim();
        if (TextUtils.isEmpty(pwd)) {
            toast("请输入密码");
            return;
        }
        if (!cbAgreement.isChecked()) {
            toast("请先勾选用户协议");
            return;
        }
        presenter.doLogin(phone, pwd);
    }

    @Override
    public void toMain() {
        startActivity(new Intent(APP, MainActivity.class));
        finish();
    }


}
