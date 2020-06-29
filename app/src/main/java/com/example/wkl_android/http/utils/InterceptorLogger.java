package com.example.wkl_android.http.utils;


import androidx.annotation.NonNull;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @author li
 * @since 2019-08-14
 */
public class InterceptorLogger implements HttpLoggingInterceptor.Logger {
    private StringBuilder mMessage = new StringBuilder();

    @Override
    public void log(@NonNull String message) {
        try {
            // 请求或者响应开始
            if (message.startsWith("--> POST")) {
                mMessage.setLength(0);
            }
            // 以{}或者[]形式的说明是响应结果的json数据，需要进行格式化
            if ((message.startsWith("{") && message.endsWith("}"))
                    || (message.startsWith("[") && message.endsWith("]"))) {
                message = JsonFormat.format(message);
            }
            mMessage.append(message).append("\n");
            // 响应结束，打印整条日志
            if (message.startsWith("<-- END HTTP")) {
                HttpLogger.d(mMessage.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
