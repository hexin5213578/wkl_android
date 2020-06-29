package com.example.wkl_android.utils;

import android.content.Context;
import android.content.res.Resources;
import android.text.Html;
import android.text.Spanned;
import android.text.TextPaint;
import android.util.TypedValue;
import android.view.WindowManager;
import android.widget.TextView;
import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseApplication;

public class UIUtil {

    /**
     * 根据手机的分辨率从 sp 的单位 转成为 px(像素)
     */
    public static int sp2px(Context context, float spValue) {
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue, context.getResources()
                .getDisplayMetrics()) + 0.5f);
    }

    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     *
     * @deprecated use
     */
    public static int dp2px(Context context, float dpValue) {
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, context.getResources().getDisplayMetrics()) + 0.5f);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dp2px(float dpValue) {
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, BaseApplication.getInstance().getResources()
                .getDisplayMetrics()) + 0.5f);
    }

    /**
     * px转换为dp
     */
    public static int px2dp(float pxValue) {
        final float scale = BaseApplication.getInstance().getResources().getDisplayMetrics().density;
        int dpValue = (int) (pxValue / scale + 0.5f);
        return dpValue;
    }

    /**
     * 根据手机的分辨率从 px 的单位 转成为 dip(像素)
     */
    public static int convertPxOrDip(Context context, int px) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f * (px >= 0 ? 1 : -1));
    }


    public static Spanned setTextColorSpan(int color, String beforeText, String behindText) {
        String textColorSpan = "<font color='" + color + "'>" + beforeText + "</font>" + " " + behindText;
        return Html.fromHtml(textColorSpan);
    }


    public static int getStringWidth(TextView textView) {
        TextPaint paint = textView.getPaint();
        float width = paint.measureText(textView.getText().toString());
        return (int) width;
    }

    public static int getColorWithAlpha(float alpha, int baseColor) {
        int a = Math.min(255, Math.max(0, (int) (alpha * 255))) << 24;
        int rgb = 0x00ffffff & baseColor;
        return a + rgb;
    }

    public static int mixColors(int fromColor, int toColor, float toAlpha) {
        float[] fromCmyk = cmykFromRgb(fromColor);
        float[] toCmyk = cmykFromRgb(toColor);
        float[] result = new float[4];
        for (int i = 0; i < 4; i++) {
            result[i] = Math.min(1, fromCmyk[i] * (1 - toAlpha) + toCmyk[i] * toAlpha);
        }
        return 0xff000000 + (0x00ffffff & rgbFromCmyk(result));
    }

    public static float[] cmykFromRgb(int rgbColor) {
        int red = (0xff0000 & rgbColor) >> 16;
        int green = (0xff00 & rgbColor) >> 8;
        int blue = (0xff & rgbColor);
        float black = Math.min(1.0f - red / 255.0f, Math.min(1.0f - green / 255.0f, 1.0f - blue / 255.0f));
        float cyan = 1.0f;
        float magenta = 1.0f;
        float yellow = 1.0f;
        if (black != 1.0f) {
            // black 1.0 causes zero divide
            cyan = (1.0f - (red / 255.0f) - black) / (1.0f - black);
            magenta = (1.0f - (green / 255.0f) - black) / (1.0f - black);
            yellow = (1.0f - (blue / 255.0f) - black) / (1.0f - black);
        }
        return new float[]{cyan, magenta, yellow, black};
    }

    public static int rgbFromCmyk(float[] cmyk) {
        float cyan = cmyk[0];
        float magenta = cmyk[1];
        float yellow = cmyk[2];
        float black = cmyk[3];
        int red = (int) ((1.0f - Math.min(1.0f, cyan * (1.0f - black) + black)) * 255);
        int green = (int) ((1.0f - Math.min(1.0f, magenta * (1.0f - black) + black)) * 255);
        int blue = (int) ((1.0f - Math.min(1.0f, yellow * (1.0f - black) + black)) * 255);
        return ((0xff & red) << 16) + ((0xff & green) << 8) + (0xff & blue);
    }

    /**
     * 得到屏幕宽度
     *
     * @return
     */
    public static int getScreenWidth() {
        WindowManager wm = (WindowManager) BaseApplication.getInstance().getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getWidth();
    }

    /**
     * 得到屏幕高度
     *
     * @return
     */
    public static int getScreenHeight() {
        WindowManager wm = (WindowManager) BaseApplication.getInstance().getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getHeight();
    }

    /**
     * 得到除去StatusBar，标题和底部导航的剩余屏幕高度
     *
     * @return
     */
    public static int getScreenHeightWithoutBar() {
        return getScreenHeight() - (int) BaseApplication.getInstance().getResources().getDimension(R.dimen.tab_layout_height) - (int) BaseApplication.getInstance().getResources().getDimension(R.dimen.titlebar_height) - getStatusBarHeight();
    }

    /**
     * 得到除去StatusBar，标题的剩余屏幕高度
     *
     * @return
     */
    public static int getScreenHeightWithoutTitleBar() {
        return getScreenHeight() - (int) BaseApplication.getInstance().getResources().getDimension(R.dimen.titlebar_height) - getStatusBarHeight();
    }

    /**
     * 得到除去StatusBar，标题的剩余屏幕高度
     *
     * @return
     */
    public static int getScreenHeightWithoutStatusBar() {
        return getScreenHeight() - getStatusBarHeight();
    }

    /**
     * 得到状态栏高度
     *
     * @return
     */
    public static int getStatusBarHeight() {
        Resources resources = BaseApplication.getInstance().getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        return height;
    }

    /**
     * 得到状态栏 加 标题栏的高度
     *
     * @return
     */
    public static int getStatusBarAndTitleHeight() {
        Resources resources = BaseApplication.getInstance().getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId) + (int) BaseApplication.getInstance().getResources().getDimension(R.dimen.titlebar_height);
        return height;
    }

    /**
     * 基于屏幕尺寸、给定宽高比 计算 缩放后的高度
     */
    public static int getHeightBaseScreen(int width, int height) {
        if (height == 0 || width == 0) return getScreenWidth();
        double ratio = Double.valueOf(height) / Double.valueOf(width);
        return (int) (getScreenWidth() * ratio);
    }

    /**
     * 基于给定宽度、给定宽高比 计算 缩放后的高度
     */
    public static int getHeightBaseWidth(int width, int height, int baseWidth) {
        if (height <= 0 || width <= 0) return baseWidth;
        double ratio = Double.valueOf(height) / Double.valueOf(width);
        return (int) (baseWidth * ratio);
    }

}