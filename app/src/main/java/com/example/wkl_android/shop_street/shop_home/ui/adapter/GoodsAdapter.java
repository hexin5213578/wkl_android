package com.example.wkl_android.shop_street.shop_home.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.wkl_android.R;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.databinding.ItemShopGoodsListBinding;
import com.example.wkl_android.good.model.GoodsListBean;
import com.example.wkl_android.good.ui.GoodsActivity;
import com.example.wkl_android.widget.rv.adapter.BaseAdapter;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by szx
 * on 2020/1/18/018
 */
public class GoodsAdapter extends BaseAdapter<GoodsListBean.DataBean, GoodsAdapter.ViewHolder> {
    private String name;

    public void setData(List<GoodsListBean.DataBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public GoodsAdapter(Context context, List<GoodsListBean.DataBean> data) {
        super(context, data);
    }

    public GoodsAdapter(Context context, List<GoodsListBean.DataBean> data, String name) {
        super(context, data);
        this.name = name;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_shop_goods_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.binding.tvName.setText(data.get(position).getSkuTitle());
        String imgUrl = Common.getResizeImg(data.get(position).getSkuTitleImage(), 60, 60);
        Glide.with(holder.itemView.getContext()).load(imgUrl).into(holder.binding.image);
        holder.binding.price.setText(data.get(position).getSkuPrice() + "");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoodsActivity.toThisActivity(context, "0", data.get(position).getSpuId(),
                        data.get(position).getSkuPrice() + "", data.get(position).getSkuTitle(), imgUrl);
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ItemShopGoodsListBinding binding;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);

        }
    }
}
