package com.example.wkl_android.main.new_category;

import java.util.List;

public class TypeBean {

    /**
     * childrenList : [{"childrenList":[{"classifyId":8,"classifyName":"西藏苹果","classifyOrder":3,"classifyPid":6,"classifyType":true,"createDate":"2020-03-05T09:52:41","createUserId":6,"reviseDate":"2020-03-05T09:52:41","reviseUserId":6}],"classifyId":6,"classifyName":"苹果","classifyOrder":3,"classifyPid":4,"classifyType":true,"createDate":"2020-03-05T09:51:16","createUserId":6,"reviseDate":"2020-03-05T09:51:16","reviseUserId":6},{"childrenList":[],"classifyId":5,"classifyName":"菠萝","classifyOrder":2,"classifyPid":4,"classifyType":true,"createDate":"2020-03-05T09:50:57","createUserId":6,"reviseDate":"2020-03-05T09:50:57","reviseUserId":6},{"childrenList":[],"classifyId":40,"classifyImage":"","classifyName":"测试","classifyOrder":0,"classifyPid":4,"classifyType":false,"createDate":"2020-03-19T22:47:40","createUserId":1,"reviseDate":"2020-03-19T22:47:40","reviseUserId":1}]
     * classifyId : 4
     * classifyImage : http://39.100.87.173/31/96/img-5c64332732ccc4d3f63d90d2ef644e27jpgimg-5c64332732ccc4d3f63d90d2ef644e27jpgd36ca9d85def4086843274dc71f9060aimg-5c64332732ccc4d3f63d90d2ef644e27jpgimg-5c64332732ccc4d3f63d90d2ef644e27.jpg
     * classifyName : 水果
     * classifyOrder : 3
     * classifyPid : 0
     * classifyType : true
     * createDate : 2020-01-13T15:12:03
     * createUserId : 1
     * reviseDate : 2020-03-21T19:21:24
     * reviseUserId : 1
     */

    private int classifyId;
    private String classifyImage;
    private String classifyName;
    private int classifyOrder;
    private int classifyPid;
    private boolean classifyType;
    private String createDate;
    private int createUserId;
    private String reviseDate;
    private int reviseUserId;
    private List<ChildrenListBeanX> childrenList;

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

    public int getClassifyOrder() {
        return classifyOrder;
    }

    public void setClassifyOrder(int classifyOrder) {
        this.classifyOrder = classifyOrder;
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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public int getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(int createUserId) {
        this.createUserId = createUserId;
    }

    public String getReviseDate() {
        return reviseDate;
    }

    public void setReviseDate(String reviseDate) {
        this.reviseDate = reviseDate;
    }

    public int getReviseUserId() {
        return reviseUserId;
    }

    public void setReviseUserId(int reviseUserId) {
        this.reviseUserId = reviseUserId;
    }

    public List<ChildrenListBeanX> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List<ChildrenListBeanX> childrenList) {
        this.childrenList = childrenList;
    }

    public static class ChildrenListBeanX {
        /**
         * childrenList : [{"classifyId":8,"classifyName":"西藏苹果","classifyOrder":3,"classifyPid":6,"classifyType":true,"createDate":"2020-03-05T09:52:41","createUserId":6,"reviseDate":"2020-03-05T09:52:41","reviseUserId":6}]
         * classifyId : 6
         * classifyName : 苹果
         * classifyOrder : 3
         * classifyPid : 4
         * classifyType : true
         * createDate : 2020-03-05T09:51:16
         * createUserId : 6
         * reviseDate : 2020-03-05T09:51:16
         * reviseUserId : 6
         * classifyImage :
         */

        private int classifyId;
        private String classifyName;
        private int classifyOrder;
        private int classifyPid;
        private boolean classifyType;
        private String createDate;
        private int createUserId;
        private String reviseDate;
        private int reviseUserId;
        private String classifyImage;

        public int getClassifyId() {
            return classifyId;
        }

        public void setClassifyId(int classifyId) {
            this.classifyId = classifyId;
        }

        public String getClassifyName() {
            return classifyName;
        }

        public void setClassifyName(String classifyName) {
            this.classifyName = classifyName;
        }

        public int getClassifyOrder() {
            return classifyOrder;
        }

        public void setClassifyOrder(int classifyOrder) {
            this.classifyOrder = classifyOrder;
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

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public int getCreateUserId() {
            return createUserId;
        }

        public void setCreateUserId(int createUserId) {
            this.createUserId = createUserId;
        }

        public String getReviseDate() {
            return reviseDate;
        }

        public void setReviseDate(String reviseDate) {
            this.reviseDate = reviseDate;
        }

        public int getReviseUserId() {
            return reviseUserId;
        }

        public void setReviseUserId(int reviseUserId) {
            this.reviseUserId = reviseUserId;
        }

        public String getClassifyImage() {
            return classifyImage;
        }

        public void setClassifyImage(String classifyImage) {
            this.classifyImage = classifyImage;
        }

    }
}
