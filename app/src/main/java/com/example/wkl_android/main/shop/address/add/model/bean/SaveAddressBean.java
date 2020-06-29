package com.example.wkl_android.main.shop.address.add.model.bean;

/**
 * Created by szx
 * on 2020/4/19/019
 */
public class SaveAddressBean {
    public String consignee;
    public String phoneNumber;
    public boolean isDefault;
    private String addressReDetail;
    public String addressDetail;
    public String addressArea;
    public String addressId;

    public String getAddressReDetail() {
        return addressReDetail;
    }

    public void setAddressReDetail(String addressReDetail) {
        this.addressReDetail = addressReDetail;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getAddressArea() {
        return addressArea;
    }

    public void setAddressArea(String addressArea) {
        this.addressArea = addressArea;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }


    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }
}
