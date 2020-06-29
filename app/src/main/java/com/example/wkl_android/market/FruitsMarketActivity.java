package com.example.wkl_android.market;

import android.view.View;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.base.BaseFragment;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.login.register.ui.adapter.TabAdapter;
import com.example.wkl_android.market.fragment.FruitsMarketFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 水果市场
 * Created by szx
 * on 2020/3/5/005
 */
public class FruitsMarketActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.ivLeft)
    View back;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_three_market;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViews() {
        back.setOnClickListener(this);
        List<String> titles = new ArrayList<>();
        titles.add("1234");
        titles.add("1234");
        titles.add("222");
        titles.add("2222");
        titles.add("222");
        List<BaseFragment> fragments = new ArrayList<>();
        for (int i = 0; i < titles.size(); i++) {
            FruitsMarketFragment fragment = FruitsMarketFragment.newInstance();
            fragment.setTitle(titles.get(i));
            fragments.add(fragment);
        }
        viewPager.setAdapter(new TabAdapter(getSupportFragmentManager(), fragments));
        tabLayout.setupWithViewPager(viewPager);
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
