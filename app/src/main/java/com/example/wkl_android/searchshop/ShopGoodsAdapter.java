package com.example.wkl_android.searchshop;

import android.content.Intent;
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
import com.example.wkl_android.good.ui.GoodsActivity;
import com.example.wkl_android.searchshop.bean.ShopGoodsVo;
import com.example.wkl_android.utils.BitmapUtil;
import com.example.wkl_android.utils.ViewBgUtils;
import com.example.wkl_android.wholesale_market.bean.MarketInfo;

import java.util.List;

public class ShopGoodsAdapter extends BaseQuickAdapter<ShopGoodsVo, BaseViewHolder> {

    public ShopGoodsAdapter(@Nullable List<ShopGoodsVo> data) {
        super(R.layout.shop_goods_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopGoodsVo item) {

        ImageView iv_img = helper.getView(R.id.iv_img);
        helper.setText(R.id.tv_price , item.getSkuPrice());
        BitmapUtil.showImage(mContext , item.getSpuTitleImage() , iv_img);

        helper.getView(R.id.ll_bg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoodsActivity.toThisActivity(mContext, "0", item.getSpuId(), item.getSkuPrice(), item.getSpuTitle(), item.getSpuTitleImage());

            }
        });


    }
}