package com.example.wkl_android.common;


import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.widget.web.ProgressWebView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * H5通用页面
 *
 * @author li
 * @since 2019/08/20
 */
public class WebCommonActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.rlTitle)
    View rlTitle;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.webView)
    ProgressWebView webView;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_webview;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    protected void initViews() {
        Intent intent = getIntent();
        if (intent == null) {
            finish();
        } else {
            String title = intent.getStringExtra("title");
            if (TextUtils.isEmpty(title)) {
                rlTitle.setVisibility(View.GONE);
            } else {
                rlTitle.setVisibility(View.VISIBLE);
                tvTitle.setText(title);
            }
            webView.setWebViewClient(new WebViewClient() {

                @Override
                public void onPageFinished(WebView view, String url) {
                    if (isPaymentPage(url)) {
                        view.loadUrl("javascript: var allLinks = document.getElementsByTagName('a'); if (allLinks) {var i;for (i=0; i<allLinks.length; i++) {var link = allLinks[i];var target = link.getAttribute('href');var aClass = link.getAttribute('class'); if (target && target == '#' && aClass && aClass =='return') {link.href = 'back_app';link.setAttribute('#','back_app');}}}");
                    }
                    super.onPageFinished(view, url);
                }

                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    if (url.contains("back_app") || url.contains("backapp")) {
                        finish();
                    } else if (url.toLowerCase().contains("toLogin".toLowerCase())) {
                        toLogin();
                    } else if (url.contains("pwdBack") || url.contains("pwdCallback")) {
                        // 支付密码回调拦截
                        finish();
                    } else if (url.contains("cashWithdrawalBack")) {
                        // 提现回调拦截
                        finish();
                    } else {
                        view.loadUrl(url);
                    }
                    return true;
                }
            });
//            webView.addJavascriptInterface(new PaymentInterface(this), PaymentInterface.NAMESPACE);
            webView.loadUrl(intent.getStringExtra("url"));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (webView != null) {
            webView.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (webView != null) {
            webView.onPause();
        }
    }

    @Override
    @OnClick({R.id.ivLeft})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
            return;
        }
        super.onBackPressed();

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
