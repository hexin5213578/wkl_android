package com.example.wkl_android.main.shop.add_shop.ui.popup;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.wkl_android.R;
import com.example.wkl_android.widget.wheel.adapters.ArrayWheelAdapter;
import com.zyyoona7.wheel.WheelView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by szx
 * on 2020/1/13/013
 */
public class WheelViewPopup extends PopupWindow implements View.OnClickListener {
    @BindView(R.id.wheelView)
    WheelView wheelView;
    @BindView(R.id.tvCancel)
    View tvCancel;
    @BindView(R.id.tvSubmit)
    View tvSubmit;
    @BindView(R.id.tvTitle)
    TextView tvTitle;

    @SuppressLint("InflateParams")
    public WheelViewPopup(Context context, String title) {
        View contentView = LayoutInflater.from(context).inflate(R.layout.popup_category, null);
        setContentView(contentView);
        ButterKnife.bind(this, contentView);
        tvTitle.setText(title);
        init();
    }

    public void setList(List<String> list) {
        wheelView.setData(list);
    }

    private void init() {
        setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        tvCancel.setOnClickListener(this);
        tvSubmit.setOnClickListener(this);
    }

    public void show(View parent) {
        if (parent == null) {
            return;
        }
        showAtLocation(parent, 0, 0, Gravity.CENTER);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvCancel:
                dismiss();
                break;
            case R.id.tvSubmit:
                dismiss();
                if (listener != null) {
                    listener.setText(wheelView.getSelectedItemPosition());
                }
                break;
        }
    }

    public interface SetCategoryListener {
        void setText(int position);
    }

    private SetCategoryListener listener;

    public void setListener(SetCategoryListener listener) {
        this.listener = listener;
    }
}
