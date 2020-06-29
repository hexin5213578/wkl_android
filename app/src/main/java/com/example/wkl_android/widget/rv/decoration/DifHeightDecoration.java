package com.example.wkl_android.widget.rv.decoration;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author li
 * @since 2019-05-30
 */
public class DifHeightDecoration extends RecyclerView.ItemDecoration {

    private SparseArray<Drawable> drawableMap;

    private int mOrientation;

    public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;
    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;

    public DifHeightDecoration(int orientation, SparseArray<Drawable> drawableMap) {
        this.mOrientation = orientation;
        this.drawableMap = drawableMap;
        setOrientation(orientation);
    }


    //设置屏幕方向
    public void setOrientation(int orientation) {
        if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
            throw new IllegalArgumentException("invalid orientation");
        }
        this.mOrientation = orientation;
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        if (mOrientation == HORIZONTAL_LIST) {
            //横向 list 画竖线
            drawVerticalLine(c, parent);
        } else if (mOrientation == VERTICAL_LIST) {
            //竖向list 画横线
            drawHorizontalLine(c, parent);
        }

    }

    private static final String TAG = "DifWidthDecoration";

    private void drawHorizontalLine(Canvas c, RecyclerView parent) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        int childCount = parent.getChildCount();

        Log.d(TAG, String.format("childCount -> %d", childCount));
        //由于RecyclerView 复用ItemView 这里的childCount 是用户可见的 count
        RecyclerView.Adapter adapter = parent.getAdapter();
        if (adapter != null) {
            for (int i = 0; i < childCount; i++) {
                final View child = parent.getChildAt(i);
                //接上面:所以要拿到itemView 对应的adapter position,但是方法的参数里不像getItemOffsets()里回调了View view
                //所以 自己取到child 通过RecyclerView 的 getChildAdapterPosition(child) 来取到adapterPosition
                //旧的写法 parent.getAdapter().getItemType(i) -> 会造成 分割线的错乱
                int adapterPosition = parent.getChildAdapterPosition(child);
                int type = adapter.getItemViewType(adapterPosition);
                Drawable drawable = drawableMap.get(type);

                final RecyclerView.LayoutParams params =
                        (RecyclerView.LayoutParams) child.getLayoutParams();
                final int top = child.getBottom() + params.bottomMargin;
                if (drawable != null) {
                    final int bottom = top + drawable.getIntrinsicHeight();
                    drawable.setBounds(left, top, right, bottom);
                    drawable.draw(c);
                }
            }
        }
    }

    private void drawVerticalLine(Canvas c, RecyclerView parent) {
        int top = parent.getPaddingTop();
        int bottom = parent.getHeight() - parent.getPaddingBottom();
        int childCount = parent.getChildCount();

        RecyclerView.Adapter adapter = parent.getAdapter();
        if (adapter != null) {
            for (int i = 0; i < childCount; i++) {
                final View child = parent.getChildAt(i);

                int adapterPosition = parent.getChildAdapterPosition(child);
                int type = adapter.getItemViewType(adapterPosition);
                Drawable drawable = drawableMap.get(type);

                if (drawable != null) {
                    final RecyclerView.LayoutParams params =
                            (RecyclerView.LayoutParams) child.getLayoutParams();
                    final int left = child.getRight() + params.rightMargin;
                    final int right = left + drawable.getIntrinsicWidth();
                    drawable.setBounds(left, top, right, bottom);
                    drawable.draw(c);
                }
            }
        }
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int adapterPosition = parent.getChildAdapterPosition(view);
        RecyclerView.Adapter adapter = parent.getAdapter();
        if (adapter != null) {
            int type = adapter.getItemViewType(adapterPosition);
            if (mOrientation == HORIZONTAL_LIST) {
                Drawable drawable = drawableMap.get(type);
                if (drawable != null) {
                    outRect.set(0, 0, drawable.getIntrinsicWidth(), 0);
                }
            } else if (mOrientation == VERTICAL_LIST) {
                Drawable drawable = drawableMap.get(type);
                if (drawable != null) {
                    outRect.set(0, 0, 0, drawable.getIntrinsicHeight());
                }
            }
        }
    }
}
