package com.example.wkl_android.orderpaycheck.bean;

import java.util.ArrayList;

public class OrderCheckVo {
    String businessName ;	//String	测试店铺
    String orderMasterTotalPrice; //	Number	0.13;
    ArrayList<OrderCheckGoods> orderListSlaveVOList ;//	Array


    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getOrderMasterTotalPrice() {
        return orderMasterTotalPrice;
    }

    public void setOrderMasterTotalPrice(String orderMasterTotalPrice) {
        this.orderMasterTotalPrice = orderMasterTotalPrice;
    }

    public ArrayList<OrderCheckGoods> getOrderListSlaveVOList() {
        return orderListSlaveVOList;
    }

    public void setOrderListSlaveVOList(ArrayList<OrderCheckGoods> orderListSlaveVOList) {
        this.orderListSlaveVOList = orderListSlaveVOList;
    }
}
