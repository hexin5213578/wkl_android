package com.example.wkl_android.shop_street.shop_home.ui.fragment;

import android.os.Bundle;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseFragment;
import com.example.wkl_android.base.preseter.BasePresenter;

public class RecommendFragment extends BaseFragment {
    public static RecommendFragment newInstance() {
        
        Bundle args = new Bundle();
        
        RecommendFragment fragment = new RecommendFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViews() {

    }
}
