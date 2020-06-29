package com.example.wkl_android.brand;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.base.BaseFragment;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.brand.adapter.ClassfiyMenuTabAdapter;
import com.example.wkl_android.brand.fragment.BrandFragment;
import com.example.wkl_android.login.register.ui.adapter.TabAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import q.rorbin.verticaltablayout.VerticalTabLayout;

/**
 * 品牌
 */
public class BrandActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.ivLeft)
    View back;
    @BindView(R.id.tabLayout)
    VerticalTabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_brand;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViews() {
        back.setOnClickListener(this);
        List<BaseFragment> fragments = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            BrandFragment fragment = BrandFragment.newInstance();
            titles.add("分类" + i);
            fragments.add(fragment);
        }
        viewPager.setAdapter(new TabAdapter(getSupportFragmentManager(), fragments));
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
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
        }
    }
}
