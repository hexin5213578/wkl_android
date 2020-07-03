package com.example.wkl_android.seckill.bean;

import java.util.List;

/**
 * @ProjectName: wkl_android
 * @Package: com.example.wkl_android.seckill.bean
 * @ClassName: GoodsCommentBean
 * @Description: (java类作用描述)
 * @Author: 何梦洋
 * @CreateDate: 2020/7/3 20:12
 */
public  class GoodsCommentBean {

    private String code;
    private DataBean data;
    private String mesg;
    private boolean status;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
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

    public static class DataBean {
        /**
         * businessEstimateVO : {"businessEstimateDetail":"会吃醋积极参加","businessEstimateGrade":3,"businessEstimateImageStatus":false,"businessEstimateImageVOList":[],"businessEstimateUserName":"phone用户_13783144246","businessName":"测试店铺","createDate":"2020-05-20 17:05:27"}
         * distributionEstimateVO : {"createDate":"2020-05-20 17:05:28","distributionEstimateDetail":"超级超级姐姐","distributionEstimateGrade":2,"distributionEstimateId":"66cc2cfae0c8430f9d50718e77cf0117","distributionEstimateImageStatus":false,"distributionEstimateImageVOList":[],"distributionEstimateUserName":"phone用户_13783144246","orderMasterId":"1261547310618382336","salesmanId":"1234358495743692800","salesmanName":"业务员1号"}
         * goodsEstimateVOList : [{"createDate":"2020-05-20 17:05:28","goodsEstimateDetail":"超级超级就超级超级","goodsEstimateGrade":3,"goodsEstimateId":"eae24fabbe1a4d2089e675e6dd6ac62f","goodsEstimateImageStatus":false,"goodsEstimateImageVOList":[],"goodsEstimateUserName":"phone用户_13783144246","goodsSkuId":"1261542082393665536","orderMasterId":"1261547310618382336","orderSlaveCommodityImage":"http://vankelai.oss-cn-beijing.aliyuncs.com/2_12.jpg","orderSlaveCommodityPrices":0.01,"orderSlaveCommodityTitle":"拉菲(LAFITE)传奇波尔多 赤霞珠干红葡萄酒 750ml*6瓶 整箱装 法国进口红酒 两瓶装 1年","orderSlaveId":"1261547310693879808"}]
         */

        private BusinessEstimateVOBean businessEstimateVO;
        private DistributionEstimateVOBean distributionEstimateVO;
        private List<GoodsEstimateVOListBean> goodsEstimateVOList;

        public BusinessEstimateVOBean getBusinessEstimateVO() {
            return businessEstimateVO;
        }

        public void setBusinessEstimateVO(BusinessEstimateVOBean businessEstimateVO) {
            this.businessEstimateVO = businessEstimateVO;
        }

        public DistributionEstimateVOBean getDistributionEstimateVO() {
            return distributionEstimateVO;
        }

        public void setDistributionEstimateVO(DistributionEstimateVOBean distributionEstimateVO) {
            this.distributionEstimateVO = distributionEstimateVO;
        }

        public List<GoodsEstimateVOListBean> getGoodsEstimateVOList() {
            return goodsEstimateVOList;
        }

        public void setGoodsEstimateVOList(List<GoodsEstimateVOListBean> goodsEstimateVOList) {
            this.goodsEstimateVOList = goodsEstimateVOList;
        }

        public static class BusinessEstimateVOBean {
            /**
             * businessEstimateDetail : 会吃醋积极参加
             * businessEstimateGrade : 3
             * businessEstimateImageStatus : false
             * businessEstimateImageVOList : []
             * businessEstimateUserName : phone用户_13783144246
             * businessName : 测试店铺
             * createDate : 2020-05-20 17:05:27
             */

            private String businessEstimateDetail;
            private int businessEstimateGrade;
            private boolean businessEstimateImageStatus;
            private String businessEstimateUserName;
            private String businessName;
            private String createDate;
            private List<?> businessEstimateImageVOList;

            public String getBusinessEstimateDetail() {
                return businessEstimateDetail;
            }

            public void setBusinessEstimateDetail(String businessEstimateDetail) {
                this.businessEstimateDetail = businessEstimateDetail;
            }

