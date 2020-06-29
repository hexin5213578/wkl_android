package com.example.wkl_android.wholesale_market.adapter;


import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wkl_android.R;
import com.example.wkl_android.utils.ViewBgUtils;
import com.example.wkl_android.wholesale_market.bean.WholesaleMarketInfo;

import java.util.List;

public class TypeAdapter extends BaseQuickAdapter<WholesaleMarketInfo, BaseViewHolder> {
    public TypeAdapter(@Nullable List<WholesaleMarketInfo> data) {
        super(R.layout.mark_type_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WholesaleMarketInfo item) {
        helper.setText(R.id.tv_name , item.getGoodsClassifyVO().getClassifyName())
        .setBackgroundColor(R.id.tv_name , item.isSelect() ? ContextCompat.getColor(mContext , R.color.color_f3f3f3):ContextCompat.getColor(mContext , R.color.color_ffffff));

    }
}
