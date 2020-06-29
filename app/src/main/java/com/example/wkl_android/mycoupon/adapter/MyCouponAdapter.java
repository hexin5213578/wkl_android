package com.example.wkl_android.mycoupon.adapter;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wkl_android.R;
import com.example.wkl_android.http.utils.HttpLogger;
import com.example.wkl_android.mycoupon.bean.CouponVo;
import com.example.wkl_android.shop_street.shop_detail.ui.activity.ShopMessageActivity;
import com.example.wkl_android.utils.BitmapUtil;
import com.example.wkl_android.utils.ViewBgUtils;

import java.util.List;

public class MyCouponAdapter extends BaseQuickAdapter<CouponVo, BaseViewHolder> {


    public MyCouponAdapter(@Nullable List<CouponVo> data) {
        super(R.layout.my_coupon_item, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, final CouponVo item) {

        TextView tv_shop = helper.getView(R.id.tv_shop);
        ViewBgUtils.setBg(tv_shop, "#ff453b", 20);

        TextView tv_type = helper.getView(R.id.tv_type);
        ViewBgUtils.setBg(tv_type, "#ff453b", 20);

        LinearLayout ll_shop = helper.getView(R.id.ll_shop);
        ImageView shop_img = helper.getView(R.id.iv_shop_img);
        ll_shop.setVisibility(View.GONE);

        String type = "平台券";
        if (!item.isDiscountType()) {
            ll_shop.setVisibility(View.VISIBLE);
            type = "商家券";
            if (!TextUtils.equals(item.getDiscountSkuId(), "0")) {
                type = "商品券";
            }
            BitmapUtil.showImage(mContext, item.getBusinessLogo(), shop_img);
            helper.setText(R.id.tv_shop_name, item.getBusinessName());

            helper.getView(R.id.tv_shop).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ShopMessageActivity.StartActivity(mContext, item.getDiscountBusinessId());
                }
            });
        }

        helper.setBackgroundRes(R.id.ll_left_bg, item.isOpen() ? R.mipmap.coupon_red_left : R.mipmap.coupon_gray_left)
                .setBackgroundRes(R.id.ll_right_bg, item.isOpen() ? R.mipmap.coupon_red_right : R.mipmap.coupon_gray_right)
                .setTextColor(R.id.tv_name, item.isOpen() ? ContextCompat.getColor(mContext, R.color.color_ff5438) : ContextCompat.getColor(mContext, R.color.color_999999));

        helper.setText(R.id.tv_price, item.getDiscountPrice())
                .setText(R.id.tv_type, type)
                .setText(R.id.tv_name, item.getDiscountName())
                .setText(R.id.tv_restrict, "购买" + item.getDiscountRestrict() + "元以上可用")
                .setText(R.id.tv_time, item.getDiscountBeginTime() + "_" + item.getDiscountOverTime());


    }
}