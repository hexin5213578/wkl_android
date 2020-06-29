package com.example.wkl_android.widget.web.client;

import android.content.Context;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

import androidx.core.content.ContextCompat;

import com.example.wkl_android.R;

/**
 * 带进度条的WebChromeClient
 *
 * @author li
 * @since 2019-05-31
 */
public class ProgressWebChromeClient extends WebChromeClient {

    private ProgressBar progressBar;

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        if (progressBar == null) {
            Context context = view.getContext();
            progressBar = new ProgressBar(context, null,
                    android.R.attr.progressBarStyleHorizontal);
            progressBar.setLayoutParams(new WebView.LayoutParams(WebView.LayoutParams.MATCH_PARENT,
                    8, 0, 0));
            view.addView(progressBar);

            progressBar.setProgressDrawable(ContextCompat.getDrawable(context, R.drawable.layer_point_plan_bar));
            progressBar.setProgress(20);
            progressBar.setVisibility(View.GONE);
        }
        if (newProgress == 100) {
            progressBar.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setProgress(newProgress);
        }
        super.onProgressChanged(view, newProgress);
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }
}
