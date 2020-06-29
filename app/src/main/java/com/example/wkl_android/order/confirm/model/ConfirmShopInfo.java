package com.example.wkl_android.order.confirm.model;

import java.util.ArrayList;

public class ConfirmShopInfo {
    String businessId;
    ArrayList<ConfirmGoods> orderSubmitMasterParamList;
    String businessDiscountId;

    public String getBusinessDiscountId() {
        return businessDiscountId;
    }

    public void setBusinessDiscountId(String businessDiscountId) {
        this.businessDiscountId = businessDiscountId;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public ArrayList<ConfirmGoods> getOrderSubmitMasterParamList() {
        return orderSubmitMasterParamList;
    }

    public void setOrderSubmitMasterParamList(ArrayList<ConfirmGoods> orderSubmitMasterParamList) {
        this.orderSubmitMasterParamList = orderSubmitMasterParamList;
    }
}
