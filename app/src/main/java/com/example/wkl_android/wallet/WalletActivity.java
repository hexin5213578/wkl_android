package com.example.wkl_android.wallet;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.base.BaseFragment;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.login.register.ui.adapter.TabAdapter;
import com.example.wkl_android.shop_street.shop_home.ui.fragment.DiscountFragment;
import com.example.wkl_android.wallet.fragment.ChargeDetailFragment;
import com.example.wkl_android.wallet.fragment.WithdrawFragment;
import com.example.wkl_android.wallet.withdraw.WithdrawActivity;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindColor;
import butterknife.BindView;

/**
 * 我的钱包
 */
public class WalletActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.ivLeft)
    View back;
    @BindView(R.id.tvTitle)
    TextView title;
    @BindView(R.id.tvRight)
    TextView right;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindColor(R.color.theme)
    int font_theme;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_wallet;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViews() {
        title.setText("我的钱包");
        back.setOnClickListener(this);
        right.setVisibility(View.VISIBLE);
        right.setText("提现");
        right.setTextColor(font_theme);
        right.setOnClickListener(this);

        List<BaseFragment> fragments = new ArrayList<>();
        ChargeDetailFragment chargeDetailFragment = ChargeDetailFragment.newInstance();
        chargeDetailFragment.setTitle("充值记录");
        fragments.add(chargeDetailFragment);
        WithdrawFragment withdrawFragment = WithdrawFragment.newInstance();
        withdrawFragment.setTitle("提现记录");
        fragments.add(withdrawFragment);
        TabAdapter adapter = new TabAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvRight:
                startActivity(new Intent(APP, WithdrawActivity.class));
                break;
        }
    }
}
