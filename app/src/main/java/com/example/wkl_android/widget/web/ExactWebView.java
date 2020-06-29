package com.example.wkl_android.widget.web;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.webkit.WebSettings;

/**
 * 计算高度的WebView,通过{@link #setDynamicHeight(Activity)}动态设置高度
 *
 * @author li
 * @since 2019-05-22
 */
public class ExactWebView extends BaseWebView {
    public ExactWebView(Context context) {
        super(context);
    }

    public ExactWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ExactWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 动态设置 WebView 高度
     *
     * @param activity 页面
     */
    public void setDynamicHeight(Activity activity) {
        WebSettings webSettings = getSettings();
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int mDensity = metrics.densityDpi;
        if (mDensity == 240) {
            webSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
        } else if (mDensity == 160) {
            webSettings.setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);
        } else if (mDensity == 120) {
            webSettings.setDefaultZoom(WebSettings.ZoomDensity.CLOSE);
        } else if (mDensity == DisplayMetrics.DENSITY_XHIGH) {
            webSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
        } else if (mDensity == DisplayMetrics.DENSITY_TV) {
            webSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
        } else {
            webSettings.setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);
        }
    }
}
