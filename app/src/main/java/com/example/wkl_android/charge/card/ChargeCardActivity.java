package com.example.wkl_android.charge.card;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.base.BaseFragment;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.charge.card.fragment.ChargeGiftFragment;
import com.example.wkl_android.charge.card.fragment.ChargeRecordFragment;
import com.example.wkl_android.charge.record.ChargeRecordActivity;
import com.example.wkl_android.login.register.ui.adapter.TabAdapter;
import com.example.wkl_android.shop_street.shop_detail.ui.activity.ShopMessageActivity;
import com.example.wkl_android.shop_street.shop_detail.ui.adapter.ShopImgAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindColor;
import butterknife.BindView;

/**
 * 充值卡
 */
public class ChargeCardActivity extends BaseActivity implements View.OnClickListener {
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
    @BindColor(R.color.font_black_9)
    int font_black_9;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_charge_card;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViews() {
        title.setText("我的充值");
        back.setOnClickListener(this);
        List<BaseFragment> fragments = new ArrayList<>();
        ChargeRecordFragment fragment = ChargeRecordFragment.newInstance();
        fragment.setTitle("充值送现金");
        fragments.add(fragment);
        ChargeGiftFragment chargeGiftFragment = ChargeGiftFragment.newInstance();
        chargeGiftFragment.setTitle("充值送礼品");
        fragments.add(chargeGiftFragment);
        viewPager.setAdapter(new TabAdapter(getSupportFragmentManager(), fragments));
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvRight:
                startActivity(new Intent(APP, ChargeRecordActivity.class));
                break;
        }
    }
}
