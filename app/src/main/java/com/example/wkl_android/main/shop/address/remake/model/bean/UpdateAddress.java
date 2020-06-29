package com.example.wkl_android.main.shop.address.remake.model.bean;

/**
 * Created by szx
 * on 2020/1/7/007
 */
public class UpdateAddress {

    /**
     * addressId : string
     * consignee : string
     * detailedAddress : string
     * isDefault : true
     * phoneNumber : string
     * site1 : 0
     * site2 : 0
     * site3 : 0
     */

    private String addressId;
    private String consignee;
    private String detailedAddress;
    private boolean isDefault;
    private String phoneNumber;
    private String site1;
    private String site2;
    private String site3;

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getDetailedAddress() {
        return detailedAddress;
    }

    public void setDetailedAddress(String detailedAddress) {
        this.detailedAddress = detailedAddress;
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

    public String getSite1() {
        return site1;
    }

    public void setSite1(String site1) {
        this.site1 = site1;
    }

    public String getSite2() {
        return site2;
    }

    public void setSite2(String site2) {
        this.site2 = site2;
    }

    public String getSite3() {
        return site3;
    }

    public void setSite3(String site3) {
        this.site3 = site3;
    }
}
