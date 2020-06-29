package com.example.wkl_android.Event;

import com.example.wkl_android.shop_street.shop_detail.ui.bean.ShopImg;

import java.util.ArrayList;

public class ShopImgEvent {
    ArrayList<ShopImg> imgs ;
    public ShopImgEvent(ArrayList<ShopImg> imgs){
        this.imgs = imgs;
    }

    public ArrayList<ShopImg> getImgs() {
        return imgs;
    }
}
