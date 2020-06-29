package com.example.wkl_android.order.confirm;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wkl_android.Event.CouponEvent;
import com.example.wkl_android.Event.Event;
import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.couponselect.bean.CouponInfo;
import com.example.wkl_android.couponselect.ui.activity.CouponSelectActivity;
import com.example.wkl_android.good.model.GoodsBean;
import com.example.wkl_android.http.HttpUtils;
import com.example.wkl_android.http.callback.impl.JsonCallBack;
import com.example.wkl_android.main.shop.address.main.model.bean.Address;
import com.example.wkl_android.main.shop.address.main.ui.activity.AddressActivity;
import com.example.wkl_android.main.shopping_cart.bean.ShopListBean;
import com.example.wkl_android.order.ConfirmOrderAdapter;
import com.example.wkl_android.order.confirm.model.ConfirmGoods;
import com.example.wkl_android.order.confirm.model.ConfirmOrder;
import com.example.wkl_android.order.confirm.model.ConfirmShopInfo;
import com.example.wkl_android.order.confirm.model.CouponSerializable;
import com.example.wkl_android.order.confirm.model.CreateOrder;
import com.example.wkl_android.order.confirm.model.RequestSubmitOrder;
import com.example.wkl_android.order.confirm.model.SubmitGoodsInfo;
import com.example.wkl_android.order.confirm.model.SubmitInfo;
import com.example.wkl_android.order.confirm.model.SubmitShopInfo;
import com.example.wkl_android.order.confirm.popup.PayTypePopup;
import com.example.wkl_android.order.confirm.presenter.PayPresenter;
import com.example.wkl_android.order.confirm.ui.IPayView;
import com.example.wkl_android.orderpay.ui.activity.OrderPayActivity;
import com.example.wkl_android.utils.C;
import com.example.wkl_android.utils.StringUtil;
import com.example.wkl_android.utils.ToastUtil;
import com.google.gson.Gson;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 确认订单
 * 从商品web页面点击支付跳转
 */
