package com.example.wkl_android.order.confirm.model;

public class GoosSku {
    String skuImage ; //	String	http://vankelai.oss-cn-beijing.aliyuncs.com/2_qq(1).png;
    String skuPrice ; //	Number	59.99
    String skuId ; //	String	1257115455270051840
    String skuTitle	 ; //String	测试 1斤 白
    String skuTypeName ; //	String	不参加活动

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

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getSkuTitle() {
        return skuTitle;
    }

    public void setSkuTitle(String skuTitle) {
        this.skuTitle = skuTitle;
    }

    public String getSkuTypeName() {
        return skuTypeName;
    }

    public void setSkuTypeName(String skuTypeName) {
        this.skuTypeName = skuTypeName;
    }
}
