package com.example.wkl_android.main.home.ui;

import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.base.BaseFragment;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.brand.adapter.ClassfiyMenuTabAdapter;
import com.example.wkl_android.brand.fragment.BrandFragment;
import com.example.wkl_android.login.register.ui.adapter.TabAdapter;
import com.example.wkl_android.main.new_category.CategoryFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import q.rorbin.verticaltablayout.VerticalTabLayout;

/**
 * 所有分类
 */
public class CategoryActivity extends BaseActivity {


    @Override
    public int getLayoutRes() {
        return R.layout.activity_category;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViews() {


    }

}
