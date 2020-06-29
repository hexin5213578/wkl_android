package com.example.wkl_android.shop_street.shop_home.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wkl_android.R;
import com.example.wkl_android.good.ui.GoodsActivity;
import com.example.wkl_android.good.model.GoodsListBean;
import com.example.wkl_android.widget.rv.adapter.BaseAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShopGoodsAdapter extends BaseAdapter<GoodsListBean.DataBean, ShopGoodsAdapter.ViewHolder> {
    private String name;
    public ShopGoodsAdapter(Context context, List<GoodsListBean.DataBean> data) {
        super(context, data);
    }
    public ShopGoodsAdapter(Context context, List<GoodsListBean.DataBean> data,String name) {
        super(context, data);
        this.name = name;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_shop_goods, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(!TextUtils.isEmpty(name)){
            holder.tvName.setText(name);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, GoodsActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvName)
        TextView tvName;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
