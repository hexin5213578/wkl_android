package com.example.wkl_android.collection.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wkl_android.R;
import com.example.wkl_android.collection.bean.CollectionGoods;
import com.example.wkl_android.searchshop.ShopGoodsAdapter;
import com.example.wkl_android.searchshop.bean.ShopVo;
import com.example.wkl_android.shop_street.shop_detail.ui.activity.ShopMessageActivity;
import com.example.wkl_android.utils.BitmapUtil;
import com.example.wkl_android.utils.StringUtil;
import com.example.wkl_android.utils.ViewBgUtils;
import com.example.wkl_android.widget.rv.adapter.BaseAdapter;

import java.util.List;

public class CollectionAdapter extends BaseQuickAdapter<CollectionGoods, BaseViewHolder> {

    public CollectionAdapter(@Nullable List<CollectionGoods> data) {
        super(R.layout.item_collection, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CollectionGoods item) {

        ImageView iv_check = helper.getView(R.id.iv_check);
        if (item.isCheck()) {
            iv_check.setVisibility(View.VISIBLE);
            if (item.isSelect()) {
                iv_check.setImageResource(R.drawable.checked);
            } else {
                iv_check.setImageResource(R.drawable.no_check);
            }

        } else {
            iv_check.setVisibility(View.GONE);
        }

        ImageView iv_img = helper.getView(R.id.iv_img);
        BitmapUtil.showImage(mContext, item.getSkuImage(), iv_img);

        helper.setText(R.id.tv_title, item.getSkuTitle())
                .setText(R.id.tv_price, StringUtil.changeSizeByDot(item.getSkuPrice(), 0.7f))
                .setText(R.id.tv_type, item.getSkuStandard());

    }
}