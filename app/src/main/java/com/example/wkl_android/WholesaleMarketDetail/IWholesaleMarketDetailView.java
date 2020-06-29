package com.example.wkl_android.WholesaleMarketDetail;

import com.example.wkl_android.WholesaleMarketDetail.bean.MarketDetailVo;
import com.example.wkl_android.base.view.IBaseView;
import com.example.wkl_android.good.model.GoodsListBean;

/**
 * Created by szx
 * on 2019/12/30/030
 */
public interface IWholesaleMarketDetailView extends IBaseView {
    public void showData(MarketDetailVo bean);
    public void setGoods(GoodsListBean bean);
 }
