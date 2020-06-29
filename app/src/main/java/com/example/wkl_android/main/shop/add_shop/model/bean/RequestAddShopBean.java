package com.example.wkl_android.main.shop.add_shop.model.bean;

public class RequestAddShopBean {
    private String businessName;
    private String businessLinkman;
    private String businessLinkmanPhoneNumber;
    private String businessCompanyName;
    private String businessLicense;
    private String businessIdCardOne;
    private String businessIdCardTwo;
    private String businessIdCardThree;
    private String businessMedicalCertificate="a";
    private String businessFoodCirculationPermit="b";
    private Integer businessClassify=4;
    private Integer businessType; //1/2/3、自营/普通商户/厂家

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessLinkman() {
        return businessLinkman;
    }

    public void setBusinessLinkman(String businessLinkman) {
        this.businessLinkman = businessLinkman;
    }

    public String getBusinessLinkmanPhoneNumber() {
        return businessLinkmanPhoneNumber;
    }

    public void setBusinessLinkmanPhoneNumber(String businessLinkmanPhoneNumber) {
        this.businessLinkmanPhoneNumber = businessLinkmanPhoneNumber;
    }

    public String getBusinessCompanyName() {
        return businessCompanyName;
    }

    public void setBusinessCompanyName(String businessCompanyName) {
        this.businessCompanyName = businessCompanyName;
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    public String getBusinessIdCardOne() {
        return businessIdCardOne;
    }

    public void setBusinessIdCardOne(String businessIdCardOne) {
        this.businessIdCardOne = businessIdCardOne;
    }

    public String getBusinessIdCardTwo() {
        return businessIdCardTwo;
    }

    public void setBusinessIdCardTwo(String businessIdCardTwo) {
        this.businessIdCardTwo = businessIdCardTwo;
    }

    public String getBusinessIdCardThree() {
        return businessIdCardThree;
    }

    public void setBusinessIdCardThree(String businessIdCardThree) {
        this.businessIdCardThree = businessIdCardThree;
    }

    public String getBusinessMedicalCertificate() {
        return businessMedicalCertificate;
    }

    public void setBusinessMedicalCertificate(String businessMedicalCertificate) {
        this.businessMedicalCertificate = businessMedicalCertificate;
    }

    public String getBusinessFoodCirculationPermit() {
        return businessFoodCirculationPermit;
    }

    public void setBusinessFoodCirculationPermit(String businessFoodCirculationPermit) {
        this.businessFoodCirculationPermit = businessFoodCirculationPermit;
    }

    public Integer getBusinessClassify() {
       // return businessClassify;
        return businessClassify;
    }

    public void setBusinessClassify(Integer businessClassify) {
        this.businessClassify = businessClassify;
    }

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }
}
