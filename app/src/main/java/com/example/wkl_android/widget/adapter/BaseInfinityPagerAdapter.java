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
 * @since 2018/11/29
 */
public abstract class BaseInfinityPagerAdapter<T> extends PagerAdapter {

    protected Context mContext;
    protected List<T> mData;
    protected LayoutInflater mInflater;

    private List<WeakReference<View>> viewList;

    public BaseInfinityPagerAdapter(Context mContext, List<T> mData) {
        this.mContext = mContext;
        this.mData = mData;
        viewList = new ArrayList<>();
        mInflater = LayoutInflater.from(mContext);
    }

    public int getRealCount() {
        return mData == null ? 0 : mData.size();
    }

    protected T getItem(int position) {
        if (mData == null) {
            return null;
        }
        if (getRealCount() == 0) {
            return null;
        }
        return mData.get(position % getRealCount());
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
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
