package com.example.wkl_android.main.category;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseFragment;
import com.example.wkl_android.brand.adapter.ClassfiyMenuTabAdapter;
import com.example.wkl_android.brand.fragment.BrandFragment;
import com.example.wkl_android.login.register.ui.adapter.TabAdapter;
import com.example.wkl_android.main.category.model.bean.Category;
import com.example.wkl_android.main.category.model.bean.CategoryName;
import com.example.wkl_android.main.category.presenter.SearchPresenter;
import com.example.wkl_android.main.category.ui.ISearchView;
import com.example.wkl_android.main.category.ui.adapter.CategoryAdapter;
import com.example.wkl_android.main.category.ui.adapter.CategoryDetailAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import q.rorbin.verticaltablayout.VerticalTabLayout;

/**
 * 分类
 */
public class SearchFragment extends BaseFragment<ISearchView, SearchPresenter> implements ISearchView {
    @BindView(R.id.tabLayout)
    VerticalTabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    public static SearchFragment newInstance() {

        Bundle args = new Bundle();

        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search;
    }

    @Override
    protected SearchPresenter createPresenter() {
        return new SearchPresenter();
    }

    @Override
    protected void initViews() {
        List<BaseFragment> fragments = new ArrayList<>();
        List<String> titles = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            BrandFragment fragment = BrandFragment.newInstance();
//            titles.add("分类" + i);
//            fragments.add(fragment);
//        }
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
    @Override
    protected void onFragmentResume() {
        super.onFragmentResume();
    }

    /**
     * 处理分类数据
     *
     * @param list 数据
     */
    @Override
    public void handleCategoryList(List<Category> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        handleOutList(list);
    }
    private void handleOutList(List<Category> list) {

    }
}
