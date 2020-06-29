package com.example.wkl_android.main.home.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseFragment;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.good.model.GoodsListBean;
import com.example.wkl_android.main.home.ui.LoopRecyclerViewPager;
import com.example.wkl_android.main.home.ui.adapter.BannerAdapter;
import com.example.wkl_android.main.home.ui.adapter.CategoryGoodsAdapter;
import com.example.wkl_android.main.home.ui.adapter.CategoryTopAdapter;
import com.example.wkl_android.main.home.ui.adapter.ThreeAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * Created by szx
 * on 2020/3/5/005
 */
public class ThreeFragment extends BaseFragment implements View.OnClickListener {
    private String name;
    @BindView(R.id.rvCategory)
    RecyclerView rvCategory;
    @BindView(R.id.rvGoods)
    RecyclerView rvGoods;
    @BindView(R.id.vpBanner)
    LoopRecyclerViewPager vpBanner;
    @BindView(R.id.tvIndicator)
    TextView tvIndicator;
    @BindView(R.id.ivGrid)
    ImageView ivGrid;
    private int position;
    private int flag;
    private GridLayoutManager gridManager;
    private LinearLayoutManager listManager;
    private ThreeAdapter listAdapter;
    private CategoryGoodsAdapter gridAdapter;

    public static ThreeFragment newInstance(String name, int position) {

        Bundle args = new Bundle();
        args.putInt("position", position);
        args.putString("name", name);
        ThreeFragment fragment = new ThreeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_three;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViews() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            name = arguments.getString("name");
            position = arguments.getInt("position");
        }
        ivGrid.setOnClickListener(this);
        initBanner();
        initRv();
    }

    private void initRv() {
        GridLayoutManager manager = new GridLayoutManager(activity, 5) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            list.add("");
        }
        rvCategory.setLayoutManager(manager);
        rvCategory.setAdapter(new CategoryTopAdapter(activity, list, name, position));
        gridManager = new GridLayoutManager(activity, 3) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        listManager = new LinearLayoutManager(activity) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        List<String> list1 = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            list1.add("");
        }
        listAdapter = new ThreeAdapter(activity, list1);
        gridAdapter = new CategoryGoodsAdapter(activity, list1);
        rvGoods.setLayoutManager(gridManager);
        rvGoods.setAdapter(gridAdapter);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivGrid:
                if (flag == 0) {
                    //调整为列表布局
                    flag = 1;
                    ivGrid.setImageResource(R.mipmap.goods_list);
                    rvGoods.setAdapter(listAdapter);
                    rvGoods.setLayoutManager(listManager);
                } else {
                    //调整为网格布局
                    flag = 0;
                    ivGrid.setImageResource(R.mipmap.goods_grid);
                    rvGoods.setLayoutManager(gridManager);
                    rvGoods.setAdapter(gridAdapter);
                }
                break;
        }
    }
}
