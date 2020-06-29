package com.example.wkl_android.good.model;

import java.io.Serializable;
import java.util.List;

public class GoodsBean implements Serializable {

    /**
     * code : 0x10035
     * data : {"brandId":"1241172136446869505","brandName":"泰国菠萝","businessId":"1234358495743692800","businessImage":"/37/01/a501988f630f42eb87136e5fac216ba9.png","businessMarketId":1001,"businessMarketType":1,"businessMarketTypeName":"批发市场","businessName":"测试店铺","goodsClassifyVOList":[{"classifyId":4,"classifyImage":"http://39.100.87.173/31/96/img-5c64332732ccc4d3f63d90d2ef644e27jpgimg-5c64332732ccc4d3f63d90d2ef644e27jpgd36ca9d85def4086843274dc71f9060aimg-5c64332732ccc4d3f63d90d2ef644e27jpgimg-5c64332732ccc4d3f63d90d2ef644e27.jpg","classifyName":"水果","classifyPid":0,"classifyType":true},{"classifyId":5,"classifyName":"菠萝","classifyPid":4,"classifyType":true}],"goodsStaticPreviewCarouselImageVOList":[{"imageId":"1246610481218048000","imageUrl":"http://39.100.87.173/37/01/icon_logo_tangdoupng817b48a99d574102a71932cbf8048889icon_logo_tangdou.png"}],"goodsStaticPreviewSkuStandardSetVOList":[{"goodsStaticPreviewSkuStandardValueSetVOSet":[{"isAt":true,"skuId":"1245182554752286720","spuBuyStandardValue":"绿"}],"skuStandardName":"颜色"}],"goodsStaticPreviewSkuVOList":[{"goodsSkuStandardVOList":[{"skuStandardId":"1245182554764869632","skuStandardValue":"绿","standardName":"颜色"}],"goodsSkuTitleImageVOList":[{"imageId":"1246610481218048000","imageUrl":"http://39.100.87.173/37/01/icon_logo_tangdoupng817b48a99d574102a71932cbf8048889icon_logo_tangdou.png"}],"skuFreight":4,"skuId":"1245182554752286720","skuImage":"http://39.100.87.173/37/01/icon_logo_tangdoupng9c0aa40c77ef4f57b00062ff163cf49aicon_logo_tangdou.png","skuPrice":1,"skuShare":false,"skuState":true,"skuStockNum":1,"skuTitle":"测试数据标题","skuType":1,"skuTypeName":"不参加活动","skuTypeStatus":true,"skuWeight":2,"spuId":"1244242652489564160"}],"masterGoodsStaticPreviewSkuVO":{"goodsSkuStandardVOList":[{"skuStandardId":"1245182554764869632","skuStandardValue":"绿","standardName":"颜色"}],"goodsSkuTitleImageVOList":[{"imageId":"1246610481218048000","imageUrl":"http://39.100.87.173/37/01/icon_logo_tangdoupng817b48a99d574102a71932cbf8048889icon_logo_tangdou.png"}],"skuFreight":4,"skuId":"1245182554752286720","skuImage":"http://39.100.87.173/37/01/icon_logo_tangdoupng9c0aa40c77ef4f57b00062ff163cf49aicon_logo_tangdou.png","skuPrice":1,"skuShare":false,"skuState":true,"skuStockNum":1,"skuTitle":"测试数据标题","skuType":1,"skuTypeName":"不参加活动","skuTypeStatus":true,"skuWeight":2,"spuId":"1244242652489564160"},"productImage":"http://39.100.87.173/37/01/icon_logo_tangdoupng9c0aa40c77ef4f57b00062ff163cf49aicon_logo_tangdou.png","productName":"哈哈","productTitle":"测试数据标题","skuEstimate":0,"spuAuditStatus":2,"spuId":"1244242652489564160","spuRichTextUploadStatus":false,"spuStandardUploadStatus":false,"spuState":1,"spuVideoUploadStatus":false}
     * mesg : 成功
     * status : true
     */

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

