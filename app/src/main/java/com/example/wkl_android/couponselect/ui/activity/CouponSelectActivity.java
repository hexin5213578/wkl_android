package com.example.wkl_android.couponselect.ui.activity;


import android.os.CountDownTimer;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import androidx.viewpager.widget.ViewPager;

import com.example.wkl_android.Event.CouponEvent;
import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.base.BaseFragment;

import com.example.wkl_android.couponselect.ICouponSelectView;
import com.example.wkl_android.couponselect.adapter.CouponVPAdapter;
import com.example.wkl_android.couponselect.bean.CouponDataInfo;
import com.example.wkl_android.couponselect.bean.CouponInfo;
import com.example.wkl_android.couponselect.presenter.CouponSelectPresenter;

import com.example.wkl_android.couponselect.ui.fagment.CouponFrament;
import com.example.wkl_android.order.confirm.model.CouponSerializable;
import com.example.wkl_android.utils.ToastUtil;
import com.example.wkl_android.utils.ViewBgUtils;
import com.google.android.material.tabs.TabLayout;


import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 优惠券
 *
 * @author szx
 */
public class CouponSelectActivity extends BaseActivity<ICouponSelectView, CouponSelectPresenter>
        implements ICouponSelectView {

    @BindView(R.id.tb_coupon)
    TabLayout tb_coupon;

    @BindView(R.id.vp_coupon)
    ViewPager vp_coupon;

    @BindView(R.id.tv_send)
    TextView tv_send;

    CouponVPAdapter adapter;
    List<BaseFragment> lists = new ArrayList<>();
    ArrayList<CouponInfo> data = new ArrayList<>();
    ArrayList<CouponInfo> noData = new ArrayList<>();
    int type = 1;           //1平台 2店铺 3商品

    @Override
    public int getLayoutRes() {
        return R.layout.activity_coupon_select;
    }

    @Override
    protected CouponSelectPresenter createPresenter() {
        return new CouponSelectPresenter();
    }

    @Override
    protected void initViews() {
        setTitleBarWithe(ContextCompat.getColor(this, R.color.transparent));
        ViewBgUtils.setBg(tv_send, "#FF4538", 10);

        data = ((CouponSerializable) getIntent().getExtras().getSerializable("coupon")).getList();
        noData = ((CouponSerializable) getIntent().getExtras().getSerializable("nocoupon")).getList();
        type = getIntent().getIntExtra("type", 1);

        CouponDataInfo dataInfo = new CouponDataInfo();
        dataInfo.setList(data);


        for (CouponInfo info : data) {
            info.setCanSelect(true);
        }
        CouponDataInfo noDataInfo = new CouponDataInfo();
        noDataInfo.setList(noData);

        CouponFrament fragment;
        for (int i = 0; i < 2; i++) {
            if (i == 0) {
                fragment = CouponFrament.newInstance(i, dataInfo);
            } else {
                fragment = CouponFrament.newInstance(i, noDataInfo);
            }
            lists.add(fragment);
        }

        adapter = new CouponVPAdapter(getSupportFragmentManager(), lists);
        vp_coupon.setAdapter(adapter);
        tb_coupon.setupWithViewPager(vp_coupon);
    }


    @OnClick(R.id.iv_close)
    public void onBackClick() {
        finish();
    }

    @OnClick(R.id.tv_send)
    public void onSendClick() {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).isSelect()) {
                CouponEvent event = new CouponEvent(data.get(i).getUserCouponId(), type);
                EventBus.getDefault().post(event);
                finish();
//                ToastUtil.show(i + "");
            }
        }
    }


}
