package com.example.wkl_android.couponselect.adapter;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wkl_android.R;
import com.example.wkl_android.couponselect.bean.CouponInfo;
import com.example.wkl_android.utils.BitmapUtil;
import com.example.wkl_android.utils.ViewBgUtils;
import com.example.wkl_android.wholesale_market.bean.MarketInfo;
import com.example.wkl_android.wholesale_market.bean.WholesaleMarketInfo;

import java.util.List;

public class CouponAdapter extends BaseQuickAdapter<CouponInfo, BaseViewHolder> {
    public CouponAdapter(@Nullable List<CouponInfo> data) {
        super(R.layout.coupon_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CouponInfo item) {

        TextView tv_type = helper.getView(R.id.tv_type);
        ViewBgUtils.setBg(tv_type, "#ff4538", 10);

        helper.setText(R.id.tv_price, item.getDiscountPrice())
                .setText(R.id.tv_name, item.getDiscountName())
                .setText(R.id.tv_time, item.getDiscountBeginTime()+"_"+item.getDiscountOverTime());


        ImageView iv_check = helper.getView(R.id.iv_check);
        if (item.isCanSelect()) {
            iv_check.setVisibility(View.VISIBLE);
        } else {
            iv_check.setVisibility(View.INVISIBLE);
        }

        if (item.isSelect()) {
            iv_check.setImageResource(R.drawable.checked);
        } else {
            iv_check.setImageResource(R.drawable.no_check);
        }
    }
}