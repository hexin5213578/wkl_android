package com.example.wkl_android.searchshop;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wkl_android.R;
import com.example.wkl_android.good.model.GoodsListBean;
import com.example.wkl_android.searchshop.bean.ShopVo;
import com.example.wkl_android.shop_street.shop_detail.ui.activity.ShopMessageActivity;
import com.example.wkl_android.utils.BitmapUtil;
import com.example.wkl_android.utils.ViewBgUtils;

import java.util.ArrayList;
import java.util.List;

public class ShopAdapter extends BaseQuickAdapter<ShopVo, BaseViewHolder> {

    public ShopAdapter(@Nullable List<ShopVo> data) {
        super(R.layout.shop_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopVo item) {
        TextView tv_in_shop = helper.getView(R.id.tv_in_shop);
        ViewBgUtils.setBg(tv_in_shop, "#ffffff", "#ff1100", 1, 14);

        RecyclerView rv_goods = helper.getView(R.id.rv_goods);
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_goods.setLayoutManager(manager);

        helper.setText(R.id.tv_name, item.getBusinessName());

        ImageView iv_logo = helper.getView(R.id.iv_logo);
        BitmapUtil.showImage(mContext, item.getBusinessLogo(), iv_logo);




        ShopGoodsAdapter goodsAdapter = new ShopGoodsAdapter(item.getGoodsSearchAppVOList());
        rv_goods.setAdapter(goodsAdapter);


        LinearLayout ll_tab = helper.getView(R.id.ll_tab);

        ll_tab.setVisibility(View.GONE);
        View tab = View.inflate(mContext, R.layout.shop_tab_item, null);
        TextView tv_tab = tab.findViewById(R.id.tv_tab);
        ViewBgUtils.setBg(tv_tab, "#f6f6f6", 15);

        ll_tab.removeAllViews();
        ll_tab.addView(tab);

        tv_in_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShopMessageActivity.StartActivity(mContext , item.getBusinessId());
            }
        });

    }
}