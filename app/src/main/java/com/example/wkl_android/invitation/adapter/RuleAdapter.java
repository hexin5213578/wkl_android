package com.example.wkl_android.invitation.adapter;

import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wkl_android.R;
import com.example.wkl_android.invitation.bean.InviationUser;
import com.example.wkl_android.invitation.bean.InviationRule;
import com.example.wkl_android.utils.ViewBgUtils;

import java.util.List;

public class RuleAdapter extends BaseQuickAdapter<InviationRule, BaseViewHolder> {

    public RuleAdapter(@Nullable List<InviationRule> data) {
        super(R.layout.inviation_rule_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, InviationRule item) {
        TextView tv_num = helper.getView(R.id.tv_num);
        ViewBgUtils.setBg(tv_num , "e9e9e9" , 10);

    }
}
