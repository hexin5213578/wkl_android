package com.example.wkl_android.order.detail.model;

import java.util.ArrayList;

public class OrderDetailInfo {
    int orderMasterStatus;
    String orderMasterLinkmanName;
    String orderMasterLinkmanPhone;

    String orderMasterId;
    String orderMasterCreateTime;
    String orderMasterActualPayment;
    String orderMasterFreight;
    String orderMasterGoodsPrice;
    String orderMasterPaymentId; //String	1261133449897578496

    String orderMasterAddressDetail; //	String	北京市东城区中华路甲10号北京市东城区中华路甲10号 '
    String businessName;
    ArrayList<OrderGoodsInfo> orderSlaveVOList; //	Array
    int payType;
    String orderMasterPaymentTime;
    boolean orderMasterDistributionType;


    public boolean isOrderMasterDistributionType() {
        return orderMasterDistributionType;
    }

    public void setOrderMasterDistributionType(boolean orderMasterDistributionType) {
        this.orderMasterDistributionType = orderMasterDistributionType;
    }

    public String getOrderMasterPaymentTime() {
        return orderMasterPaymentTime;
    }

    public void setOrderMasterPaymentTime(String orderMasterPaymentTime) {
        this.orderMasterPaymentTime = orderMasterPaymentTime;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public String getOrderMasterAddressDetail() {
        return orderMasterAddressDetail;
    }

    public void setOrderMasterAddressDetail(String orderMasterAddressDetail) {
        this.orderMasterAddressDetail = orderMasterAddressDetail;
    }

    public String getOrderMasterPaymentId() {
        return orderMasterPaymentId;
    }

    public void setOrderMasterPaymentId(String orderMasterPaymentId) {
        this.orderMasterPaymentId = orderMasterPaymentId;
    }

    public ArrayList<OrderGoodsInfo> getOrderSlaveVOList() {
        return orderSlaveVOList;
    }

    public void setOrderSlaveVOList(ArrayList<OrderGoodsInfo> orderSlaveVOList) {
        this.orderSlaveVOList = orderSlaveVOList;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getOrderMasterId() {
        return orderMasterId;
    }

    public void setOrderMasterId(String orderMasterId) {
        this.orderMasterId = orderMasterId;
    }

    public String getOrderMasterCreateTime() {
        return orderMasterCreateTime;
    }

    public void setOrderMasterCreateTime(String orderMasterCreateTime) {
        this.orderMasterCreateTime = orderMasterCreateTime;
    }

    public String getOrderMasterActualPayment() {
        return orderMasterActualPayment;
    }

    public void setOrderMasterActualPayment(String orderMasterActualPayment) {
        this.orderMasterActualPayment = orderMasterActualPayment;
    }

    public String getOrderMasterFreight() {
        return orderMasterFreight;
    }

    public void setOrderMasterFreight(String orderMasterFreight) {
        this.orderMasterFreight = orderMasterFreight;
    }

    public String getOrderMasterGoodsPrice() {
        return orderMasterGoodsPrice;
    }

    public void setOrderMasterGoodsPrice(String orderMasterGoodsPrice) {
        this.orderMasterGoodsPrice = orderMasterGoodsPrice;
    }

    public int getOrderMasterStatus() {
        return orderMasterStatus;
    }

    public void setOrderMasterStatus(int orderMasterStatus) {
        this.orderMasterStatus = orderMasterStatus;
    }

    public String getOrderMasterLinkmanName() {
        return orderMasterLinkmanName;
    }

    public void setOrderMasterLinkmanName(String orderMasterLinkmanName) {
        this.orderMasterLinkmanName = orderMasterLinkmanName;
    }

    public String getOrderMasterLinkmanPhone() {
        return orderMasterLinkmanPhone;
    }

    public void setOrderMasterLinkmanPhone(String orderMasterLinkmanPhone) {
        this.orderMasterLinkmanPhone = orderMasterLinkmanPhone;
    }
}
