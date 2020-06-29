package com.example.wkl_android.order.main.ui.activity;

import android.content.Intent;
import android.view.View;

import androidx.viewpager.widget.ViewPager;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.base.BaseFragment;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.login.register.ui.adapter.TabAdapter;
import com.example.wkl_android.order.main.ui.fragment.OrderFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 订单
 * Created by szx
 * on 2020/1/2/002
 */
public class OrderActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.ivLeft)
    View back;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private int index;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_order;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViews() {
        setTitleBarWithe();
        Intent intent = getIntent();
        if (intent != null) {
            index = intent.getIntExtra("index", 0);
        }
        back.setOnClickListener(this);
        List<BaseFragment> fragments = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        titles.add("全部");
        titles.add("待付款");
        titles.add("待发货");
        titles.add("待收货");
        titles.add("待评价");
        for (int i = 0; i < titles.size(); i++) {
            OrderFragment orderFragment = OrderFragment.newInstance(i);
            orderFragment.setTitle(titles.get(i));
            fragments.add(orderFragment);
        }
        viewPager.setAdapter(new TabAdapter(getSupportFragmentManager(), fragments));
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(index);
        viewPager.setOffscreenPageLimit(5);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
        }
    }
}
