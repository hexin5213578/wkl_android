package com.example.wkl_android.searchshop.bean;

import java.util.ArrayList;

public class ShopVo {
    String businessId; //	String	1234358495743692800
    String businessLogo;//	String	http://vankelai.oss-cn-beijing.aliyuncs.com/2_wb.png
    String businessName; //	String	测试店铺
    Boolean businessPrestoreGiftStatus;//	Boolean	false
    boolean businessPrestorePriceStatus; //Boolean	false
    String businessSales; //	String	0
    int businessType; //	Integer	2

    ArrayList<ShopGoodsVo> goodsSearchAppVOList	;

    public ArrayList<ShopGoodsVo> getGoodsSearchAppVOList() {
        return goodsSearchAppVOList;
    }

    public void setGoodsSearchAppVOList(ArrayList<ShopGoodsVo> goodsSearchAppVOList) {
        this.goodsSearchAppVOList = goodsSearchAppVOList;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

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

    public Boolean getBusinessPrestoreGiftStatus() {
        return businessPrestoreGiftStatus;
    }

    public void setBusinessPrestoreGiftStatus(Boolean businessPrestoreGiftStatus) {
        this.businessPrestoreGiftStatus = businessPrestoreGiftStatus;
    }

    public boolean isBusinessPrestorePriceStatus() {
        return businessPrestorePriceStatus;
    }

    public void setBusinessPrestorePriceStatus(boolean businessPrestorePriceStatus) {
        this.businessPrestorePriceStatus = businessPrestorePriceStatus;
    }

    public String getBusinessSales() {
        return businessSales;
    }

    public void setBusinessSales(String businessSales) {
        this.businessSales = businessSales;
    }

    public int getBusinessType() {
        return businessType;
    }

    public void setBusinessType(int businessType) {
        this.businessType = businessType;
    }
}
