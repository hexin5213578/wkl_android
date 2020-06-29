package com.example.wkl_android.extension;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.base.BaseFragment;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.extension.shop.ExtensionShopFragment;
import com.example.wkl_android.login.register.ui.adapter.TabAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 推广
 * Created by szx
 * on 2020/1/9/009
 */
public class ExtensionActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.ivLeft)
    View back;
    @BindView(R.id.etToSearch)
    EditText etToSearch;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_extension;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViews() {
//        etToSearch.setOnClickListener(this);
        back.setOnClickListener(this);
        List<String> titles = new ArrayList<>();
        titles.add("首页");
        titles.add("洗护");
        titles.add("美妆");
        titles.add("百货");
        titles.add("女装");
        titles.add("生鲜");
        titles.add("食品");
        titles.add("数码");
        List<BaseFragment> fragments = new ArrayList<>();
        for (int i = 0; i < titles.size(); i++) {
            ExtensionShopFragment fragment = ExtensionShopFragment.newInstance();
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
