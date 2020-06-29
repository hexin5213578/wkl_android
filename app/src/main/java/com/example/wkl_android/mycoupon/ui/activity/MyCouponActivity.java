package com.example.wkl_android.mycoupon.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.base.Constant;
import com.example.wkl_android.good.model.GoodsListBean;
import com.example.wkl_android.main.home.ui.adapter.HomeGoodsItemAdapter;
import com.example.wkl_android.mycoupon.IMyCouponView;
import com.example.wkl_android.mycoupon.presenter.MyCouponPresenter;
import com.example.wkl_android.mycoupon.ui.fragment.MyCouponFragment;
import com.example.wkl_android.searchinput.ui.activity.SearchInputActivity;
import com.example.wkl_android.searchshop.ShopAdapter;
import com.example.wkl_android.searchshop.bean.ShopVo;
import com.example.wkl_android.utils.SPUtils;
import com.example.wkl_android.utils.ToastUtil;
import com.google.android.material.tabs.TabLayout;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的优惠券
 *
 * @author szx
 */
public class MyCouponActivity extends BaseActivity<IMyCouponView, MyCouponPresenter>
        implements IMyCouponView {


    @BindView(R.id.tvTitle)
    TextView tvTitle;

    @BindView(R.id.tb_coupon)
    TabLayout tb_coupon;

    @BindView(R.id.vp_coupon)
    ViewPager vp_coupon;

    String[] tabName = {"可用优惠券", "不可用优惠券"};


    @Override
    public int getLayoutRes() {
        return R.layout.activity_my_coupon;
    }

    @Override
    protected MyCouponPresenter createPresenter() {
        return new MyCouponPresenter();
    }

    @Override
    protected void initViews() {
        setTitleBarWithe();
        tvTitle.setText("优惠券");


        vp_coupon.setOffscreenPageLimit(2);
        vp_coupon.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return MyCouponFragment.newInstance(position);
            }

            @Override
            public int getCount() {
                return 2;
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return tabName[position] + "(0)";
            }
        });

        tb_coupon.setupWithViewPager(vp_coupon);
    }

    @OnClick(R.id.ivLeft)
    public void onBackClick() {
        finish();
    }
}
