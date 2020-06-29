package com.example.wkl_android.shop_discount;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.base.BaseFragment;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.login.register.ui.adapter.TabAdapter;
import com.example.wkl_android.shop_discount.fragment.ShopDiscountFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 店铺活动
 */
public class ShopDiscountActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.ivLeft)
    View back;
    @BindView(R.id.tvTitle)
    TextView title;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tvRight)
    TextView right;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_shop_discount;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViews() {
        title.setText("店铺活动");
        back.setOnClickListener(this);
        right.setVisibility(View.VISIBLE);
        right.setText("活动规则");
        List<String> titles = new ArrayList<>();
        titles.add("预存有礼");
        titles.add("满减");
        titles.add("满送");
        titles.add("上新");
        titles.add("推广");
        titles.add("加价购");

        List<BaseFragment> fragments = new ArrayList<>();
        for (int i = 1; i < titles.size(); i++) {
            ShopDiscountFragment fragment = ShopDiscountFragment.newInstance();
            fragment.setTitle(titles.get(i));
            fragments.add(fragment);
        }
        viewPager.setAdapter(new TabAdapter(getSupportFragmentManager(), fragments));
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvRight:
                startActivity(new Intent(APP, DiscountRulesActivity.class));
                break;
        }
    }
}
