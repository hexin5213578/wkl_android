package com.example.wkl_android.errands.help_buy.bean;

/**
 * Created by szx
 * on 2020/3/2/002
 */
public class SelectBean {
    private String name;
    private String detail;
    private boolean isSelect;


    public SelectBean(String name, String detail, boolean isSelect) {
        this.isSelect = isSelect;
        this.name = name;
        this.detail = detail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
