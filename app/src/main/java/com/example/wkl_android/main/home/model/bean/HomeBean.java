package com.example.wkl_android.main.home.model.bean;

import com.example.wkl_android.good.model.GoodsListBean;

import java.io.Serializable;

/**
 * Created by max
 * 组合首页板块内容, 可实现首页的特价..精选..商品等板块
 * on 2020/4/24/024
 */
public class HomeBean implements Serializable {
    public enum Type {GOODS, MODULE, BANNER}
    private Type type;
    private GoodsListBean goodsListBean;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public GoodsListBean getGoodsListBean() {
        return goodsListBean;
    }

    public void setGoodsListBean(GoodsListBean goodsListBean) {
        this.goodsListBean = goodsListBean;
    }
}
