package com.example.wkl_android.utils;

import android.util.Log;

/**
 * @author li
 * @since 2019/4/3
 */
public class LogUtils {
    private static final String TAG = "aaa";

    public static void d(Object obj) {
        d(TAG, obj);
    }

    public static void d(String tag, Object obj) {
        if (C.LOG_SWITCH) {
            Log.d(tag, String.valueOf(obj));
        }
    }
}
