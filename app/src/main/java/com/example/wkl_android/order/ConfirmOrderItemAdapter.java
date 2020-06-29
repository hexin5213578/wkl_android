package com.example.wkl_android.order;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseAdapter;
import com.example.wkl_android.couponselect.ui.activity.CouponSelectActivity;
import com.example.wkl_android.databinding.ItemConfirmOrderChildBinding;
import com.example.wkl_android.main.shopping_cart.bean.ShopListBean;
import com.example.wkl_android.order.confirm.model.CouponSerializable;
import com.example.wkl_android.order.confirm.model.GoosSku;
import com.example.wkl_android.order.confirm.model.SubmitGoodsInfo;

import java.util.List;

import androidx.annotation.NonNull;

public class ConfirmOrderItemAdapter extends BaseAdapter<ConfirmOrderItemAdapter.Holder, SubmitGoodsInfo> {


    public ConfirmOrderItemAdapter(List<SubmitGoodsInfo> dataList, Context context) {
        super(dataList, context);
    }

    @Override
    protected void onBindViewHolder(Holder holder, int position, SubmitGoodsInfo itemData) {
        Glide.with(holder.binding.getRoot().getContext()).load(itemData.getGoodsSkuVO().getSkuImage()).into(holder.binding.icon);
        holder.binding.name.setText(itemData.getGoodsSkuVO().getSkuTitle());
        holder.binding.price.setText("￥ " + itemData.getGoodsSkuVO().getSkuPrice());
        holder.binding.count.setText("x" + itemData.getCount());
        if (itemData.getUserCoupon() != null && !TextUtils.isEmpty(itemData.getUserCoupon().getDiscountPrice())) {
            holder.binding.tvCoupon.setText("-" + itemData.getUserCoupon().getDiscountPrice());
        } else {
            holder.binding.tvCoupon.setText("");
        }

        holder.binding.rlCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, CouponSelectActivity.class);
                Bundle bundle = new Bundle();
                CouponSerializable serializable = new CouponSerializable();
                serializable.setList(itemData.getUserCouponList());
                bundle.putSerializable("coupon", serializable);

                CouponSerializable noCoupon = new CouponSerializable();
                noCoupon.setList(itemData.getUserCouponList());
                bundle.putSerializable("nocoupon", noCoupon);

                intent.putExtra("type", 3);
                intent.putExtras(bundle);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getLayout(int type) {
        return R.layout.item_confirm_order_child;
    }

    class Holder extends BaseAdapter.BaseHolder<ItemConfirmOrderChildBinding> {
        public Holder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
