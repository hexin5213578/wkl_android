package com.example.wkl_android.shop_street.shop_detail.ui.bean;

import java.io.Serializable;

public class ShopImg implements Serializable {
    String businessImageId;//	Integer	1;
    String businessImageUrl;//String	http://vankelai.oss-cn-beijing.aliyuncs.com/2_dui1.png

    int businessHeight;
    //Integer	64
    int businessWidth;
   // Integer	64


    public int getBusinessHeight() {
        return businessHeight;
    }

    public void setBusinessHeight(int businessHeight) {
        this.businessHeight = businessHeight;
    }

    public int getBusinessWidth() {
        return businessWidth;
    }

    public void setBusinessWidth(int businessWidth) {
        this.businessWidth = businessWidth;
    }

    public String getBusinessImageId() {
        return businessImageId;
    }

    public void setBusinessImageId(String businessImageId) {
        this.businessImageId = businessImageId;
    }

    public String getBusinessImageUrl() {
        return businessImageUrl;
    }

    public void setBusinessImageUrl(String businessImageUrl) {
        this.businessImageUrl = businessImageUrl;
    }
}
