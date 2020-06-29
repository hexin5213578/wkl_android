package com.example.wkl_android.main.new_category;

import java.util.List;

public  class SecondTypeBean {


    /**
     * endRow : 3
     * hasNextPage : false
     * hasPreviousPage : false
     * isFirstPage : true
     * isLastPage : true
     * list : [{"classifyId":5,"classifyName":"菠萝","classifyPid":4,"classifyType":true},{"classifyId":6,"classifyName":"苹果","classifyPid":4,"classifyType":true},{"classifyId":40,"classifyImage":"","classifyName":"测试","classifyPid":4,"classifyType":false}]
     * navigateFirstPage : 1
     * navigateLastPage : 1
     * navigatePages : 8
     * navigatepageNums : [1]
     * nextPage : 0
     * pageNum : 1
     * pageSize : 30
     * pages : 1
     * prePage : 0
     * size : 3
     * startRow : 1
     * total : 3
     */

    private int endRow;
    private boolean hasNextPage;
    private boolean hasPreviousPage;
    private boolean isFirstPage;
    private boolean isLastPage;
    private int navigateFirstPage;
    private int navigateLastPage;
    private int navigatePages;
    private int nextPage;
    private int pageNum;
    private int pageSize;
    private int pages;
    private int prePage;
    private int size;
    private int startRow;
    private int total;
    private List<ListBean> list;
    private List<Integer> navigatepageNums;

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }

    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    public boolean isIsFirstPage() {
        return isFirstPage;
    }

    public void setIsFirstPage(boolean isFirstPage) {
        this.isFirstPage = isFirstPage;
    }

    public boolean isIsLastPage() {
        return isLastPage;
    }

    public void setIsLastPage(boolean isLastPage) {
        this.isLastPage = isLastPage;
    }

    public int getNavigateFirstPage() {
        return navigateFirstPage;
    }

    public void setNavigateFirstPage(int navigateFirstPage) {
        this.navigateFirstPage = navigateFirstPage;
    }

    public int getNavigateLastPage() {
        return navigateLastPage;
    }

    public void setNavigateLastPage(int navigateLastPage) {
        this.navigateLastPage = navigateLastPage;
    }

    public int getNavigatePages() {
        return navigatePages;
    }

    public void setNavigatePages(int navigatePages) {
        this.navigatePages = navigatePages;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public List<Integer> getNavigatepageNums() {
        return navigatepageNums;
    }

    public void setNavigatepageNums(List<Integer> navigatepageNums) {
        this.navigatepageNums = navigatepageNums;
    }

    public static class ListBean {
        /**
         * classifyId : 5
         * classifyName : 菠萝
         * classifyPid : 4
         * classifyType : true
         * classifyImage :
         */

        private int classifyId;
        private String classifyName;
        private int classifyPid;
        private boolean classifyType;
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

        public String getClassifyImage() {
            return classifyImage;
        }

        public void setClassifyImage(String classifyImage) {
            this.classifyImage = classifyImage;
        }
    }
}
