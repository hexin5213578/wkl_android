package com.example.wkl_android.base.activity;

import android.annotation.SuppressLint;
import android.app.Application;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.wkl_android.base.app.BaseApp;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.utils.ActivityUtils;
import com.example.wkl_android.widget.dialog.LoadingDialog;

/**
 * @author szx
 * @since 2019-12-22
 */
public class BaseAppCompatActivity extends AppCompatActivity {

    protected InputMethodManager imm;
    protected Application APP;
    private Toast toast;
    private Dialog loginTimeoutDialog;

    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        APP = getApplication();
        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        ActivityUtils.getInstance().addActivity(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        dismissLoading();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityUtils.getInstance().removeActivity(this);
    }

    /**
     * 软键盘弹出时触摸其他地方软键盘消失
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (imm == null) {
            return super.onTouchEvent(event);
        }
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View currentFocus = getCurrentFocus();
            if (currentFocus != null && currentFocus.getWindowToken() != null) {
                // 隐藏软键盘
                imm.hideSoftInputFromWindow(currentFocus.getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
        return super.onTouchEvent(event);
    }

    /**
     * APP字体不随系统设置变化
     *
     * @return {@link Resources}
     */
    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration conf = res.getConfiguration();
        conf.fontScale = 1;
        res.updateConfiguration(conf, res.getDisplayMetrics());
        return res;
    }

    @SuppressLint("ShowToast")
    public void toast(CharSequence msg) {
        if (toast == null) {
            toast = Toast.makeText(APP, msg, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
            toast.setDuration(Toast.LENGTH_SHORT);
        }
        toast.show();
    }

    @SuppressLint("ShowToast")
    public void toast(@StringRes int stringRes) {
        if (toast == null) {
            toast = Toast.makeText(APP, stringRes, Toast.LENGTH_SHORT);
        } else {
            toast.setText(stringRes);
            toast.setDuration(Toast.LENGTH_SHORT);
        }
        toast.show();
    }

    public void showCodeMessage(String code, String message) {
        if ("900".equals(code)) {
            loginTimeout();
            return;
        }
        toast(message);
    }

    public void showLoading() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(this);
        }
        loadingDialog.show();
    }

    public void dismissLoading() {
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

    public boolean isPermissionAllow(String permission) {
        return ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * 登录超时
     * 调接口返回900执行的操作
     * 首先清除登录信息
     * 然后展示登录超时弹框
     */
    public void loginTimeout() {
        Common.logout();
        showLoginTimeoutDialog();
    }

    /**
     * 展示登录超时的弹框
     */
    private void showLoginTimeoutDialog() {
        if (loginTimeoutDialog == null) {
            loginTimeoutDialog = new AlertDialog.Builder(this)
                    .setTitle("温馨提示")
                    .setMessage("长时间无操作，请重新登录")
                    .setPositiveButton("重新登录", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            toLogin();
                        }
                    })
                    .create();
        }
        if (loginTimeoutDialog.isShowing()) {
            return;
        }
        loginTimeoutDialog.setCanceledOnTouchOutside(true);
        loginTimeoutDialog.show();
    }

    /**
     * 跳转登录页面
     */
    public void toLogin() {
        throw new UnsupportedOperationException("onNotLogin 方法未实现");
    }

    public void showRealNameDialog(String message) {
        throw new UnsupportedOperationException("showRealNameDialog 方法未实现");
    }

    /**
     * 隐藏键盘
     */
    protected void hideInput() {
        if (imm != null && imm.isActive() && getCurrentFocus() != null) {
            if (getCurrentFocus().getWindowToken() != null) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    public boolean isNetworkConnected() {
        return BaseApp.APP.isNetworkConnected();
    }
}
