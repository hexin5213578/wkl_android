package com.example.wkl_android.main.shop.add_shop.model.bean;

public class ResultShopMessage {


    /**
     * code : 0x10035
     * data : {"businessAuditDate":"2020-04-12 09:37:35","businessAuditDetail":"不够帅，拒绝通过","businessAuditStatus":3,"businessAuditUserId":1,"businessClassify":1,"businessClassifyDetail":{"classifyId":1,"classifyImage":"http://192.168.0.111/59/91/adwafawpng00b84dc4f6114ca2945307ebda9a521dadwafaw.png","classifyName":"test","classifyPid":0,"classifyType":true},"businessCompanyName":"test","businessId":"1000000","businessIdCardOne":"test","businessIdCardThree":"test","businessIdCardTwo":"test","businessLicense":"test","businessLinkman":"test","businessLinkmanPhoneNumber":"13783144247","businessMedicalCertificate":"test","businessName":"test","businessType":1,"businessTypeName":"公司店铺","createDate":"2020-04-10 21:45:17","userId":1}
     * mesg : 成功
     * status : true
     */

    private String code;
    private DataBean data;
    private String mesg;
    private boolean status;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMesg() {
        return mesg;
    }

    public void setMesg(String mesg) {
        this.mesg = mesg;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public static class DataBean {
        /**
         * businessAuditDate : 2020-04-12 09:37:35
         * businessAuditDetail : 不够帅，拒绝通过
         * businessAuditStatus : 3
         * businessAuditUserId : 1
         * businessClassify : 1
         * businessClassifyDetail : {"classifyId":1,"classifyImage":"http://192.168.0.111/59/91/adwafawpng00b84dc4f6114ca2945307ebda9a521dadwafaw.png","classifyName":"test","classifyPid":0,"classifyType":true}
         * businessCompanyName : test
         * businessId : 1000000
         * businessIdCardOne : test
         * businessIdCardThree : test
         * businessIdCardTwo : test
         * businessLicense : test
         * businessLinkman : test
         * businessLinkmanPhoneNumber : 13783144247
         * businessMedicalCertificate : test
         * businessName : test
         * businessType : 1
         * businessTypeName : 公司店铺
         * createDate : 2020-04-10 21:45:17
         * userId : 1
         */

        private String businessAuditDate;
        private String businessAuditDetail;
        private int businessAuditStatus;
        private int businessAuditUserId;
        private int businessClassify;
        private BusinessClassifyDetailBean businessClassifyDetail;
        private String businessCompanyName;
        private String businessId;
        private String businessIdCardOne;
        private String businessIdCardThree;
        private String businessIdCardTwo;
        private String businessLicense;
        private String businessLinkman;
        private String businessLinkmanPhoneNumber;
        private String businessMedicalCertificate;
        private String businessName;
        private int businessType;
        private String businessTypeName;
        private String createDate;
        private int userId;
        private String businessFoodCirculationPermit;
        public String getBusinessAuditDate() {
            return businessAuditDate;
        }

        public String getBusinessFoodCirculationPermit() {
            return businessFoodCirculationPermit;
        }

        public void setBusinessFoodCirculationPermit(String businessFoodCirculationPermit) {
            this.businessFoodCirculationPermit = businessFoodCirculationPermit;
        }

        public void setBusinessAuditDate(String businessAuditDate) {
            this.businessAuditDate = businessAuditDate;
        }

        public String getBusinessAuditDetail() {
            return businessAuditDetail;
        }

        public void setBusinessAuditDetail(String businessAuditDetail) {
            this.businessAuditDetail = businessAuditDetail;
        }

        public int getBusinessAuditStatus() {
            return businessAuditStatus;
        }

        public void setBusinessAuditStatus(int businessAuditStatus) {
            this.businessAuditStatus = businessAuditStatus;
        }

        public int getBusinessAuditUserId() {
            return businessAuditUserId;
        }

        public void setBusinessAuditUserId(int businessAuditUserId) {
            this.businessAuditUserId = businessAuditUserId;
        }

        public int getBusinessClassify() {
            return businessClassify;
        }

        public void setBusinessClassify(int businessClassify) {
            this.businessClassify = businessClassify;
        }

        public BusinessClassifyDetailBean getBusinessClassifyDetail() {
            return businessClassifyDetail;
        }

        public void setBusinessClassifyDetail(BusinessClassifyDetailBean businessClassifyDetail) {
            this.businessClassifyDetail = businessClassifyDetail;
        }

        public String getBusinessCompanyName() {
            return businessCompanyName;
        }

        public void setBusinessCompanyName(String businessCompanyName) {
            this.businessCompanyName = businessCompanyName;
        }

        public String getBusinessId() {
            return businessId;
        }

        public void setBusinessId(String businessId) {
            this.businessId = businessId;
        }

        public String getBusinessIdCardOne() {
            return businessIdCardOne;
        }

        public void setBusinessIdCardOne(String businessIdCardOne) {
            this.businessIdCardOne = businessIdCardOne;
        }

        public String getBusinessIdCardThree() {
            return businessIdCardThree;
        }

        public void setBusinessIdCardThree(String businessIdCardThree) {
            this.businessIdCardThree = businessIdCardThree;
        }

        public String getBusinessIdCardTwo() {
            return businessIdCardTwo;
        }

        public void setBusinessIdCardTwo(String businessIdCardTwo) {
            this.businessIdCardTwo = businessIdCardTwo;
        }

        public String getBusinessLicense() {
            return businessLicense;
        }

        public void setBusinessLicense(String businessLicense) {
            this.businessLicense = businessLicense;
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

        public String getBusinessMedicalCertificate() {
            return businessMedicalCertificate;
        }

        public void setBusinessMedicalCertificate(String businessMedicalCertificate) {
            this.businessMedicalCertificate = businessMedicalCertificate;
        }

        public String getBusinessName() {
            return businessName;
        }

        public void setBusinessName(String businessName) {
            this.businessName = businessName;
        }

        public int getBusinessType() {
            return businessType;
        }

        public void setBusinessType(int businessType) {
            this.businessType = businessType;
        }

        public String getBusinessTypeName() {
            return businessTypeName;
        }

        public void setBusinessTypeName(String businessTypeName) {
            this.businessTypeName = businessTypeName;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public static class BusinessClassifyDetailBean {
            /**
             * classifyId : 1
             * classifyImage : http://192.168.0.111/59/91/adwafawpng00b84dc4f6114ca2945307ebda9a521dadwafaw.png
             * classifyName : test
             * classifyPid : 0
             * classifyType : true
             */

            private int classifyId;
            private String classifyImage;
            private String classifyName;
            private int classifyPid;
            private boolean classifyType;

            public int getClassifyId() {
                return classifyId;
            }

            public void setClassifyId(int classifyId) {
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

            public int getClassifyPid() {
                return classifyPid;
            }

            public void setClassifyPid(int classifyPid) {
                this.classifyPid = classifyPid;
            }

            public boolean isClassifyType() {
                return classifyType;
            }

            public void setClassifyType(boolean classifyType) {
                this.classifyType = classifyType;
            }
        }
    }
}
