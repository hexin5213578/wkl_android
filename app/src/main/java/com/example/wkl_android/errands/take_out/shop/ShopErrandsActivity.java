package com.example.wkl_android.errands.take_out.shop;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.base.BaseFragment;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.errands.take_out.evaluate.EvaluateFragment;
import com.example.wkl_android.errands.take_out.shop.fragment.ErrandsBusinessFragment;
import com.example.wkl_android.errands.take_out.shop.fragment.ErrandsGoodsFragment;
import com.example.wkl_android.login.register.ui.adapter.TabAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ShopErrandsActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.ivLeft)
    View back;
    @BindView(R.id.tvTitle)
    TextView title;
    @BindView(R.id.ivCollection)
    ImageView ivCollection;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    //    @BindView(R.id.tvSearch)
//    View tvSearch;
    private int flag;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_shop_errands;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViews() {
        title.setText("店铺名");
        back.setOnClickListener(this);
        ivCollection.setOnClickListener(this);
//        tvSearch.setOnClickListener(this);
        List<String> titles = new ArrayList<>();
        titles.add("商品");
        titles.add("评价");
        titles.add("商家");
        titles.add("活动");
        List<BaseFragment> fragments = new ArrayList<>();
        ErrandsGoodsFragment fragment = ErrandsGoodsFragment.newInstance();
        fragment.setTitle("商品");
        fragments.add(fragment);
        EvaluateFragment evaluateFragment = EvaluateFragment.newInstance();
        evaluateFragment.setTitle("评价");
        fragments.add(evaluateFragment);
        ErrandsBusinessFragment errandsBusinessFragment = ErrandsBusinessFragment.newInstance();
        errandsBusinessFragment.setTitle("商家");
        fragments.add(errandsBusinessFragment);
        viewPager.setAdapter(new TabAdapter(getSupportFragmentManager(), fragments));
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.ivCollection:
                if (flag == 0) {
                    toast("收藏成功");
                    ivCollection.setImageResource(R.mipmap.collection);
                    flag = 1;
                } else {
                    toast("取消收藏");
                    ivCollection.setImageResource(R.mipmap.no_collection);
                    flag = 0;
                }
                break;
//            case R.id.tvSearch:
//                startActivity(new Intent(APP, SearchActivity.class));
//                break;
        }
    }
}
