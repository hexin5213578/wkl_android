package com.example.wkl_android.base.app;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.StringRes;

import com.example.wkl_android.R;
import com.example.wkl_android.utils.MD5Utils;
import com.example.wkl_android.widget.dialog.LoadingDialog;

/**
 * @author li
 * @since 2019-05-08
 */
public class BaseApp extends Application {
    public static BaseApp APP;
    private ConnectivityManager cm;
    private WindowManager wm;
    private Toast toast;
    private LoadingDialog loadingDialog;

    public static String sVersion;

    @Override
    public void onCreate() {
        super.onCreate();
        APP = this;
        cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        wm = (WindowManager)getSystemService(Context.WINDOW_SERVICE);
    }

    /**
     * 设置版本号
     *
     * @param version 当前版本号
     */
    public static void setVersion(String version) {
        sVersion = version;
    }

    /**
     * 判断是否联网
     *
     * @return true:联网 false:未联网
     */
    public boolean isNetworkConnected() {
        NetworkInfo mNetworkInfo = cm.getActiveNetworkInfo();
        boolean isNetWorkConnected = mNetworkInfo != null && mNetworkInfo.isAvailable();
        if (!isNetWorkConnected) {
            toast(R.string.network_disconnected);
        }
        return isNetWorkConnected;
    }

    /**
     * 判断当前网络是否是wifi网络
     * if(activeNetInfo.getType()==ConnectivityManager.TYPE_MOBILE) //判断3G网
     *
     * @return true:Wi-Fi连接 false:非Wi-Fi连接
     */
    public boolean isWifi() {
        NetworkInfo activeNetInfo = cm.getActiveNetworkInfo();
        return activeNetInfo != null && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI;
    }

    @SuppressLint("ShowToast")
    public void toast(CharSequence msg) {
        if (toast == null) {
            toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
            toast.setDuration(Toast.LENGTH_SHORT);
        }
        toast.show();
    }

    @SuppressLint("ShowToast")
    public void toast(@StringRes int stringRes) {
        if (toast == null) {
            toast = Toast.makeText(this, stringRes, Toast.LENGTH_SHORT);
        } else {
            toast.setText(stringRes);
            toast.setDuration(Toast.LENGTH_SHORT);
        }
        toast.show();
    }

    /**
     * dp转px
     *
     * @param dipValue dp值
     * @return px 值
     */
    public int dip2px(float dipValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 手机号加星号
     *
     * @param mobile 手机号
     * @return 130****0000
     */
    public String mobileEncryption(String mobile) {
        if (TextUtils.isEmpty(mobile)) {
            return "";
        }
        if (mobile.length() < 4) {
            return mobile;
        }
        StringBuilder sb = new StringBuilder(mobile.substring(0, 3));
        sb.append("****");
        if (mobile.length() >= 7) {
            sb.append(mobile.substring(7));
        }
        return sb.toString();
    }

    /**
     * 获取设备唯一标识
     *
     * @return 设备唯一标识的MD5值
     */
    @SuppressLint("HardwareIds")
    public String getDeviceToken() {
        String androidID = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        String id = androidID + Build.SERIAL;
        return MD5Utils.getMD5String(id);
    }

    /**
     * 展示Loading弹框
     *
     * @param activity loading弹框Context
     */
    public void showLoading(Activity activity) {
        if (activity == null || activity.isFinishing()) {
            return;
        }
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(activity);
        }
        loadingDialog.show();
    }

    /**
     * 关闭Loading弹框
     */
    public void dismissLoading(Activity activity) {
        activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

    /**
     * 获取屏幕宽度
     * @return 宽度
     */
    public int getScreenWidth() {
        int width = wm.getDefaultDisplay().getWidth();
        return width;
    }

    /**
     * 获取屏幕高度
     * @return 高度
     */
    public int getScreenHeight() {
        int height = wm.getDefaultDisplay().getHeight();
        return height;
    }
}
