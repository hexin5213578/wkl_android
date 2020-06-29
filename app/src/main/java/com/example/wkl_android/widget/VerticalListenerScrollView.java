package com.example.wkl_android.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.core.widget.NestedScrollView;

/**
 * 带Y轴滚动监听ScrollView
 *
 * @author li
 * @since 2018/12/6
 */
public class VerticalListenerScrollView extends NestedScrollView {

    private OnScrollYListener onScrollYListener;

    public VerticalListenerScrollView(Context context) {
        super(context);
    }

    public VerticalListenerScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VerticalListenerScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldL, int oldT) {
        super.onScrollChanged(l, t, oldL, oldT);
        if (onScrollYListener != null) {
            onScrollYListener.onScroll(t);
        }
    }

    public void setOnScrollYListener(OnScrollYListener listener) {
        this.onScrollYListener = listener;
    }

    public interface OnScrollYListener {
        void onScroll(int scrollY);
    }

    private int downY;
    private int mTouchSlop;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        int action = e.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downY = (int) e.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                int moveY = (int) e.getRawY();
                if (Math.abs(moveY - downY) > mTouchSlop) {
                    return true;
                }
        }
        return super.onInterceptTouchEvent(e);
    }
}
