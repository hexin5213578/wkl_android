package com.example.wkl_android.order.confirm.model;

import com.example.wkl_android.couponselect.bean.CouponInfo;

import java.util.ArrayList;

public class SubmitGoodsInfo {
    int count;
    GoosSku goodsSkuVO;//	Object	 ; //	Array
    ArrayList<CouponInfo> userCouponList ; //	Array
    CouponInfo userCoupon ; //	Object
    ArrayList<CouponInfo> noMeetUserCouponList ; //	Array


    public ArrayList<CouponInfo> getNoMeetUserCouponList() {
        return noMeetUserCouponList;
    }

    public void setNoMeetUserCouponList(ArrayList<CouponInfo> noMeetUserCouponList) {
        this.noMeetUserCouponList = noMeetUserCouponList;
    }

    public CouponInfo getUserCoupon() {
        return userCoupon;
    }

    public void setUserCoupon(CouponInfo userCoupon) {
        this.userCoupon = userCoupon;
    }

    public ArrayList<CouponInfo> getUserCouponList() {
        return userCouponList;
    }

    public void setUserCouponList(ArrayList<CouponInfo> userCouponList) {
        this.userCouponList = userCouponList;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public GoosSku getGoodsSkuVO() {
        return goodsSkuVO;
    }

    public void setGoodsSkuVO(GoosSku goodsSkuVO) {
        this.goodsSkuVO = goodsSkuVO;
    }
}
