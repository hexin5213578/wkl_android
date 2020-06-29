package com.example.wkl_android.order.confirm.ui;

import com.example.wkl_android.base.view.IBaseView;
import com.example.wkl_android.order.confirm.model.SubmitInfo;

public interface IPayView extends IBaseView {
    public void setData(SubmitInfo data);
    public void payOrder(String orderid);
}
