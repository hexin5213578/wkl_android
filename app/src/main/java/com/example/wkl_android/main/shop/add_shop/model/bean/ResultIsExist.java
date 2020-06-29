package com.example.wkl_android.main.shop.add_shop.model.bean;

public class ResultIsExist {

    /**
     * code : 0x10035
     * data : {"businessApplicationIsExist":true,"businessIsExist":false}
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
         * businessApplicationIsExist : true
         * businessIsExist : false
         */

        private boolean businessApplicationIsExist;
        private boolean businessIsExist;

        public boolean isBusinessApplicationIsExist() {
            return businessApplicationIsExist;
        }

        public void setBusinessApplicationIsExist(boolean businessApplicationIsExist) {
            this.businessApplicationIsExist = businessApplicationIsExist;
        }

        public boolean isBusinessIsExist() {
            return businessIsExist;
        }

        public void setBusinessIsExist(boolean businessIsExist) {
            this.businessIsExist = businessIsExist;
        }
    }
}
