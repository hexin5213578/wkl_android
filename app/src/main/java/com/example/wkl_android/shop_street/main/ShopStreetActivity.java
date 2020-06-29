package com.example.wkl_android.shop_street.main;

import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.shop_street.main.fragment.ShopStreetFragment;
import com.example.wkl_android.shop_street.main.fragment.StreetCategoryFragment;


import butterknife.BindColor;
import butterknife.BindView;

/**
 * 店铺街
 * Created by szx
 * on 2020/1/19/019
 */
public class ShopStreetActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.ivLeft)
    View back;
    @BindView(R.id.tvShop)
    TextView tvShop;
    @BindView(R.id.tvCategory)
    TextView tvCategory;
    @BindColor(R.color.theme)
    int theme;
    @BindColor(R.color.white)
    int white;
    /**
     * 当前展示的Fragment
     */
    private Fragment currentFragment;
    private Fragment shop, category;
    private FragmentManager fmManager;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_shop_street;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViews() {
        back.setOnClickListener(this);
        tvShop.setOnClickListener(this);
        fmManager = getSupportFragmentManager();
        tvShop.setOnClickListener(this);
        tvCategory.setOnClickListener(this);
        initFragments();

    }

    private void initFragments() {
        shop = ShopStreetFragment.newInstance();
        category = StreetCategoryFragment.newInstance();
        fmManager.beginTransaction()
                .add(R.id.flContent, shop)
                .commitAllowingStateLoss();
    }

    /**
     * 切换页面显示fragment
     *
     * @param to 跳转到的fragment
     */
    private void replace(Fragment to) {
        if (to == null || to == currentFragment) {
            // 如果跳转的fragment为空或者跳转的fragment为当前fragment则不做操作
            return;
        }
        if (currentFragment == null) {
            // 如果当前fragment为空,即为第一次添加fragment
            fmManager.beginTransaction()
                    .add(R.id.flContent, to)
                    .commitAllowingStateLoss();
            currentFragment = to;
            return;
        }
        // 切换fragment
        FragmentTransaction transaction = fmManager.beginTransaction().hide(currentFragment);
        if (!to.isAdded()) {
            transaction.add(R.id.flContent, to);
        } else {
            transaction.show(to);
        }
        transaction.commitAllowingStateLoss();
        currentFragment = to;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvShop:
                tvShop.setBackground(getDrawable(R.drawable.shape_theme_left));
                tvShop.setTextColor(white);
                tvCategory.setBackground(getDrawable(R.drawable.shape_strock_right));
                tvCategory.setTextColor(theme);
                replace(shop);
                break;
            case R.id.tvCategory:
                tvShop.setBackground(getDrawable(R.drawable.shape_strock_left));
                tvShop.setTextColor(theme);
                tvCategory.setBackground(getDrawable(R.drawable.shape_theme_right));
                tvCategory.setTextColor(white);
                replace(category);
                break;
        }
    }
}
