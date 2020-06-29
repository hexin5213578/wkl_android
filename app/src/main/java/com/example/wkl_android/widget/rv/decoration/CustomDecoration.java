package com.example.wkl_android.widget.rv.decoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.DrawableRes;
import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

/**
 * RecyclerView 分割线(不画最底部的分割线)
 *
 * @author li
 * @since 2019-05-13
 */
public class CustomDecoration extends RecyclerView.ItemDecoration {
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    private Drawable mDivider;
    @Orientation
    private int mOrientation;
    private final Rect mBounds = new Rect();

    public CustomDecoration(Context context, @Orientation int orientation, @DrawableRes int drawableRes) {
        this.mDivider = ContextCompat.getDrawable(context, drawableRes);
        this.setOrientation(orientation);
    }

    public void setOrientation(@Orientation int orientation) {
        this.mOrientation = orientation;
    }

    public void setDrawable(@NonNull Drawable drawable) {
        this.mDivider = drawable;
    }

    public void onDraw(@NonNull Canvas canvas, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        if (parent.getLayoutManager() != null && this.mDivider != null) {
            switch (this.mOrientation) {
                case HORIZONTAL:
                    this.drawHorizontal(canvas, parent);
                    break;
                case VERTICAL:
                    this.drawVertical(canvas, parent);
                    break;
            }
        }
    }

    private void drawVertical(Canvas canvas, RecyclerView parent) {
        canvas.save();
        int left;
        int right;
        if (parent.getClipToPadding()) {
            left = parent.getPaddingLeft();
            right = parent.getWidth() - parent.getPaddingRight();
            canvas.clipRect(left, parent.getPaddingTop(), right, parent.getHeight() - parent.getPaddingBottom());
        } else {
            left = 0;
            right = parent.getWidth();
        }

        int childCount = parent.getChildCount();

        for (int i = 0; i < childCount - 1; ++i) {
            View child = parent.getChildAt(i);
            parent.getDecoratedBoundsWithMargins(child, this.mBounds);
            int bottom = this.mBounds.bottom + Math.round(child.getTranslationY());
            int top = bottom - this.mDivider.getIntrinsicHeight();
            this.mDivider.setBounds(left, top, right, bottom);
            this.mDivider.draw(canvas);
        }

        canvas.restore();
    }

    private void drawHorizontal(Canvas canvas, RecyclerView parent) {
        canvas.save();
        int top;
        int bottom;
        if (parent.getClipToPadding()) {
            top = parent.getPaddingTop();
            bottom = parent.getHeight() - parent.getPaddingBottom();
            canvas.clipRect(parent.getPaddingLeft(), top, parent.getWidth() - parent.getPaddingRight(), bottom);
        } else {
            top = 0;
            bottom = parent.getHeight();
        }

        int childCount = parent.getChildCount();

        for (int i = 0; i < childCount - 1; ++i) {
            View child = parent.getChildAt(i);
            parent.getLayoutManager().getDecoratedBoundsWithMargins(child, this.mBounds);
            int right = this.mBounds.right + Math.round(child.getTranslationX());
            int left = right - this.mDivider.getIntrinsicWidth();
            this.mDivider.setBounds(left, top, right, bottom);
            this.mDivider.draw(canvas);
        }

        canvas.restore();
    }

    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        if (this.mDivider == null) {
            outRect.set(0, 0, 0, 0);
        } else {
            switch (this.mOrientation) {
                case HORIZONTAL:
                    outRect.set(0, 0, this.mDivider.getIntrinsicWidth(), 0);
                    break;
                case VERTICAL:
                    outRect.set(0, 0, 0, this.mDivider.getIntrinsicHeight());
                    break;
            }
        }
    }

    @IntDef({HORIZONTAL, VERTICAL})
    @interface Orientation {

    }
}
