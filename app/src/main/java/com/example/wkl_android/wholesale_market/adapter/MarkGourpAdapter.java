package com.example.wkl_android.wholesale_market.adapter;


import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wkl_android.R;
import com.example.wkl_android.wholesale_market.bean.WholesaleMarketInfo;
import com.example.wkl_android.widget.rv.ScrollRecyclerView;

import java.util.List;

public class MarkGourpAdapter extends BaseQuickAdapter<WholesaleMarketInfo, BaseViewHolder> {
    public MarkGourpAdapter(@Nullable List<WholesaleMarketInfo> data) {
        super(R.layout.mark_group_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WholesaleMarketInfo item) {
        RecyclerView rv_market = helper.getView(R.id.rv_market);
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_market.setLayoutManager(manager);
        rv_market.setAdapter(new MrakAdapter(item.getWholesaleMarketVOList()));
    }
}
