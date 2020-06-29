package com.example.wkl_android.main.shop.join_in;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.base.BaseFragment;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.http.ResultCode;
import com.example.wkl_android.login.register.ui.adapter.TabAdapter;
import com.example.wkl_android.main.shop.address.location.LocationAddressActivity;
import com.example.wkl_android.main.shop.address.select.SelectSiteActivity;
import com.example.wkl_android.main.shop.join_in.fragment.JoinInDistrictFragment;
import com.example.wkl_android.main.shop.join_in.fragment.JoinInLittleFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 我要合作
 * Created by szx
 * on 2020/1/2/002
 */
public class JoinInActivity extends BaseActivity implements View.OnClickListener {
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
        return R.layout.activity_join_in;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViews() {
        title.setText("我要合作");
        back.setOnClickListener(this);
        List<BaseFragment> fragments = new ArrayList<>();
        JoinInLittleFragment joinInLittleFragment = JoinInLittleFragment.newInstance();
        joinInLittleFragment.setTitle("小区合作");
        fragments.add(joinInLittleFragment);
        JoinInDistrictFragment joinInDistrictFragment = JoinInDistrictFragment.newInstance();
        joinInDistrictFragment.setTitle("区域合作");
        fragments.add(joinInDistrictFragment);
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
