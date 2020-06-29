package com.example.wkl_android.main.home.ui.fragment;

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

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;

/**
 * 首页顶部分区 */
public class HomeCategoryFragment extends BaseFragment {
    @BindView(R.id.rvCategory)
    RecyclerView rvCategory;
    @BindView(R.id.rvGoods)
    RecyclerView rvGoods;
    @BindView(R.id.vpBanner)
    LoopRecyclerViewPager vpBanner;
    @BindView(R.id.tvIndicator)
    TextView tvIndicator;
    List<GoodsListBean.GoodsSlideshowVOList> goodsBanner;

    public static HomeCategoryFragment newInstance() {

        Bundle args = new Bundle();
        HomeCategoryFragment fragment = new HomeCategoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_category;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViews() {
        goodsBanner = new ArrayList<>();
        if( goodsBanner.size() >0 ) {
            vpBanner.setAdapter(new BannerAdapter(activity, goodsBanner));
            vpBanner.setCurrentItem(Integer.MAX_VALUE / 2 - (Integer.MAX_VALUE / 2 % goodsBanner.size()));
            tvIndicator.setText(String.format(Locale.getDefault(),
                    "%d/%d", vpBanner.getCurrentItem() % goodsBanner.size() + 1,
                    goodsBanner.size()));
            vpBanner.setPageMargin(100);
            vpBanner.startAutoScroll();
            vpBanner.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
//                tvIndicator.setText(String.format(Locale.getDefault(), "%d/%d",
//                        position % goodsBanner.size() + 1, goodsBanner.size()));
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }


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
        rvCategory.setAdapter(new CategoryTopAdapter(activity, list));
        GridLayoutManager manager1 = new GridLayoutManager(activity, 3) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        List<String> list1 = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            list1.add("");
        }
        rvGoods.setLayoutManager(manager1);
        rvGoods.setAdapter(new CategoryGoodsAdapter(activity, list1));
    }
}
