package com.example.wkl_android.wholesale_market.bean;

public class MarketInfo {
    String marketAddress ; //	String	34.62066835370762,112.46247553704542;
    String marketDetail ; //	String	河南省洛阳市洛龙区展览路
    String marketId; //	Integer	1010
    String marketImage ; //	String	http://vankelai.oss-cn-beijing.aliyuncs.com/2_qq(1).png
    String marketName; //	String	测绘师
    String marketPhone ; //	String	0379-32758988
    boolean marketStatus ; //	Boolean	true
    int marketType ; //	Integer	1
    String marketTypeName ;//	String	批发市场

    public String getMarketAddress() {
        return marketAddress;
    }

    public void setMarketAddress(String marketAddress) {
        this.marketAddress = marketAddress;
    }

    public String getMarketDetail() {
        return marketDetail;
    }

    public void setMarketDetail(String marketDetail) {
        this.marketDetail = marketDetail;
    }

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }

    public String getMarketImage() {
        return marketImage;
    }

    public void setMarketImage(String marketImage) {
        this.marketImage = marketImage;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public String getMarketPhone() {
        return marketPhone;
    }

    public void setMarketPhone(String marketPhone) {
        this.marketPhone = marketPhone;
    }

    public boolean isMarketStatus() {
        return marketStatus;
    }

    public void setMarketStatus(boolean marketStatus) {
        this.marketStatus = marketStatus;
    }

    public int getMarketType() {
        return marketType;
    }

    public void setMarketType(int marketType) {
        this.marketType = marketType;
    }

    public String getMarketTypeName() {
        return marketTypeName;
    }

    public void setMarketTypeName(String marketTypeName) {
        this.marketTypeName = marketTypeName;
    }
}
