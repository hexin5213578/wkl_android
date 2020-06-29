package com.example.wkl_android.order.confirm.model;

import com.example.wkl_android.couponselect.bean.CouponInfo;

import java.util.ArrayList;

public class SubmitShopInfo {
    //    BussinessVo businessVO; //	Object;
    String masterCountPirce;
    String masterFreightPirce; //	Number	5
    String masterGoodsPrice; //	Number	59.99
    String masterPaymentPrice; //	Number	64.99;
    String businessName;
    String businessId;
    String remark;
    ArrayList<CouponInfo> userCouponList ; //	Array
    CouponInfo userCoupon ; //	Object
    ArrayList<SubmitGoodsInfo> orderSubmitSlaveVOList; //	Array
    int orderSubmitCount; //	Integer	1
    ArrayList<CouponInfo> noMeetUserCouponList ; //	Array

    public ArrayList<CouponInfo> getNoMeetUserCouponList() {
        return noMeetUserCouponList;
    }

    public void setNoMeetUserCouponList(ArrayList<CouponInfo> noMeetUserCouponList) {
        this.noMeetUserCouponList = noMeetUserCouponList;
    }

    public ArrayList<CouponInfo> getUserCouponList() {
        return userCouponList;
    }

    public void setUserCouponList(ArrayList<CouponInfo> userCouponList) {
        this.userCouponList = userCouponList;
    }

    public CouponInfo getUserCoupon() {
        return userCoupon;
    }

    public void setUserCoupon(CouponInfo userCoupon) {
        this.userCoupon = userCoupon;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public int getOrderSubmitCount() {
        return orderSubmitCount;
    }

    public void setOrderSubmitCount(int orderSubmitCount) {
        this.orderSubmitCount = orderSubmitCount;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getMasterCountPirce() {
        return masterCountPirce;
    }

    public void setMasterCountPirce(String masterCountPirce) {
        this.masterCountPirce = masterCountPirce;
    }

    public String getMasterFreightPirce() {
        return masterFreightPirce;
    }

    public void setMasterFreightPirce(String masterFreightPirce) {
        this.masterFreightPirce = masterFreightPirce;
    }

    public String getMasterGoodsPrice() {
        return masterGoodsPrice;
    }

    public void setMasterGoodsPrice(String masterGoodsPrice) {
        this.masterGoodsPrice = masterGoodsPrice;
    }

    public String getMasterPaymentPrice() {
        return masterPaymentPrice;
    }

    public void setMasterPaymentPrice(String masterPaymentPrice) {
        this.masterPaymentPrice = masterPaymentPrice;
    }

    public ArrayList<SubmitGoodsInfo> getOrderSubmitSlaveVOList() {
        return orderSubmitSlaveVOList;
    }

    public void setOrderSubmitSlaveVOList(ArrayList<SubmitGoodsInfo> orderSubmitSlaveVOList) {
        this.orderSubmitSlaveVOList = orderSubmitSlaveVOList;
    }
}
