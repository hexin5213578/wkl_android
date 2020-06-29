package com.example.wkl_android.http.bean;

import com.google.gson.annotations.SerializedName;

/**
 * @author li
 * @since 2019/4/1
 */
public class BaseBean<T> {
    /**
     * 当前后台框架返回字段"code",200表示成功,900表示登录状态失效
     * <p>
     * 适配原后台框架返回字段"resultCode",200表示成功,900表示登录状态失效
     * 适配后台商城框架返回字段"errno",0表示请求成功
     */
    @SerializedName(value = "code", alternate = {"resultCode", "error_code"})
    private String code;
    /**
     * 当前后台框架返回字段"message"
     * <p>
     * 适配原后台框架返回字段"resultMessage"
     * 适配后台商城框架返回字段"errmsg"
     */
    @SerializedName(value = "message", alternate = {"resultMessage", "reason"})
    private String message;

    @SerializedName(value = "data", alternate = {"resultData", "result"})
    private T data;
    @SerializedName(value = "state")
    private boolean state;
    private boolean exist;
    private int status;
    private Boolean tokenFailed;

    public Boolean getTokenFailed() {
        return tokenFailed;
    }

    public void setTokenFailed(Boolean tokenFailed) {
        this.tokenFailed = tokenFailed;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isExist() {
        return exist;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
