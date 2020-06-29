package com.example.wkl_android.orderpay;

import com.example.wkl_android.base.view.IBaseView;
import com.example.wkl_android.good.model.GoodsListBean;
import com.example.wkl_android.orderpay.bean.WXPreOrderInfo;

/**
 * Created by szx
 * on 2019/12/30/030
 */
public interface IOrderPayView extends IBaseView {
    public void wxPay(WXPreOrderInfo info);
    public void alipay(String info);
 }
