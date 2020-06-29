package com.example.wkl_android.select_city.adapter;

import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wkl_android.R;
import com.example.wkl_android.main.shop.address.select.model.bean.SelectSites;
import com.example.wkl_android.utils.ViewBgUtils;

import java.util.List;

public class SelectCityAdapter extends BaseQuickAdapter<SelectSites, BaseViewHolder> {
    public SelectCityAdapter(@Nullable List<SelectSites> data) {
        super(R.layout.item_site, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SelectSites item) {
        helper.setText(R.id.tvSite, item.getName());
    }
}
