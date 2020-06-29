package com.example.wkl_android.seckill;

import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseFragment;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.seckill.adapter.SeckillAdapter;
import com.example.wkl_android.seckill.adapter.SeckillDiscountAdapter;
import com.example.wkl_android.widget.rv.decoration.CustomDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SeckillFragment extends BaseFragment {
    @BindView(R.id.rvSeckill)RecyclerView rvSeckill;
    @BindView(R.id.rvDiscount)RecyclerView rvDiscount;

    public static SeckillFragment newInstance() {

        Bundle args = new Bundle();

        SeckillFragment fragment = new SeckillFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_seckill;
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
        rvSeckill.setAdapter(new SeckillAdapter(activity, list));
        CustomDecoration customDecoration = new CustomDecoration(activity,
                CustomDecoration.VERTICAL, R.drawable.shape_ll_divider_gray_10dp);
        rvSeckill.addItemDecoration(customDecoration);

        GridLayoutManager manager = new GridLayoutManager(activity, 1);
        manager.setOrientation(RecyclerView.HORIZONTAL);
        List<String> discountList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            discountList.add("");
        }
        SeckillDiscountAdapter seckillDiscountAdapter = new SeckillDiscountAdapter(activity,discountList);
        rvDiscount.setLayoutManager(manager);
        rvDiscount.setAdapter(seckillDiscountAdapter);
    }
}
