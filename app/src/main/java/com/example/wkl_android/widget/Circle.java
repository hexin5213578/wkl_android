package com.example.wkl_android.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.wkl_android.R;

/**
 * 圆圈
 *
 * @author li
 * @since 2017/9/15
 */

public class Circle extends View {

    protected int mRadius = 10;//默认给个10吧,要不然都不知道怎么回事
    protected int mColor;
    protected Paint paint;

    public Circle(Context context) {
        this(context, null);
    }

    public Circle(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public Circle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public Circle(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    /**
     * 初始化,设置画笔,获取半径和颜色属性
     *
     * @param attrs 属性集
     */
    private void init(Context context, AttributeSet attrs) {
        // 创建画笔
        paint = new Paint();
        // 抗锯齿
        paint.setAntiAlias(true);
        // 边线和填充
        paint.setStyle(Paint.Style.FILL_AND_STROKE);

        TypedArray array = null;
        try {
            array = context.obtainStyledAttributes(attrs, R.styleable.Circle);
            // 获取半径
            mRadius = array.getDimensionPixelOffset(R.styleable.Circle_radius, mRadius);
            // 获取颜色
            mColor = array.getColor(R.styleable.Circle_color, Color.BLACK);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (array != null) {
                array.recycle();
            }
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = this.getMeasureSize(widthMeasureSpec, true);
        int height = this.getMeasureSize(heightMeasureSpec, false);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //设置画笔颜色
        paint.setColor(mColor);
        //画圆
        canvas.drawCircle(mRadius + getPaddingLeft(), mRadius + getPaddingTop(),
                mRadius, paint);
    }

    /**
     * 获取控件的实际大小
     *
     * @param length  {@link #onMeasure(int, int)} 方法获取的值
     * @param isWidth true: 宽度 false: 高度
     * @return int 计算后的实际大小
     */
    private int getMeasureSize(int length, boolean isWidth) {
        // 模式
        int specMode = MeasureSpec.getMode(length);
        // 尺寸
        int specSize = MeasureSpec.getSize(length);
        // 计算所得的实际尺寸,要被返回
        int retSize;
        // 得到两侧的 padding(留边)
        int padding = (isWidth ? getPaddingLeft() + getPaddingRight() : getPaddingTop() + getPaddingBottom());
        if (specMode == MeasureSpec.EXACTLY) {// 显示指定大小,如 10dp 或 match_parent
            retSize = specSize;
        } else {
            retSize = mRadius * 2 + padding;
        }
        return retSize;
    }

    public void setRadius(int mRadius) {
        this.mRadius = mRadius;
        invalidate();
    }

    public void setColor(int mColor) {
        this.mColor = mColor;
        invalidate();
    }
}
