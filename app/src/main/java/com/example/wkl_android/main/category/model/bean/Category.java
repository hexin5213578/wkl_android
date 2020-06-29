package com.example.wkl_android.main.category.model.bean;

import java.util.List;

/**
 * Created by szx
 * on 2020/1/13/013
 */
public class Category {

    /**
     * classifyId : 1
     * classifyName : 生活用纸
     * classifyPid : 0
     * classifyImage :
     * classifyOrder : 0
     * classifyType : true
     * childrenList : [{"classifyId":2,"classifyName":"抽纸","classifyPid":1,"classifyImage":"","classifyOrder":0,"classifyType":true,"childrenList":[{"classifyId":4,"classifyName":"丝柔","classifyPid":2,"classifyImage":null,"classifyOrder":0,"classifyType":true,"childrenList":null},{"classifyId":5,"classifyName":"银鸽","classifyPid":2,"classifyImage":null,"classifyOrder":0,"classifyType":true,"childrenList":null}]},{"classifyId":3,"classifyName":"纸巾","classifyPid":1,"classifyImage":"","classifyOrder":0,"classifyType":true,"childrenList":[{"classifyId":6,"classifyName":"银鸽1","classifyPid":3,"classifyImage":null,"classifyOrder":0,"classifyType":true,"childrenList":null},{"classifyId":7,"classifyName":"丝柔2","classifyPid":3,"classifyImage":null,"classifyOrder":0,"classifyType":true,"childrenList":null}]}]
     */

    private String classifyId;
    private String classifyName;
    private String classifyPid;
    private String classifyImage;
    private String classifyOrder;
    private boolean classifyType;
    private List<ChildrenListBeanX> childrenList;

    public String getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(String classifyId) {
        this.classifyId = classifyId;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    public String getClassifyPid() {
        return classifyPid;
    }

    public void setClassifyPid(String classifyPid) {
        this.classifyPid = classifyPid;
    }

    public String getClassifyImage() {
        return classifyImage;
    }

    public void setClassifyImage(String classifyImage) {
        this.classifyImage = classifyImage;
    }

    public String getClassifyOrder() {
        return classifyOrder;
    }

    public void setClassifyOrder(String classifyOrder) {
        this.classifyOrder = classifyOrder;
    }

    public boolean isClassifyType() {
        return classifyType;
    }

    public void setClassifyType(boolean classifyType) {
        this.classifyType = classifyType;
    }

    public List<ChildrenListBeanX> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List<ChildrenListBeanX> childrenList) {
        this.childrenList = childrenList;
    }

    public static class ChildrenListBeanX {
        /**
         * classifyId : 2
         * classifyName : 抽纸
         * classifyPid : 1
         * classifyImage :
         * classifyOrder : 0
         * classifyType : true
         * childrenList : [{"classifyId":4,"classifyName":"丝柔","classifyPid":2,"classifyImage":null,"classifyOrder":0,"classifyType":true,"childrenList":null},{"classifyId":5,"classifyName":"银鸽","classifyPid":2,"classifyImage":null,"classifyOrder":0,"classifyType":true,"childrenList":null}]
         */

        private String classifyId;
        private String classifyName;
        private String classifyPid;
        private String classifyImage;
        private String classifyOrder;
        private boolean classifyType;
        private List<ChildrenListBean> childrenList;

        public String getClassifyId() {
            return classifyId;
        }

        public void setClassifyId(String classifyId) {
            this.classifyId = classifyId;
        }

        public String getClassifyName() {
            return classifyName;
        }

        public void setClassifyName(String classifyName) {
            this.classifyName = classifyName;
        }

        public String getClassifyPid() {
            return classifyPid;
        }

        public void setClassifyPid(String classifyPid) {
            this.classifyPid = classifyPid;
        }

        public String getClassifyImage() {
            return classifyImage;
        }

        public void setClassifyImage(String classifyImage) {
            this.classifyImage = classifyImage;
        }

        public String getClassifyOrder() {
            return classifyOrder;
        }

        public void setClassifyOrder(String classifyOrder) {
            this.classifyOrder = classifyOrder;
        }

        public boolean isClassifyType() {
            return classifyType;
        }

        public void setClassifyType(boolean classifyType) {
            this.classifyType = classifyType;
        }

        public List<ChildrenListBean> getChildrenList() {
            return childrenList;
        }

        public void setChildrenList(List<ChildrenListBean> childrenList) {
            this.childrenList = childrenList;
        }

        public static class ChildrenListBean {
            /**
             * classifyId : 4
             * classifyName : 丝柔
             * classifyPid : 2
             * classifyImage : null
             * classifyOrder : 0
             * classifyType : true
             * childrenList : null
             */

            private String classifyId;
            private String classifyName;
            private String classifyPid;
            private String classifyImage;
            private String classifyOrder;
            private boolean classifyType;
            private Object childrenList;

            public String getClassifyId() {
                return classifyId;
            }

            public void setClassifyId(String classifyId) {
                this.classifyId = classifyId;
            }

            public String getClassifyName() {
                return classifyName;
            }

            public void setClassifyName(String classifyName) {
                this.classifyName = classifyName;
            }

            public String getClassifyPid() {
                return classifyPid;
            }

            public void setClassifyPid(String classifyPid) {
                this.classifyPid = classifyPid;
            }

            public String getClassifyImage() {
                return classifyImage;
            }

            public void setClassifyImage(String classifyImage) {
                this.classifyImage = classifyImage;
            }

            public String getClassifyOrder() {
                return classifyOrder;
            }

            public void setClassifyOrder(String classifyOrder) {
                this.classifyOrder = classifyOrder;
            }

            public boolean isClassifyType() {
                return classifyType;
            }

            public void setClassifyType(boolean classifyType) {
                this.classifyType = classifyType;
            }

            public Object getChildrenList() {
                return childrenList;
            }

            public void setChildrenList(Object childrenList) {
                this.childrenList = childrenList;
            }
        }
    }
}
