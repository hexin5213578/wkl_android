package com.example.wkl_android.shop_street.shop_home.ui.fragment;

import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseFragment;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.shop_street.shop_home.ui.adapter.ShopCategoryAdapter;
import com.example.wkl_android.widget.rv.decoration.CustomDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CategoryFragment extends BaseFragment {
    @BindView(R.id.rvCategory)
    RecyclerView rvCategory;
    public static CategoryFragment newInstance() {

        Bundle args = new Bundle();

        CategoryFragment fragment = new CategoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_category;
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
        rvCategory.setAdapter(new ShopCategoryAdapter(activity, list));
        CustomDecoration customDecoration = new CustomDecoration(activity,
                CustomDecoration.VERTICAL, R.drawable.shape_ll_divider_gray_10dp);
        rvCategory.addItemDecoration(customDecoration);
    }
}
