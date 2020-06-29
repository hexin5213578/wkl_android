package com.example.wkl_android.collection.bean;

public class CollectionGoods {
    String createDate; //": "2020-05-22T09:48:10",
    String skuId; //": "1261546613726769152",
    String skuImage; //":"http://vankelai.oss-cn-beijing.aliyuncs.com/2_12.jpg",
    String skuPrice; //":59.99,
    String skuStandard;//":"测试:13 试试:33",
    String skuTitle; //":"宝树行 轩尼诗VSOP700ml Hennessy干邑白兰地法国原装进口洋酒 13 33",
    String userGoodsFavoriteId; //":"1263647808444043264"
    boolean check ;
    boolean select;

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
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

    public String getSkuStandard() {
        return skuStandard;
    }

    public void setSkuStandard(String skuStandard) {
        this.skuStandard = skuStandard;
    }

    public String getSkuTitle() {
        return skuTitle;
    }

    public void setSkuTitle(String skuTitle) {
        this.skuTitle = skuTitle;
    }

    public String getUserGoodsFavoriteId() {
        return userGoodsFavoriteId;
    }

    public void setUserGoodsFavoriteId(String userGoodsFavoriteId) {
        this.userGoodsFavoriteId = userGoodsFavoriteId;
    }
}
