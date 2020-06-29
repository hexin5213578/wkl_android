package com.example.wkl_android.shop_street.shop_home.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wkl_android.R;
import com.example.wkl_android.widget.rv.adapter.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShopCategoryAdapter extends BaseAdapter<String, ShopCategoryAdapter.ViewHolder> {

    public ShopCategoryAdapter(Context context, List<String> data) {
        super(context, data);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_shop_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add("");
        }
        holder.rvCategoryItem.setAdapter(new CategoryItemAdapter(context, list));
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.rvCategoryItem)
        RecyclerView rvCategoryItem;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
