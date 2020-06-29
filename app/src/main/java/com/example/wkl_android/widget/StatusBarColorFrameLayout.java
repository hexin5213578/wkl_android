package com.example.wkl_android.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.FrameLayout;

import com.example.wkl_android.R;

import java.lang.reflect.Field;

/**
 * 调整 DrawerLayout 的标题和状态栏
 *
 * @author li
 * @since 2018/03/15
 */
public class StatusBarColorFrameLayout extends FrameLayout {

    public StatusBarColorFrameLayout(Context context) {
        super(context);
        init(context);
    }

    public StatusBarColorFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public StatusBarColorFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private Rect mStatusBarRect;
    private Paint mStatusBarColorPaint;
    private int mStatusBarHeight;

    private void init(Context context) {

        // 让ToolBar处于系统状态栏下方
        setFitsSystemWindows(true);
        // 设置画笔
        mStatusBarColorPaint = new Paint();
        mStatusBarColorPaint.setColor(getThemeColor(context, R.color.theme));
        mStatusBarColorPaint.setAntiAlias(true);
        mStatusBarColorPaint.setStyle(Paint.Style.FILL);

        mStatusBarRect = new Rect();

        mStatusBarHeight = getStatusBarHeight(context);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        // 要绘制的区域
        mStatusBarRect.set(getLeft(), getTop(), getRight(), mStatusBarHeight);
        // 绘制系统状态栏颜色
        canvas.drawRect(mStatusBarRect, mStatusBarColorPaint);
        super.dispatchDraw(canvas);
    }

    /**
     * 获取系统状态栏高度
     *
     * @param context 上下文
     * @return 状态栏高度
     */
    public int getStatusBarHeight(Context context) {
        Class<?> c;
        Object obj;
        Field field;
        int x, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusBarHeight;
    }

    /**
     * 获取当前主题里的颜色
     *
     * @param context 上下文
     * @param resId   colorId
     * @return 色值
     */
    public int getThemeColor(Context context, int resId) {
        TypedValue value = new TypedValue();
        context.getTheme().resolveAttribute(resId, value, true);
        return value.data;
    }
}