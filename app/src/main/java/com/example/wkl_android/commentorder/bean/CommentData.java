package com.example.wkl_android.commentorder.bean;

import java.util.ArrayList;

public class CommentData {
    String orderMasterId;
    String businessEstimateDetail;   //订单评语
    boolean businessEstimateAnonymity;      //匿名
    int businessEstimateGrade;      //评分
    ArrayList<CommentImg> businessEstimateImageParamList = new ArrayList<>();


    String distributionEstimateDetail;
    int distributionEstimateGrade;
    boolean distributionEstimateAnonymity;
    ArrayList<CommentImg> distributionEstimateImageParamList = new ArrayList<>();


    ArrayList<CommentGoods> goodsEstimateParamList = new ArrayList<>();


    public String getOrderMasterId() {
        return orderMasterId;
    }

    public void setOrderMasterId(String orderMasterId) {
        this.orderMasterId = orderMasterId;
    }

    public String getBusinessEstimateDetail() {
        return businessEstimateDetail;
    }

    public void setBusinessEstimateDetail(String businessEstimateDetail) {
        this.businessEstimateDetail = businessEstimateDetail;
    }

    public boolean isBusinessEstimateAnonymity() {
        return businessEstimateAnonymity;
    }

    public void setBusinessEstimateAnonymity(boolean businessEstimateAnonymity) {
        this.businessEstimateAnonymity = businessEstimateAnonymity;
    }

    public int getBusinessEstimateGrade() {
        return businessEstimateGrade;
    }

    public void setBusinessEstimateGrade(int businessEstimateGrade) {
        this.businessEstimateGrade = businessEstimateGrade;
    }

    public ArrayList<CommentImg> getBusinessEstimateImageParamList() {
        return businessEstimateImageParamList;
    }

    public void setBusinessEstimateImageParamList(ArrayList<CommentImg> businessEstimateImageParamList) {
        this.businessEstimateImageParamList = businessEstimateImageParamList;
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

    public boolean isDistributionEstimateAnonymity() {
        return distributionEstimateAnonymity;
    }

    public void setDistributionEstimateAnonymity(boolean distributionEstimateAnonymity) {
        this.distributionEstimateAnonymity = distributionEstimateAnonymity;
    }

    public ArrayList<CommentImg> getDistributionEstimateImageParamList() {
        return distributionEstimateImageParamList;
    }

    public void setDistributionEstimateImageParamList(ArrayList<CommentImg> distributionEstimateImageParamList) {
        this.distributionEstimateImageParamList = distributionEstimateImageParamList;
    }

    public ArrayList<CommentGoods> getGoodsEstimateParamList() {
        return goodsEstimateParamList;
    }

    public void setGoodsEstimateParamList(ArrayList<CommentGoods> goodsEstimateParamList) {
        this.goodsEstimateParamList = goodsEstimateParamList;
    }
}
