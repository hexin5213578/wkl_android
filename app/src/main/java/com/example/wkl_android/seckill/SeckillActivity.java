package com.example.wkl_android.seckill;

import android.view.View;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.base.BaseFragment;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.login.register.ui.adapter.TabAdapter;
import com.example.wkl_android.seckill.adapter.SeckillDiscountAdapter;
import com.example.wkl_android.widget.NoScrollViewPager;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 特价秒杀
 */
public class SeckillActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.ivLeft)
    View back;
    @BindView(R.id.tvTitle)
    TextView title;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    NoScrollViewPager viewPager;
    private List<String> list;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_seckill;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViews() {
        setTitleBarWithe(ContextCompat.getColor(this, R.color.color_ff383b));
        title.setText("特价秒杀");
        back.setOnClickListener(this);

        List<BaseFragment> fragments = new ArrayList<>();
        list = new ArrayList<>();
        initData();
        for (int i = 0; i < list.size(); i++) {
            SeckillFragment fragment = SeckillFragment.newInstance();
            fragment.setTitle(list.get(i));
            fragments.add(fragment);
        }
        TabAdapter adapter = new TabAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(4);
    }

    private void initData() {
        list.add("8:00\n已结束");
        list.add("10:00\n已结束");
        list.add("12:00\n已结束");
        list.add("14:00\n已结束");
        list.add("16:00\n已开抢");
        list.add("18:00\n未开始");
        list.add("20:00\n未开始");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
        }
    }
}
