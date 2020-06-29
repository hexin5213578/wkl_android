package com.example.wkl_android.main.home.ui;

import com.example.wkl_android.base.view.IBaseView;
import com.example.wkl_android.good.model.GoodsListBean;

import java.util.List;

/**
 * 首页板块view
 */
public interface IHomeBlankView extends IBaseView {
    void handleGoodsList(boolean isTop, GoodsListBean bean, List<GoodsListBean.GoodsSlideshowVOList> bannerBeans, List<GoodsListBean.GoodsPlateVOList> menuBeans);
}
