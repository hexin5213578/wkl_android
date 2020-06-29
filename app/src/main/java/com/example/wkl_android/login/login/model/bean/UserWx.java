package com.example.wkl_android.login.login.model.bean;

import java.util.List;

public class UserWx {

    /**
     * openid : oLfUhxEmiJ3gIzytZiJGVYZXpoQ4
     * nickname : 石新鲜
     * sex : 0
     * language : zh_CN
     * city :
     * province :
     * country :
     * headimgurl : http://thirdwx.qlogo.cn/mmopen/vi_32/sXV00X3a1Bk84Y3iaasicmLVNBAZFWUroDnacwibedTLFhE9ZSGHUr4xicB8k1FYuPssK3Z5vzRz1GgQMl8tGkcy8g/132
     * privilege : []
     * unionid : oQQk3wrO6jl614AIcgFZZLWrZ0SU
     */

    private String openid;
    private String nickname;
    private int sex; //0女1男
    private String language;//zh_CN
    private String city;
    private String province;
    private String country;
    private String headimgurl;
    private String unionid;
    private List<?> privilege;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public List<?> getPrivilege() {
        return privilege;
    }

    public void setPrivilege(List<?> privilege) {
        this.privilege = privilege;
    }
}
