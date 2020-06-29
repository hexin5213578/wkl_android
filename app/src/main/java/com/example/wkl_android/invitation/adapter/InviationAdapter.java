package com.example.wkl_android.invitation.adapter;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wkl_android.R;
import com.example.wkl_android.collection.bean.CollectionGoods;
import com.example.wkl_android.invitation.bean.InviationUser;
import com.example.wkl_android.utils.BitmapUtil;
import com.example.wkl_android.utils.StringUtil;

import java.util.List;

public class InviationAdapter extends BaseQuickAdapter<InviationUser, BaseViewHolder> {

    public InviationAdapter(@Nullable List<InviationUser> data) {
        super(R.layout.inviaton_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, InviationUser item) {


    }
}
