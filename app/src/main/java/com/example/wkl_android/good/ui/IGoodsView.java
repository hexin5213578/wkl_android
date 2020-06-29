package com.example.wkl_android.good.ui;

import com.example.wkl_android.base.view.IBaseView;
import com.example.wkl_android.good.model.GoodsBean;

import java.util.List;

/**
 * Created by szx
 * on 2020/1/18/018
 */
public interface IGoodsView extends IBaseView {
    void handleGoodsDetail(GoodsBean goodsBean);
    void collectSuccess();
}
