package com.example.wkl_android.address;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.baidu.mapapi.search.core.PoiInfo;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wkl_android.R;
import com.example.wkl_android.order.detail.model.OrderGoodsInfo;
import com.example.wkl_android.utils.BitmapUtil;

import java.util.List;

public class SearchAdapter extends BaseQuickAdapter<PoiInfo, BaseViewHolder> {
    public SearchAdapter(@Nullable List<PoiInfo> data) {
        super(R.layout.search_add_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PoiInfo item) {

        helper.setText(R.id.tv_name, item.getName())
                .setText(R.id.tv_type, item.getAddress());
    }

}
