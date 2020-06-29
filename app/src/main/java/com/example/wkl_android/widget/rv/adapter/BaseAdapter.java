package com.example.wkl_android.widget.rv.adapter;

import android.content.Context;
import android.view.LayoutInflater;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * @author li
 * @since 2019-05-09
 */
public abstract class BaseAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    protected Context context;
    protected List<T> data;
    protected LayoutInflater inflater;

    public BaseAdapter(Context context, List<T> data) {
        this.context = context.getApplicationContext();
        this.data = data;
        this.inflater = LayoutInflater.from(context);
    }
    public BaseAdapter(Context context) {
        this.context = context.getApplicationContext();
        this.inflater = LayoutInflater.from(context);
    }

    public T getItem(int position) {
        return data != null && position < data.size() ? data.get(position) : null;
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }
}
