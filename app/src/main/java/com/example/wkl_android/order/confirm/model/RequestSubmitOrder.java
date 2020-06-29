package com.example.wkl_android.order.confirm.model;

import java.util.HashMap;
import java.util.List;

public class RequestSubmitOrder {
    //地址Id
    Long addressId;
    //订单类型
    Integer orderType=1;
    //paymentType
    Integer orderPaymentType=1;
    //唯一标记
    String soleId="XXX-XXX-XXX";
    //订单参数
    List<OrderMasterParam> orderMasterParam;

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getOrderPaymentType() {
        return orderPaymentType;
    }

    public void setOrderPaymentType(Integer orderPaymentType) {
        this.orderPaymentType = orderPaymentType;
    }

    public String getSoleId() {
        return soleId;
    }

    public void setSoleId(String soleId) {
        this.soleId = soleId;
    }


    public List<OrderMasterParam> getOrderMasterParam() {
        return orderMasterParam;
    }

    public void setOrderMasterParam(List<OrderMasterParam> orderMasterParam) {
        this.orderMasterParam = orderMasterParam;
    }

    public static class OrderMasterParam{
       //商家iD
       Long bussinessId;
       //备注
       String orderRemark;
        List<OrderSlaveParam> orderSlaveParamList;
        public Long getBussinessId() {
            return bussinessId;
        }

        public void setBussinessId(Long bussinessId) {
            this.bussinessId = bussinessId;
        }

        public String getOrderRemark() {
            return orderRemark;
        }

        public void setOrderRemark(String orderRemark) {
            this.orderRemark = orderRemark;
        }

        public List<OrderSlaveParam> getOrderSlaveParamList() {
            return orderSlaveParamList;
        }

        public void setOrderSlaveParamList(List<OrderSlaveParam> orderSlaveParamList) {
            this.orderSlaveParamList = orderSlaveParamList;
        }
    }
   public static class  OrderSlaveParam{
       Long skuId;
       //商家优惠券ID
       String orderMasterDiscountId;
       //数量
       Integer orderSlaveCommodityCount;
       //增值服务ID集合
       List<Long> safeguardIdList;

       public Long getSkuId() {
           return skuId;
       }

       public void setSkuId(Long skuId) {
           this.skuId = skuId;
       }

       public String getOrderMasterDiscountId() {
           return orderMasterDiscountId;
       }

       public void setOrderMasterDiscountId(String orderMasterDiscountId) {
           this.orderMasterDiscountId = orderMasterDiscountId;
       }

       public Integer getOrderSlaveCommodityCount() {
           return orderSlaveCommodityCount;
       }

       public void setOrderSlaveCommodityCount(Integer orderSlaveCommodityCount) {
           this.orderSlaveCommodityCount = orderSlaveCommodityCount;
       }

       public List<Long> getSafeguardIdList() {
           return safeguardIdList;
       }

       public void setSafeguardIdList(List<Long> safeguardIdList) {
           this.safeguardIdList = safeguardIdList;
       }
   }
}
