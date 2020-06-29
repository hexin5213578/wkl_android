package com.example.wkl_android.order.confirm.model;

import com.example.wkl_android.couponselect.bean.CouponInfo;

import java.util.ArrayList;

public class SubmitInfo {

    SubmitAddress userAddressVO ;//Object
    String paymentPrice ; //	Number	64.99
    ArrayList<SubmitShopInfo> orderSubmitMasterVOList; //	Array
    ArrayList<CouponInfo> userCouponList ; //	Array
    ArrayList<CouponInfo> noMeetUserCouponList ; //	Array
    CouponInfo userCoupon ; //	Object


    public CouponInfo getUserCoupon() {
        return userCoupon;
    }

    public void setUserCoupon(CouponInfo userCoupon) {
        this.userCoupon = userCoupon;
    }

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

    public ArrayList<SubmitShopInfo> getOrderSubmitMasterVOList() {
        return orderSubmitMasterVOList;
    }

    public void setOrderSubmitMasterVOList(ArrayList<SubmitShopInfo> orderSubmitMasterVOList) {
        this.orderSubmitMasterVOList = orderSubmitMasterVOList;
    }

    public String getPaymentPrice() {
        return paymentPrice;
    }

    public void setPaymentPrice(String paymentPrice) {
        this.paymentPrice = paymentPrice;
    }

    public SubmitAddress getUserAddressVO() {
        return userAddressVO;
    }

    public void setUserAddressVO(SubmitAddress userAddressVO) {
        this.userAddressVO = userAddressVO;
    }
}
