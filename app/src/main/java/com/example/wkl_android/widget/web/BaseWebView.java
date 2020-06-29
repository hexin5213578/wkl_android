package com.example.wkl_android.widget.web;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.wkl_android.base.app.BaseApp;
import com.example.wkl_android.utils.LogUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 添加UserAgent: jdAPP/版本号
 * <p>
 * 加载的Url添加验签规则
 *
 * @author li
 * @since 2019-05-31
 */
public abstract class BaseWebView extends WebView {
    public BaseWebView(Context context) {
        super(context);
        init();
    }

    public BaseWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BaseWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @SuppressLint("SetJavaScriptEnabled")
    public void init() {
        WebSettings webSettings = getSettings();
        webSettings.setSupportZoom(false);
        webSettings.setBuiltInZoomControls(false);
        webSettings.setDisplayZoomControls(false);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true); //webView拓展的api打开：
        webSettings.setAllowFileAccessFromFileURLs(true);//在高版本的时候我们是需要使用允许访问文件的urls：
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setSupportMultipleWindows(true);
        webSettings.setBlockNetworkImage(false);
        webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);

        setUA(webSettings);
    }

    public void setUA(WebSettings settings) {
        String ua = settings.getUserAgentString();
        if (!ua.contains("jdAPP")) {
            ua = ua + " jdAPP/" + BaseApp.sVersion;
        }
        settings.setUserAgentString(ua);
    }

    @Override
    public void loadUrl(String url) {
        if (TextUtils.isEmpty(url) || !url.contains("http")
                || isPaymentPage(url)) {
            // 不加签名的情况
            LogUtils.d("url", url);
            super.loadUrl(url);
            return;
        }
        Map<String, String> params = new HashMap<>();
//        url = ParamUtils.addKeySignWithMethodGet(url, params);
        LogUtils.d("url", url);
        super.loadUrl(url);
    }

    /**
     * 判断url是否为第三方支付页面
     *
     * @param url url
     * @return 结果
     */
    private boolean isPaymentPage(String url) {
        return url.contains("data") && url.contains("encryptkey");
    }
}
