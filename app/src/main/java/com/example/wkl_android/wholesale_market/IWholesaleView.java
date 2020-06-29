package com.example.wkl_android.wholesale_market;

import com.example.wkl_android.base.view.IBaseView;
import com.example.wkl_android.good.model.GoodsListBean;
import com.example.wkl_android.wholesale_market.bean.WholesaleMarketInfo;

import java.util.ArrayList;

/**
 * Created by szx
 * on 2019/12/30/030
 */
public interface IWholesaleView extends IBaseView {
    public void showData(ArrayList<WholesaleMarketInfo> bean);
 }
