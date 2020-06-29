package com.example.wkl_android.errands.help_buy.popup;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.wkl_android.R;
import com.example.wkl_android.utils.LogUtils;
import com.zyyoona7.wheel.WheelView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TimePopup extends PopupWindow implements View.OnClickListener {
    @BindView(R.id.wheelViewDate)
    WheelView wheelViewDate;
    @BindView(R.id.wheelViewHour)
    WheelView wheelViewHour;
    @BindView(R.id.wheelViewMinute)
    WheelView wheelViewMinute;
    @BindView(R.id.tvCancel)
    View tvCancel;
    @BindView(R.id.tvSubmit)
    View tvSubmit;
    @BindColor(R.color.font_black_3)
    int font_black_3;
    @BindColor(R.color.theme)
    int theme;
    private List<String> newHourList, hourList, minuteList;

    @SuppressLint("InflateParams")
    public TimePopup(Context context, List<String> dateList, List<String> timeList, List<String> minuteList) {
        this.hourList = timeList;
        this.minuteList = minuteList;
        newHourList = new ArrayList<>();
        newHourList.addAll(timeList);
        newHourList.remove(0);
        View view = LayoutInflater.from(context).inflate(R.layout.popup_time, null);
        setContentView(view);
        ButterKnife.bind(this, view);
        wheelViewDate.setData(dateList);
        wheelViewHour.setData(timeList);
        initView();
    }

    private void initView() {
        setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        tvCancel.setOnClickListener(this);
        tvSubmit.setOnClickListener(this);
        //日期监听
        wheelViewDate.setOnWheelChangedListener(new WheelView.OnWheelChangedListener() {
            @Override
            public void onWheelScroll(int scrollOffsetY) {

            }

            @Override
            public void onWheelItemChanged(int oldPosition, int newPosition) {
                if (newPosition != 0) {
                    wheelViewHour.setData(newHourList);
                    wheelViewMinute.setData(minuteList);
                } else {
                    wheelViewHour.setData(hourList);
                    wheelViewMinute.setData(new ArrayList());
                }
            }

            @Override
            public void onWheelSelected(int position) {

            }

            @Override
            public void onWheelScrollStateChanged(int state) {

            }
        });
        //小时监听
        wheelViewHour.setOnWheelChangedListener(new WheelView.OnWheelChangedListener() {
            @Override
            public void onWheelScroll(int scrollOffsetY) {

            }

            @Override
            public void onWheelItemChanged(int oldPosition, int newPosition) {
                if ("尽快送达".equals(hourList.get(newPosition))) {
                    LogUtils.d("进去了");
                    wheelViewMinute.setData(new ArrayList());
                } else {
                    wheelViewMinute.setData(minuteList);
                }
            }

            @Override
            public void onWheelSelected(int position) {

            }

            @Override
            public void onWheelScrollStateChanged(int state) {

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
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvSubmit:
                dismiss();
                if (listener != null) {
                    listener.onClick(wheelViewDate.getSelectedItemPosition(), wheelViewHour.getSelectedItemPosition(),
                            wheelViewMinute.getSelectedItemPosition());
                }
                break;
            case R.id.tvCancel:
                dismiss();
                break;
        }
    }

    public interface OnClickListener {
        void onClick(int datePosition, int timePosition, int minutePosition);
    }

    private OnClickListener listener;

    public void setListener(OnClickListener listener) {
        this.listener = listener;
    }
}
