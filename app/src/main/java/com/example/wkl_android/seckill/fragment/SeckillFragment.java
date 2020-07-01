package com.example.wkl_android.seckill.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.wkl_android.R;
import com.example.wkl_android.base.all.BaseFragment;
import com.example.wkl_android.base.all.BasePresenter;
import com.example.wkl_android.seckill.adapter.SeckillAdapter;
import com.example.wkl_android.seckill.adapter.SeckillAdapter2;
import com.example.wkl_android.seckill.adapter.SeckillDiscountAdapter;
import com.example.wkl_android.seckill.bean.SpikeBean;
import com.example.wkl_android.widget.rv.decoration.CustomDecoration;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class SeckillFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.rvSeckill)
    RecyclerView rvSeckill;
    @BindView(R.id.rvDiscount)
    RecyclerView rvDiscount;
    private List<SpikeBean.DataBean> list;

    @Override
    public void onClick(View view) {

    }

    @Override
    protected void initView(View v) {
        rvSeckill = v.findViewById(R.id.rvSeckill);
        rvDiscount = v.findViewById(R.id.rvDiscount);
    }

    @Override
    protected void initData() {
        GridLayoutManager manager = new GridLayoutManager(getContext(), 1);

        List<String> discountList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            discountList.add("");
        }
        SeckillDiscountAdapter seckillDiscountAdapter = new SeckillDiscountAdapter(getContext(), discountList);
        rvDiscount.setLayoutManager(manager);
        rvDiscount.setAdapter(seckillDiscountAdapter);


        if (list != null && list.size() > 0) {
            SimpleDateFormat formatter = new SimpleDateFormat("HH");

            Date curDate = new Date(System.currentTimeMillis());
            int time = Integer.valueOf(formatter.format(curDate));

            for (int i = 0; i < list.size(); i++) {
                String killBeginTime = list.get(i).getKillBeginTime();

                int timeNow = Integer.valueOf(killBeginTime.substring(11, 13));


                if (time < timeNow) {

                    rvSeckill.setLayoutManager(manager);
                    rvSeckill.setAdapter(new SeckillAdapter2(getActivity(), list));
                    CustomDecoration customDecoration = new CustomDecoration(getActivity(),
                            CustomDecoration.VERTICAL, R.drawable.shape_ll_divider_gray_10dp);
                    rvSeckill.addItemDecoration(customDecoration);

                    manager.setOrientation(RecyclerView.HORIZONTAL);
                }

                if (time >= timeNow) {

                    SeckillAdapter seckillAdapter = new SeckillAdapter(getActivity(), list);
                    rvSeckill.setLayoutManager(manager);
                    rvSeckill.setAdapter(seckillAdapter);
                    CustomDecoration customDecoration = new CustomDecoration(getActivity(),
                            CustomDecoration.VERTICAL, R.drawable.shape_ll_divider_gray_10dp);
                    rvSeckill.addItemDecoration(customDecoration);

                    manager.setOrientation(RecyclerView.HORIZONTAL);
                }

            }
        }
    }
    @Override
    protected int getLayout () {
        return R.layout.fragment_seckill;
    }

    @Override
    protected BasePresenter initPresenter () {
        return null;
    }
    public void setData(List<SpikeBean.DataBean> data) {
        list = new ArrayList<>();
        list.addAll(data);
        if(getUserVisibleHint()) {
            initData();
        }
    }

}
