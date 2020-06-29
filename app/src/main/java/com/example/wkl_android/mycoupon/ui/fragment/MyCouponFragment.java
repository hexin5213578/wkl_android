package com.example.wkl_android.mycoupon.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wkl_android.Event.LoginEvent;
import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseFragment;
import com.example.wkl_android.charge.card.ChargeCardActivity;
import com.example.wkl_android.collection.CollectionActivity;
import com.example.wkl_android.extension.ExtensionActivity;
import com.example.wkl_android.footprint.FootprintActivity;
import com.example.wkl_android.login.login.model.bean.User;
import com.example.wkl_android.main.shop.add_shop.ui.activity.AddShopActivity;
import com.example.wkl_android.main.shop.address.main.ui.activity.AddressActivity;
import com.example.wkl_android.main.shop.join_in.JoinInActivity;
import com.example.wkl_android.main.shop.settings.main.ui.activity.SettingsActivity;
import com.example.wkl_android.mycoupon.adapter.MyCouponAdapter;
import com.example.wkl_android.mycoupon.bean.CouponVo;
import com.example.wkl_android.mycoupon.ui.activity.MyCouponActivity;
import com.example.wkl_android.mycoupon.ui.fragment.presenter.ShopPresenter;
import com.example.wkl_android.mycoupon.ui.fragment.ui.IShopView;
import com.example.wkl_android.order.main.ui.activity.OrderActivity;
import com.example.wkl_android.order.refund.list.RefundListActivity;
import com.example.wkl_android.utils.BitmapUtil;
import com.example.wkl_android.utils.UserUtil;
import com.example.wkl_android.utils.ViewBgUtils;
import com.example.wkl_android.wallet.WalletActivity;
import com.example.wkl_android.widget.CircleImageView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class MyCouponFragment extends BaseFragment<IShopView, ShopPresenter>
        implements IShopView {

    @BindView(R.id.rv_coupon)
    RecyclerView rv_coupon;

    int position;

    ArrayList<CouponVo> data = new ArrayList<>();
    MyCouponAdapter mAdapter;

    public static MyCouponFragment newInstance(int positon) {

        Bundle args = new Bundle();
        args.putInt("position", positon);
        MyCouponFragment fragment = new MyCouponFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_coupon;
    }

    @Override
    protected ShopPresenter createPresenter() {
        return new ShopPresenter();
    }

    @Override
    protected void initViews() {
        position = getArguments().getInt("position");

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_coupon.setLayoutManager(manager);

        mAdapter = new MyCouponAdapter(data);
        rv_coupon.setAdapter(mAdapter);


        if (position == 1) {
            position = 0;
        } else {
            position = 1;
        }
        presenter.search(position);


    }


    @Override
    protected void onFragmentResume() {
        super.onFragmentResume();

    }


    @Override
    public void setData(ArrayList<CouponVo> coupons) {
        data.clear();
        data.addAll(coupons);
        mAdapter.notifyDataSetChanged();
    }
}
