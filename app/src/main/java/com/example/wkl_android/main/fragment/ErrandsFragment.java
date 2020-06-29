package com.example.wkl_android.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseFragment;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.errands.help_buy.HelpMeBuyActivity;
import com.example.wkl_android.errands.order.ErrandsOrderActivity;
import com.example.wkl_android.errands.pick_up.PickUpPartsActivity;
import com.example.wkl_android.errands.take_out.shop.all_list.AllErrandsShopActivity;
import com.example.wkl_android.good.model.GoodsListBean;
import com.example.wkl_android.main.fragment.adapter.ShopErrandsAdapter;
import com.example.wkl_android.main.fragment.adapter.ShopSelectedAdapter;
import com.example.wkl_android.main.home.ui.LoopRecyclerViewPager;
import com.example.wkl_android.main.home.ui.adapter.BannerAdapter;
import com.example.wkl_android.main.home.ui.adapter.HorizontalAdapter;
import com.example.wkl_android.utils.SPUtils;
import com.example.wkl_android.widget.rv.ScrollRecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;

/**
 * 跑腿
 */
public class ErrandsFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.ivLeft)
    View back;
    @BindView(R.id.tvTitle)
    TextView title;
    @BindView(R.id.tvHelpMeBuy)
    View tvHelpMeBuy;
    @BindView(R.id.tvPickUpParts)
    View tvPickUpParts;
    @BindView(R.id.vpBanner)
    LoopRecyclerViewPager vpBanner;
    @BindView(R.id.tvIndicator)
    TextView tvIndicator;
    @BindView(R.id.rvErrands)
    RecyclerView rvErrands;
    @BindView(R.id.rvShop)
    RecyclerView rvShop;
    @BindView(R.id.rvShopSelected)
    ScrollRecyclerView rvShopSelected;
    @BindView(R.id.tvShowMore)
    View tvShowMore;
    @BindView(R.id.tvLocation)
    TextView tvLocation;
    @BindView(R.id.tvToOrder)
    View tvToOrder;
    private List<GoodsListBean.GoodsSlideshowVOList> goodsBanner;
    private List<GoodsListBean.GoodsPlateVOList> menus;

    public static ErrandsFragment newInstance() {

        Bundle args = new Bundle();
        ErrandsFragment fragment = new ErrandsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_find;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViews() {
        title.setText("同城跑腿");
        back.setVisibility(View.GONE);
        tvHelpMeBuy.setOnClickListener(this);
        tvPickUpParts.setOnClickListener(this);
        tvShowMore.setOnClickListener(this);
        tvToOrder.setOnClickListener(this);
        initBanner();

        GridLayoutManager layoutManager = new GridLayoutManager(activity, 2);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        rvErrands.setLayoutManager(layoutManager);
        if (null != menus && menus.size() > 0) {
            rvErrands.setAdapter(new HorizontalAdapter(activity, menus, 0));
            GridLayoutManager layoutManager1 = new GridLayoutManager(activity, 2);
            layoutManager1.setOrientation(RecyclerView.HORIZONTAL);
            rvShopSelected.setLayoutManager(layoutManager1);
            rvShopSelected.setAdapter(new ShopSelectedAdapter(activity, menus));

            rvShop.setAdapter(new ShopErrandsAdapter(activity, menus));
        }
    }

    @Override
    protected void onFragmentResume() {
        super.onFragmentResume();
        String address = SPUtils.getInstance().getString("address", "");
        tvLocation.setText(address);
    }

    /**
     * 填充banner
     */
    private void initBanner() {
        goodsBanner = new ArrayList<>();
        if (goodsBanner.size() > 0) {
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
                    tvIndicator.setText(String.format(Locale.getDefault(), "%d/%d",
                            position % goodsBanner.size() + 1, goodsBanner.size()));
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvHelpMeBuy:
                startActivity(new Intent(APP, HelpMeBuyActivity.class));
                break;
            case R.id.tvPickUpParts:
                startActivity(new Intent(APP, PickUpPartsActivity.class));
                break;
            case R.id.tvShowMore:
                startActivity(new Intent(APP, AllErrandsShopActivity.class));
                break;
            case R.id.tvToOrder:
                startActivity(new Intent(APP, ErrandsOrderActivity.class));
                break;
        }
    }
}
