package com.example.wkl_android.widget.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * {@link android.widget.ListView} 和 {@link android.widget.GridView}及其子类设置 Adapter 的基类
 * 简单适配器可直接使用,复杂的另行解决
 *
 * @author li
 * @since 2018/1/19
 */

public abstract class BaseListAdapter<T> extends BaseAdapter {

    protected Context mContext;
    protected LayoutInflater mInflater;
    protected List<T> mData;

    public BaseListAdapter(Context context, List<T> data) {
        this.mContext = context;
        this.mData = data;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public T getItem(int position) {
        return mData == null ? null : mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent, getItem(position));
    }

    public abstract View getView(int position, View convertView, ViewGroup parent, T bean);
}
