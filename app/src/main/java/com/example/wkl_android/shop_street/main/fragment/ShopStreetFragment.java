package com.example.wkl_android.shop_street.main.fragment;

import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseFragment;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.shop_street.main.adapter.StreetShopAdapter;
import com.example.wkl_android.widget.rv.decoration.CustomDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by szx
 * on 2020/3/5/005
 */
public class ShopStreetFragment extends BaseFragment {
    @BindView(R.id.rvShop)
    RecyclerView rvShop;

    public static ShopStreetFragment newInstance() {

        Bundle args = new Bundle();
        ShopStreetFragment fragment = new ShopStreetFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shop_street;
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
        rvShop.setAdapter(new StreetShopAdapter(activity, list));
        CustomDecoration customDecoration = new CustomDecoration(activity,
                CustomDecoration.VERTICAL, R.drawable.shape_ll_divider_gray);
        rvShop.addItemDecoration(customDecoration);
    }
}
