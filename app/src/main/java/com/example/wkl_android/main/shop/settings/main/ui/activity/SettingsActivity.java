package com.example.wkl_android.main.shop.settings.main.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.login.login.ui.activity.LoginActivity;
import com.example.wkl_android.main.shop.settings.information.bind_phone.ui.activity.CheckPhoneActivity;
import com.example.wkl_android.main.shop.settings.information.certification.CertificationActivity;
import com.example.wkl_android.main.shop.settings.information.login_pwd.ui.activity.UpdateLoginPwdActivity;
import com.example.wkl_android.main.shop.settings.information.main.ui.activity.UserInfoActivity;
import com.example.wkl_android.main.shop.settings.information.pay_pwd.ui.activity.CheckPayPwdActivity;
import com.example.wkl_android.main.shop.settings.information.pay_pwd.ui.activity.SetPayPwdStep1Activity;
import com.example.wkl_android.main.shop.settings.main.presenter.SettingsPresenter;
import com.example.wkl_android.main.shop.settings.main.ui.ISettingsView;
import com.example.wkl_android.main.shop.settings.safe_pwd.ui.activity.SetPwdSafeActivity;
import com.example.wkl_android.utils.DataCleanManager;

import butterknife.BindColor;
import butterknife.BindView;

/**
 * Created by szx
 * on 2019/12/31/031
 */
public class SettingsActivity extends BaseActivity<ISettingsView, SettingsPresenter>
        implements ISettingsView, View.OnClickListener {
    @BindView(R.id.tvTitle)
    TextView title;
    @BindView(R.id.ivLeft)
    View back;
    @BindView(R.id.llBasicInformation)
    View llBasicInformation;
    @BindView(R.id.llUpdateBindPhone)
    View llUpdateBindPhone;
    @BindView(R.id.llLoginPwd)
    View llLoginPwd;
    @BindView(R.id.llPayPwd)
    View llPayPwd;
    @BindView(R.id.llCertification)
    View llCertification;
    @BindView(R.id.llFeedback)
    View llFeedback;
    @BindView(R.id.llClearCache)
    View llClearCache;
    @BindView(R.id.tvCacheSize)
    TextView tvCacheSize;
    @BindView(R.id.llLogout)
    View llLogout;
    @BindView(R.id.llSafe)
    View llSafe;
    @BindView(R.id.tvPwdSafeState)
    TextView tvPwdSafeState;
    @BindView(R.id.tvPayPwdExists)
    TextView tvPayPwdExists;
    @BindView(R.id.tvIsCertification)
    TextView tvIsCertification;
    @BindColor(R.color.theme)
    int font_theme;
    @BindColor(R.color.font_black_9)
    int font_black_9;

    private boolean isPayPwdExist, isCertification, isPwdSafe;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_settings;
    }

    @Override
    protected SettingsPresenter createPresenter() {
        return new SettingsPresenter();
    }

    @Override
    protected void initViews() {
        back.setOnClickListener(this);
        title.setText("设置");
        llBasicInformation.setOnClickListener(this);
        llUpdateBindPhone.setOnClickListener(this);
        llLoginPwd.setOnClickListener(this);
        llPayPwd.setOnClickListener(this);
        llCertification.setOnClickListener(this);
        llFeedback.setOnClickListener(this);
        llClearCache.setOnClickListener(this);
        llLogout.setOnClickListener(this);
        llSafe.setOnClickListener(this);
        tvCacheSize.setText(DataCleanManager.getTotalCacheSize(this));
        new Handler().postDelayed(this::dismissLoading, 2500);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.loadPage();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.llBasicInformation:
                //基础信息
                startActivity(new Intent(APP, UserInfoActivity.class));
                break;
            case R.id.llUpdateBindPhone:
                //更换手机号
                startActivity(new Intent(APP, CheckPhoneActivity.class));
                break;
            case R.id.llLoginPwd:
                //登录密码
                startActivity(new Intent(APP, UpdateLoginPwdActivity.class));
                break;
            case R.id.llPayPwd:
                //支付密码
                if (isPayPwdExist) {
                    startActivity(new Intent(APP, CheckPayPwdActivity.class));
                } else {
                    startActivity(new Intent(APP, SetPayPwdStep1Activity.class));
                }
                break;
            case R.id.llCertification:
                //实名认证
                if (!isCertification) {
                    startActivity(new Intent(APP, CertificationActivity.class));
                }
                break;
            case R.id.llClearCache:
                //清除缓存
                showClearDialog();
                break;
            //退出登录
            case R.id.llLogout:
                showLogoutDialog();
                break;
            //安全中心
            case R.id.llSafe:
                if (!isPwdSafe) {
                    startActivity(new Intent(APP, SetPwdSafeActivity.class));
                }
                break;
        }
    }

    private void showLogoutDialog() {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage("确定要退出登录吗")
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Common.logout();
                        startActivity(new Intent(APP, LoginActivity.class));
                    }
                }).create();
        dialog.show();
        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(font_theme);
        dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(font_black_9);
    }

    /**
     * 清除缓存弹框
     */
    private void showClearDialog() {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage("确定要清除缓存吗")
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", (dialog1, which) -> {
                    DataCleanManager.clearAllCache(APP);
                    tvCacheSize.setText(DataCleanManager.getTotalCacheSize(APP));
                }).create();
        dialog.show();
        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(font_theme);
        dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(font_black_9);
    }

    /**
     * 判断安全问题是否设置
     *
     * @param isExist 是否设置
     */
    @Override
    public void handleIsExists(boolean isExist) {
        isPwdSafe = isExist;
        tvPwdSafeState.setText(isExist ? "已设置" : "未设置");
    }

    /**
     * 判断支付密码是否设置
     *
     * @param isExist 是否设置
     */
    @Override
    public void handlePayPwdIsExist(boolean isExist) {
        isPayPwdExist = isExist;
        tvPayPwdExists.setText(isExist ? "已设置" : "未设置");
    }

    /**
     * 判断用户是否实名
     *
     * @param exist 是否实名
     */
    @Override
    public void handleIsCertification(boolean exist) {
        isCertification = exist;
        tvIsCertification.setText(exist ? "已实名" : "未实名");
    }
}
