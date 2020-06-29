package com.example.wkl_android.main.fragment.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import com.example.wkl_android.R;
import com.example.wkl_android.errands.take_out.shop.ShopErrandsActivity;
import com.example.wkl_android.good.model.GoodsListBean;
import com.example.wkl_android.widget.rv.adapter.BaseAdapter;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.ButterKnife;

public class ShopSelectedAdapter extends BaseAdapter<GoodsListBean.GoodsPlateVOList, ShopSelectedAdapter.ViewHolder> {
    public ShopSelectedAdapter(Context context, List<GoodsListBean.GoodsPlateVOList> data) {
        super(context, data);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_shop_selected, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, ShopErrandsActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