    public static class DataBean  implements Serializable {
        /**
         * brandId : 1241172136446869505
         * brandName : 泰国菠萝
         * businessId : 1234358495743692800
         * businessImage : /37/01/a501988f630f42eb87136e5fac216ba9.png
         * businessMarketId : 1001
         * businessMarketType : 1
         * businessMarketTypeName : 批发市场
         * businessName : 测试店铺
         * goodsClassifyVOList : [{"classifyId":4,"classifyImage":"http://39.100.87.173/31/96/img-5c64332732ccc4d3f63d90d2ef644e27jpgimg-5c64332732ccc4d3f63d90d2ef644e27jpgd36ca9d85def4086843274dc71f9060aimg-5c64332732ccc4d3f63d90d2ef644e27jpgimg-5c64332732ccc4d3f63d90d2ef644e27.jpg","classifyName":"水果","classifyPid":0,"classifyType":true},{"classifyId":5,"classifyName":"菠萝","classifyPid":4,"classifyType":true}]
         * goodsStaticPreviewCarouselImageVOList : [{"imageId":"1246610481218048000","imageUrl":"http://39.100.87.173/37/01/icon_logo_tangdoupng817b48a99d574102a71932cbf8048889icon_logo_tangdou.png"}]
         * goodsStaticPreviewSkuStandardSetVOList : [{"goodsStaticPreviewSkuStandardValueSetVOSet":[{"isAt":true,"skuId":"1245182554752286720","spuBuyStandardValue":"绿"}],"skuStandardName":"颜色"}]
         * goodsStaticPreviewSkuVOList : [{"goodsSkuStandardVOList":[{"skuStandardId":"1245182554764869632","skuStandardValue":"绿","standardName":"颜色"}],"goodsSkuTitleImageVOList":[{"imageId":"1246610481218048000","imageUrl":"http://39.100.87.173/37/01/icon_logo_tangdoupng817b48a99d574102a71932cbf8048889icon_logo_tangdou.png"}],"skuFreight":4,"skuId":"1245182554752286720","skuImage":"http://39.100.87.173/37/01/icon_logo_tangdoupng9c0aa40c77ef4f57b00062ff163cf49aicon_logo_tangdou.png","skuPrice":1,"skuShare":false,"skuState":true,"skuStockNum":1,"skuTitle":"测试数据标题","skuType":1,"skuTypeName":"不参加活动","skuTypeStatus":true,"skuWeight":2,"spuId":"1244242652489564160"}]
         * masterGoodsStaticPreviewSkuVO : {"goodsSkuStandardVOList":[{"skuStandardId":"1245182554764869632","skuStandardValue":"绿","standardName":"颜色"}],"goodsSkuTitleImageVOList":[{"imageId":"1246610481218048000","imageUrl":"http://39.100.87.173/37/01/icon_logo_tangdoupng817b48a99d574102a71932cbf8048889icon_logo_tangdou.png"}],"skuFreight":4,"skuId":"1245182554752286720","skuImage":"http://39.100.87.173/37/01/icon_logo_tangdoupng9c0aa40c77ef4f57b00062ff163cf49aicon_logo_tangdou.png","skuPrice":1,"skuShare":false,"skuState":true,"skuStockNum":1,"skuTitle":"测试数据标题","skuType":1,"skuTypeName":"不参加活动","skuTypeStatus":true,"skuWeight":2,"spuId":"1244242652489564160"}
         * productImage : http://39.100.87.173/37/01/icon_logo_tangdoupng9c0aa40c77ef4f57b00062ff163cf49aicon_logo_tangdou.png
         * productName : 哈哈
         * productTitle : 测试数据标题
         * skuEstimate : 0
         * spuAuditStatus : 2
         * spuId : 1244242652489564160
         * spuRichTextUploadStatus : false
         * spuStandardUploadStatus : false
         * spuState : 1
         * spuVideoUploadStatus : false
         */

