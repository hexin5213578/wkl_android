package com.example.wkl_android.seckill;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wkl_android.R;
import com.example.wkl_android.base.all.BaseFragment;
import com.example.wkl_android.base.all.BasePresenter;
import com.example.wkl_android.seckill.adapter.SeckillAdapter;
import com.example.wkl_android.seckill.adapter.SeckillDiscountAdapter;
import com.example.wkl_android.seckill.bean.SpikeBean;
import com.example.wkl_android.seckill.contract.SpikeContract;
import com.example.wkl_android.seckill.presenter.SpikePresenter;
import com.example.wkl_android.widget.rv.decoration.CustomDecoration;

import org.jetbrains.annotations.Contract;

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
    protected void getid(View view) {

    }

    @Override
    protected int getResId() {
        return R.layout.fragment_seckill;
    }

    @Override
    protected BasePresenter initPresenter() {
        return new SpikePresenter(this);
    }

    @Override
    protected void getData() {

        GridLayoutManager manager = new GridLayoutManager(getContext(), 1);

        List<String> discountList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            discountList.add("");
        }
        SeckillDiscountAdapter seckillDiscountAdapter = new SeckillDiscountAdapter(getContext(),discountList);
        rvDiscount.setLayoutManager(manager);
        rvDiscount.setAdapter(seckillDiscountAdapter);


       /* rvSeckill.setAdapter(new SeckillAdapter(getActivity(), data));
        CustomDecoration customDecoration = new CustomDecoration(getActivity(),
                CustomDecoration.VERTICAL, R.drawable.shape_ll_divider_gray_10dp);
        rvSeckill.addItemDecoration(customDecoration);

        manager.setOrientation(RecyclerView.HORIZONTAL);*/
    }
}
