package com.example.wkl_android.login.login.model.bean;

public class UserInfo {
    String roleId; //	Integer	2
    String roleName ; //String	MERCHANT
    String roleNickName	; //String	商家
    String roleState ; //	Boolean	true
    String userId ; //	Integer	6
    String userName ; //	String	phone用户_13783144246
    boolean userType ; //	Boolean	true
    User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleNickName() {
        return roleNickName;
    }

    public void setRoleNickName(String roleNickName) {
        this.roleNickName = roleNickName;
    }

    public String getRoleState() {
        return roleState;
    }

    public void setRoleState(String roleState) {
        this.roleState = roleState;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isUserType() {
        return userType;
    }

    public void setUserType(boolean userType) {
        this.userType = userType;
    }
}
