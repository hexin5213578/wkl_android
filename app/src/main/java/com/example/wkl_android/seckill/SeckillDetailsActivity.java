package com.example.wkl_android.seckill;

import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;

import com.example.wkl_android.R;
import com.example.wkl_android.base.all.BaseAvtivity;
import com.example.wkl_android.base.all.BasePresenter;
import com.example.wkl_android.seckill.bean.SpikeBean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * @ProjectName: wkl_android
 * @Package: com.example.wkl_android.seckill
 * @ClassName: SeckillDetailsActivity
 * @Description: (java类作用描述)
 * @Author: 何梦洋
 * @CreateDate: 2020/7/1 17:31
 */
public class SeckillDetailsActivity extends BaseAvtivity {
    @Override
    protected int getResId() {
        return R.layout.activity_seckilldetails;
    }

    @Override
    protected void getData() {
        Intent intent = getIntent();
        ArrayList<SpikeBean.DataBean> list = intent.getParcelableArrayListExtra("list");
        Log.d("hmy",list.get(0).getSkuTitle());
        String killId = list.get(0).getKillId();
        Log.d("hmy",killId);

    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }
}
