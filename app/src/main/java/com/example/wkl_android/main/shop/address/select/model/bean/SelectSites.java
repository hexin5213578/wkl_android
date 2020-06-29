package com.example.wkl_android.main.shop.address.select.model.bean;

/**
 * Created by szx
 * on 2020/1/6/006
 */
public class SelectSites {

    /**
     * id : 0
     * name : string
     * pid : 0
     */

    private String id;
    private String name;
    private String pid;
    boolean select ;

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
}
