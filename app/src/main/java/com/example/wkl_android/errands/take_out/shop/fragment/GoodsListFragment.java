package com.example.wkl_android.errands.take_out.shop.fragment;

import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseFragment;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.errands.take_out.shop.adapter.ErrandsGoodsListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class GoodsListFragment extends BaseFragment {
    @BindView(R.id.rvGoodsList)
    RecyclerView rvGoodsList;

    public static GoodsListFragment newInstance() {

        Bundle args = new Bundle();

        GoodsListFragment fragment = new GoodsListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_goods_list;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViews() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("");
        }
        rvGoodsList.setAdapter(new ErrandsGoodsListAdapter(activity, list));
    }
}
