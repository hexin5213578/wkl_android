package com.example.wkl_android.shop_street.shop_detail.ui.bean;

import java.io.Serializable;
import java.util.ArrayList;

public class ShopDetailVo implements Serializable {
    String businessName	; //String	测试店铺;
    String businessImageUrl ; //	String	http://vankelai.oss-cn-beijing.aliyuncs.com/2_wb.png
    String businessAttentionNumber;
    boolean businessAttentionStatus;

    String businessPresentation;

    String businessAddressDetail ; //	String	河南省洛阳市洛龙区翠云路;

    boolean businessPrestoreGiftStatus ;//	Boolean	false
    boolean businessPrestorePriceStatus	;//Boolean	false

    public boolean isBusinessPrestoreGiftStatus() {
        return businessPrestoreGiftStatus;
    }

    public void setBusinessPrestoreGiftStatus(boolean businessPrestoreGiftStatus) {
        this.businessPrestoreGiftStatus = businessPrestoreGiftStatus;
    }

    public boolean isBusinessPrestorePriceStatus() {
        return businessPrestorePriceStatus;
    }

    public void setBusinessPrestorePriceStatus(boolean businessPrestorePriceStatus) {
        this.businessPrestorePriceStatus = businessPrestorePriceStatus;
    }

    public String getBusinessPresentation() {
        return businessPresentation;
    }

    public void setBusinessPresentation(String businessPresentation) {
        this.businessPresentation = businessPresentation;
    }

    public String getBusinessAddressDetail() {
        return businessAddressDetail;
    }

    public void setBusinessAddressDetail(String businessAddressDetail) {
        this.businessAddressDetail = businessAddressDetail;
    }

    public String getBusinessAttentionNumber() {
        return businessAttentionNumber;
    }

    public void setBusinessAttentionNumber(String businessAttentionNumber) {
        this.businessAttentionNumber = businessAttentionNumber;
    }

    public boolean isBusinessAttentionStatus() {
        return businessAttentionStatus;
    }

    public void setBusinessAttentionStatus(boolean businessAttentionStatus) {
        this.businessAttentionStatus = businessAttentionStatus;
    }

    ArrayList<ShopImg> businessImageVOList ; //	Array

    public ArrayList<ShopImg> getBusinessImageVOList() {
        return businessImageVOList;
    }

    public void setBusinessImageVOList(ArrayList<ShopImg> businessImageVOList) {
        this.businessImageVOList = businessImageVOList;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessImageUrl() {
        return businessImageUrl;
    }

    public void setBusinessImageUrl(String businessImageUrl) {
        this.businessImageUrl = businessImageUrl;
    }
}
