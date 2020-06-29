package com.example.wkl_android.charge.detail.popup;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.wkl_android.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PaySelectorPopup extends PopupWindow implements View.OnClickListener {
    @BindView(R.id.ivCancel)
    View ivCancel;
    @BindView(R.id.tvWechatPay)
    View tvWechatPay;
    @BindView(R.id.tvAliPay)
    View tvAliPay;
    @BindView(R.id.tvBankPay)
    View tvBankPay;

    public PaySelectorPopup(Context context) {
        View contentView = LayoutInflater.from(context).inflate(R.layout.popup_pay_selector, null);
        setContentView(contentView);
        ButterKnife.bind(this, contentView);
        initView();
    }

    private void initView() {
        setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ivCancel.setOnClickListener(this);
        tvWechatPay.setOnClickListener(this);
        tvAliPay.setOnClickListener(this);
        tvBankPay.setOnClickListener(this);
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
            case R.id.ivCancel:
                dismiss();
                break;
            case R.id.tvWechatPay:
            case R.id.tvAliPay:
            case R.id.tvBankPay:
                if (listener != null) {
                    listener.onClick();
                }
                break;
        }
    }

    public interface OnClickListener {
        void onClick();
    }

    private OnClickListener listener;

    public void setListener(OnClickListener listener) {
        this.listener = listener;
    }
}
