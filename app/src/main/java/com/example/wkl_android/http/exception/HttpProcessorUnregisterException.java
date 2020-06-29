package com.example.wkl_android.http.exception;

/**
 * @author li
 * @since 2019-05-07
 */
public class HttpProcessorUnregisterException extends RuntimeException {

    public HttpProcessorUnregisterException() {
        super("未注册网络代理");
    }
}
