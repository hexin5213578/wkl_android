package com.example.wkl_android.main.shop.settings.information.main.ui.popup;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.wkl_android.R;
import com.example.wkl_android.utils.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by szx
 * on 2020/1/2/002
 */
public class DatePopup extends PopupWindow implements View.OnClickListener {
    @BindView(R.id.tvCancel)
    View tvCancel;
    @BindView(R.id.tvConfirm)
    View tvConfirm;
    @BindView(R.id.datePicker)
    DatePicker datePicker;

    private String date = "1990-01-01";

    @SuppressLint("InflateParams")
    public DatePopup(Context context) {
        View contentView = LayoutInflater.from(context).inflate(R.layout.popup_date_picker, null);
        setContentView(contentView);
        ButterKnife.bind(this, contentView);
        init();
    }

    private void init() {
        setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        tvCancel.setOnClickListener(this);
        tvConfirm.setOnClickListener(this);
        datePicker.init(1990, 0, 1, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                int month = monthOfYear + 1;
                String monthStr = month < 10 ? "0" + month : String.valueOf(month);
                String dayStr = dayOfMonth < 10 ? "0" + dayOfMonth : String.valueOf(dayOfMonth);
                date = year + "-" + monthStr + "-" + dayStr;
            }
        });

    }

    public void show(View parent) {
        if (parent == null) {
            return;
        }
        showAtLocation(parent, Gravity.CENTER, 0, 0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvCancel:
                dismiss();
                break;
            case R.id.tvConfirm:
                if (listener != null) {
                    listener.setDate(date);
                }
                dismiss();
                break;
        }
    }

    public interface SetDateListener {
        void setDate(String date);
    }

    private SetDateListener listener;

    public void setListener(SetDateListener listener) {
        this.listener = listener;
    }
}
