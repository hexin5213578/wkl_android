package com.example.wkl_android.errands.order;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.base.BaseFragment;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.errands.order.fragment.ErrandsOrderFragment;
import com.example.wkl_android.login.register.ui.adapter.TabAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 跑腿订单
 */
public class ErrandsOrderActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.ivLeft)
    View back;
    @BindView(R.id.tvTitle)
    TextView title;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_errands_order;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViews() {
        Intent intent = getIntent();
        if (intent != null) {
            int index = intent.getIntExtra("index", 0);
            viewPager.setCurrentItem(index);
        }
        title.setText("跑腿订单");
        back.setOnClickListener(this);
        List<String> titles = new ArrayList<>();
        titles.add("全部");
        titles.add("待支付");
        titles.add("待受理");
        titles.add("待收货");
        titles.add("待评价");
        titles.add("退款/撤销");
        List<BaseFragment> fragments = new ArrayList<>();
        for (int i = 0; i < titles.size(); i++) {
            ErrandsOrderFragment fragment = ErrandsOrderFragment.newInstance(i);
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
        }
    }
}
