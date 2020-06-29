package com.example.wkl_android.mycoupon.ui.fragment.ui;

import com.example.wkl_android.base.view.IBaseView;
import com.example.wkl_android.couponselect.bean.CouponInfo;
import com.example.wkl_android.mycoupon.bean.CouponVo;

import java.util.ArrayList;

/**
 * Created by szx
 * on 2020/1/14/014
 */
public interface IShopView extends IBaseView {
    public void setData(ArrayList<CouponVo> data);
}
