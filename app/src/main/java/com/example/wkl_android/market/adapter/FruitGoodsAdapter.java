package com.example.wkl_android.market.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wkl_android.R;
import com.example.wkl_android.widget.rv.adapter.BaseAdapter;

import java.util.List;

/**
 * Created by szx
 * on 2020/3/5/005
 */
public class FruitGoodsAdapter extends BaseAdapter<String, RecyclerView.ViewHolder> {
    public FruitGoodsAdapter(Context context, List<String> data) {
        super(context, data);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==R.id.type_default){
            View view = inflater.inflate(R.layout.item_fruits,parent,false);
            return new DefaultViewHolder(view);
        }else{
            View view = inflater.inflate(R.layout.item_fruits_img,parent,false);
            return new DiscountViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemViewType(int position) {
        if(position==0||position==5){
            return R.id.type_header;
        }
        return R.id.type_default;

    }

    class DefaultViewHolder extends RecyclerView.ViewHolder{

        public DefaultViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }


    class DiscountViewHolder extends RecyclerView.ViewHolder{

        public DiscountViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
