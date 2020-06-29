package com.example.wkl_android.shop_street.main.fragment;

import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseFragment;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.shop_street.main.adapter.StreetCategoryAdapter;
import com.example.wkl_android.widget.rv.decoration.CustomDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 店铺街分类
 * Created by szx
 * on 2020/3/5/005
 */
public class StreetCategoryFragment extends BaseFragment {
    @BindView(R.id.rvStreetCategory)
    RecyclerView rvStreetCategory;

    public static StreetCategoryFragment newInstance() {
        
        Bundle args = new Bundle();
        
        StreetCategoryFragment fragment = new StreetCategoryFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_street_category;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViews() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("");
        }
        rvStreetCategory.setAdapter(new StreetCategoryAdapter(activity, list));
        CustomDecoration customDecoration = new CustomDecoration(activity,
                CustomDecoration.VERTICAL, R.drawable.shape_ll_divider_gray);
        rvStreetCategory.addItemDecoration(customDecoration);
    }
}