        private String brandId;
        private String brandName;
        private String businessId;
        private String businessImage;
        private int businessMarketId;
        private int businessMarketType;
        private String businessMarketTypeName;
        private String businessName;
        private MasterGoodsStaticPreviewSkuVOBean masterGoodsStaticPreviewSkuVO;
        private String productImage;
        private String productName;
        private String productTitle;
        private int skuEstimate;
        private int spuAuditStatus;

        private String spuId;
        private String spuDetailRichText;
        private boolean spuRichTextUploadStatus;
        private boolean spuStandardUploadStatus;
        private int spuState;
        private boolean spuVideoUploadStatus;
        private List<GoodsClassifyVOListBean> goodsClassifyVOList;
        private List<GoodsStaticPreviewCarouselImageVOListBean> goodsStaticPreviewCarouselImageVOList;
        private List<GoodsStaticPreviewSkuStandardSetVOListBean> goodsStaticPreviewSkuStandardSetVOList;
        private List<GoodsStaticPreviewSkuVOListBean> goodsStaticPreviewSkuVOList;

        public String getSpuDetailRichText() {
            return spuDetailRichText;
        }

        public void setSpuDetailRichText(String spuDetailRichText) {
            this.spuDetailRichText = spuDetailRichText;
        }

        public String getBrandId() {
            return brandId;
        }

        public void setBrandId(String brandId) {
            this.brandId = brandId;
        }

        public String getBrandName() {
            return brandName;
        }

        public void setBrandName(String brandName) {
            this.brandName = brandName;
        }

        public String getBusinessId() {
            return businessId;
        }

        public void setBusinessId(String businessId) {
            this.businessId = businessId;
        }

        public String getBusinessImage() {
            return businessImage;
        }

        public void setBusinessImage(String businessImage) {
            this.businessImage = businessImage;
        }

        public int getBusinessMarketId() {
            return businessMarketId;
        }

        public void setBusinessMarketId(int businessMarketId) {
            this.businessMarketId = businessMarketId;
        }

        public int getBusinessMarketType() {
            return businessMarketType;
        }

        public void setBusinessMarketType(int businessMarketType) {
            this.businessMarketType = businessMarketType;
        }

        public String getBusinessMarketTypeName() {
            return businessMarketTypeName;
        }

        public void setBusinessMarketTypeName(String businessMarketTypeName) {
            this.businessMarketTypeName = businessMarketTypeName;
        }

        public String getBusinessName() {
            return businessName;
        }

        public void setBusinessName(String businessName) {
            this.businessName = businessName;
        }

        public MasterGoodsStaticPreviewSkuVOBean getMasterGoodsStaticPreviewSkuVO() {
            return masterGoodsStaticPreviewSkuVO;
        }

        public void setMasterGoodsStaticPreviewSkuVO(MasterGoodsStaticPreviewSkuVOBean masterGoodsStaticPreviewSkuVO) {
            this.masterGoodsStaticPreviewSkuVO = masterGoodsStaticPreviewSkuVO;
        }

        public String getProductImage() {
            return productImage;
        }

