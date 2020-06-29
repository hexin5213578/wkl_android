package com.example.wkl_android.WholesaleMarketDetail.bean;

public class MarketPointVO {

    boolean classifyDeleteStatus;//	Boolean	false
    String classifyId;//	Integer	70
    String classifyImage; //	String
    String classifyName; //	String	网页UI
    String classifyOrder; //	Integer	10
    String classifyPid; //	Integer	69
    boolean classifyType;//	Boolean	true

    public boolean isClassifyDeleteStatus() {
        return classifyDeleteStatus;
    }

    public void setClassifyDeleteStatus(boolean classifyDeleteStatus) {
        this.classifyDeleteStatus = classifyDeleteStatus;
    }

    public String getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(String classifyId) {
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

    public String getClassifyOrder() {
        return classifyOrder;
    }

    public void setClassifyOrder(String classifyOrder) {
        this.classifyOrder = classifyOrder;
    }

    public String getClassifyPid() {
        return classifyPid;
    }

    public void setClassifyPid(String classifyPid) {
        this.classifyPid = classifyPid;
    }

    public boolean isClassifyType() {
        return classifyType;
    }

    public void setClassifyType(boolean classifyType) {
        this.classifyType = classifyType;
    }
}
