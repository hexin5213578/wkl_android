package com.example.wkl_android.login.login.model.bean;

public class User {
    String userId; //	Integer	6
    String userBirthday	; //String	1988-08-02
    String userNickname	; //String	4444
    String userImage;  //	String	http://vankelai.oss-cn-beijing.aliyuncs.com/2_用户 头像.png


    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(String userBirthday) {
        this.userBirthday = userBirthday;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }
}
