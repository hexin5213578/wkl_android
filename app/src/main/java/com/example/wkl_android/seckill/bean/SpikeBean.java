package com.example.wkl_android.seckill.bean;

import java.util.List;

public class SpikeBean {

    /**
     * code : 0x10035
     * data : [{"businessId":"1234358495743692800","killBeginCode":3,"killBeginDate":"2020-06-30","killBeginTime":"2020-06-30 10:00:00","killId":"1277783777896173568","killOverTime":"2020-07-01 00:00:00","killPrice":3,"killPriceRaw":59.99,"killStock":7,"killStockRaw":7,"skuId":"1261545942420025344","skuImage":"http://vankelai.oss-cn-beijing.aliyuncs.com/2_12323.jpg","skuTitle":"和乐怡（HOROYOI） 宾三得利 日本进口 预调酒 鸡尾酒 果酒 梅酒苏打口味350ml*6罐 4罐 大","spuId":"1261545682729693184"}]
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

    public static class DataBean {
        /**
         * businessId : 1234358495743692800
         * killBeginCode : 3
         * killBeginDate : 2020-06-30
         * killBeginTime : 2020-06-30 10:00:00
         * killId : 1277783777896173568
         * killOverTime : 2020-07-01 00:00:00
         * killPrice : 3
         * killPriceRaw : 59.99
         * killStock : 7
         * killStockRaw : 7
         * skuId : 1261545942420025344
         * skuImage : http://vankelai.oss-cn-beijing.aliyuncs.com/2_12323.jpg
         * skuTitle : 和乐怡（HOROYOI） 宾三得利 日本进口 预调酒 鸡尾酒 果酒 梅酒苏打口味350ml*6罐 4罐 大
         * spuId : 1261545682729693184
         */

        private String businessId;
        private int killBeginCode;
        private String killBeginDate;
        private String killBeginTime;
        private String killId;
        private String killOverTime;
        private int killPrice;
        private double killPriceRaw;
        private int killStock;
        private int killStockRaw;
        private String skuId;
        private String skuImage;
        private String skuTitle;
        private String spuId;

        public String getBusinessId() {
            return businessId;
        }

        public void setBusinessId(String businessId) {
            this.businessId = businessId;
        }

        public int getKillBeginCode() {
            return killBeginCode;
        }

        public void setKillBeginCode(int killBeginCode) {
            this.killBeginCode = killBeginCode;
        }

        public String getKillBeginDate() {
            return killBeginDate;
        }

        public void setKillBeginDate(String killBeginDate) {
            this.killBeginDate = killBeginDate;
        }

        public String getKillBeginTime() {
            return killBeginTime;
        }

        public void setKillBeginTime(String killBeginTime) {
            this.killBeginTime = killBeginTime;
        }

        public String getKillId() {
            return killId;
        }

        public void setKillId(String killId) {
            this.killId = killId;
        }

        public String getKillOverTime() {
            return killOverTime;
        }

        public void setKillOverTime(String killOverTime) {
            this.killOverTime = killOverTime;
        }

        public int getKillPrice() {
            return killPrice;
        }

        public void setKillPrice(int killPrice) {
            this.killPrice = killPrice;
        }

        public double getKillPriceRaw() {
            return killPriceRaw;
        }

        public void setKillPriceRaw(double killPriceRaw) {
            this.killPriceRaw = killPriceRaw;
        }

        public int getKillStock() {
            return killStock;
        }

        public void setKillStock(int killStock) {
            this.killStock = killStock;
        }

        public int getKillStockRaw() {
            return killStockRaw;
        }

        public void setKillStockRaw(int killStockRaw) {
            this.killStockRaw = killStockRaw;
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

        public String getSkuTitle() {
            return skuTitle;
        }

        public void setSkuTitle(String skuTitle) {
            this.skuTitle = skuTitle;
        }

        public String getSpuId() {
            return spuId;
        }

        public void setSpuId(String spuId) {
            this.spuId = spuId;
        }
    }
}
