package com.example.wkl_android.searchshop;

import com.example.wkl_android.base.view.IBaseView;
import com.example.wkl_android.good.model.GoodsListBean;
import com.example.wkl_android.searchshop.bean.ShopVo;
import com.example.wkl_android.wholesale_market.bean.MarketInfo;

import java.util.ArrayList;

/**
 * Created by szx
 * on 2019/12/30/030
 */
public interface ISearchShopView extends IBaseView {
    public void showData(ArrayList<ShopVo> bean);
 }
