package com.example.wkl_android.order;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseAdapter;
import com.example.wkl_android.couponselect.ui.activity.CouponSelectActivity;
import com.example.wkl_android.databinding.ItemConfirmOrderBinding;
import com.example.wkl_android.databinding.ItemConfirmOrderChildBinding;
import com.example.wkl_android.main.shopping_cart.bean.ShopListBean;
import com.example.wkl_android.order.confirm.model.ConfirmOrder;
import com.example.wkl_android.order.confirm.model.ConfirmShopInfo;
import com.example.wkl_android.order.confirm.model.CouponSerializable;
import com.example.wkl_android.order.confirm.model.SubmitShopInfo;

import java.util.List;

public class ConfirmOrderAdapter extends BaseAdapter<ConfirmOrderAdapter.Holder, SubmitShopInfo> {
    public ConfirmOrderAdapter(List<SubmitShopInfo> dataList, Context context) {
        super(dataList, context);
    }

    @Override
    protected void onBindViewHolder(Holder holder, int position, SubmitShopInfo itemData) {
        holder.binding.name.setText(itemData.getBusinessName());
        holder.binding.rvShoppingCartItem.setAdapter(new ConfirmOrderItemAdapter(itemData.getOrderSubmitSlaveVOList(), holder.itemView.getContext()));

        holder.binding.price.setText("" + itemData.getMasterPaymentPrice());
        holder.binding.tvGoodsNumber.setText("共" + itemData.getOrderSubmitCount() + "件");
        holder.binding.tvFright.setText("￥" + itemData.getMasterFreightPirce());
        holder.binding.etRemark.setText(itemData.getRemark());

        if (itemData.getUserCoupon() != null)
            holder.binding.tvCoupon.setText("-" + itemData.getUserCoupon().getDiscountPrice());
        else
            holder.binding.tvCoupon.setText("");

        holder.binding.rlCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CouponSelectActivity.class);
                Bundle bundle = new Bundle();
                CouponSerializable serializable = new CouponSerializable();
                serializable.setList(itemData.getUserCouponList());
                bundle.putSerializable("coupon", serializable);

                CouponSerializable noCoupon = new CouponSerializable();
                noCoupon.setList(itemData.getNoMeetUserCouponList());
                bundle.putSerializable("nocoupon", noCoupon);

                intent.putExtra("type" , 2);
                intent.putExtras(bundle);

                context.startActivity(intent);
            }
        });

        holder.binding.etRemark.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                itemData.setRemark(holder.binding.etRemark.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public int getLayout(int type) {
        return R.layout.item_confirm_order;
    }

    class Holder extends BaseAdapter.BaseHolder<ItemConfirmOrderBinding> {
        public Holder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
