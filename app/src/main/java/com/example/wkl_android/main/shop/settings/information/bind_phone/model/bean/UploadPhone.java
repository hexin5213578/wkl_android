package com.example.wkl_android.main.shop.settings.information.bind_phone.model.bean;

/**
 * Created by szx
 * on 2020/1/8/008
 */
public class UploadPhone {

    /**
     * phoneNumber : string
     * verifyCode : string
     */

    private String phoneNumber;
    private String verifyCode;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }
}
