package com.example.wkl_android.commentorder.bean;

import com.luck.picture.lib.entity.LocalMedia;

import java.util.ArrayList;
import java.util.List;

public class CommentGoods {
    String goodsEstimateDetail;
    boolean goodsEstimateAnonymity;
    int goodsEstimateGrade;
    String orderSlaveId;
    ArrayList<CommentImg> goodsEstimateImageParamList = new ArrayList<>();

    public String getOrderSlaveId() {
        return orderSlaveId;
    }

    public void setOrderSlaveId(String orderSlaveId) {
        this.orderSlaveId = orderSlaveId;
    }

    public String getGoodsEstimateDetail() {
        return goodsEstimateDetail;
    }

    public void setGoodsEstimateDetail(String goodsEstimateDetail) {
        this.goodsEstimateDetail = goodsEstimateDetail;
    }

    public boolean isGoodsEstimateAnonymity() {
        return goodsEstimateAnonymity;
    }

    public void setGoodsEstimateAnonymity(boolean goodsEstimateAnonymity) {
        this.goodsEstimateAnonymity = goodsEstimateAnonymity;
    }

    public int getGoodsEstimateGrade() {
        return goodsEstimateGrade;
    }

    public void setGoodsEstimateGrade(int goodsEstimateGrade) {
        this.goodsEstimateGrade = goodsEstimateGrade;
    }

    public ArrayList<CommentImg> getGoodsEstimateImageParamList() {
        return goodsEstimateImageParamList;
    }

    public void setGoodsEstimateImageParamList(ArrayList<CommentImg> goodsEstimateImageParamList) {
        this.goodsEstimateImageParamList = goodsEstimateImageParamList;
    }
}
