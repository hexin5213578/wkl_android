package com.example.wkl_android.widget.rv.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.example.wkl_android.widget.rv.adapter.wrapper.BaseWrapper;

/**
 * @author li
 * @since 2019-05-27
 */
public class EmptyRecyclerView extends RecyclerView {

    private View emptyView;
    private static final String TAG = "EmptyRecyclerView";

    public EmptyRecyclerView(Context context) {
        super(context);
    }

    public EmptyRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EmptyRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private final AdapterDataObserver observer = new AdapterDataObserver() {
        @Override
        public void onChanged() {
            checkIfEmpty();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            Log.i(TAG, "onItemRangeInserted" + itemCount);
            checkIfEmpty();
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            checkIfEmpty();
        }
    };

    private void checkIfEmpty() {
        Adapter adapter = getAdapter();
        if (emptyView == null) {
            return;
        }
        if (adapter == null) {
            emptyView.setVisibility(VISIBLE);
            setVisibility(GONE);
            return;
        }
        boolean emptyViewVisible;
        if (adapter instanceof BaseWrapper) {
            emptyViewVisible = ((BaseWrapper) adapter).getDataCount() == 0;
        } else {
            emptyViewVisible = adapter.getItemCount() == 0;
        }
        emptyView.setVisibility(emptyViewVisible ? VISIBLE : GONE);
        setVisibility(emptyViewVisible ? GONE : VISIBLE);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        final Adapter oldAdapter = getAdapter();
        if (oldAdapter != null) {
            oldAdapter.unregisterAdapterDataObserver(observer);
        }
        super.setAdapter(adapter);
        if (adapter != null) {
            adapter.registerAdapterDataObserver(observer);
        }
        checkIfEmpty();
    }

    //设置没有内容时，提示用户的空布局
    public void setEmptyView(View emptyView) {
        this.emptyView = emptyView;
        checkIfEmpty();
    }
}
