package com.example.wkl_android.seckill;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.wkl_android.R;
import com.example.wkl_android.base.all.BaseAvtivity;
import com.example.wkl_android.base.all.BaseFragment;
import com.example.wkl_android.base.all.BasePresenter;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.good.model.GoodsBean;
import com.example.wkl_android.seckill.bean.SpikeBean;
import com.example.wkl_android.seckill.contract.SeckillContract;
import com.example.wkl_android.seckill.fragment.SeckillFragment;
import com.example.wkl_android.seckill.fragment.SeckillFragmentfive;
import com.example.wkl_android.seckill.fragment.SeckillFragmentfour;
import com.example.wkl_android.seckill.fragment.SeckillFragmentseven;
import com.example.wkl_android.seckill.fragment.SeckillFragmentsix;
import com.example.wkl_android.seckill.fragment.SeckillFragmentthree;
import com.example.wkl_android.seckill.fragment.SeckillFragmenttwo;
import com.example.wkl_android.seckill.presenter.SeckillPresenter;
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
public class SeckillActivity extends BaseAvtivity implements SeckillContract.IView,View.OnClickListener {
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
    private SeckillFragment seckillFragment;
    private SeckillFragmenttwo seckillFragmenttwo;
    private SeckillFragmentthree seckillFragmentthree;
    private SeckillFragmentfour seckillFragmentfour;
    private SeckillFragmentfive seckillFragmentfive;
    private SeckillFragmentsix seckillFragmentsix;
    private SeckillFragmentseven seckillFragmentseven;
    private void initData() {
        //获取当前手机时间
        SimpleDateFormat formatter   =   new   SimpleDateFormat   ("HH");
        Date curDate =  new Date(System.currentTimeMillis());
        int   time   =   Integer.valueOf(formatter.format(curDate));
        if(time<8){
            list.add("8:00\n未开始");
        }else if(time>=8 && time<10){
            list.add("8:00\n已开抢");
        }else{
            list.add("8:00\n已结束");
        }
        if(time<10){
            list.add("10:00\n未开始");
        }else if(time>=10 && time <12){
            list.add("10:00\n已开抢");
        }else{
            list.add("10:00\n已结束");
        }
        if(time<12){
            list.add("12:00\n未开始");
        }else if(time>=12 && time<14){
            list.add("12:00\n已开抢");
        }else{
            list.add("12:00\n已结束");
        }
        if(time<14){
            list.add("14:00\n未开始");
        }else if(time>=14 && time<16){
            list.add("14:00\n已开抢");
        }else{
            list.add("14:00\n已结束");
        }
        if(time<16){
            list.add("16:00\n未开始");
        }else if(time>=16 && time <18){
            list.add("16:00\n已开抢");
        }else{
            list.add("16:00\n已结束");
        }
        if(time<18){
            list.add("18:00\n未开始");
        }else if(time>=18 && time <20){
            list.add("18:00\n已开抢");
        }else{
            list.add("18:00\n已结束");
        }
        if(time<20){
            list.add("20:00\n未开始");
        }else if(time>=20 && time<22){
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
    protected int getResId() {
        return R.layout.activity_seckill;
    }

    @Override
    protected void getData() {
        title.setText("秒杀抢购");
        back.setOnClickListener(this);
        fragments = new ArrayList<>();
        list = new ArrayList<>();
        initData();
        seckillFragment = new SeckillFragment();
        seckillFragmenttwo = new SeckillFragmenttwo();
        seckillFragmentthree = new SeckillFragmentthree();
        seckillFragmentfour = new SeckillFragmentfour();
        seckillFragmentfive = new SeckillFragmentfive();
        seckillFragmentsix = new SeckillFragmentsix();
        seckillFragmentseven = new SeckillFragmentseven();

        seckillFragment.setTitle(list.get(0));
        seckillFragmenttwo.setTitle(list.get(1));
        seckillFragmentthree.setTitle(list.get(2));
        seckillFragmentfour.setTitle(list.get(3));
        seckillFragmentfive.setTitle(list.get(4));
        seckillFragmentsix.setTitle(list.get(5));
        seckillFragmentseven.setTitle(list.get(6));

        fragments.add(seckillFragment);
        fragments.add(seckillFragmenttwo);
        fragments.add(seckillFragmentthree);
        fragments.add(seckillFragmentfour);
        fragments.add(seckillFragmentfive);
        fragments.add(seckillFragmentsix);
        fragments.add(seckillFragmentseven);
        ViewPager viewPager = new ViewPager(getSupportFragmentManager());
        this.viewPager.setAdapter(viewPager);
        tabLayout.setupWithViewPager(this.viewPager);
        //获取当前手机时间
        SimpleDateFormat formatter   =  new SimpleDateFormat   ("HH");
        Date curDate =  new Date(System.currentTimeMillis());
        int   time   =   Integer.valueOf(formatter.format(curDate));
        if(time>=8 && time <10){
            this.viewPager.setCurrentItem(0);
        }
        else if(time>=10 && time <12){
            this.viewPager.setCurrentItem(1);
        }
        else if(time>=12 && time <14){
            this.viewPager.setCurrentItem(2);
        }
        else if(time>=14 && time <16){
            this.viewPager.setCurrentItem(3);
        }
        else if(time>=16 && time <18){
            this.viewPager.setCurrentItem(4);
        }
        else if(time>=18 && time <20){
            this.viewPager.setCurrentItem(5);
        }else if(time>=20 && time<22){
            this.viewPager.setCurrentItem(6);
        }else{
            this.viewPager.setCurrentItem(0);
        }
        BasePresenter basePresenter =getPresenter();
        if(basePresenter instanceof SeckillPresenter){
            ((SeckillPresenter) basePresenter).doGetSeckill();
        }
    }

    @Override
    protected BasePresenter initPresenter() {
        return new SeckillPresenter(this);
    }

    @Override
    public void onGetSuccess(SpikeBean spikeBean) {
        List<SpikeBean.DataBean> data = spikeBean.getData();

        if(data!=null && data.size()>0){
            Log.d("hmy",data.size()+"");
            for (int i=0;i<data.size();i++){
                String killBeginTime = data.get(i).getKillBeginTime();
                int time = Integer.valueOf(killBeginTime.substring(11, 13));

                List<SpikeBean.DataBean> list = new ArrayList<>();
                if(time==8){
                    list.add(data.get(i));
                    seckillFragment.setData(list);
                    list.clear();
                }else if(time==10){
                    list.add(data.get(i));
                    seckillFragmenttwo.setData(list);
                }else if(time==12){
                    list.add(data.get(i));
                    seckillFragmentthree.setData(list);
                    list.clear();
                }else if(time==14){
                    list.add(data.get(i));
                    seckillFragmentfour.setData(list);
                    list.clear();
                }else if(time==16){
                    list.add(data.get(i));
                    seckillFragmentfive.setData(list);
                    list.clear();
                }else if(time==18){
                    list.add(data.get(i));
                    seckillFragmentsix.setData(list);
                    list.clear();
                }else{
                    list.add(data.get(i));
                    seckillFragmentseven.setData(list);
                    list.clear();
                }
            }
        }

    }

    @Override
    public void onGetError(String msg) {

    }

    @Override
    public void onGetGoodsDetailsSuccess(GoodsBean goodsBean) {

    }


    @Override
    public void onGetGoodsDetailsError(String msg) {

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
