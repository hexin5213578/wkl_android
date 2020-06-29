package com.example.wkl_android.shop_street.shop_home.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseFragment;
import com.example.wkl_android.good.model.GoodsBean;
import com.example.wkl_android.good.presenter.GoodsDetailPresenter;
import com.example.wkl_android.good.ui.IGoodsView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by szx
 * on 2020/1/18/018
 */
public class GoodsFragment extends BaseFragment<IGoodsView, GoodsDetailPresenter> implements IGoodsView {
    @BindView(R.id.rv)
    RecyclerView rv;

    public static GoodsFragment newInstance() {

        Bundle args = new Bundle();

        GoodsFragment fragment = new GoodsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.goods_fragment;
    }

    @Override
    protected GoodsDetailPresenter createPresenter() {
        return new GoodsDetailPresenter();
    }

    @Override
    protected void initViews() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("");
        }
        FragmentActivity activity = weakReference.get();
//        rv.setAdapter(new ShopGoodsAdapter(activity, list));
    }

    @Override
    public void handleGoodsDetail(GoodsBean goodsBean) {

    }

    @Override
    public void collectSuccess() {

    }
}
