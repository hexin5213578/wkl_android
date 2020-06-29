package com.example.wkl_android.order.confirm.model;

public class ConfirmGoods {
    String skuId;
    String skuType;
    int count;
    String orderDiscountId;


    public String getOrderDiscountId() {
        return orderDiscountId;
    }

    public void setOrderDiscountId(String orderDiscountId) {
        this.orderDiscountId = orderDiscountId;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getSkuType() {
        return skuType;
    }

    public void setSkuType(String skuType) {
        this.skuType = skuType;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
