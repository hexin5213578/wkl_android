package com.example.wkl_android.widget.dialog;


import android.app.Dialog;
import android.content.Context;

import com.example.wkl_android.R;

/**
 * 不带进度的等待dialog
 *
 * @author li
 * @version 1.0.0
 * @since 2019/05/20
 */
public class LoadingDialog extends Dialog {

    public LoadingDialog(Context context) {
        super(context, R.style.LoadingDialog);
        setContentView(R.layout.dialog_loading);
    }
}