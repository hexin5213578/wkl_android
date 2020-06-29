package com.example.wkl_android.main.shop.add_shop.model.bean;

/**
 * Created by szx
 * on 2020/1/9/009
 */
public class AddShop {
    /**
     * businessAddress : string
     * businessClassify : string
     * businessDepositBank : string
     * businessGrade : string
     * businessId : 0
     * businessImageId : 0
     * businessImageType : true
     * businessName : string
     * businessPaymentCode : string
     * businessPhone : string
     * businessType : string
     * businessVerify : 0
     * companyAddress : string
     * companyEmail : string
     * companyIntroduce : string
     * companyLocation : string
     * companyName : string
     * companyPhone : string
     * companyUrl : string
     * createDate : 2020-01-09T09:24:48.303Z
     * createUserId : 0
     * deleteFlag : true
     * invoiceTitle : string
     * linkman : string
     * linkmanPhone : string
     * mallBusinessNum : string
     * postcode : string
     * reviseDate : 2020-01-09T09:24:48.303Z
     * reviseUserId : 0
     * taxpayerCode : string
     * userId : 0
     */

    private String businessName;//店铺名称1(1必填,其他选填)
    private String businessPhone;//商家电话
    private String mallBusinessNum;//商家工商号1
    private String businessType;//店铺类型
    private String businessCategory;//店铺分类1
    private String businessClassify;//经营类目1
    private String companyName;//公司名称 1
    private String companyLocation;//公司所在地
    private String companyAddress;//公司地址
    private String postcode;//邮编
    private String companyPhone;//公司电话1
    private String companyUrl;//公司网址
    private String companyIntroduce;//公司介绍
    private String companyEmail;//公司邮箱
    private String linkman;//联系人1
    private String linkmanPhone;//联系电话1
    private String taxpayerCode;//纳税人识别号1
    private String invoiceTitle;//发票抬头1
    private String businessGrade;//店铺等级1
    private String businessDepositBank;//商家开户银行1
    private String businessPaymentCode;//商家银行账号1
    private String businessImageUrl;//商家图片路径1
    private String businessImageName;//上传图片返回的图片名称1

    public String getBusinessCategory() {
        return businessCategory;
    }

    public void setBusinessCategory(String businessCategory) {
        this.businessCategory = businessCategory;
    }

    public String getBusinessImageUrl() {
        return businessImageUrl;
    }

    public void setBusinessImageUrl(String businessImageUrl) {
        this.businessImageUrl = businessImageUrl;
    }

    public String getBusinessImageName() {
        return businessImageName;
    }

    public void setBusinessImageName(String businessImageName) {
        this.businessImageName = businessImageName;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }


    public String getBusinessPhone() {
        return businessPhone;
    }

    public void setBusinessPhone(String businessPhone) {
        this.businessPhone = businessPhone;
    }

    public String getMallBusinessNum() {
        return mallBusinessNum;
    }

    public void setMallBusinessNum(String mallBusinessNum) {
        this.mallBusinessNum = mallBusinessNum;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getBusinessClassify() {
        return businessClassify;
    }

    public void setBusinessClassify(String businessClassify) {
        this.businessClassify = businessClassify;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyLocation() {
        return companyLocation;
    }

    public void setCompanyLocation(String companyLocation) {
        this.companyLocation = companyLocation;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    public String getCompanyUrl() {
        return companyUrl;
    }

    public void setCompanyUrl(String companyUrl) {
        this.companyUrl = companyUrl;
    }

    public String getCompanyIntroduce() {
        return companyIntroduce;
    }

    public void setCompanyIntroduce(String companyIntroduce) {
        this.companyIntroduce = companyIntroduce;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getLinkmanPhone() {
        return linkmanPhone;
    }

    public void setLinkmanPhone(String linkmanPhone) {
        this.linkmanPhone = linkmanPhone;
    }

    public String getTaxpayerCode() {
        return taxpayerCode;
    }

    public void setTaxpayerCode(String taxpayerCode) {
        this.taxpayerCode = taxpayerCode;
    }

    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }

    public String getBusinessGrade() {
        return businessGrade;
    }

    public void setBusinessGrade(String businessGrade) {
        this.businessGrade = businessGrade;
    }

    public String getBusinessDepositBank() {
        return businessDepositBank;
    }

    public void setBusinessDepositBank(String businessDepositBank) {
        this.businessDepositBank = businessDepositBank;
    }

    public String getBusinessPaymentCode() {
        return businessPaymentCode;
    }

    public void setBusinessPaymentCode(String businessPaymentCode) {
        this.businessPaymentCode = businessPaymentCode;
    }

}
