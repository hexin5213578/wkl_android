package com.example.wkl_android.utils;

/**
 * Created by szx
 * on 2020/1/16/016
 */
public class GatewayResult {
    private Integer status;

    private String message;

    private Boolean state;

    private Boolean tokenFailed;


    public Boolean getTokenFailed() {
        return tokenFailed;
    }

    public void setTokenFailed(Boolean tokenFailed) {
        this.tokenFailed = tokenFailed;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }
}
