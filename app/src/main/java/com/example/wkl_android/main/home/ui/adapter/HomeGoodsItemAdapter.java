package com.example.wkl_android.main.home.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wkl_android.R;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.good.model.GoodsListBean;
import com.example.wkl_android.good.ui.GoodsActivity;
import com.example.wkl_android.utils.StringUtil;
import com.example.wkl_android.widget.rv.adapter.BaseAdapter;
import com.sunfusheng.GlideImageView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 商品列表
 * Created by szx
 * on 2020/1/17/017
 */
public class HomeGoodsItemAdapter extends BaseAdapter<GoodsListBean.DataBean, HomeGoodsItemAdapter.ViewHolder> {

    public static final int TYPE_GRID = 0;
    public static final int TYPE_LINE = 1;
    int type;

    public HomeGoodsItemAdapter(Context context, List<GoodsListBean.DataBean> data, int type) {
        super(context, data);
        this.type = type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(type == TYPE_GRID){
            view = inflater.inflate(R.layout.item_goods, parent, false);
        }else {
            view = inflater.inflate(R.layout.item_goods_line, parent, false);
        }

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GoodsListBean.DataBean dataBean = data.get(position);
        String imgUrl = Common.getResizeImg(dataBean.getSpuTitleImage(), 300, 300);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoodsActivity.toThisActivity(context, "0", dataBean.getSpuId(), dataBean.getSkuPrice(), dataBean.getSkuTitle(), imgUrl);
            }
        });

        holder.ivPointIcon.load(imgUrl);
        holder.tvName.setText(dataBean.getSpuTitle() + "");
        holder.tvSkuName.setText(dataBean.getSkuType() + "|" + dataBean.getProductName());
        holder.tvUnitPrice.setText(StringUtil.changeSizeByDot(dataBean.getSkuPrice() + "" , 0.7f));
        holder.tvNumber.setText(dataBean.getSkuSales() + "人付款");
        if (dataBean.getSkuFreight() <= 0) {
            holder.tvFreight.setVisibility(View.VISIBLE);
        } else {
            holder.tvFreight.setVisibility(View.INVISIBLE);
        }
        holder.tvShopName.setText(dataBean.getBusinessName());
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivPointIcon)
        GlideImageView ivPointIcon;
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvUnitPrice)
        TextView tvUnitPrice;
        @BindView(R.id.tvNumber)
        TextView tvNumber;
        @BindView(R.id.tvSkuName)
        TextView tvSkuName;
        @BindView(R.id.tvFreight)
        TextView tvFreight;
        @BindView(R.id.tvShopName)
        TextView tvShopName;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
