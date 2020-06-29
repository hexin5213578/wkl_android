package com.example.wkl_android.main.shop.join_in.fragment;

import android.os.Bundle;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseFragment;
import com.example.wkl_android.base.preseter.BasePresenter;

public class JoinInDistrictFragment extends BaseFragment {
    public static JoinInDistrictFragment newInstance() {
        
        Bundle args = new Bundle();
        
        JoinInDistrictFragment fragment = new JoinInDistrictFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_join_in_district;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViews() {

    }
}