            public int getBusinessEstimateGrade() {
                return businessEstimateGrade;
            }

            public void setBusinessEstimateGrade(int businessEstimateGrade) {
                this.businessEstimateGrade = businessEstimateGrade;
            }

            public boolean isBusinessEstimateImageStatus() {
                return businessEstimateImageStatus;
            }

            public void setBusinessEstimateImageStatus(boolean businessEstimateImageStatus) {
                this.businessEstimateImageStatus = businessEstimateImageStatus;
            }

            public String getBusinessEstimateUserName() {
                return businessEstimateUserName;
            }

            public void setBusinessEstimateUserName(String businessEstimateUserName) {
                this.businessEstimateUserName = businessEstimateUserName;
            }

            public String getBusinessName() {
                return businessName;
            }

            public void setBusinessName(String businessName) {
                this.businessName = businessName;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public List<?> getBusinessEstimateImageVOList() {
                return businessEstimateImageVOList;
            }

            public void setBusinessEstimateImageVOList(List<?> businessEstimateImageVOList) {
                this.businessEstimateImageVOList = businessEstimateImageVOList;
            }
        }

        public static class DistributionEstimateVOBean {
            /**
             * createDate : 2020-05-20 17:05:28
             * distributionEstimateDetail : 超级超级姐姐
             * distributionEstimateGrade : 2
             * distributionEstimateId : 66cc2cfae0c8430f9d50718e77cf0117
             * distributionEstimateImageStatus : false
             * distributionEstimateImageVOList : []
             * distributionEstimateUserName : phone用户_13783144246
             * orderMasterId : 1261547310618382336
             * salesmanId : 1234358495743692800
             * salesmanName : 业务员1号
             */

            private String createDate;
            private String distributionEstimateDetail;
            private int distributionEstimateGrade;
            private String distributionEstimateId;
            private boolean distributionEstimateImageStatus;
            private String distributionEstimateUserName;
            private String orderMasterId;
            private String salesmanId;
            private String salesmanName;
            private List<?> distributionEstimateImageVOList;

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public String getDistributionEstimateDetail() {
                return distributionEstimateDetail;
            }

            public void setDistributionEstimateDetail(String distributionEstimateDetail) {
                this.distributionEstimateDetail = distributionEstimateDetail;
            }

            public int getDistributionEstimateGrade() {
                return distributionEstimateGrade;
            }

            public void setDistributionEstimateGrade(int distributionEstimateGrade) {
                this.distributionEstimateGrade = distributionEstimateGrade;
            }

            public String getDistributionEstimateId() {
                return distributionEstimateId;
            }

            public void setDistributionEstimateId(String distributionEstimateId) {
                this.distributionEstimateId = distributionEstimateId;
            }

            public boolean isDistributionEstimateImageStatus() {
                return distributionEstimateImageStatus;
            }

            public void setDistributionEstimateImageStatus(boolean distributionEstimateImageStatus) {
                this.distributionEstimateImageStatus = distributionEstimateImageStatus;
            }

            public String getDistributionEstimateUserName() {
                return distributionEstimateUserName;
            }

            public void setDistributionEstimateUserName(String distributionEstimateUserName) {
                this.distributionEstimateUserName = distributionEstimateUserName;
            }

            public String getOrderMasterId() {
                return orderMasterId;
            }

            public void setOrderMasterId(String orderMasterId) {
                this.orderMasterId = orderMasterId;
            }

            public String getSalesmanId() {
                return salesmanId;
            }

            public void setSalesmanId(String salesmanId) {
                this.salesmanId = salesmanId;
            }

            public String getSalesmanName() {
                return salesmanName;
            }

            public void setSalesmanName(String salesmanName) {
                this.salesmanName = salesmanName;
            }

            public List<?> getDistributionEstimateImageVOList() {
                return distributionEstimateImageVOList;
            }

            public void setDistributionEstimateImageVOList(List<?> distributionEstimateImageVOList) {
                this.distributionEstimateImageVOList = distributionEstimateImageVOList;
            }
        }

