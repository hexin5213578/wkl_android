package com.example.wkl_android.main.shop.main.bean;

/**
 * Created by szx
 * on 2020/1/14/014
 */
public class ShopItem {
    private int id;
    private String text;
    private int imgResource;

    public ShopItem(int id,String text, int imgResource) {
        this.id= id;
        this.text = text;
        this.imgResource = imgResource;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getImgResource() {
        return imgResource;
    }

    public void setImgResource(int imgResource) {
        this.imgResource = imgResource;
    }
}
