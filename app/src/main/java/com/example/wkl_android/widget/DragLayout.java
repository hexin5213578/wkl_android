package com.example.wkl_android.widget;


import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.customview.widget.ViewDragHelper;

import com.example.wkl_android.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author li
 * @since 2019-09-09
 */
public class DragLayout extends FrameLayout {
    private ViewDragHelper mHelper;

    public DragLayout(@NonNull Context context) {
        this(context, null);
    }

    public DragLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DragLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mHelper = ViewDragHelper.create(this, new ViewDragHelper.Callback() {

            private int mCurrentTop;
            private int mCurrentLeft;

            @Override
            public boolean tryCaptureView(@NonNull View child, int pointerId) {
                for (View dragChild : mDragChildren) {
                    if (child == dragChild) {
                        return true;
                    }
                }
                return false;
            }

            @Override
            public int clampViewPositionHorizontal(@NonNull View child, int left, int dx) {
                // 最小 x 坐标值不能小于 leftBound
                final int leftBound = getPaddingLeft();
                // 最大 x 坐标值不能大于 rightBound
                final int rightBound = getWidth() - child.getWidth() - getPaddingRight();
                final int newLeft = Math.min(Math.max(left, leftBound), rightBound);
                mCurrentLeft = newLeft;
                return newLeft;
            }

            @Override
            public int clampViewPositionVertical(@NonNull View child, int top, int dy) {
                // 最小 y 坐标值不能小于 topBound
                final int topBound = getPaddingTop();
                // 最大 y 坐标值不能大于 bottomBound
                final int bottomBound = getHeight() - child.getHeight() - getPaddingBottom();
                final int newTop = Math.min(Math.max(top, topBound), bottomBound);
                mCurrentTop = newTop;
                return newTop;
            }

            @Override
            public void onViewReleased(@NonNull View releasedChild, float xvel, float yvel) {
                super.onViewReleased(releasedChild, xvel, yvel);
                // 释放View的宽度
                int childWidth = releasedChild.getWidth();
                int parentWidth = getWidth();
                int leftBound = getPaddingLeft();// 左边缘
                int rightBound = getWidth() - releasedChild.getWidth() - getPaddingRight();// 右边缘
                // 方块的中点超过 ViewGroup 的中点时，滑动到左边缘，否则滑动到右边缘
                if ((childWidth / 2 + mCurrentLeft) < parentWidth / 2) {
                    mHelper.settleCapturedViewAt(leftBound, mCurrentTop);
                } else {
                    mHelper.settleCapturedViewAt(rightBound, mCurrentTop);
                }
                invalidate();
            }

            @Override
            public int getViewHorizontalDragRange(@NonNull View child) {
                return getMeasuredWidth() - child.getMeasuredWidth();
            }

            @Override
            public int getViewVerticalDragRange(@NonNull View child) {
                return getMeasuredHeight() - child.getMeasuredHeight();
            }
        });
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mHelper != null && mHelper.continueSettling(true)) {
            invalidate();
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        getDragChildren();
    }

    private List<View> mDragChildren = new ArrayList<>();

    private void getDragChildren() {
        mDragChildren.clear();
        for (int i = 0, childCount = getChildCount(); i < childCount; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != VISIBLE) {
                continue;
            }
            LayoutParams lp = (LayoutParams) child.getLayoutParams();
            if (lp.isDragEnabled()) {
                mDragChildren.add(child);
            }
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mHelper.processTouchEvent(event);
        return true;
    }

    public static class LayoutParams extends FrameLayout.LayoutParams {
        private boolean dragEnabled;

        public LayoutParams(@NonNull Context c, @Nullable AttributeSet attrs) {
            super(c, attrs);
            TypedArray a = c.obtainStyledAttributes(attrs, R.styleable.DragLayout_Layout);
            dragEnabled = a.getBoolean(R.styleable.DragLayout_Layout_layout_dragEnabled, false);
            a.recycle();
        }

        public boolean isDragEnabled() {
            return dragEnabled;
        }

        public void setDragEnabled(boolean dragEnabled) {
            this.dragEnabled = dragEnabled;
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof LayoutParams;
    }
}
