package com.example.wkl_android.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.wkl_android.R;

/**
 * PDF文件下载loading弹框
 *
 * @author li
 * @since 2019-10-11
 */
public class PdfLoadingDialog extends Dialog {

    private String title;

    public PdfLoadingDialog(@NonNull Context context, String title) {
        super(context, R.style.LoadingDialog);
        setContentView(R.layout.dialog_loading);
        setCanceledOnTouchOutside(false);
        this.title = title;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView tvMessage = findViewById(R.id.tvMessage);
        tvMessage.setText(title);
    }
}
