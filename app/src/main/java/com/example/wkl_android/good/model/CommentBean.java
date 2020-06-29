package com.example.wkl_android.good.model;

import java.util.ArrayList;

public class CommentBean {
    String createDate; //	String	2020-04-27 11:52:09
    String goodsEstimateDetail; //	String	发布会开始，开始预订，银色大字体很好看，独特立体反光。这款颜色应该很喜欢，屏幕色彩也很正，满意。拍照效果刚试10倍效果清晰，肉眼看模糊的字体，拍照出来后字体清晰，对我来说够强大了。第一次用华为，系统优化不错。刚收到手机，至于待机时间用后再追评，返正手机比图片好看大气独特，非常满意。
    int goodsEstimateGrade; //	Integer	3
    String goodsEstimateId; //	String	d3a05338f7a34cb7ae90eeb081a7ee2b
    Boolean goodsEstimateImageStatus; //	Boolean	true
    String goodsEstimateUserName; //	String	匿名用户
    String goodsSkuId; //	String	1256506967452020736
    String orderMasterId; //	String	1247356845904359400
    String orderSlaveCommodityImage; //	String	http://vankelai.oss-cn-beijing.aliyuncs.com/2_64x64.png
    String orderSlaveCommodityPrices; //	Number	1
    String orderSlaveCommodityTitle; //	String	测试数据标题
    String orderSlaveId; //	String	1247367635654930432
    ArrayList<CommentImg> goodsEstimateImageVOList;//	Array

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

    public Boolean getGoodsEstimateImageStatus() {
        return goodsEstimateImageStatus;
    }

    public void setGoodsEstimateImageStatus(Boolean goodsEstimateImageStatus) {
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

    public String getOrderSlaveCommodityPrices() {
        return orderSlaveCommodityPrices;
    }

    public void setOrderSlaveCommodityPrices(String orderSlaveCommodityPrices) {
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

    public ArrayList<CommentImg> getGoodsEstimateImageVOList() {
        return goodsEstimateImageVOList;
    }

    public void setGoodsEstimateImageVOList(ArrayList<CommentImg> goodsEstimateImageVOList) {
        this.goodsEstimateImageVOList = goodsEstimateImageVOList;
    }
}
