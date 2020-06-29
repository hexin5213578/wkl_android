package com.example.wkl_android.main.home.ui;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.base.BaseFragment;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.login.register.ui.adapter.TabAdapter;
import com.example.wkl_android.main.home.ui.adapter.BannerAdapter;
import com.example.wkl_android.main.home.ui.adapter.CategoryGoodsAdapter;
import com.example.wkl_android.main.home.ui.adapter.CategoryTopAdapter;
import com.example.wkl_android.main.home.ui.fragment.ThreeFragment;
import com.example.wkl_android.shop_street.main.ShopStreetActivity;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;

/**
 * 蔬菜
 */
public class ThreeMarketActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.ivLeft)
    View back;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    private String name;
    private int position;

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
        Intent intent = getIntent();
        if (intent != null) {
            name = intent.getStringExtra("name");
            position = intent.getIntExtra("position", 0);
        }
        back.setOnClickListener(this);
        List<String> titles = new ArrayList<>();
        titles.add("商品");
        titles.add("批发市场");
        titles.add("农贸市场");
        titles.add("合作种植");
        List<BaseFragment> fragments = new ArrayList<>();
        for (int i = 0; i < titles.size(); i++) {
            ThreeFragment fragment = ThreeFragment.newInstance(name, position);
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
                startActivity(new Intent(APP, ShopStreetActivity.class));
                break;
        }
    }
}