        public void setProductImage(String productImage) {
            this.productImage = productImage;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getProductTitle() {
            return productTitle;
        }

        public void setProductTitle(String productTitle) {
            this.productTitle = productTitle;
        }

        public int getSkuEstimate() {
            return skuEstimate;
        }

        public void setSkuEstimate(int skuEstimate) {
            this.skuEstimate = skuEstimate;
        }

        public int getSpuAuditStatus() {
            return spuAuditStatus;
        }

        public void setSpuAuditStatus(int spuAuditStatus) {
            this.spuAuditStatus = spuAuditStatus;
        }

        public String getSpuId() {
            return spuId;
        }

        public void setSpuId(String spuId) {
            this.spuId = spuId;
        }

        public boolean isSpuRichTextUploadStatus() {
            return spuRichTextUploadStatus;
        }

        public void setSpuRichTextUploadStatus(boolean spuRichTextUploadStatus) {
            this.spuRichTextUploadStatus = spuRichTextUploadStatus;
        }

        public boolean isSpuStandardUploadStatus() {
            return spuStandardUploadStatus;
        }

        public void setSpuStandardUploadStatus(boolean spuStandardUploadStatus) {
            this.spuStandardUploadStatus = spuStandardUploadStatus;
        }

        public int getSpuState() {
            return spuState;
        }

        public void setSpuState(int spuState) {
            this.spuState = spuState;
        }

        public boolean isSpuVideoUploadStatus() {
            return spuVideoUploadStatus;
        }

        public void setSpuVideoUploadStatus(boolean spuVideoUploadStatus) {
            this.spuVideoUploadStatus = spuVideoUploadStatus;
        }

        public List<GoodsClassifyVOListBean> getGoodsClassifyVOList() {
            return goodsClassifyVOList;
        }

        public void setGoodsClassifyVOList(List<GoodsClassifyVOListBean> goodsClassifyVOList) {
            this.goodsClassifyVOList = goodsClassifyVOList;
        }

        public List<GoodsStaticPreviewCarouselImageVOListBean> getGoodsStaticPreviewCarouselImageVOList() {
            return goodsStaticPreviewCarouselImageVOList;
        }

        public void setGoodsStaticPreviewCarouselImageVOList(List<GoodsStaticPreviewCarouselImageVOListBean> goodsStaticPreviewCarouselImageVOList) {
            this.goodsStaticPreviewCarouselImageVOList = goodsStaticPreviewCarouselImageVOList;
        }

        public List<GoodsStaticPreviewSkuStandardSetVOListBean> getGoodsStaticPreviewSkuStandardSetVOList() {
            return goodsStaticPreviewSkuStandardSetVOList;
        }

        public void setGoodsStaticPreviewSkuStandardSetVOList(List<GoodsStaticPreviewSkuStandardSetVOListBean> goodsStaticPreviewSkuStandardSetVOList) {
            this.goodsStaticPreviewSkuStandardSetVOList = goodsStaticPreviewSkuStandardSetVOList;
        }

        public List<GoodsStaticPreviewSkuVOListBean> getGoodsStaticPreviewSkuVOList() {
            return goodsStaticPreviewSkuVOList;
        }

        public void setGoodsStaticPreviewSkuVOList(List<GoodsStaticPreviewSkuVOListBean> goodsStaticPreviewSkuVOList) {
            this.goodsStaticPreviewSkuVOList = goodsStaticPreviewSkuVOList;
        }

        public static class MasterGoodsStaticPreviewSkuVOBean implements Serializable{
            /**
             * goodsSkuStandardVOList : [{"skuStandardId":"1245182554764869632","skuStandardValue":"绿","standardName":"颜色"}]
             * goodsSkuTitleImageVOList : [{"imageId":"1246610481218048000","imageUrl":"http://39.100.87.173/37/01/icon_logo_tangdoupng817b48a99d574102a71932cbf8048889icon_logo_tangdou.png"}]
             * skuFreight : 4.0
             * skuId : 1245182554752286720
             * skuImage : http://39.100.87.173/37/01/icon_logo_tangdoupng9c0aa40c77ef4f57b00062ff163cf49aicon_logo_tangdou.png
             * skuPrice : 1.0
             * skuShare : false
             * skuState : true
             * skuStockNum : 1
             * skuTitle : 测试数据标题
             * skuType : 1
             * skuTypeName : 不参加活动
             * skuTypeStatus : true
             * skuWeight : 2.0
             * spuId : 1244242652489564160
             */

            private double skuFreight;
            private boolean skuFullGiveStatus;
            private boolean skuFullReductionStatus;
            private boolean skuGroupStatus;
            private String skuId;
            private String skuImage;
            private double skuPrice;
            private boolean skuShare;
            private boolean skuState;
            private int skuStockNum;
            private String skuTitle;
            private int skuType;
            private String skuTypeName;
            private boolean skuTypeStatus;
            private double skuWeight;
            private String spuId;
            private List<GoodsSkuStandardVOListBean> goodsSkuStandardVOList;
            private List<GoodsSkuTitleImageVOListBean> goodsSkuTitleImageVOList;
          private GoodsStaticPreviewSkuEstimateVO  goodsStaticPreviewSkuEstimateVO;

            public GoodsStaticPreviewSkuEstimateVO getGoodsStaticPreviewSkuEstimateVO() {
                return goodsStaticPreviewSkuEstimateVO;
            }

            public void setGoodsStaticPreviewSkuEstimateVO(GoodsStaticPreviewSkuEstimateVO goodsStaticPreviewSkuEstimateVO) {
                this.goodsStaticPreviewSkuEstimateVO = goodsStaticPreviewSkuEstimateVO;
            }

            public boolean isSkuFullGiveStatus() {
                return skuFullGiveStatus;
            }

            public void setSkuFullGiveStatus(boolean skuFullGiveStatus) {
                this.skuFullGiveStatus = skuFullGiveStatus;
            }

            public boolean isSkuFullReductionStatus() {
                return skuFullReductionStatus;
            }

            public void setSkuFullReductionStatus(boolean skuFullReductionStatus) {
                this.skuFullReductionStatus = skuFullReductionStatus;
            }

            public boolean isSkuGroupStatus() {
                return skuGroupStatus;
            }

            public void setSkuGroupStatus(boolean skuGroupStatus) {
                this.skuGroupStatus = skuGroupStatus;
            }

            public double getSkuFreight() {
                return skuFreight;
            }

            public void setSkuFreight(double skuFreight) {
                this.skuFreight = skuFreight;
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

            public boolean isSkuShare() {
                return skuShare;
            }

            public void setSkuShare(boolean skuShare) {
                this.skuShare = skuShare;
            }

            public boolean isSkuState() {
                return skuState;
            }

            public void setSkuState(boolean skuState) {
                this.skuState = skuState;
            }

            public int getSkuStockNum() {
                return skuStockNum;
            }

            public void setSkuStockNum(int skuStockNum) {
                this.skuStockNum = skuStockNum;
            }

            public String getSkuTitle() {
                return skuTitle;
            }

            public void setSkuTitle(String skuTitle) {
                this.skuTitle = skuTitle;
            }

            public int getSkuType() {
                return skuType;
            }

            public void setSkuType(int skuType) {
                this.skuType = skuType;
            }

            public String getSkuTypeName() {
                return skuTypeName;
            }

            public void setSkuTypeName(String skuTypeName) {
                this.skuTypeName = skuTypeName;
            }

            public boolean isSkuTypeStatus() {
                return skuTypeStatus;
            }

            public void setSkuTypeStatus(boolean skuTypeStatus) {
                this.skuTypeStatus = skuTypeStatus;
            }

            public double getSkuWeight() {
                return skuWeight;
            }

            public void setSkuWeight(double skuWeight) {
                this.skuWeight = skuWeight;
            }

            public String getSpuId() {
                return spuId;
            }

            public void setSpuId(String spuId) {
                this.spuId = spuId;
            }

            public List<GoodsSkuStandardVOListBean> getGoodsSkuStandardVOList() {
                return goodsSkuStandardVOList;
            }

            public void setGoodsSkuStandardVOList(List<GoodsSkuStandardVOListBean> goodsSkuStandardVOList) {
                this.goodsSkuStandardVOList = goodsSkuStandardVOList;
            }

            public List<GoodsSkuTitleImageVOListBean> getGoodsSkuTitleImageVOList() {
                return goodsSkuTitleImageVOList;
            }

            public void setGoodsSkuTitleImageVOList(List<GoodsSkuTitleImageVOListBean> goodsSkuTitleImageVOList) {
                this.goodsSkuTitleImageVOList = goodsSkuTitleImageVOList;
            }

            public static class GoodsSkuStandardVOListBean implements Serializable{
                /**
                 * skuStandardId : 1245182554764869632
                 * skuStandardValue : 绿
                 * standardName : 颜色
                 */

                private String skuStandardId;
                private String skuStandardValue;
                private String standardName;

                public String getSkuStandardId() {
                    return skuStandardId;
                }

                public void setSkuStandardId(String skuStandardId) {
                    this.skuStandardId = skuStandardId;
                }

                public String getSkuStandardValue() {
                    return skuStandardValue;
                }

                public void setSkuStandardValue(String skuStandardValue) {
                    this.skuStandardValue = skuStandardValue;
                }

                public String getStandardName() {
                    return standardName;
                }

                public void setStandardName(String standardName) {
                    this.standardName = standardName;
                }
            }

            public static class GoodsSkuTitleImageVOListBean implements Serializable{
                /**
                 * imageId : 1246610481218048000
                 * imageUrl : http://39.100.87.173/37/01/icon_logo_tangdoupng817b48a99d574102a71932cbf8048889icon_logo_tangdou.png
                 */

                private String imageId;
                private String imageUrl;

                public String getImageId() {
                    return imageId;
                }

                public void setImageId(String imageId) {
                    this.imageId = imageId;
                }

                public String getImageUrl() {
                    return imageUrl;
                }

                public void setImageUrl(String imageUrl) {
                    this.imageUrl = imageUrl;
                }
            }
        }

        public static class GoodsStaticPreviewSkuEstimateVO implements  Serializable{
            /*"skuBadEstimate": 0,
                    "skuCommonEstimate": 0,
                    "skuEstimate": 0,
                    "skuGoodEstimate": 0*/

            private int skuBadEstimate;
            private int skuCommonEstimate;
            private int skuEstimate;
            private int skuGoodEstimate;

            public int getSkuBadEstimate() {
                return skuBadEstimate;
            }

            public void setSkuBadEstimate(int skuBadEstimate) {
                this.skuBadEstimate = skuBadEstimate;
            }

            public int getSkuCommonEstimate() {
                return skuCommonEstimate;
            }

            public void setSkuCommonEstimate(int skuCommonEstimate) {
                this.skuCommonEstimate = skuCommonEstimate;
            }

            public int getSkuEstimate() {
                return skuEstimate;
            }

            public void setSkuEstimate(int skuEstimate) {
                this.skuEstimate = skuEstimate;
            }

            public int getSkuGoodEstimate() {
                return skuGoodEstimate;
            }

            public void setSkuGoodEstimate(int skuGoodEstimate) {
                this.skuGoodEstimate = skuGoodEstimate;
            }
        }

        public static class GoodsClassifyVOListBean implements Serializable{
            /**
             * classifyId : 4
             * classifyImage : http://39.100.87.173/31/96/img-5c64332732ccc4d3f63d90d2ef644e27jpgimg-5c64332732ccc4d3f63d90d2ef644e27jpgd36ca9d85def4086843274dc71f9060aimg-5c64332732ccc4d3f63d90d2ef644e27jpgimg-5c64332732ccc4d3f63d90d2ef644e27.jpg
             * classifyName : 水果
             * classifyPid : 0
             * classifyType : true
             */

            private int classifyId;
            private String classifyImage;
            private String classifyName;
            private int classifyPid;
            private boolean classifyType;

            public int getClassifyId() {
                return classifyId;
            }

            public void setClassifyId(int classifyId) {
                this.classifyId = classifyId;
            }

            public String getClassifyImage() {
                return classifyImage;
            }

            public void setClassifyImage(String classifyImage) {
                this.classifyImage = classifyImage;
            }

            public String getClassifyName() {
                return classifyName;
            }

            public void setClassifyName(String classifyName) {
                this.classifyName = classifyName;
            }

            public int getClassifyPid() {
                return classifyPid;
            }

            public void setClassifyPid(int classifyPid) {
                this.classifyPid = classifyPid;
            }

            public boolean isClassifyType() {
                return classifyType;
            }

            public void setClassifyType(boolean classifyType) {
                this.classifyType = classifyType;
            }
        }

        public static class GoodsStaticPreviewCarouselImageVOListBean implements Serializable{
            /**
             * imageId : 1246610481218048000
             * imageUrl : http://39.100.87.173/37/01/icon_logo_tangdoupng817b48a99d574102a71932cbf8048889icon_logo_tangdou.png
             */

            private String imageId;
            private String imageUrl;

            public String getImageId() {
                return imageId;
            }

            public void setImageId(String imageId) {
                this.imageId = imageId;
            }

            public String getImageUrl() {
                return imageUrl;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }
        }

        public static class GoodsStaticPreviewSkuStandardSetVOListBean implements Serializable{
            /**
             * goodsStaticPreviewSkuStandardValueSetVOSet : [{"isAt":true,"skuId":"1245182554752286720","spuBuyStandardValue":"绿"}]
             * skuStandardName : 颜色
             */

            private String skuStandardName;
            private List<GoodsStaticPreviewSkuStandardValueSetVOSetBean> goodsStaticPreviewSkuStandardValueSetVOSet;

            public String getSkuStandardName() {
                return skuStandardName;
            }

            public void setSkuStandardName(String skuStandardName) {
                this.skuStandardName = skuStandardName;
            }

            public List<GoodsStaticPreviewSkuStandardValueSetVOSetBean> getGoodsStaticPreviewSkuStandardValueSetVOSet() {
                return goodsStaticPreviewSkuStandardValueSetVOSet;
            }

            public void setGoodsStaticPreviewSkuStandardValueSetVOSet(List<GoodsStaticPreviewSkuStandardValueSetVOSetBean> goodsStaticPreviewSkuStandardValueSetVOSet) {
                this.goodsStaticPreviewSkuStandardValueSetVOSet = goodsStaticPreviewSkuStandardValueSetVOSet;
            }

            public static class GoodsStaticPreviewSkuStandardValueSetVOSetBean implements Serializable{
                /**
                 * isAt : true
                 * skuId : 1245182554752286720
                 * spuBuyStandardValue : 绿
                 */
                private boolean isAt;
                private String skuId;
                private String spuBuyStandardValue;

                public boolean isIsAt() {
                    return isAt;
                }

                public void setIsAt(boolean isAt) {
                    this.isAt = isAt;
                }

                public String getSkuId() {
                    return skuId;
                }

                public void setSkuId(String skuId) {
                    this.skuId = skuId;
                }

                public String getSpuBuyStandardValue() {
                    return spuBuyStandardValue;
                }

                public void setSpuBuyStandardValue(String spuBuyStandardValue) {
                    this.spuBuyStandardValue = spuBuyStandardValue;
                }
            }
        }

        public static class GoodsStaticPreviewSkuVOListBean implements Serializable{
            /**
             * goodsSkuStandardVOList : [{"skuStandardId":"1245182554764869632","skuStandardValue":"绿","standardName":"颜色"}]
             * goodsSkuTitleImageVOList : [{"imageId":"1246610481218048000","imageUrl":"http://39.100.87.173/37/01/icon_logo_tangdoupng817b48a99d574102a71932cbf8048889icon_logo_tangdou.png"}]
             * skuFreight : 4.0
             * skuId : 1245182554752286720
             * skuImage : http://39.100.87.173/37/01/icon_logo_tangdoupng9c0aa40c77ef4f57b00062ff163cf49aicon_logo_tangdou.png
             * skuPrice : 1.0
             * skuShare : false
             * skuState : true
             * skuStockNum : 1
             * skuTitle : 测试数据标题
             * skuType : 1
             * skuTypeName : 不参加活动
             * skuTypeStatus : true
             * skuWeight : 2.0
             * spuId : 1244242652489564160
             */

            private double skuFreight;
            private String skuId;
            private String skuImage;
            private String skuPrice;
            private boolean skuShare;
            private boolean skuState;
            private int skuStockNum;
            private String skuTitle;
            private int skuType;
            private String skuTypeName;
            private boolean skuTypeStatus;
            private double skuWeight;
            private String spuId;
            private List<GoodsSkuStandardVOListBeanX> goodsSkuStandardVOList;
            private List<GoodsSkuTitleImageVOListBeanX> goodsSkuTitleImageVOList;

            public double getSkuFreight() {
                return skuFreight;
            }

            public void setSkuFreight(double skuFreight) {
                this.skuFreight = skuFreight;
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

            public String getSkuPrice() {
                return skuPrice;
            }

            public void setSkuPrice(String skuPrice) {
                this.skuPrice = skuPrice;
            }

            public boolean isSkuShare() {
                return skuShare;
            }

            public void setSkuShare(boolean skuShare) {
                this.skuShare = skuShare;
            }

            public boolean isSkuState() {
                return skuState;
            }

            public void setSkuState(boolean skuState) {
                this.skuState = skuState;
            }

            public int getSkuStockNum() {
                return skuStockNum;
            }

            public void setSkuStockNum(int skuStockNum) {
                this.skuStockNum = skuStockNum;
            }

            public String getSkuTitle() {
                return skuTitle;
            }

            public void setSkuTitle(String skuTitle) {
                this.skuTitle = skuTitle;
            }

            public int getSkuType() {
                return skuType;
            }

            public void setSkuType(int skuType) {
                this.skuType = skuType;
            }

            public String getSkuTypeName() {
                return skuTypeName;
            }

            public void setSkuTypeName(String skuTypeName) {
                this.skuTypeName = skuTypeName;
            }

            public boolean isSkuTypeStatus() {
                return skuTypeStatus;
            }

            public void setSkuTypeStatus(boolean skuTypeStatus) {
                this.skuTypeStatus = skuTypeStatus;
            }

            public double getSkuWeight() {
                return skuWeight;
            }

            public void setSkuWeight(double skuWeight) {
                this.skuWeight = skuWeight;
            }

            public String getSpuId() {
                return spuId;
            }

            public void setSpuId(String spuId) {
                this.spuId = spuId;
            }

            public List<GoodsSkuStandardVOListBeanX> getGoodsSkuStandardVOList() {
                return goodsSkuStandardVOList;
            }

            public void setGoodsSkuStandardVOList(List<GoodsSkuStandardVOListBeanX> goodsSkuStandardVOList) {
                this.goodsSkuStandardVOList = goodsSkuStandardVOList;
            }

            public List<GoodsSkuTitleImageVOListBeanX> getGoodsSkuTitleImageVOList() {
                return goodsSkuTitleImageVOList;
            }

            public void setGoodsSkuTitleImageVOList(List<GoodsSkuTitleImageVOListBeanX> goodsSkuTitleImageVOList) {
                this.goodsSkuTitleImageVOList = goodsSkuTitleImageVOList;
            }

            public static class GoodsSkuStandardVOListBeanX implements Serializable{
                /**
                 * skuStandardId : 1245182554764869632
                 * skuStandardValue : 绿
                 * standardName : 颜色
                 */

                private String skuStandardId;
                private String skuStandardValue;
                private String standardName;

                public String getSkuStandardId() {
                    return skuStandardId;
                }

                public void setSkuStandardId(String skuStandardId) {
                    this.skuStandardId = skuStandardId;
                }

                public String getSkuStandardValue() {
                    return skuStandardValue;
                }

                public void setSkuStandardValue(String skuStandardValue) {
                    this.skuStandardValue = skuStandardValue;
                }

                public String getStandardName() {
                    return standardName;
                }

                public void setStandardName(String standardName) {
                    this.standardName = standardName;
                }
            }

            public static class GoodsSkuTitleImageVOListBeanX implements Serializable{
                /**
                 * imageId : 1246610481218048000
                 * imageUrl : http://39.100.87.173/37/01/icon_logo_tangdoupng817b48a99d574102a71932cbf8048889icon_logo_tangdou.png
                 */

                private String imageId;
                private String imageUrl;

                public String getImageId() {
                    return imageId;
                }

                public void setImageId(String imageId) {
                    this.imageId = imageId;
                }

                public String getImageUrl() {
                    return imageUrl;
                }

                public void setImageUrl(String imageUrl) {
                    this.imageUrl = imageUrl;
                }
            }
        }
    }
}
