package com.example.wkl_android.shop_street.shop_home.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseFragment;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.login.register.ui.adapter.TabAdapter;
 import com.example.wkl_android.shop_street.shop_detail.ui.activity.ShopMessageActivity;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ShopHomeFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.llShop)
    View llShop;
    @BindView(R.id.ivCollection)
    ImageView ivCollection;
    private int collectionFlag;

    public static ShopHomeFragment newInstance() {
        Bundle args = new Bundle();
        ShopHomeFragment fragment = new ShopHomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shop_home;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViews() {
        List<BaseFragment> fragments = new ArrayList<>();
        RecommendFragment recommendFragment = RecommendFragment.newInstance();
        recommendFragment.setTitle("推荐");
        fragments.add(recommendFragment);
        DiscountFragment discountFragment = DiscountFragment.newInstance();
        discountFragment.setTitle("活动");
        fragments.add(discountFragment);
        GoodsFragment goodsFragment = GoodsFragment.newInstance();
        goodsFragment.setTitle("新品");
        fragments.add(goodsFragment);
        viewPager.setAdapter(new TabAdapter(getChildFragmentManager(), fragments));
        tabLayout.setupWithViewPager(viewPager);
        llShop.setOnClickListener(this);
        ivCollection.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.llShop:
                startActivity(new Intent(APP, ShopMessageActivity.class));
                break;
            case R.id.ivCollection:
                if (collectionFlag == 0) {
                    toast("收藏成功");
                    ivCollection.setImageResource(R.mipmap.collection);
                    collectionFlag = 1;
                } else {
                    toast("取消收藏");
                    ivCollection.setImageResource(R.mipmap.no_collection);
                    collectionFlag = 0;
                }
                break;
        }
    }
}
