package com.example.wkl_android.utils;

/**
 * Created by szx
 * on 2020/1/16/016
 */
public class ConvertResult {
    private String code;
    private String message;
    private Boolean state;

    public ConvertResult() {
    }

    public ConvertResult(String code, String message, boolean state) {
        this.code = code;
        this.message = message;
        this.state = state;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}

