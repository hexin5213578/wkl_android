package com.example.wkl_android.orderpay.abapter;


import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wkl_android.R;
import com.example.wkl_android.utils.ViewBgUtils;

import java.util.List;

public class PayTypeAdapter extends BaseQuickAdapter<PayTypeInfo, BaseViewHolder> {
    public PayTypeAdapter(@Nullable List<PayTypeInfo> data) {
        super(R.layout.pay_type_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PayTypeInfo item) {
        helper.setText(R.id.tv_name, item.getName())
                .setImageResource(R.id.iv_img, item.getImg())
                .setImageResource(R.id.iv_check, item.isSelect() ? R.drawable.checked : R.drawable.no_check);


    }
}
