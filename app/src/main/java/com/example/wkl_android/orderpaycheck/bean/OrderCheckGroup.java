package com.example.wkl_android.orderpaycheck.bean;

import java.util.ArrayList;

public class OrderCheckGroup {

    String payId;//	String	1262225417876672512
    String payPrice ; //	Number	0.13
    ArrayList<OrderCheckVo> orderListVOList ;//	Array

    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }

    public String getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(String payPrice) {
        this.payPrice = payPrice;
    }

    public ArrayList<OrderCheckVo> getOrderListVOList() {
        return orderListVOList;
    }

    public void setOrderListVOList(ArrayList<OrderCheckVo> orderListVOList) {
        this.orderListVOList = orderListVOList;
    }
}
