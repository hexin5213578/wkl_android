package com.example.wkl_android.errands.take_out.shop.all_list.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.example.wkl_android.R;
import com.example.wkl_android.good.model.GoodsListBean;
import com.example.wkl_android.widget.rv.adapter.BaseAdapter;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ShopErrandsGridAdapter extends BaseAdapter<GoodsListBean.GoodsPlateVOList, ShopErrandsGridAdapter.ViewHolder> {
    public ShopErrandsGridAdapter(Context context, List<GoodsListBean.GoodsPlateVOList> data) {
        super(context, data);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_shop_errands_grid,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
