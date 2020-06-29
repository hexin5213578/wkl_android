package com.example.wkl_android.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
 import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.core.content.ContextCompat;


/**
 * 设置背景和字体色值
 *
 * @author gaoyahang
 */
public class ViewBgUtils {

    public static void setBg(View view, String bgcolor) {
        // TODO Auto-generated method stub
        try {
            GradientDrawable d = new GradientDrawable();
            d.setColor(Color.parseColor(bgcolor));// 设置颜色
            view.setBackgroundDrawable(d);
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    /**
     * 设置TextView字体色值
     *
     * @param bgcolor
     * @author gaoyahang
     */
    public static void setTextColor(TextView tv, String bgcolor) {
        try {
            tv.setTextColor(Color.parseColor(bgcolor));
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    /**
     * 设置背景 圆角
     *
     * @param view
     * @param bgcolor
     * @param radios
     */
    @SuppressLint("WrongConstant")
    public static void setBg(View view, String bgcolor, int radios) {
        // TODO Auto-generated method stub
        try {
            GradientDrawable d = new GradientDrawable();
            d.setColor(Color.parseColor(bgcolor));// 设置颜色
            d.setGradientType(GradientDrawable.RECTANGLE);
            d.setCornerRadius(DisplayUtil.dipToPixel(radios));
            view.setBackgroundDrawable(d);
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    /**
     * 设置背景 圆角, 边线
     *
     * @param view
     * @param bgcolor
     * @param radios
     */
    public static void setBg(View view, String bgcolor, int lineColor, int radios) {
        // TODO Auto-generated method stub
        setBg(view, bgcolor, lineColor, 1, radios);

    }

    public static void setBg(View view, String bgcolor, String lineColor, int radios) {
        // TODO Auto-generated method stub
        setBg(view, bgcolor, Color.parseColor(lineColor), 1, radios);
    }

    public static void setBg(View view, String bgcolor, String lineColor, int linewidth, int radios) {
        // TODO Auto-generated method stub
        setBg(view, bgcolor, Color.parseColor(lineColor), linewidth, radios);
    }


    @SuppressLint("WrongConstant")
    public static void setBg(View view, String bgcolor, int lineColor, int lineWidth,
                             int radios) {
        // TODO Auto-generated method stub
        try {
            GradientDrawable d = new GradientDrawable();
            d.setColor(Color.parseColor(bgcolor));// 设置颜色
            d.setStroke(lineWidth, lineColor);
            d.setGradientType(GradientDrawable.RECTANGLE);
            d.setCornerRadius(DisplayUtil.dipToPixel(radios));
            view.setBackgroundDrawable(d);
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    @SuppressLint("WrongConstant")
    public static void setBg(View view, String bgcolor, String lineColor, int lineWidth,
                             float[] radios) {
        // TODO Auto-generated method stub
        try {
            GradientDrawable d = new GradientDrawable();
            d.setColor(Color.parseColor(bgcolor));// 设置颜色
            d.setStroke(lineWidth, Color.parseColor(lineColor));
            d.setGradientType(GradientDrawable.RECTANGLE);
            d.setCornerRadii(radios);
            view.setBackgroundDrawable(d);
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    public static void setBg(Context mContext, View view, int bgcolor) {
        // TODO Auto-generated method stub
        try {
            GradientDrawable d = new GradientDrawable();
            d.setColor(mContext.getResources().getColor(bgcolor));// 设置颜色
            view.setBackgroundDrawable(d);
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    public static void setBg(View view) {
        // TODO Auto-generated method stub
        try {
            GradientDrawable d = new GradientDrawable();
            view.setBackgroundDrawable(d);
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    /**
     * 设置字体大小
     * 单位sp
     *
     * @param textView
     * @param textSize
     */
    public static void setTextSize(TextView textView, float textSize){
        try {
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        } catch (Exception e) {
        }
    }

    /**
     * 设置TextView字体色值
     *
     * @param tv
     * @param textColor
     * @author gaoyahang
     */
    public static void setTextColor(TextView tv, @ColorRes int textColor) {
        try {
            tv.setTextColor(ContextCompat.getColor(tv.getContext(), textColor));
        } catch (Exception e) {
        }
    }

}
