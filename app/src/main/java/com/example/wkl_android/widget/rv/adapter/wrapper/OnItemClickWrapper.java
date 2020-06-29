package com.example.wkl_android.widget.rv.adapter.wrapper;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author szx
 * @since 2019-05-11
 */
public class OnItemClickWrapper extends BaseWrapper<RecyclerView.ViewHolder>
        implements View.OnClickListener {

    /**
     * 点击监听器
     */
    private OnItemClickListener onItemClickListener;

    public OnItemClickWrapper(RecyclerView.Adapter adapter) {
        super(adapter);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return adapter.onCreateViewHolder(viewGroup, i);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        // 正常View设置点击事件
        holder.itemView.setOnClickListener(this);
        holder.itemView.setTag(position);
        adapter.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return adapter.getItemCount();
    }

    @Override
    public void onClick(View v) {
        if (onItemClickListener != null) {
            onItemClickListener.onItemClick(v, (int) v.getTag());
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * Item点击监听
     */
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
