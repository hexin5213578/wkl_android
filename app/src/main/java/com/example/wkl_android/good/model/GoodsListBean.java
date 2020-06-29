package com.example.wkl_android.good.model;

import com.example.wkl_android.main.home.model.bean.HomeAd;
import com.example.wkl_android.searchshop.bean.ShopVo;

import java.util.ArrayList;
import java.util.List;

public class GoodsListBean {

    /**
     * data : [{"brandId":"1241172136446869505","businessId":"1234358495743692800","businessMarketId":1001,"businessMarketType":1,"businessName":"test","businessType":2,"classifyId":5,"createDate":"2020-04-05 17:32:33","productName":"哈哈","skuFreight":4,"skuId":"1245182554752286720","skuPrice":1,"skuSales":"0","skuStock":true,"skuTitle":"测试数据标题","skuTitleImage":"http://39.100.87.173/37/01/icon_logo_tangdoupng9c0aa40c77ef4f57b00062ff163cf49aicon_logo_tangdou.png","skuType":1,"skuTypeName":"不参加活动","spuId":"1244242652489564160"}]
     * page : 1
     * size : 200
     * total : 1
     */

    private int page;
    private int size;
    private String total;
    private List<DataBean> data = new ArrayList<>();
    private List<GoodsPlateVOList> goodsPlateVOList;
    private List<GoodsSlideshowVOList> goodsSlideshowVOList;
    private List<HomeAd> goodsAdvertisingVOList; //	Array
    ShopVo businessSearchAppVO;//	Object;


    public ShopVo getBusinessSearchAppVO() {
        return businessSearchAppVO;
    }

    public void setBusinessSearchAppVO(ShopVo businessSearchAppVO) {
        this.businessSearchAppVO = businessSearchAppVO;
    }

    public List<HomeAd> getGoodsAdvertisingVOList() {
        return goodsAdvertisingVOList;
    }

    public void setGoodsAdvertisingVOList(List<HomeAd> goodsAdvertisingVOList) {
        this.goodsAdvertisingVOList = goodsAdvertisingVOList;
    }

    public List<GoodsPlateVOList> getGoodsPlateVOList() {
        return goodsPlateVOList;
    }

    public void setGoodsPlateVOList(List<GoodsPlateVOList> goodsPlateVOList) {
        this.goodsPlateVOList = goodsPlateVOList;
    }

    public List<GoodsSlideshowVOList> getGoodsSlideshowVOList() {
        return goodsSlideshowVOList;
    }

