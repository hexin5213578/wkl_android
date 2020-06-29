package com.example.wkl_android.order.detail.model;

import com.luck.picture.lib.entity.LocalMedia;

import java.util.ArrayList;
import java.util.List;

public class OrderGoodsInfo {
    String bussinessId; //	String	1234358495743692800
    String orderSlaveCommodityCount; //	Integer	1
    String orderSlaveCommodityImage; //	String	http://vankelai.oss-cn-beijing.aliyuncs.com/2_59280381N22d15755.jpg
    String orderSlaveCommodityPrices; //	Number	0.01
    String orderSlaveCommodityStandardValue;//String	种类:白心巨无霸 2个
    String orderSlaveCommodityTitle; //	String	黄金蜜瓜 2个 约3.25kg 新鲜水果 白心巨无霸 2个
    String skuId; //	String	1260773785100345344

    String orderSlaveId;

    List<LocalMedia> imgs = new ArrayList<>();
    ArrayList<String> imgUrls = new ArrayList<>();
    String commentDetail;
    int star;

    public String getOrderSlaveId() {
        return orderSlaveId;
    }

    public void setOrderSlaveId(String orderSlaveId) {
        this.orderSlaveId = orderSlaveId;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getCommentDetail() {
        return commentDetail;
    }

    public void setCommentDetail(String commentDetail) {
        this.commentDetail = commentDetail;
    }

    public void setImgs(List<LocalMedia> imgs) {
        this.imgs = imgs;
    }

    public ArrayList<String> getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(ArrayList<String> imgUrls) {
        this.imgUrls = imgUrls;
    }

    public List<LocalMedia> getImgs() {
        return imgs;
    }

    public String getBussinessId() {
        return bussinessId;
    }

    public void setBussinessId(String bussinessId) {
        this.bussinessId = bussinessId;
    }

    public String getOrderSlaveCommodityCount() {
        return orderSlaveCommodityCount;
    }

    public void setOrderSlaveCommodityCount(String orderSlaveCommodityCount) {
        this.orderSlaveCommodityCount = orderSlaveCommodityCount;
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

    public String getOrderSlaveCommodityStandardValue() {
        return orderSlaveCommodityStandardValue;
    }

    public void setOrderSlaveCommodityStandardValue(String orderSlaveCommodityStandardValue) {
        this.orderSlaveCommodityStandardValue = orderSlaveCommodityStandardValue;
    }

    public String getOrderSlaveCommodityTitle() {
        return orderSlaveCommodityTitle;
    }

    public void setOrderSlaveCommodityTitle(String orderSlaveCommodityTitle) {
        this.orderSlaveCommodityTitle = orderSlaveCommodityTitle;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }
}
