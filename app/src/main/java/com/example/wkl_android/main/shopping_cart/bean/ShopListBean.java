package com.example.wkl_android.main.shopping_cart.bean;

import java.io.Serializable;
import java.util.List;

public class ShopListBean implements Serializable{


    /**
     * code : 0x10035
     * data : [{"businessId":"1234358495743692800","businessName":"测试店铺","goodsShopCarVOSet":[{"businessId":"1234358495743692800","businessName":"测试店铺","carId":"1248887061570785280","skuCount":5,"skuId":"1240121642391609344","skuImage":"http://39.100.87.173/37/52/谛我美家清库(1)jpg35dfa9dcd5b04ea4b7be76634f581096谛我美家清库(1).jpg","skuPrice":52,"skuTitle":"测试"},{"businessId":"1234358495743692800","businessName":"测试店铺","carId":"1250322973311766528","skuCount":9,"skuId":"1245182554752286720","skuImage":"http://39.100.87.173/37/01/icon_logo_tangdoupng9c0aa40c77ef4f57b00062ff163cf49aicon_logo_tangdou.png","skuPrice":1,"skuTitle":"测试数据标题"}]}]
     * mesg : 成功
     * status : true
     */

    private String code;
    private String mesg;
    private boolean status;
    private List<DataBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMesg() {
        return mesg;
    }

    public void setMesg(String mesg) {
        this.mesg = mesg;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * businessId : 1234358495743692800
         * businessName : 测试店铺
         * goodsShopCarVOSet : [{"businessId":"1234358495743692800","businessName":"测试店铺","carId":"1248887061570785280","skuCount":5,"skuId":"1240121642391609344","skuImage":"http://39.100.87.173/37/52/谛我美家清库(1)jpg35dfa9dcd5b04ea4b7be76634f581096谛我美家清库(1).jpg","skuPrice":52,"skuTitle":"测试"},{"businessId":"1234358495743692800","businessName":"测试店铺","carId":"1250322973311766528","skuCount":9,"skuId":"1245182554752286720","skuImage":"http://39.100.87.173/37/01/icon_logo_tangdoupng9c0aa40c77ef4f57b00062ff163cf49aicon_logo_tangdou.png","skuPrice":1,"skuTitle":"测试数据标题"}]
         */

        private String businessId;
        private String businessName;
        private List<GoodsShopCarVOSetBean> goodsShopCarVOSet;

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

        public List<GoodsShopCarVOSetBean> getGoodsShopCarVOSet() {
            return goodsShopCarVOSet;
        }

        public void setGoodsShopCarVOSet(List<GoodsShopCarVOSetBean> goodsShopCarVOSet) {
            this.goodsShopCarVOSet = goodsShopCarVOSet;
        }

        public static class GoodsShopCarVOSetBean implements Serializable {
            /**
             * businessId : 1234358495743692800
             * businessName : 测试店铺
             * carId : 1248887061570785280
             * skuCount : 5
             * skuId : 1240121642391609344
             * skuImage : http://39.100.87.173/37/52/谛我美家清库(1)jpg35dfa9dcd5b04ea4b7be76634f581096谛我美家清库(1).jpg
             * skuPrice : 52
             * skuTitle : 测试
             */

            private String businessId;
            private String businessName;
            private String carId;
            private int skuCount;
            private String skuId;
            private String skuImage;
            private double skuPrice;
            private String skuTitle;
            String orderDiscountId;


            public String getOrderDiscountId() {
                return orderDiscountId;
            }

            public void setOrderDiscountId(String orderDiscountId) {
                this.orderDiscountId = orderDiscountId;
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

            public String getCarId() {
                return carId;
            }

            public void setCarId(String carId) {
                this.carId = carId;
            }

            public int getSkuCount() {
                return skuCount;
            }

            public void setSkuCount(int skuCount) {
                this.skuCount = skuCount;
            }

            public String getSkuId() {
                return skuId;
            }

            public void setSkuId(String skuId) {
                this.skuId = skuId;
            }

            public String getSkuImage() {
                return skuImage;
            }

            public void setSkuImage(String skuImage) {
                this.skuImage = skuImage;
            }

            public double getSkuPrice() {
                return skuPrice;
            }

            public void setSkuPrice(double skuPrice) {
                this.skuPrice = skuPrice;
            }

            public String getSkuTitle() {
                return skuTitle;
            }

            public void setSkuTitle(String skuTitle) {
                this.skuTitle = skuTitle;
            }
        }
    }
}
