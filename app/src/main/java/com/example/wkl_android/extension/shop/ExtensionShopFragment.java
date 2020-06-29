package com.example.wkl_android.extension.shop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseFragment;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.extension.discount.ExtensionDiscountActivity;
import com.example.wkl_android.shop_street.main.adapter.StreetAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ExtensionShopFragment extends BaseFragment {
    @BindView(R.id.rvExtensionShop)
    RecyclerView rvExtensionShop;

    public static ExtensionShopFragment newInstance() {

        Bundle args = new Bundle();

        ExtensionShopFragment fragment = new ExtensionShopFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_extension_shop;
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
        StreetAdapter adapter = new StreetAdapter(activity, list);
        rvExtensionShop.setAdapter(adapter);
        adapter.setListener(new StreetAdapter.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(APP, ExtensionDiscountActivity.class));
            }
        });
    }
}