public class ConfirmOrderActivity extends BaseActivity<IPayView, PayPresenter>
        implements IPayView, View.OnClickListener {
    @BindView(R.id.ivLeft)
    View back;
    @BindView(R.id.tvTitle)
    TextView title;
    @BindView(R.id.llAddress)
    View llAddress;
    @BindView(R.id.rl_empty_address)
    RelativeLayout rl_empty_address;

    @BindView(R.id.tvTips)
    TextView tvTips;

    @BindColor(R.color.theme)
    int font_theme;
    @BindView(R.id.address_name)
    TextView tvAddressName;
    @BindView(R.id.address_phone)
    TextView tvAddressPhone;
    @BindView(R.id.address_detail)
    TextView tvAddressDetail;
    @BindView(R.id.address_area)
    TextView addressArea;

    @BindView(R.id.price)
    TextView tvAllPrice;
    @BindView(R.id.list)
    RecyclerView listView;

    @BindView(R.id.rl_coupon)
    RelativeLayout rl_coupon;

    @BindView(R.id.tv_coupon)
    TextView tv_coupon;


    RequestSubmitOrder requestSubmitOrder = new RequestSubmitOrder();

    ConfirmOrderAdapter adapter;

    ConfirmOrder confirmOrder;
    boolean first = true;
    SubmitInfo mData;

    GoodsBean goods;
    String skuID;
    String mCount;

    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;


    @Override
    public int getLayoutRes() {
        return R.layout.activity_confirm_order;
    }

    public static void toThisActivity(Context context, GoodsBean bean, String sku, String count) {
        ArrayList<ShopListBean.DataBean> beans = new ArrayList<>();
        ShopListBean.DataBean e = new ShopListBean.DataBean();
        GoodsBean.DataBean data = bean.getData();
        e.setBusinessId(data.getBusinessId());
        e.setBusinessName(data.getBusinessName());
        ArrayList<ShopListBean.DataBean.GoodsShopCarVOSetBean> goodsShopCarVOSet = new ArrayList<>();
        ShopListBean.DataBean.GoodsShopCarVOSetBean goods = new ShopListBean.DataBean.GoodsShopCarVOSetBean();
        goods.setBusinessId(data.getBusinessId());
        goods.setBusinessName(data.getBusinessName());
        goods.setSkuImage(data.getProductImage());
        goods.setSkuCount(1);
        //   goods.setSkuId(data.getGoodsStaticPreviewSkuStandardSetVOList());
        // goods.setSkuPrice(data.getp);
        goodsShopCarVOSet.add(goods);
        e.setGoodsShopCarVOSet(goodsShopCarVOSet);
        beans.add(e);

        Intent intent = new Intent(context, ConfirmOrderActivity.class);
        intent.putExtra("goods", bean);
        intent.putExtra("sku", sku);
        intent.putExtra("count", count);
        context.startActivity(intent);
    }

    public static void toThisActivity(Context context, ArrayList<ShopListBean.DataBean> bean) {
        Intent intent = new Intent(context, ConfirmOrderActivity.class);
        intent.putExtra("list", bean);
        context.startActivity(intent);
    }

    @Override
    protected PayPresenter createPresenter() {
        return new PayPresenter();
    }

    ArrayList<ShopListBean.DataBean> list;

    @Override
    protected void initViews() {
        setTitleBarWithe();

//        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) rlTitle.getLayoutParams();
//        params.topMargin = getStatusBarHeight(this);

        back.setOnClickListener(this);
        title.setText("确认订单");
        llAddress.setOnClickListener(this);
        rl_empty_address.setOnClickListener(this);
        initTips();

        llAddress.setVisibility(View.GONE);
        rl_empty_address.setVisibility(View.GONE);

        if (getIntent().hasExtra("goods")) {
            goods = (GoodsBean) getIntent().getSerializableExtra("goods");
            skuID = getIntent().getStringExtra("sku");
            mCount = getIntent().getStringExtra("count");
        } else {
            list = (ArrayList<ShopListBean.DataBean>) getIntent().getSerializableExtra("list");
        }
        getData();
    }




    //确认订单
    private void getData() {

        confirmOrder = new ConfirmOrder();
        ArrayList<ConfirmShopInfo> shop = new ArrayList<>();

        ConfirmShopInfo shopInfo;
        ArrayList<ConfirmGoods> goodsList = new ArrayList<>();

        if (mData == null) {
            if (list != null && list.size() > 0) {
                for (ShopListBean.DataBean item : list) {
                    shopInfo = new ConfirmShopInfo();
                    shopInfo.setBusinessId(item.getBusinessId());
                    for (ShopListBean.DataBean.GoodsShopCarVOSetBean goods : item.getGoodsShopCarVOSet()) {
                        ConfirmGoods confirmGoods = new ConfirmGoods();
                        confirmGoods.setCount(goods.getSkuCount());
                        confirmGoods.setSkuId(goods.getSkuId());
                        confirmGoods.setSkuType("1");
                        confirmGoods.setOrderDiscountId(goods.getOrderDiscountId());
                        goodsList.add(confirmGoods);
                    }
                    shopInfo.setOrderSubmitMasterParamList(goodsList);
                    shop.add(shopInfo);
                }
            } else {
                shopInfo = new ConfirmShopInfo();
                shopInfo.setBusinessId(goods.getData().getBusinessId());
                ConfirmGoods confirmGoods = new ConfirmGoods();
                confirmGoods.setCount(Integer.valueOf(mCount));
                confirmGoods.setSkuType("1");
                confirmGoods.setSkuId(skuID);
                goodsList.add(confirmGoods);

                shopInfo.setOrderSubmitMasterParamList(goodsList);
                shop.add(shopInfo);

            }
        } else {
            for (SubmitShopInfo info : mData.getOrderSubmitMasterVOList()) {
                shopInfo = new ConfirmShopInfo();
                shopInfo.setBusinessId(info.getBusinessId());
                if (info.getUserCoupon() != null && TextUtils.isEmpty(info.getUserCoupon().getUserCouponId())) {
                    shopInfo.setBusinessDiscountId(info.getUserCoupon().getUserCouponId());
                }

                List<CreateOrder.OrderMasterParamListBean.OrderSlaveParamListBean> goods = new ArrayList<>();
                for (SubmitGoodsInfo goodsInfo : info.getOrderSubmitSlaveVOList()) {

                    ConfirmGoods confirmGoods = new ConfirmGoods();

                    confirmGoods.setSkuId(goodsInfo.getGoodsSkuVO().getSkuId());
                    confirmGoods.setSkuType("1");
                    confirmGoods.setCount(goodsInfo.getCount());
                    if (goodsInfo.getUserCoupon() != null && !TextUtils.isEmpty(goodsInfo.getUserCoupon().getUserCouponId()))
                        confirmGoods.setOrderDiscountId(goodsInfo.getUserCoupon().getUserCouponId());  //优惠券
                    goodsList.add(confirmGoods);
                }

                shopInfo.setOrderSubmitMasterParamList(goodsList);
                shop.add(shopInfo);
            }
        }
        confirmOrder.setOrderSubmitParamList(shop);
        if (mData != null) {
            confirmOrder.setUserAddressId(mData.getUserAddressVO().getAddressId());
        } else if (selectAddress != null) {
            confirmOrder.setUserAddressId(selectAddress.getAddressID());
        }

        if (mData != null && mData.getUserCoupon() != null && !TextUtils.isEmpty(mData.getUserCoupon().getDiscountPrice())) {
            confirmOrder.setMasterDiscountId(mData.getUserCoupon().getUserCouponId());
        }
        presenter.confirmOrder(confirmOrder);
    }

    private void initTips() {
        String str1 = "提交订单则表示您同意";
        String str2 = "服务和授权协议";
        SpannableStringBuilder ssb = new SpannableStringBuilder(str1);
        ssb.append(str2);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(font_theme);
            }

            @Override
            public void onClick(@NonNull View view) {
                toast(str2);
            }
        };
        ssb.setSpan(clickableSpan, str1.length(), str1.length() + str2.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        tvTips.setHighlightColor(Color.TRANSPARENT);
        tvTips.setText(ssb);
        tvTips.setMovementMethod(LinkMovementMethod.getInstance());
    }


    Address selectAddress;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RESULT_OK == resultCode && requestCode == 10) {
            selectAddress = data.getParcelableExtra("address");
            tvAddressPhone.setText(selectAddress.getPhoneNumber());
            tvAddressName.setText(selectAddress.getConsignee());
            tvAddressDetail.setText(selectAddress.getDetailedAddress());
            String addressID = selectAddress.getAddressID();
            Log.e("Zyy", selectAddress.toString());
            requestSubmitOrder.setAddressId(Long.valueOf(addressID));

            if (mData != null) {
                mData.getUserAddressVO().setAddressId(selectAddress.getAddressId());
                getData();
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.rl_empty_address:
            case R.id.llAddress:
                AddressActivity.toThisActivity(this);
                break;

        }
    }

    @OnClick(R.id.rl_coupon)
    public void onCouponClick() {
        if (mData == null) {
            return;
        }

        Intent intent = new Intent(this, CouponSelectActivity.class);
        Bundle bundle = new Bundle();
        CouponSerializable serializable = new CouponSerializable();
        serializable.setList(mData.getUserCouponList());
        bundle.putSerializable("coupon", serializable);

        CouponSerializable noCoupon = new CouponSerializable();
        noCoupon.setList(mData.getNoMeetUserCouponList());
        bundle.putSerializable("nocoupon", noCoupon);

        intent.putExtra("type", 1);
        intent.putExtras(bundle);

        startActivity(intent);
    }

    @OnClick(R.id.tvSubmit)
    public void onSubmitClick() {
        if (mData == null) {
            return;
        }

        if (mData.getUserAddressVO() == null || TextUtils.isEmpty(mData.getUserAddressVO().getAddressArea())) {
            ToastUtil.show("请添加地址");
            return;
        }

        presenter.createOrder(getCreateOrder());
    }

    //提交订单信息
    private CreateOrder getCreateOrder() {

        if (mData == null) {
            return null;
        }

        CreateOrder order = new CreateOrder();
        order.setOrderMasterType(1);
        order.setUserAddressId(mData.getUserAddressVO().getAddressId());
        if (mData != null && mData.getUserCoupon() != null && !TextUtils.isEmpty(mData.getUserCoupon().getDiscountPrice())) {
            order.setMasterDiscountId(mData.getUserCoupon().getUserCouponId());
        }

        List<CreateOrder.OrderMasterParamListBean> shoplist = new ArrayList<>();
        CreateOrder.OrderMasterParamListBean shop;
        for (SubmitShopInfo info : mData.getOrderSubmitMasterVOList()) {
            shop = new CreateOrder.OrderMasterParamListBean();
            shop.setBusinessId(info.getBusinessId());
            shop.setOrderRemark(info.getRemark());

            if (info.getUserCoupon() != null && TextUtils.isEmpty(info.getUserCoupon().getUserCouponId())) {
                shop.setBusinessDiscountId(info.getUserCoupon().getUserCouponId());
            }

            List<CreateOrder.OrderMasterParamListBean.OrderSlaveParamListBean> goods = new ArrayList<>();
            for (SubmitGoodsInfo goodsInfo : info.getOrderSubmitSlaveVOList()) {
                CreateOrder.OrderMasterParamListBean.OrderSlaveParamListBean goodBean = new CreateOrder.OrderMasterParamListBean.OrderSlaveParamListBean();
                goodBean.setSkuId(goodsInfo.getGoodsSkuVO().getSkuId());
                goodBean.setSkuType("1");
                goodBean.setSkuCount(goodsInfo.getCount() + "");
                if (goodsInfo.getUserCoupon() != null && !TextUtils.isEmpty(goodsInfo.getUserCoupon().getUserCouponId()))
                    goodBean.setOrderDiscountId(goodsInfo.getUserCoupon().getUserCouponId());  //优惠券


                goods.add(goodBean);
            }
            shop.setOrderFourSlaveParamList(goods);
            shoplist.add(shop);
        }

        order.setOrderFourMasterParamList(shoplist);

        return order;
    }

    @Override
    public void setData(SubmitInfo data) {

        if (mData != null) {
            for (int i = 0; i < mData.getOrderSubmitMasterVOList().size(); i++) {
                data.getOrderSubmitMasterVOList().get(i).setRemark(mData.getOrderSubmitMasterVOList().get(i).getRemark());
            }
        }

        mData = data;

        if (mData != null && data.getUserAddressVO() != null && !TextUtils.isEmpty(data.getUserAddressVO().getAddressArea())) {
            addressArea.setText(data.getUserAddressVO().getAddressArea());
            tvAddressDetail.setText(data.getUserAddressVO().getAddressReDetail());
            tvAddressName.setText(data.getUserAddressVO().getConsignee());
            tvAddressPhone.setText(data.getUserAddressVO().getPhoneNumber());
            llAddress.setVisibility(View.VISIBLE);
            rl_empty_address.setVisibility(View.GONE);
        } else {
            llAddress.setVisibility(View.GONE);
            rl_empty_address.setVisibility(View.VISIBLE);
        }


        adapter = new ConfirmOrderAdapter(data.getOrderSubmitMasterVOList(), this);
        listView.setAdapter(adapter);

        tvAllPrice.setText("￥"+StringUtil.changeSizeByDot(  data.getPaymentPrice(), 0.7f));

        if (mData.getUserCoupon() != null && !TextUtils.isEmpty(mData.getUserCoupon().getDiscountPrice())) {
            tv_coupon.setText("-" + mData.getUserCoupon().getDiscountPrice());
        } else {
            tv_coupon.setText("");
        }
    }

    @Override
    public void payOrder(String orderid) {
        Intent intent = new Intent(this, OrderPayActivity.class);
        intent.putExtra("id", orderid);
        intent.putExtra("price" , mData.getPaymentPrice());
        startActivity(intent);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void EventBusEvent(CouponEvent event) {

        ToastUtil.show(event.getType() + "");
        if (event.getType() == 1) {
            if (mData.getUserCoupon() != null && !TextUtils.isEmpty(mData.getUserCoupon().getDiscountPrice())) {
                mData.getUserCoupon().setUserCouponId(event.getId());
            }
        } else if (event.getType() == 2) {
            for (SubmitShopInfo shopInfo : mData.getOrderSubmitMasterVOList()) {
                for (CouponInfo info : shopInfo.getUserCouponList()) {
                    if (TextUtils.equals(info.getUserCouponId(), event.getId())) {
                        shopInfo.getUserCoupon().setUserCouponId(event.getId());
                    }
                }
            }
        } else if (event.getType() == 3) {
            for (SubmitShopInfo shopInfo : mData.getOrderSubmitMasterVOList()) {
                for (SubmitGoodsInfo goodsInfo : shopInfo.getOrderSubmitSlaveVOList()) {
                    for (CouponInfo info : goodsInfo.getUserCouponList()) {
                        if (TextUtils.equals(info.getUserCouponId(), event.getId())) {
                            goodsInfo.getUserCoupon().setUserCouponId(event.getId());
                        }
                    }
                }
            }
        }

        getData();
    }
}
