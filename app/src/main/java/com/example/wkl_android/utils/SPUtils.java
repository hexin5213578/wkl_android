package com.example.wkl_android.utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author li
 * @since 2018/10/16
 */
public class SPUtils {

    private static final String FILE_NAME = "com.wkl.share";

    /**
     * 日期转换器
     * 格式:年年年年-月月-日日
     */
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINESE);

    public static final String KEY_USER = "KEY_USER";
    public static final String KEY_TOKEN = "KEY_TOKEN";
    public static final String KEY_REFRESH_TOKEN = "KEY_REFRESH_TOKEN";
    public static final String USER_INFO = "USER_INFO";
    public static final String USER_PHONE = "USER_PHONE";


    //纬度
    public static final String LATITUDE = "LATITUDE";
    //经度
    public static final String LONGITUDE = "LONGITUDE";


    public static final String KEY_AUTO_LOGIN = "KEY_AUTO_LOGIN";
    public static final String KEY_IGNORE_VERSION = "KEY_IGNORE_VERSION";

    /***
     * 银行卡身份证扫描次数
     */
    public static final String KEY_BANK_TODAY_COUNT = "bankTodayCount";
    public static final String KEY_BANK_LAST_DATE = "bankLastDate";
    public static final String KEY_ID_CARD_LAST_DATE = "idCardLastDate";
    public static final String KEY_ID_CARD_TODAY_COUNT = "IDCardTodayCount";

    /**
     * 微信头像昵称开关是否打开
     */
    public static final String KEY_IS_WX_OPEN = "isWxOpen";

    private static SharedPreferences preferences;

    private SPUtils() {
    }

    private static volatile SPUtils INSTANCE;

    public static SPUtils getInstance() {
        if (INSTANCE == null) {
            synchronized (SPUtils.class) {
                if (INSTANCE == null) {
                    INSTANCE = new SPUtils();
                }
            }
        }
        return INSTANCE;
    }

    public void register(Application application) {
        preferences = application.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    public SharedPreferences getPreferences() {
        return preferences;
    }

    public int getInt(String key, int defValue) {
        return preferences.getInt(key, defValue);
    }

    public void putInt(String key, int val) {
        preferences.edit().putInt(key, val).apply();
    }

    public String getString(String key, String defValue) {
        return preferences.getString(key, defValue);
    }

    public void putString(String key, String value) {
        preferences.edit().putString(key, value).apply();
    }

    public boolean getBoolean(String key, boolean defValue) {
        return preferences.getBoolean(key, defValue);
    }

    public void putBoolean(String key, boolean value) {
        preferences.edit().putBoolean(key, value).apply();
    }

    public void remove(String key) {
        preferences.edit().remove(key).apply();
    }

    public static String currentDate() {
        return DATE_FORMAT.format(new Date());
    }
}
