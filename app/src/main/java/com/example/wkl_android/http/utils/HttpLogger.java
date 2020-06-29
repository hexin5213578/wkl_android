package com.example.wkl_android.http.utils;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

/**
 * @author li
 * @since 2019/1/7
 */
public class HttpLogger {
    /**
     * 初始化log工具，在app入口处调用
     */
    public static void init() {
        Logger.init("OkHttp")
                .hideThreadInfo()
                .logLevel(LogLevel.FULL)
                .methodOffset(1);
    }

    public static void d(String message) {
        Logger.d(message);
    }

    public static void i(String message) {
        Logger.i(message);
    }

    public static void w(String message, Throwable e) {
        String info = e != null ? e.toString() : "null";
        Logger.w(message + "：" + info);
    }

    public static void e(String message, Throwable e) {
        Logger.e(e, message);
    }

    public static void json(String json) {
        Logger.json(json);
    }
}
