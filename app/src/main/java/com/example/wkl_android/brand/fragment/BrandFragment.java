package com.example.wkl_android.brand.fragment;

import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseFragment;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.brand.adapter.BrandAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class BrandFragment extends BaseFragment {
    @BindView(R.id.rvBrand)
    RecyclerView rvBrand;

    public static BrandFragment newInstance() {
        
        Bundle args = new Bundle();
        
        BrandFragment fragment = new BrandFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_brand;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViews() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            list.add("");
        }
        BrandAdapter adapter = new BrandAdapter(activity,list);
        rvBrand.setAdapter(adapter);
    }
}
