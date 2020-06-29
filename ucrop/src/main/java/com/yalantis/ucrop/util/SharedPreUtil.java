package com.yalantis.ucrop.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreUtil {

    private static SharedPreferences getSharedPreferences(Context context , String spName) {
        return context.getSharedPreferences(spName, Context.MODE_PRIVATE);
    }

    private static SharedPreferences.Editor getEditor(Context context , String spName) {
        return getSharedPreferences(context , spName).edit();
    }

    public static String getString(Context context ,String spName, String spKey) {
        return getSharedPreferences(context , spName).getString(spKey, "");
    }

    public static void putValue(Context context , String spName, String spKey, String value) {
        getEditor(context , spName).putString(spKey, value).commit();
    }

}
