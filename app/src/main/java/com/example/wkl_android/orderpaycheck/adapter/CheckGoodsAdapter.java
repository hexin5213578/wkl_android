package com.example.wkl_android.orderpaycheck.adapter;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wkl_android.R;
import com.example.wkl_android.orderpaycheck.bean.OrderCheckGoods;
import com.example.wkl_android.orderpaycheck.bean.OrderCheckVo;

import java.util.List;

public class CheckGoodsAdapter extends BaseQuickAdapter<OrderCheckGoods, BaseViewHolder> {
    public CheckGoodsAdapter(@Nullable List<OrderCheckGoods> data) {
        super(R.layout.check_goods_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderCheckGoods item) {

            helper.setText(R.id.tv_name , item.getOrderSlaveCommodityTitle())
                    .setText(R.id.tv_num , "x"+item.getOrderSlaveCommodityCount())
                    .setText(R.id.tv_price , "￥"+item.getOrderSlaveCommodityPrices());

    }
}