package com.example.wkl_android.errands.pick_up.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wkl_android.R;
import com.example.wkl_android.errands.pick_up.bean.PickUpBean;
import com.example.wkl_android.widget.rv.adapter.BaseAdapter;

import java.util.List;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PickUpGoodsAdapter extends BaseAdapter<PickUpBean, PickUpGoodsAdapter.ViewHolder> {
    public PickUpGoodsAdapter(Context context, List<PickUpBean> data) {
        super(context, data);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_pick_up, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PickUpBean bean = data.get(position);
        holder.tvName.setText(bean.getName());
        holder.tvName.setCompoundDrawablesRelativeWithIntrinsicBounds(0,
                bean.getImgResource(), 0, 0);
        if (bean.isSelect()) {
            holder.ivChecked.setVisibility(View.VISIBLE);
        } else {
            holder.ivChecked.setVisibility(View.GONE);
        }
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onClick(position);
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.ivChecked)
        View ivChecked;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnClickListener {
        void onClick(int position);
    }

    private OnClickListener listener;

    public void setListener(OnClickListener listener) {
        this.listener = listener;
    }
}
