package com.example.wkl_android.search;

import com.example.wkl_android.base.view.IBaseView;
import com.example.wkl_android.good.model.GoodsListBean;
import com.example.wkl_android.searchshop.bean.ShopVo;

/**
 * Created by szx
 * on 2019/12/30/030
 */
public interface ISearchView extends IBaseView {
    public void showData(GoodsListBean bean );
 }
