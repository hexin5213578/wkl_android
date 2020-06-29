package com.example.wkl_android.order.confirm.model;

import java.io.Serializable;
import java.util.List;

public class CreateOrder {


    private String userAddressId;
    private List<OrderMasterParamListBean> orderFourMasterParamList;
    int orderMasterType;
    String masterDiscountId;

    public List<OrderMasterParamListBean> getOrderFourMasterParamList() {
        return orderFourMasterParamList;
    }

    public void setOrderFourMasterParamList(List<OrderMasterParamListBean> orderFourMasterParamList) {
        this.orderFourMasterParamList = orderFourMasterParamList;
    }

    public int getOrderMasterType() {
        return orderMasterType;
    }

    public void setOrderMasterType(int orderMasterType) {
        this.orderMasterType = orderMasterType;
    }

    public String getMasterDiscountId() {
        return masterDiscountId;
    }

    public void setMasterDiscountId(String masterDiscountId) {
        this.masterDiscountId = masterDiscountId;
    }

    public String getUserAddressId() {
        return userAddressId;
    }

    public void setUserAddressId(String userAddressId) {
        this.userAddressId = userAddressId;
    }

    public static class OrderMasterParamListBean {


        private String businessId;
        private String orderRemark;
        private List<OrderSlaveParamListBean> orderFourSlaveParamList;
        String businessDiscountId;


        public String getBusinessDiscountId() {
            return businessDiscountId;
        }

        public void setBusinessDiscountId(String businessDiscountId) {
            this.businessDiscountId = businessDiscountId;
        }

        public String getBusinessId() {
            return businessId;
        }

        public void setBusinessId(String businessId) {
            this.businessId = businessId;
        }

        public String getOrderRemark() {
            return orderRemark;
        }

        public void setOrderRemark(String orderRemark) {
            this.orderRemark = orderRemark;
        }

        public List<OrderSlaveParamListBean> getOrderFourSlaveParamList() {
            return orderFourSlaveParamList;
        }

        public void setOrderFourSlaveParamList(List<OrderSlaveParamListBean> orderFourSlaveParamList) {
            this.orderFourSlaveParamList = orderFourSlaveParamList;
        }

        public static class OrderSlaveParamListBean {


            private String skuId;
            String skuType;
            String skuCount;
            String orderDiscountId;


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

            public String getSkuCount() {
                return skuCount;
            }

            public void setSkuCount(String skuCount) {
                this.skuCount = skuCount;
            }

            public String getOrderDiscountId() {
                return orderDiscountId;
            }

            public void setOrderDiscountId(String orderDiscountId) {
                this.orderDiscountId = orderDiscountId;
            }
        }
    }
}
