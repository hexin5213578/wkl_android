package com.example.wkl_android.shop_discount.fragment;

import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseFragment;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.shop_street.shop_home.ui.adapter.GoodsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ShopDiscountFragment extends BaseFragment {
    @BindView(R.id.rvShopDiscount)
    RecyclerView rvShopDiscount;

    public static ShopDiscountFragment newInstance() {

        Bundle args = new Bundle();

        ShopDiscountFragment fragment = new ShopDiscountFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shop_discount;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViews() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("");
        }
      //  rvShopDiscount.setAdapter(new GoodsAdapter(activity, list));

    }
}
