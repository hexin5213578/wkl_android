package com.example.wkl_android.order.detail.ui;

import com.example.wkl_android.base.view.IBaseView;
import com.example.wkl_android.order.detail.model.OrderDetailInfo;

public interface IOrderDetailView extends IBaseView {
    public void setData(OrderDetailInfo detailInfo);
    public void cancelOrder();
}