        public static class GoodsEstimateVOListBean {
            /**
             * createDate : 2020-05-20 17:05:28
             * goodsEstimateDetail : 超级超级就超级超级
             * goodsEstimateGrade : 3
             * goodsEstimateId : eae24fabbe1a4d2089e675e6dd6ac62f
             * goodsEstimateImageStatus : false
             * goodsEstimateImageVOList : []
             * goodsEstimateUserName : phone用户_13783144246
             * goodsSkuId : 1261542082393665536
             * orderMasterId : 1261547310618382336
             * orderSlaveCommodityImage : http://vankelai.oss-cn-beijing.aliyuncs.com/2_12.jpg
             * orderSlaveCommodityPrices : 0.01
             * orderSlaveCommodityTitle : 拉菲(LAFITE)传奇波尔多 赤霞珠干红葡萄酒 750ml*6瓶 整箱装 法国进口红酒 两瓶装 1年
             * orderSlaveId : 1261547310693879808
             */

            private String createDate;
            private String goodsEstimateDetail;
            private int goodsEstimateGrade;
            private String goodsEstimateId;
            private boolean goodsEstimateImageStatus;
            private String goodsEstimateUserName;
            private String goodsSkuId;
            private String orderMasterId;
            private String orderSlaveCommodityImage;
            private double orderSlaveCommodityPrices;
            private String orderSlaveCommodityTitle;
            private String orderSlaveId;
            private List<?> goodsEstimateImageVOList;

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public String getGoodsEstimateDetail() {
                return goodsEstimateDetail;
            }

            public void setGoodsEstimateDetail(String goodsEstimateDetail) {
                this.goodsEstimateDetail = goodsEstimateDetail;
            }

            public int getGoodsEstimateGrade() {
                return goodsEstimateGrade;
            }

            public void setGoodsEstimateGrade(int goodsEstimateGrade) {
                this.goodsEstimateGrade = goodsEstimateGrade;
            }

            public String getGoodsEstimateId() {
                return goodsEstimateId;
            }

            public void setGoodsEstimateId(String goodsEstimateId) {
                this.goodsEstimateId = goodsEstimateId;
            }

            public boolean isGoodsEstimateImageStatus() {
                return goodsEstimateImageStatus;
            }

            public void setGoodsEstimateImageStatus(boolean goodsEstimateImageStatus) {
                this.goodsEstimateImageStatus = goodsEstimateImageStatus;
            }

            public String getGoodsEstimateUserName() {
                return goodsEstimateUserName;
            }

            public void setGoodsEstimateUserName(String goodsEstimateUserName) {
                this.goodsEstimateUserName = goodsEstimateUserName;
            }

            public String getGoodsSkuId() {
                return goodsSkuId;
            }

            public void setGoodsSkuId(String goodsSkuId) {
                this.goodsSkuId = goodsSkuId;
            }

            public String getOrderMasterId() {
                return orderMasterId;
            }

            public void setOrderMasterId(String orderMasterId) {
                this.orderMasterId = orderMasterId;
            }

            public String getOrderSlaveCommodityImage() {
                return orderSlaveCommodityImage;
            }

            public void setOrderSlaveCommodityImage(String orderSlaveCommodityImage) {
                this.orderSlaveCommodityImage = orderSlaveCommodityImage;
            }

            public double getOrderSlaveCommodityPrices() {
                return orderSlaveCommodityPrices;
            }

            public void setOrderSlaveCommodityPrices(double orderSlaveCommodityPrices) {
                this.orderSlaveCommodityPrices = orderSlaveCommodityPrices;
            }

            public String getOrderSlaveCommodityTitle() {
                return orderSlaveCommodityTitle;
            }

            public void setOrderSlaveCommodityTitle(String orderSlaveCommodityTitle) {
                this.orderSlaveCommodityTitle = orderSlaveCommodityTitle;
            }

            public String getOrderSlaveId() {
                return orderSlaveId;
            }

            public void setOrderSlaveId(String orderSlaveId) {
                this.orderSlaveId = orderSlaveId;
            }

            public List<?> getGoodsEstimateImageVOList() {
                return goodsEstimateImageVOList;
            }

            public void setGoodsEstimateImageVOList(List<?> goodsEstimateImageVOList) {
                this.goodsEstimateImageVOList = goodsEstimateImageVOList;
            }
        }
    }
}
