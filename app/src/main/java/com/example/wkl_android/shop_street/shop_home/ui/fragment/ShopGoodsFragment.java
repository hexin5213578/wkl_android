package com.example.wkl_android.shop_street.shop_home.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseFragment;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.shop_street.shop_detail.ui.activity.ShopMessageActivity;
import com.example.wkl_android.shop_street.shop_home.ui.adapter.GoodsAdapter;
import com.example.wkl_android.shop_street.shop_home.ui.adapter.ShopGoodsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ShopGoodsFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.rvGoods)
    RecyclerView rvGoods;
    @BindView(R.id.ivSelectList)
    ImageView ivSelectList;
    @BindView(R.id.tvFollow)
    TextView tvFollow;
    @BindView(R.id.llShop)
    View llShop;
    private GoodsAdapter listAdapter;
    private ShopGoodsAdapter gridAdapter;
    private int flag;//0列表布局，1Grid布局
    private int followFlag;
    private GridLayoutManager gridLayoutManager;
    private LinearLayoutManager linearLayoutManager;

    public static ShopGoodsFragment newInstance() {

        Bundle args = new Bundle();

        ShopGoodsFragment fragment = new ShopGoodsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shop_goods;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViews() {
        ivSelectList.setOnClickListener(this);
        tvFollow.setOnClickListener(this);
        llShop.setOnClickListener(this);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("");
        }
        linearLayoutManager = new LinearLayoutManager(activity);
        gridLayoutManager = new GridLayoutManager(activity, 2);
//        listAdapter = new GoodsAdapter(activity, list);
//        gridAdapter = new ShopGoodsAdapter(activity, list);
        rvGoods.setLayoutManager(linearLayoutManager);
        rvGoods.setAdapter(listAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivSelectList:
                if (flag == 0) {
                    rvGoods.setLayoutManager(gridLayoutManager);
                    rvGoods.setAdapter(gridAdapter);
                    ivSelectList.setImageResource(R.mipmap.goods_grid);
                    flag = 1;
                } else {
                    rvGoods.setLayoutManager(linearLayoutManager);
                    rvGoods.setAdapter(listAdapter);
                    ivSelectList.setImageResource(R.mipmap.goods_list);
                    flag = 0;
                }
                break;
            case R.id.tvFollow:
                if (followFlag == 0) {
                    tvFollow.setText("已关注");
                    followFlag = 1;
                } else {
                    tvFollow.setText("关注");
                    followFlag = 0;
                }
                break;
            case R.id.llShop:
                startActivity(new Intent(APP, ShopMessageActivity.class));
                break;
        }
    }
}
