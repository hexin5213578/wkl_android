package com.example.wkl_android.orderpay.bean;

public class WXPreOrderInfo {
    String package_value; //	String	Sign=WXPay
    String appid; //	String	wx4df431b6c5f636e7
    String sign; //	String	B1F42B2BEC98395BFF71E970FFDDE26B
    String trade_type; //	String	APP
    String partnerid; //	String	1557099981
    String prepayid; //	String	wx13094135119634400fadcc7d1207828500
    String noncestr; //	String	BVGWRAZcPu22tcMi
    String timestamp; //	String	1589334095

    public String getPackage_value() {
        return package_value;
    }

    public void setPackage_value(String package_value) {
        this.package_value = package_value;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
