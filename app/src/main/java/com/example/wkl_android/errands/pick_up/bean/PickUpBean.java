package com.example.wkl_android.errands.pick_up.bean;

/**
 * Created by szx
 * on 2020/3/2/002
 */
public class PickUpBean {
    private String name;
    private int imgResource;
    private boolean isSelect;

    public PickUpBean(String name, int imgResource) {
        this.name = name;
        this.imgResource = imgResource;
    }

    public String getName() {
        return name;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImgResource() {
        return imgResource;
    }

    public void setImgResource(int imgResource) {
        this.imgResource = imgResource;
    }
}
