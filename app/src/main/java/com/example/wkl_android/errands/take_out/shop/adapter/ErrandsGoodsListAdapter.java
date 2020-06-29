package com.example.wkl_android.errands.take_out.shop.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wkl_android.R;
import com.example.wkl_android.good.ui.GoodsActivity;
import com.example.wkl_android.widget.rv.adapter.BaseAdapter;

import java.util.List;

import butterknife.ButterKnife;

public class ErrandsGoodsListAdapter extends BaseAdapter<String, ErrandsGoodsListAdapter.ViewHolder> {
    public ErrandsGoodsListAdapter(Context context, List<String> data) {
        super(context, data);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_errands_goods, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(view ->
                context.startActivity(new Intent(context, GoodsActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)));
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