    public void setGoodsSlideshowVOList(List<GoodsSlideshowVOList> goodsSlideshowVOList) {
        this.goodsSlideshowVOList = goodsSlideshowVOList;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class GoodsPlateVOList {
        private int plateId;
        private String plateImage;
        private String plateName;
        private String plateOrder;
        private String plateStatus;
        private String plateUrl;

        public int getPlateId() {
            return plateId;
        }

        public void setPlateId(int plateId) {
            this.plateId = plateId;
        }

        public String getPlateImage() {
            return plateImage;
        }

        public void setPlateImage(String plateImage) {
            this.plateImage = plateImage;
        }

        public String getPlateName() {
            return plateName;
        }

        public void setPlateName(String plateName) {
            this.plateName = plateName;
        }

        public String getPlateOrder() {
            return plateOrder;
        }

        public void setPlateOrder(String plateOrder) {
            this.plateOrder = plateOrder;
        }

        public String getPlateStatus() {
            return plateStatus;
        }

        public void setPlateStatus(String plateStatus) {
            this.plateStatus = plateStatus;
        }

        public String getPlateUrl() {
            return plateUrl;
        }

        public void setPlateUrl(String plateUrl) {
            this.plateUrl = plateUrl;
        }
    }

    public static class GoodsSlideshowVOList {
        private int slideshowId;
        private String slideshowImage;
        private String slideshowName;
        private int slideshowOrder;
        private boolean slideshowStatus;
        private String slideshowUrl;

        public int getSlideshowId() {
            return slideshowId;
        }

        public void setSlideshowId(int slideshowId) {
            this.slideshowId = slideshowId;
        }

        public String getSlideshowImage() {
            return slideshowImage;
        }

        public void setSlideshowImage(String slideshowImage) {
            this.slideshowImage = slideshowImage;
        }

        public String getSlideshowName() {
            return slideshowName;
        }

        public void setSlideshowName(String slideshowName) {
            this.slideshowName = slideshowName;
        }

        public int getSlideshowOrder() {
            return slideshowOrder;
        }

        public void setSlideshowOrder(int slideshowOrder) {
            this.slideshowOrder = slideshowOrder;
        }

        public boolean isSlideshowStatus() {
            return slideshowStatus;
        }

        public void setSlideshowStatus(boolean slideshowStatus) {
            this.slideshowStatus = slideshowStatus;
        }

        public String getSlideshowUrl() {
            return slideshowUrl;
        }

        public void setSlideshowUrl(String slideshowUrl) {
            this.slideshowUrl = slideshowUrl;
        }
    }

    public static class DataBean {


        private String brandId;
        private String businessId;
        private int businessMarketId;
        private int businessMarketType;
        private String businessName;
        private int businessType;
        private int classifyId;
        private String createDate;
        private String productName;
        private int skuFreight;
        private String skuId;
        private String skuPrice;
        private String skuSales;
        private boolean skuStock;
        private String skuTitle;
        private String skuTitleImage;
        private int skuType;
        private String skuTypeName;
        private String spuId;


        String spuTitle; //	String	【10斤仅需39.9】西域美农纸袋精品红富士苹果水果生鲜当季新鲜非冰糖心苹果 5斤装纸袋红富士苹果
        String spuTitleImage; //	String	http://vankelai.oss-cn-beijing.aliyuncs.com/2_123.jpg


        public String getSpuTitle() {
            return spuTitle;
        }

        public void setSpuTitle(String spuTitle) {
            this.spuTitle = spuTitle;
        }

        public String getSpuTitleImage() {
            return spuTitleImage;
        }

        public void setSpuTitleImage(String spuTitleImage) {
            this.spuTitleImage = spuTitleImage;
        }

        public String getBrandId() {
            return brandId;
        }

        public void setBrandId(String brandId) {
            this.brandId = brandId;
        }

        public String getBusinessId() {
            return businessId;
        }

        public void setBusinessId(String businessId) {
            this.businessId = businessId;
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

        public String getBusinessName() {
            return businessName;
        }

        public void setBusinessName(String businessName) {
            this.businessName = businessName;
        }

        public int getBusinessType() {
            return businessType;
        }

        public void setBusinessType(int businessType) {
            this.businessType = businessType;
        }

        public int getClassifyId() {
            return classifyId;
        }

        public void setClassifyId(int classifyId) {
            this.classifyId = classifyId;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public int getSkuFreight() {
            return skuFreight;
        }

        public void setSkuFreight(int skuFreight) {
            this.skuFreight = skuFreight;
        }

        public String getSkuId() {
            return skuId;
        }

        public void setSkuId(String skuId) {
            this.skuId = skuId;
        }

        public String getSkuPrice() {
            return skuPrice;
        }

        public void setSkuPrice(String skuPrice) {
            this.skuPrice = skuPrice;
        }

        public String getSkuSales() {
            return skuSales;
        }

        public void setSkuSales(String skuSales) {
            this.skuSales = skuSales;
        }

        public boolean isSkuStock() {
            return skuStock;
        }

        public void setSkuStock(boolean skuStock) {
            this.skuStock = skuStock;
        }

        public String getSkuTitle() {
            return skuTitle;
        }

        public void setSkuTitle(String skuTitle) {
            this.skuTitle = skuTitle;
        }

        public String getSkuTitleImage() {
            return skuTitleImage;
        }

        public void setSkuTitleImage(String skuTitleImage) {
            this.skuTitleImage = skuTitleImage;
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

        public String getSpuId() {
            return spuId;
        }

        public void setSpuId(String spuId) {
            this.spuId = spuId;
        }
    }
}
