package com.example.wkl_android.paysuccess;

import com.example.wkl_android.base.view.IBaseView;
import com.example.wkl_android.good.model.GoodsListBean;

/**
 * Created by szx
 * on 2019/12/30/030
 */
public interface IPaySuccessView extends IBaseView {
    public void showData(GoodsListBean bean);
 }
