package com.example.wkl_android.orderpaycheck.adapter;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wkl_android.R;
import com.example.wkl_android.orderpaycheck.bean.OrderCheckVo;

import java.util.List;

public class CheckShopAdapter extends BaseQuickAdapter<OrderCheckVo, BaseViewHolder> {
    public CheckShopAdapter(@Nullable List<OrderCheckVo> data) {
        super(R.layout.order_check_shop, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderCheckVo item) {

        helper.setText(R.id.tv_shop , item.getBusinessName());
        RecyclerView rv_goods = helper.getView(R.id.rv_goods);
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        rv_goods.setLayoutManager(manager);

        CheckGoodsAdapter goodsAdapter = new CheckGoodsAdapter(item.getOrderListSlaveVOList());
        rv_goods.setAdapter(goodsAdapter);

    }
}