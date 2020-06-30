package com.example.wkl_android.seckill;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.base.all.BaseFragment;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.login.register.ui.adapter.TabAdapter;
import com.example.wkl_android.seckill.bean.SpikeBean;
import com.example.wkl_android.seckill.contract.SpikeContract;
import com.example.wkl_android.widget.NoScrollViewPager;
import com.google.android.material.tabs.TabLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

/**
 * 特价秒杀
 */
public class SeckillActivity extends BaseActivity implements View.OnClickListener, SpikeContract.iView {
    @BindView(R.id.ivLeft)
    View back;
    @BindView(R.id.tvTitle)
    TextView title;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    NoScrollViewPager viewPager;
    private List<String> list;
    private List<BaseFragment> fragments;

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

        fragments = new ArrayList<>();
        list = new ArrayList<>();
        initData();
        for (int i = 0; i < list.size(); i++) {
            SeckillFragment fragment = SeckillFragment.newInstance();
            fragment.setTitle(list.get(i));
            fragments.add(fragment);
        }
        ViewPager viewPager = new ViewPager(getSupportFragmentManager());
        this.viewPager.setAdapter(viewPager);
        tabLayout.setupWithViewPager(this.viewPager);
        //获取当前手机时间
        SimpleDateFormat formatter   =  new SimpleDateFormat   ("HH");
        Date curDate =  new Date(System.currentTimeMillis());
        int   time   =   Integer.valueOf(formatter.format(curDate));
        if(time>8 && time <10){
            this.viewPager.setCurrentItem(0);
        }
        else if(time>10 && time <12){
            this.viewPager.setCurrentItem(1);
        }
        else if(time>12 && time <14){
            this.viewPager.setCurrentItem(2);
        }
       else if(time>14 && time <16){
            this.viewPager.setCurrentItem(3);
        }
        else if(time>16 && time <18){
            this.viewPager.setCurrentItem(4);
        }
        else if(time>18 && time <20){
            this.viewPager.setCurrentItem(5);
        }else{
            this.viewPager.setCurrentItem(6);
        }
    }

    private void initData() {
        //获取当前手机时间
        SimpleDateFormat formatter   =   new   SimpleDateFormat   ("HH");
        Date curDate =  new Date(System.currentTimeMillis());
        int   time   =   Integer.valueOf(formatter.format(curDate));
        if(time<8){
            list.add("8:00\n未开始");
        }else if(time>8 && time<9){
            list.add("8:00\n已开抢");
        }else{
            list.add("8:00\n已结束");
        }
        if(time<10){
            list.add("10:00\n未开始");
        }else if(time>10 && time <11){
            list.add("10:00\n已开抢");
        }else{
            list.add("10:00\n已结束");
        }
        if(time<12){
            list.add("12:00\n未开始");
        }else if(time>12 && time<13){
            list.add("12:00\n已开抢");
        }else{
            list.add("12:00\n已结束");
        }
        if(time<14){
            list.add("14:00\n未开始");
        }else if(time>14 && time<15){
            list.add("14:00\n已开抢");
        }else{
            list.add("14:00\n已结束");
        }
        if(time<16){
            list.add("16:00\n未开始");
        }else if(time>16 && time <17){
            list.add("16:00\n已开抢");
        }else{
            list.add("16:00\n已结束");
        }
        if(time<18){
            list.add("18:00\n未开始");
        }else if(time==18){
            list.add("18:00\n已开抢");
        }else{
            list.add("18:00\n已结束");
        }
        if(time<20){
            list.add("20:00\n未开始");
        }else if(time>20 && time<21){
            list.add("20:00\n已开抢");
        }else{
            list.add("20:00\n已结束");
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
        }
    }
    @Override
    public void onGetSuccess(SpikeBean spikeBean) {
        List<SpikeBean.DataBean> data = spikeBean.getData();
        Log.d("my",data.size()+"");
    }

    @Override
    public void onGetError(String msg) {

    }


    public class ViewPager extends FragmentPagerAdapter{
        public ViewPager(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return fragments.get(position).getTitle();
        }
    }
}
