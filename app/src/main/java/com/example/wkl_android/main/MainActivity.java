package com.example.wkl_android.main;

import android.content.Intent;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.main.fragment.ErrandsFragment;
import com.example.wkl_android.main.new_category.CategoryFragment;
import com.example.wkl_android.main.home.ui.fragment.HomeFragment;
import com.example.wkl_android.main.category.SearchFragment;
import com.example.wkl_android.main.shop.main.MyFragment;
import com.example.wkl_android.main.shopping_cart.ShoppingCartFragment;
import com.jaeger.library.StatusBarUtil;


import butterknife.BindAnim;
import butterknife.BindColor;
import butterknife.BindView;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    @BindView(R.id.flContent)FrameLayout flContent;
    @BindView(R.id.rgMenu)RadioGroup rgMenu;
    @BindView(R.id.rbHome)RadioButton rbHome;
    @BindView(R.id.rbShoppingCart)RadioButton rbShoppingCart;
    @BindAnim(R.anim.anim_scale)Animation anim_scale;
    @BindColor(R.color.theme)int font_theme;

    private FragmentManager fmManager;
    /**
     * 当前展示的Fragment
     */
    private Fragment currentFragment;

    /**
     * tab栏
     */
    private Fragment home, search, find, shoppingCart, shop, category;
    /**
     * 上次点击返回按钮的时间戳
     */
    private long firstPressedTime;
    private String newCity;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViews() {
        fmManager = getSupportFragmentManager();
        rgMenu.setOnCheckedChangeListener(this);
        initFragments();
//        setTitleBarWithe();
    }


    public void initFragments() {
        home = HomeFragment.newInstance();
        category = CategoryFragment.newInstance();
        find = ErrandsFragment.newInstance();
        search = SearchFragment.newInstance();
        shoppingCart = ShoppingCartFragment.newInstance();
        shop = MyFragment.newInstance();
        rbHome.setChecked(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        group.findViewById(checkedId).startAnimation(anim_scale);
        switch (checkedId) {
            case R.id.rbHome:
                replace(home);
                setTitleBarWithe(ContextCompat.getColor(this, R.color.color_ff383b));
                break;
            case R.id.rbCategory:
                replace(category);
                setTitleBarWithe();
                break;
            case R.id.rbSearch:
                replace(search);
                setTitleBarWithe();
                break;
            case R.id.rbTakeOut:
                replace(find);
                setTitleBarWithe();
                break;
            case R.id.rbShoppingCart:
                replace(shoppingCart);
                setTitleBarWithe();
                break;
            case R.id.rbShop:
                replace(shop);
                setTitleBarWithe(ContextCompat.getColor(this, R.color.color_ff383b));

                break;
            default:
                break;
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        int index = intent.getIntExtra("index", -1);
        if (index == 2) {
            rgMenu.check(R.id.rbShoppingCart);
        }
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
    public void onBackPressed() {
        exitWithDoubleClick();
    }

    /**
     * 点击两次返回按钮退出APP
     */
    private void exitWithDoubleClick() {
        if (System.currentTimeMillis() - firstPressedTime < 3_000) {
            super.onBackPressed();
        } else {
            toast("再按一次返回键退出");
            firstPressedTime = System.currentTimeMillis();
        }
    }

}
