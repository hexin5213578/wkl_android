package com.example.wkl_android.market.fragment;

import android.os.Bundle;
import android.widget.TextView;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseFragment;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.good.model.GoodsListBean;
import com.example.wkl_android.main.home.ui.LoopRecyclerViewPager;
import com.example.wkl_android.main.home.ui.adapter.BannerAdapter;
import com.example.wkl_android.main.home.ui.adapter.CategoryGoodsAdapter;
import com.example.wkl_android.main.home.ui.adapter.CategoryTopAdapter;
import com.example.wkl_android.market.adapter.FruitGoodsAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import butterknife.BindView;

/**
 * Created by szx
 * on 2020/3/5/005
 */
public class FruitsMarketFragment extends BaseFragment {
    @BindView(R.id.rvGoodsDiscount)
    RecyclerView rvGoodsDiscount;
    @BindView(R.id.vpBanner)
    LoopRecyclerViewPager vpBanner;
    @BindView(R.id.tvIndicator)
    TextView tvIndicator;
    @BindView(R.id.rvGoods)
    RecyclerView rvGoods;
    @BindView(R.id.rvCategory)
    RecyclerView rvCategory;
    @BindView(R.id.rvDayDiscount)
    RecyclerView rvDayDiscount;

    public static FruitsMarketFragment newInstance() {

        Bundle args = new Bundle();

        FruitsMarketFragment fragment = new FruitsMarketFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_fruit_market;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViews() {
        initRv();
        initBanner();
    }

    private void initRv() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        rvGoodsDiscount.setLayoutManager(layoutManager);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(activity);
        layoutManager1.setOrientation(RecyclerView.HORIZONTAL);
        rvDayDiscount.setLayoutManager(layoutManager1);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            list.add("");
        }
        rvGoodsDiscount.setAdapter(new CategoryGoodsAdapter(activity, list));
        rvDayDiscount.setAdapter(new CategoryGoodsAdapter(activity, list));

        rvGoods.setLayoutManager(new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL));
        rvGoods.setAdapter(new FruitGoodsAdapter(activity, list));

        GridLayoutManager manager = new GridLayoutManager(activity, 5) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        rvCategory.setLayoutManager(manager);
        rvCategory.setAdapter(new CategoryTopAdapter(activity, list));
    }

    private void initBanner() {
        List<GoodsListBean.GoodsSlideshowVOList> goodsBanner = new ArrayList<>();
        if (goodsBanner.size() > 0) {
            vpBanner.setAdapter(new BannerAdapter(activity, goodsBanner));
            vpBanner.setCurrentItem(Integer.MAX_VALUE / 2 - (Integer.MAX_VALUE / 2 % goodsBanner.size()));
            tvIndicator.setText(String.format(Locale.getDefault(),
                    "%d/%d", vpBanner.getCurrentItem() % goodsBanner.size() + 1,
                    goodsBanner.size()));
            vpBanner.setPageMargin(100);
            vpBanner.startAutoScroll();
        }
    }

}
