package com.example.wkl_android.WholesaleMarketDetail.bean;

import java.util.ArrayList;

public class MarketDetailVo {

    ArrayList<BannerInfo> wholesaleMarketSlideshowVOList; //	Array
    ArrayList<MarketPointVO> goodsClassifyVOList ;//	Array''

    ArrayList<MarketAd> wholesaleMarketAdvertisingVOList;//	Array


    public ArrayList<MarketAd> getWholesaleMarketAdvertisingVOList() {
        return wholesaleMarketAdvertisingVOList;
    }

    public void setWholesaleMarketAdvertisingVOList(ArrayList<MarketAd> wholesaleMarketAdvertisingVOList) {
        this.wholesaleMarketAdvertisingVOList = wholesaleMarketAdvertisingVOList;
    }

    public ArrayList<MarketPointVO> getGoodsClassifyVOList() {
        return goodsClassifyVOList;
    }

    public void setGoodsClassifyVOList(ArrayList<MarketPointVO> goodsClassifyVOList) {
        this.goodsClassifyVOList = goodsClassifyVOList;
    }

    public ArrayList<BannerInfo> getWholesaleMarketSlideshowVOList() {
        return wholesaleMarketSlideshowVOList;
    }

    public void setWholesaleMarketSlideshowVOList(ArrayList<BannerInfo> wholesaleMarketSlideshowVOList) {
        this.wholesaleMarketSlideshowVOList = wholesaleMarketSlideshowVOList;
    }
}
