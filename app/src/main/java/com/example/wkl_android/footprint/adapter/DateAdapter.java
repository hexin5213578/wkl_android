package com.example.wkl_android.footprint.adapter;

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

public class DateAdapter extends BaseAdapter<String, DateAdapter.ViewHolder> {
    public DateAdapter(Context context, List<String> data) {
        super(context, data);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_date, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (position == 0) {
            holder.rvFootprint.setVisibility(View.VISIBLE);
        }
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            list.add("");
        }
        holder.rvFootprint.setAdapter(new FootprintAdapter(context, list));
        holder.tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.rvFootprint.getVisibility()==View.GONE){
                    holder.rvFootprint.setVisibility(View.VISIBLE);
                }else{
                    holder.rvFootprint.setVisibility(View.GONE);
                }
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.rvFootprint)
        RecyclerView rvFootprint;
        @BindView(R.id.tvDate)
        View tvDate;

         ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
