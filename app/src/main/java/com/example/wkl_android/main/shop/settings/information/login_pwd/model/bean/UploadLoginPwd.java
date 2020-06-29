package com.example.wkl_android.main.shop.settings.information.login_pwd.model.bean;

/**
 * Created by szx
 * on 2020/1/8/008
 */
public class UploadLoginPwd {

    /**
     * newPassword : string
     * newPassword2 : string
     * oldPassword : string
     */

    private String newPassword;
    private String newPassword2;
    private String oldPassword;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPassword2() {
        return newPassword2;
    }

    public void setNewPassword2(String newPassword2) {
        this.newPassword2 = newPassword2;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

}
