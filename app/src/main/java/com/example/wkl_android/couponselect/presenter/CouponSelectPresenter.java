package com.example.wkl_android.couponselect.presenter;

import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.couponselect.ICouponSelectView;
import com.example.wkl_android.good.model.GoodsListBean;
import com.example.wkl_android.http.HttpUtils;
import com.example.wkl_android.http.callback.impl.JsonCallBack;
import com.example.wkl_android.login.login.model.LoginModel;
import com.example.wkl_android.utils.C;
import com.example.wkl_android.utils.ToastUtil;
import com.google.gson.Gson;

/**
 * Created by szx
 * on 2019/12/30/030
 */
public class CouponSelectPresenter extends BasePresenter<ICouponSelectView> {

    private LoginModel model;

    @Override
    public void cancel() {
        model.cancel();
    }

}
