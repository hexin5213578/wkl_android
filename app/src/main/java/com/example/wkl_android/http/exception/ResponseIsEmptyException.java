package com.example.wkl_android.http.exception;

import java.io.IOException;

/**
 * 响应体为空异常
 *
 * @author li
 * @since 2019/3/20
 */
public class ResponseIsEmptyException extends IOException {

    public ResponseIsEmptyException() {
        super("响应体为空");
    }
}
