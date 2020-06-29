package com.example.wkl_android.faceback.adapter;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wkl_android.R;
import com.example.wkl_android.couponselect.bean.CouponInfo;
import com.example.wkl_android.faceback.bean.FadebackType;
import com.example.wkl_android.http.utils.HttpLogger;
import com.example.wkl_android.utils.ViewBgUtils;

import java.util.List;

public class FBTypeAdapter extends BaseQuickAdapter<FadebackType, BaseViewHolder> {
    public FBTypeAdapter(@Nullable List<FadebackType> data) {
        super(R.layout.fadeback_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FadebackType item) {

        helper.setText(R.id.tv_title, item.getTitle())
                .setText(R.id.tv_msg, item.getMsg())
                .setImageResource(R.id.iv_check, item.isSelect() ? R.mipmap.ic_fb_check : R.mipmap.ic_fb_nocheck);


    }
}