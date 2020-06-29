package com.example.wkl_android.widget.rv.adapter.wrapper;

import androidx.recyclerview.widget.RecyclerView;

/**
 * @author li
 * @since 2019-05-27
 */
public abstract class BaseWrapper<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    protected RecyclerView.Adapter adapter;

    public BaseWrapper(RecyclerView.Adapter adapter) {
        this.adapter = adapter;
    }

    public final int getDataCount() {
        if (adapter instanceof BaseWrapper) {
            return ((BaseWrapper) adapter).getDataCount();
        } else {
            return adapter.getItemCount();
        }
    }

    @Override
    public int getItemViewType(int position) {
        return adapter.getItemViewType(position);
    }
}
