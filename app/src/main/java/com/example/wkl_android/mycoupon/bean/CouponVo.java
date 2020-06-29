package com.example.wkl_android.mycoupon.bean;

public class CouponVo {
    String createTime;//	String	2020-05-20 10:40:27
    String discountBeginTime; //	String	2020-05-13 00:00:00
    String discountBusinessId; //	String	1234358495743692800
    String discountCouponId; //	String	ecea31e69d0e458980929fa48b57f702
    String discountName; //String	测试
    String discountOverTime;//	String	2020-05-21 00:00:00
    String discountPrice;//	Number	10
    String discountRestrict; //	Number	100
    String discountSkuId; //	String	0
    boolean discountType; //	Boolean	false
    String userCouponId; //	String	c2c063c0e2324b50b981b09421fdaf17
    boolean isOpen;
    String businessLogo;//	String	https://vankelai.oss-cn-beijing.aliyuncs.com/1_64x64.png
    String businessName; //	String	测试店铺


    public String getBusinessLogo() {
        return businessLogo;
    }

    public void setBusinessLogo(String businessLogo) {
        this.businessLogo = businessLogo;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDiscountBeginTime() {
        return discountBeginTime;
    }

    public void setDiscountBeginTime(String discountBeginTime) {
        this.discountBeginTime = discountBeginTime;
    }

    public String getDiscountBusinessId() {
        return discountBusinessId;
    }

    public void setDiscountBusinessId(String discountBusinessId) {
        this.discountBusinessId = discountBusinessId;
    }

    public String getDiscountCouponId() {
        return discountCouponId;
    }

    public void setDiscountCouponId(String discountCouponId) {
        this.discountCouponId = discountCouponId;
    }

    public String getDiscountName() {
        return discountName;
    }

    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }

    public String getDiscountOverTime() {
        return discountOverTime;
    }

    public void setDiscountOverTime(String discountOverTime) {
        this.discountOverTime = discountOverTime;
    }

    public String getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(String discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getDiscountRestrict() {
        return discountRestrict;
    }

    public void setDiscountRestrict(String discountRestrict) {
        this.discountRestrict = discountRestrict;
    }

    public String getDiscountSkuId() {
        return discountSkuId;
    }

    public void setDiscountSkuId(String discountSkuId) {
        this.discountSkuId = discountSkuId;
    }

    public boolean isDiscountType() {
        return discountType;
    }

    public void setDiscountType(boolean discountType) {
        this.discountType = discountType;
    }

    public String getUserCouponId() {
        return userCouponId;
    }

    public void setUserCouponId(String userCouponId) {
        this.userCouponId = userCouponId;
    }
}
