package com.example.wkl_android.mycoupon.ui.fragment.presenter;

import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.good.model.GoodsListBean;
import com.example.wkl_android.http.HttpUtils;
import com.example.wkl_android.http.callback.impl.JsonCallBack;
import com.example.wkl_android.mycoupon.bean.CouponVo;
import com.example.wkl_android.mycoupon.ui.fragment.ui.IShopView;
import com.example.wkl_android.order.main.ui.bean.OrderInfo;
import com.example.wkl_android.utils.C;
import com.example.wkl_android.utils.ToastUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by szx
 * on 2020/1/14/014
 */
public class ShopPresenter extends BasePresenter<IShopView> {
    @Override
    public void cancel() {

    }


    public void search(int type) {

        HttpUtils.getInstance().doGet(C.MY_COUPON + type, Common.getToken(), this, new JsonCallBack() {
            @Override
            public void onSuccess(String json) throws Exception {

                if (!isViewAttached()) {
                    return;
                }

                JSONObject object = new JSONObject(json);
                ArrayList<CouponVo> data = new Gson().fromJson(object.optString("data"), new TypeToken<ArrayList<CouponVo>>() {
                }.getType());

                getView().setData(data);

            }

            @Override
            public void onFinished() {
                super.onFinished();
                if (!isViewAttached()) {
                    return;
                }
                getView().dismissLoading();
            }
        });


    }
}
