package com.example.wkl_android.collection.bean;

public class CollectionShop {

    int businessCountNumber;//	Integer	3
    String businessId ;//	String	1234358495743692800
    String businessLogo ;//	String	https://vankelai.oss-cn-beijing.aliyuncs.com/1_64x64.png
    String businessName ;//	String	测试店铺

    boolean check;
    boolean select ;

    public int getBusinessCountNumber() {
        return businessCountNumber;
    }

    public void setBusinessCountNumber(int businessCountNumber) {
        this.businessCountNumber = businessCountNumber;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getBusinessLogo() {
        return businessLogo;
    }

    public void setBusinessLogo(String businessLogo) {
        this.businessLogo = businessLogo;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}
