package com.example.wkl_android.errands.take_out.shop.fragment;



import android.os.Bundle;

import androidx.viewpager.widget.ViewPager;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseFragment;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.brand.adapter.ClassfiyMenuTabAdapter;
import com.example.wkl_android.login.register.ui.adapter.TabAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import q.rorbin.verticaltablayout.VerticalTabLayout;

public class ErrandsGoodsFragment extends BaseFragment {
    @BindView(R.id.tabLayout)
    VerticalTabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    public static ErrandsGoodsFragment newInstance() {

        Bundle args = new Bundle();

        ErrandsGoodsFragment fragment = new ErrandsGoodsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_errands_goods;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViews() {
        List<BaseFragment> fragments = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            titles.add("新品小吃");
        }
        for (int i = 0; i < titles.size(); i++) {
            GoodsListFragment fragment = GoodsListFragment.newInstance();
            fragment.setTitle(titles.get(i));
            fragments.add(fragment);
        }
        viewPager.setAdapter(new TabAdapter(getChildFragmentManager(), fragments));
        bindTabAndPager(titles);
    }

    /**
     * a.关联TabLayout和ViewPager
     * b.创建TabLayout的数据适配器
     * c.设置TabLayout的数据适配器
     */
    private void bindTabAndPager(List<String> classfiys) {
        tabLayout.setupWithViewPager(viewPager);
        ClassfiyMenuTabAdapter classfiyMenuTabAdapter =
                new ClassfiyMenuTabAdapter(classfiys);
        tabLayout.setTabAdapter(classfiyMenuTabAdapter);
    }
}
