package com.example.wkl_android.couponselect.bean;

import java.io.Serializable;

public class CouponInfo implements Serializable {
    boolean canSelect;
    boolean select;

    String discountOverTime; //	String	2020-05-29 00:00:00
    String discountName; //String	5月11
    String discountPrice; //
    String discountBeginTime; //	String	2020-05-11 18:17:33
    String userCouponId; //	String	9dd7d99d113d46c5bcc621fea669a84b

    public String getUserCouponId() {
        return userCouponId;
    }

    public void setUserCouponId(String userCouponId) {
        this.userCouponId = userCouponId;
    }

    public String getDiscountBeginTime() {
        return discountBeginTime;
    }

    public void setDiscountBeginTime(String discountBeginTime) {
        this.discountBeginTime = discountBeginTime;
    }

    public String getDiscountOverTime() {
        return discountOverTime;
    }

    public void setDiscountOverTime(String discountOverTime) {
        this.discountOverTime = discountOverTime;
    }

    public String getDiscountName() {
        return discountName;
    }

    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }

    public String getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(String discountPrice) {
        this.discountPrice = discountPrice;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public boolean isCanSelect() {
        return canSelect;
    }

    public void setCanSelect(boolean canSelect) {
        this.canSelect = canSelect;
    }
}
