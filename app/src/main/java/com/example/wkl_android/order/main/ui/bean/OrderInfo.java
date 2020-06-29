package com.example.wkl_android.order.main.ui.bean;

import com.example.wkl_android.order.confirm.model.ConfirmGoods;

import java.util.ArrayList;

public class OrderInfo {
    String businessId; //	String	1234358495743692800
    String businessName; //	String	测试店铺
    //    orderListSlaveVOList	Array
    ArrayList<OrderGoodsInfo> orderListSlaveVOList;
    String orderMasterActualPayment; //	Number	3
    String orderMasterDiscountPrice; //	Number	117.98
    String orderMasterFreight; //	Number	1
    String orderMasterGoodsPrice; //	Number	119.98
    String orderMasterTotalPrice; //	Number	120.98
    String orderMasterWeight; //	Number	10
    int orderMasterStatus; //	Integer	8
    String orderMasterId;
    String orderMasterPaymentId;


    public String getOrderMasterPaymentId() {
        return orderMasterPaymentId;
    }

    public void setOrderMasterPaymentId(String orderMasterPaymentId) {
        this.orderMasterPaymentId = orderMasterPaymentId;
    }

    public String getOrderMasterId() {
        return orderMasterId;
    }

    public void setOrderMasterId(String orderMasterId) {
        this.orderMasterId = orderMasterId;
    }

    public int getOrderMasterStatus() {
        return orderMasterStatus;
    }

    public void setOrderMasterStatus(int orderMasterStatus) {
        this.orderMasterStatus = orderMasterStatus;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public ArrayList<OrderGoodsInfo> getOrderListSlaveVOList() {
        return orderListSlaveVOList;
    }

    public void setOrderListSlaveVOList(ArrayList<OrderGoodsInfo> orderListSlaveVOList) {
        this.orderListSlaveVOList = orderListSlaveVOList;
    }

    public String getOrderMasterActualPayment() {
        return orderMasterActualPayment;
    }

    public void setOrderMasterActualPayment(String orderMasterActualPayment) {
        this.orderMasterActualPayment = orderMasterActualPayment;
    }

    public String getOrderMasterDiscountPrice() {
        return orderMasterDiscountPrice;
    }

    public void setOrderMasterDiscountPrice(String orderMasterDiscountPrice) {
        this.orderMasterDiscountPrice = orderMasterDiscountPrice;
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

    public String getOrderMasterTotalPrice() {
        return orderMasterTotalPrice;
    }

    public void setOrderMasterTotalPrice(String orderMasterTotalPrice) {
        this.orderMasterTotalPrice = orderMasterTotalPrice;
    }

    public String getOrderMasterWeight() {
        return orderMasterWeight;
    }

    public void setOrderMasterWeight(String orderMasterWeight) {
        this.orderMasterWeight = orderMasterWeight;
    }
}
