package com.example.wkl_android.wholesale_market.adapter;


import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wkl_android.R;
import com.example.wkl_android.utils.BitmapUtil;
import com.example.wkl_android.utils.ViewBgUtils;
import com.example.wkl_android.wholesale_market.bean.MarketInfo;
import com.example.wkl_android.wholesale_market.bean.WholesaleMarketInfo;

import java.util.List;

public class MrakAdapter extends BaseQuickAdapter<MarketInfo, BaseViewHolder> {
    public MrakAdapter(@Nullable List<MarketInfo> data) {
        super(R.layout.mark_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MarketInfo item) {
        ImageView img = helper.getView(R.id.iv_img);
        BitmapUtil.showImage(mContext , item.getMarketImage() , img);
        helper.setText(R.id.tv_name , item.getMarketName());
        helper.setText(R.id.tv_address , "地址："+item.getMarketDetail())
        .setText(R.id.tv_phone , "电话："+item.getMarketPhone());
    }
}
