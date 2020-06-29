package com.example.wkl_android.common;

import android.content.Context;
import android.util.Log;

import com.example.wkl_android.base.app.BaseApp;
import com.example.wkl_android.login.login.model.bean.UserWx;
import com.example.wkl_android.main.shop.settings.information.main.model.bean.UserInfo;
import com.example.wkl_android.main.shop.settings.safe_pwd.model.bean.TokenBean;
import com.example.wkl_android.utils.LogUtils;
import com.example.wkl_android.utils.SPUtils;
import com.example.wkl_android.utils.UserUtil;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 共用工具类
 *
 * @author li
 * @since 2019/4/1
 */
public class Common {

    public static final Locale LOCALE = Locale.CHINA;
    public static String PASSWORD_INPUT_PATTERN = "^[A-Za-z0-9@#$%!~&\\^\\?\\.\\*]{0,22}$";
    public static final String APP_ID = "wx4df431b6c5f636e7";
    public static final String APP_SECRET = "c5fa7bfc782457f95a730d0f0c050b96";
    public static final String APP_ID_QQ = "101853091";
    public static UserWx userWx;

    public static void logout() {
         userWx = null;
        SPUtils.getInstance().remove(SPUtils.KEY_TOKEN);
        SPUtils.getInstance().remove(SPUtils.KEY_AUTO_LOGIN);
        UserUtil.quitLogin();
    }

    public static String getToken() {
        return SPUtils.getInstance().getString(SPUtils.KEY_TOKEN, "");
    }

    public static String getRefreshToken() {
        return SPUtils.getInstance().getString(SPUtils.KEY_REFRESH_TOKEN, "");
    }

    public static String getTokenJson() {
        TokenBean bean = new TokenBean();
        bean.setToken(getToken());
        return new Gson().toJson(bean);
    }

    public static String getUserPhone() {
        return SPUtils.getInstance().getString(SPUtils.USER_PHONE, "");
    }

    /**
     * 获取token,不为空内种
     *
     * @return token:未登录状态返回空字符串
     */

    public static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm", LOCALE);

    public static int dip2px(Context context, int dp) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    /**
     * 获取指定文件大小
     *
     * @param file 文件
     * @return 文件长度
     */
    public static long getFileSize(File file) {
        long size = 0;
        try {
            if (file.exists()) {
                FileInputStream fis = null;
                fis = new FileInputStream(file);
                size = fis.available();
            } else {
                Log.e("获取文件大小", "文件不存在!");
            }
        } catch (Exception e) {
            LogUtils.d("获取文件大小失败");
        }
        return size;
    }

    /**
     * 处理token过期
     *
     * @param json token数据
     */
    public static void handleTokenOverFiled(String json) {
        TokenBean bean = new Gson().fromJson(json, TokenBean.class);
        SPUtils.getInstance().putString(SPUtils.KEY_TOKEN, bean.getToken());
    }

    /**
     * 判断是否为正规手机号
     *
     * @param phone 手机号
     */
    public static boolean checkIsPhone(String phone) {
        boolean matches = false;
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        if (phone.length() != 11) {
            BaseApp.APP.toast("手机号长度应该为11位");
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            matches = m.matches();
        }
        return matches;
    }

    public static String getResizeImg(String imageUrl, int width, int height) {
        return imageUrl + "?x-oss-process=image/resize,m_fill,h_" + height + ",w_" + width;
    }

    public String getOrderId() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String translationHtml(String content) {
        String replace = content.replace("&lt;", "<");
        String replace1 = replace.replace("&gt;", ">");
        String replace2 = replace1.replace("&amp;", "&");
        String replace3 = replace2.replace("&quot;", "\"");
        return replace3.replace("&copy;", "©");
    }
}
