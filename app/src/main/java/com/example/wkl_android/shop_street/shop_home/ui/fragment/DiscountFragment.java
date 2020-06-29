package com.example.wkl_android.shop_street.shop_home.ui.fragment;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseFragment;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.good.ui.GoodsActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class DiscountFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.tvDiscount)
    TextView tvDiscount;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.llToGoods)
    View llToGoods;

    public static DiscountFragment newInstance() {

        Bundle args = new Bundle();

        DiscountFragment fragment = new DiscountFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_discount;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViews() {
        llToGoods.setOnClickListener(this);
        //折扣 文字中间加横线
        tvDiscount.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            list.add("");
        }
        GridLayoutManager manager = new GridLayoutManager(activity, 2) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        rv.setLayoutManager(manager);
       // rv.setAdapter(new ShopGoodsAdapter(activity, list));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.llToGoods:
                startActivity(new Intent(APP, GoodsActivity.class));
                break;
        }
    }
}
