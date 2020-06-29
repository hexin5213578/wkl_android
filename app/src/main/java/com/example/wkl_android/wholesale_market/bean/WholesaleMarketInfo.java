package com.example.wkl_android.wholesale_market.bean;

import java.util.ArrayList;

public class WholesaleMarketInfo {
    ClassifyInfo goodsClassifyVO ; //	Object
    ArrayList<MarketInfo> wholesaleMarketVOList;//	Array;
    int num;
    boolean select;


    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public ClassifyInfo getGoodsClassifyVO() {
        return goodsClassifyVO;
    }

    public void setGoodsClassifyVO(ClassifyInfo goodsClassifyVO) {
        this.goodsClassifyVO = goodsClassifyVO;
    }

    public ArrayList<MarketInfo> getWholesaleMarketVOList() {
        return wholesaleMarketVOList;
    }

    public void setWholesaleMarketVOList(ArrayList<MarketInfo> wholesaleMarketVOList) {
        this.wholesaleMarketVOList = wholesaleMarketVOList;
    }
}
