package com.example.wkl_android.orderpaycheck;

import com.example.wkl_android.base.view.IBaseView;
import com.example.wkl_android.good.model.GoodsListBean;
import com.example.wkl_android.orderpaycheck.bean.OrderCheckGroup;
import com.example.wkl_android.orderpaycheck.bean.OrderCheckVo;

import java.util.ArrayList;

/**
 * Created by szx
 * on 2019/12/30/030
 */
public interface IOrderCheckView extends IBaseView {
  public void setData(OrderCheckGroup data);
  }
