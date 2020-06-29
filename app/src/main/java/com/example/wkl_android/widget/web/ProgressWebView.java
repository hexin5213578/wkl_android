package com.example.wkl_android.widget.web;
import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebChromeClient;
import android.widget.AbsoluteLayout;
import android.widget.ProgressBar;

import com.example.wkl_android.widget.web.client.ProgressWebChromeClient;

/**
 * @author li
 * @since 2019-05-31
 */
public class ProgressWebView extends BaseWebView {

    private ProgressWebChromeClient webChromeClient;
    private ProgressBar progressBar;

    public ProgressWebView(Context context) {
        super(context);
    }

    public ProgressWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ProgressWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 初始化{@link #webChromeClient},进度条,并设置WebChromeClient
     */
    @Override
    public void init() {
        super.init();
        webChromeClient = new ProgressWebChromeClient();
        progressBar = webChromeClient.getProgressBar();
        setWebChromeClient(webChromeClient);
    }

    @Override
    public void setWebChromeClient(WebChromeClient client) {
        if (client instanceof ProgressWebChromeClient) {
            this.webChromeClient = (ProgressWebChromeClient) client;
            this.progressBar = webChromeClient.getProgressBar();
            super.setWebChromeClient(client);
        }
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldL, int oldT) {
        if (progressBar != null) {
            AbsoluteLayout.LayoutParams lp = (AbsoluteLayout.LayoutParams) progressBar.getLayoutParams();
            lp.x = l;
            lp.y = t;
            progressBar.setLayoutParams(lp);
        }
        super.onScrollChanged(l, t, oldL, oldT);
    }

    public ProgressBar getProgressbar() {
        return progressBar;
    }
}
