package com.example.wkl_android.widget.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * @author li
 * @since 2018/9/16
 */
public abstract class BasePagerAdapter<T> extends PagerAdapter {

    protected Context mContext;
    protected LayoutInflater mInflater;
    protected List<T> mData;

    private List<WeakReference<View>> viewList;

    public BasePagerAdapter(Context mContext, List<T> mData) {
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(mContext);
        this.mData = mData;
        viewList = new ArrayList<>();
    }

    protected T getItem(int position) {
        if (mData == null) {
            return null;
        }
        return mData.get(position % mData.size());
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object obj) {
        return view == obj;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view;
        if (viewList.size() > 0 && viewList.get(0) != null) {
            view = getView(viewList.get(0).get(), position, getItem(position));
            viewList.remove(0);
        } else {
            view = getView(null, position, getItem(position));
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        if (object instanceof View) {
            container.removeView((View) object);
            viewList.add(new WeakReference<>((View) object));
        }
    }

    protected abstract View getView(View convertView, int position, T bean);
}
