package com.example.wkl_android.main.shop.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.example.wkl_android.Event.LoginEvent;
import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseFragment;
import com.example.wkl_android.charge.card.ChargeCardActivity;
import com.example.wkl_android.collection.CollectionActivity;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.faceback.ui.activity.FaceBackActivity;
import com.example.wkl_android.footprint.FootprintActivity;
import com.example.wkl_android.invitation.ui.activity.InvitationActivity;
import com.example.wkl_android.login.login.model.bean.User;
import com.example.wkl_android.main.shop.add_shop.ui.activity.AddShopActivity;
import com.example.wkl_android.main.shop.address.main.ui.activity.AddressActivity;
import com.example.wkl_android.extension.ExtensionActivity;
import com.example.wkl_android.main.shop.join_in.JoinInActivity;
import com.example.wkl_android.main.shop.main.presenter.ShopPresenter;
import com.example.wkl_android.main.shop.main.ui.IShopView;
import com.example.wkl_android.main.shop.settings.information.main.model.bean.UserInfo;
import com.example.wkl_android.main.shop.settings.main.ui.activity.SettingsActivity;
import com.example.wkl_android.mycoupon.ui.activity.MyCouponActivity;
import com.example.wkl_android.order.refund.list.RefundListActivity;
import com.example.wkl_android.order.main.ui.activity.OrderActivity;
import com.example.wkl_android.utils.BitmapUtil;
import com.example.wkl_android.utils.UserUtil;
import com.example.wkl_android.utils.ViewBgUtils;
import com.example.wkl_android.wallet.WalletActivity;
import com.example.wkl_android.widget.CircleImageView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;

public class MyFragment extends BaseFragment<IShopView, ShopPresenter>
        implements IShopView, View.OnClickListener {
    @BindView(R.id.ivLeft)
    View back;
    @BindView(R.id.tvTitle)
    TextView title;
    @BindView(R.id.ivSettings)
    View ivSettings;
    @BindView(R.id.llToOrder)
    View llToOrder;
    @BindView(R.id.tvWaitPay)
    View tvWaitPay;
    @BindView(R.id.tvWaitReceivingGoods)
    View tvWaitReceivingGoods;
    @BindView(R.id.tvWaitEvaluate)
    View tvWaitEvaluate;
    @BindView(R.id.tvRefund)
    View tvRefund;

    @BindView(R.id.tvAddress)
    View tvAddress;
    @BindView(R.id.tvShop)
    View tvShop;
    @BindView(R.id.tvJoinIn)
    View tvJoinIn;
    @BindView(R.id.llCollection)
    View llCollection;
    @BindView(R.id.llFootprint)
    View llFootprint;
    @BindView(R.id.tvUserName)
    TextView tvUserName;
    @BindView(R.id.tvExtension)
    View tvExtension;
    @BindView(R.id.llCharge)
    View llCharge;

    @BindView(R.id.tv_money)
    TextView tv_money;
    @BindView(R.id.ll_extension)
    LinearLayout ll_extension;

    @BindView(R.id.iv_header)
    CircleImageView iv_header;
    @BindView(R.id.ll_address)
    LinearLayout ll_address;
    @BindView(R.id.ll_shop)
    LinearLayout ll_shop;


    public static MyFragment newInstance() {

        Bundle args = new Bundle();

        MyFragment fragment = new MyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    protected ShopPresenter createPresenter() {
        return new ShopPresenter();
    }

    @Override
    protected void initViews() {
        title.setText("我的");
        back.setVisibility(View.GONE);
        ivSettings.setOnClickListener(this);
        llToOrder.setOnClickListener(this);
        tvWaitPay.setOnClickListener(this);
        tvWaitReceivingGoods.setOnClickListener(this);
        tvWaitEvaluate.setOnClickListener(this);
        tvRefund.setOnClickListener(this);
        tvAddress.setOnClickListener(this);
        tvShop.setOnClickListener(this);
        tvJoinIn.setOnClickListener(this);
        llCollection.setOnClickListener(this);
        llFootprint.setOnClickListener(this);
        tvExtension.setOnClickListener(this);
        llCharge.setOnClickListener(this);
        ll_extension.setOnClickListener(this);
        ll_address.setOnClickListener(this);
        ll_shop.setOnClickListener(this);
        tv_money.setOnClickListener(this);


        tv_money.setOnClickListener(this);
        ViewBgUtils.setBg(tv_money, "#ffdf6e", 20);

        EventBus.getDefault().register(this);


        presenter.getUserInfo();
    }

    private void setData(UserInfo userInfo) {
        try {

            tvUserName.setText(userInfo.getUserNickname());

            try {
                JSONObject object = new JSONObject(userInfo.getUserImage());
                String img = object.optString("url");

                Glide.with(this)
                        .load(img)
                        .into(iv_header);
            } catch (JSONException e) {
                e.printStackTrace();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onFragmentResume() {
        super.onFragmentResume();

    }

    @OnClick(R.id.ll_shop_order)
    public void onOrderClick() {
        toOrderActivity(0);
    }

    @OnClick(R.id.ll_coupon)
    public void onCouponClick() {
        getContext().startActivity(new Intent(getContext(), MyCouponActivity.class));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivSettings:
                //跳转设置
                startActivity(new Intent(APP, SettingsActivity.class));
                break;
            case R.id.llToOrder:
                //跳转订单
                toOrderActivity(0);
                break;
            case R.id.tvWaitPay:
                //待支付
                toOrderActivity(1);
                break;
            case R.id.tvWaitReceivingGoods:
                //待收货
                toOrderActivity(3);
                break;
            case R.id.tvWaitEvaluate:
                //待评价
                toOrderActivity(4);
                break;
            case R.id.tvRefund:
                //退货
                startActivity(new Intent(APP, RefundListActivity.class));
                break;
            case R.id.tv_money:
                //我的钱包
                startActivity(new Intent(APP, WalletActivity.class));
                break;
            case R.id.ll_address:
            case R.id.tvAddress:
                //地址列表
                startActivity(new Intent(APP, AddressActivity.class));
                break;
            case R.id.ll_shop:
            case R.id.tvShop:
                //店铺入驻
                startActivity(new Intent(APP, AddShopActivity.class));
                break;
            case R.id.tvJoinIn:
                //加入我们
                startActivity(new Intent(APP, JoinInActivity.class));
                break;
            case R.id.llCharge:
                startActivity(new Intent(APP, ChargeCardActivity.class));
                break;
            case R.id.llCollection:
                //收藏
                startActivity(new Intent(APP, CollectionActivity.class));
                break;
            case R.id.llFootprint:
                startActivity(new Intent(APP, FootprintActivity.class));
                break;
            case R.id.ll_extension:
            case R.id.tvExtension:
                startActivity(new Intent(APP, ExtensionActivity.class));
                break;
            default:
                break;
        }
    }

    /**
     * 跳转订单页面
     *
     * @param index 下标
     */
    private void toOrderActivity(int index) {
        startActivity(new Intent(APP, OrderActivity.class).putExtra("index", index));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void EventBusEvent(LoginEvent event) {
        presenter.getUserInfo();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getUserInfo();
    }

    @OnClick(R.id.ll_faceback)
    public void onFaceBackClick() {
        getContext().startActivity(new Intent(getContext(), FaceBackActivity.class));
    }

    @OnClick(R.id.ll_inviation)
    public void onInviationClick(){
        startActivity(new Intent(getContext() , InvitationActivity.class));
    }

    @Override
    public void handleUserInfo(UserInfo info) {
        setData(info);
     }
}
