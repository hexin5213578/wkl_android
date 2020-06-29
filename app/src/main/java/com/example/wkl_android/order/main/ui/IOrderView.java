package com.example.wkl_android.order.main.ui;

import com.example.wkl_android.base.view.IBaseView;
import com.example.wkl_android.order.main.ui.bean.OrderInfo;
import com.example.wkl_android.utils.PayUtil;

import java.security.PublicKey;
import java.util.ArrayList;

/**
 * Created by szx
 * on 2020/1/2/002
 */
public interface IOrderView extends IBaseView {
    public void setData(ArrayList<OrderInfo> data);
    public  void cancelOrder();
}
