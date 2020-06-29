package com.example.wkl_android.shop_street.shop_home.ui.activity;

import android.view.View;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.shop_street.shop_home.ui.fragment.CategoryFragment;
import com.example.wkl_android.shop_street.shop_home.ui.fragment.ShopGoodsFragment;
import com.example.wkl_android.shop_street.shop_home.ui.fragment.ShopHomeFragment;

import butterknife.BindAnim;
import butterknife.BindView;

/**
 * 店铺首页
 * Created by szx
 * on 2020/1/18/018
 */
public class ShopHomeActivity extends BaseActivity
        implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.flContent)
    FrameLayout flContent;
    @BindView(R.id.rgMenu)
    RadioGroup rgMenu;
    @BindView(R.id.rbHome)
    RadioButton rbHome;
    @BindAnim(R.anim.anim_scale)
    Animation anim_scale;
    @BindView(R.id.ivLeft)
    View back;

    /**
     * tab栏
     */
    private Fragment home, category, goods;

    private FragmentManager fmManager;
    /**
     * 当前展示的Fragment
     */
    private Fragment currentFragment;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_shop_home;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViews() {
        back.setOnClickListener(this);
        fmManager = getSupportFragmentManager();
        rgMenu.setOnCheckedChangeListener(this);

        initFragments();
    }

    public void initFragments() {
        home = ShopHomeFragment.newInstance();
        category = CategoryFragment.newInstance();
        goods = ShopGoodsFragment.newInstance();

        rbHome.setChecked(true);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        group.findViewById(checkedId).startAnimation(anim_scale);
        switch (checkedId) {
            case R.id.rbHome:
                replace(home);
                break;
            case R.id.rbSearch:
                replace(category);
                break;
            case R.id.rbGoods:
                replace(goods);
                break;
            case R.id.rbCustomer:
                toast("跳转客服对话页面");
                break;
            default:
                break;
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
        }
    }
}
