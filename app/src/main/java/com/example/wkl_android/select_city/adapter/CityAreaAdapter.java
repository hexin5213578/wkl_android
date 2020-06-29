package com.example.wkl_android.select_city.adapter;

import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wkl_android.R;
import com.example.wkl_android.main.shop.address.select.model.bean.SelectSites;
import com.example.wkl_android.utils.ViewBgUtils;

import java.util.List;

public class CityAreaAdapter extends BaseQuickAdapter<SelectSites, BaseViewHolder> {
    public CityAreaAdapter(@Nullable List<SelectSites> data) {
        super(R.layout.area_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SelectSites item) {
        helper.setText(R.id.tv_area, item.getName());
        TextView tv_area = helper.getView(R.id.tv_area);

        if (item.isSelect()) {
            ViewBgUtils.setBg(tv_area, "#f6d6ce", "#f72607", 3);
            tv_area.setTextColor(ContextCompat.getColor(mContext, R.color.color_f72607));

        } else {
            ViewBgUtils.setBg(tv_area, "#ffffff", "#dcdcdc", 3);
            tv_area.setTextColor(ContextCompat.getColor(mContext, R.color.color_999999));

        }
    }
}
