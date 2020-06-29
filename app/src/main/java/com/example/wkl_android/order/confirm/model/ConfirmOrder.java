package com.example.wkl_android.order.confirm.model;

import java.util.ArrayList;

public class ConfirmOrder {
    ArrayList<ConfirmShopInfo> orderSubmitParamList;
    String userAddressId;
    String masterDiscountId;


    public String getUserAddressId() {
        return userAddressId;
    }

    public void setUserAddressId(String userAddressId) {
        this.userAddressId = userAddressId;
    }

    public String getMasterDiscountId() {
        return masterDiscountId;
    }

    public void setMasterDiscountId(String masterDiscountId) {
        this.masterDiscountId = masterDiscountId;
    }

    public ArrayList<ConfirmShopInfo> getOrderSubmitParamList() {
        return orderSubmitParamList;
    }

    public void setOrderSubmitParamList(ArrayList<ConfirmShopInfo> orderSubmitParamList) {
        this.orderSubmitParamList = orderSubmitParamList;
    }
}
