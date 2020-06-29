package com.example.wkl_android.shop_street.shop_detail.charge.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseFragment;
import com.example.wkl_android.shop_street.shop_detail.charge.presenter.ShopChargePresenter;
import com.example.wkl_android.shop_street.shop_detail.ui.adapter.ShopImgAdapter;
import com.example.wkl_android.shop_street.shop_detail.ui.bean.ShopDetailVo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ShopChargeFragment extends BaseFragment<IShopChargeView, ShopChargePresenter>
        implements IShopChargeView {


    @BindView(R.id.iv_gift)
    ImageView iv_gift;

    @BindView(R.id.iv_price)
    ImageView iv_price;

    ShopDetailVo mDetail;

    public static ShopChargeFragment newInstance(ShopDetailVo detailVo) {

        Bundle args = new Bundle();
        args.putSerializable("detail", detailVo);

        ShopChargeFragment fragment = new ShopChargeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shop_charge;
    }

    @Override
    protected ShopChargePresenter createPresenter() {
        return new ShopChargePresenter();
    }

    @Override
    protected void initViews() {

        mDetail = (ShopDetailVo) getArguments().getSerializable("detail");

        if (mDetail.isBusinessPrestoreGiftStatus()) {
            iv_gift.setVisibility(View.VISIBLE);
        } else {
            iv_gift.setVisibility(View.GONE);
        }

        if (mDetail.isBusinessPrestorePriceStatus()) {
            iv_price.setVisibility(View.VISIBLE);
        } else {
            iv_price.setVisibility(View.GONE);
        }

    }


    @OnClick(R.id.iv_price)
    public void onPriceClick() {

    }


    @OnClick(R.id.iv_gift)
    public void onGiftClick() {
    }


}
