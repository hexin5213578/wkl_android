package com.example.wkl_android.searchinput.adapter;


import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wkl_android.R;
import com.example.wkl_android.utils.ViewBgUtils;

import java.util.List;

public class SearchHistoryAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public SearchHistoryAdapter(@Nullable List<String> data) {
        super(R.layout.search_history_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_msg , item);

        TextView msg = (TextView) helper.getView(R.id.tv_msg);
        ViewBgUtils.setBg(msg , "#f6f6f6" , 30);
    }
}
