package com.example.wkl_android.login.register.model.bean;

/**
 * Created by szx
 * on 2020/1/8/008
 */
public class UploadRegister {

    /**
     * authCode : string
     * invitationCode : string
     * phoneNumber : string
     * userPassword : string
     */

    private String authCode;
    private String invitationCode;
    private String phoneNumber;
    private String userPassword;

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
