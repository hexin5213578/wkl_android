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

public class SharePopup extends PopupWindow implements View.OnClickListener {
    @BindView(R.id.tvDismiss)
    View tvDismiss;
    @BindView(R.id.tvShareWechat)
    View tvShareWechat;
    @BindView(R.id.tvShareCircle)
    View tvShareCircle;
    @BindView(R.id.tvShareQQ)
    View tvShareQQ;
    public SharePopup(Context context) {
        View contentView = LayoutInflater.from(context).inflate(R.layout.popup_share, null);
        setContentView(contentView);
        ButterKnife.bind(this, contentView);
        initView();
    }

    private void initView() {
        setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        tvDismiss.setOnClickListener(this);
        tvShareWechat.setOnClickListener(this);
        tvShareCircle.setOnClickListener(this);
        tvShareQQ.setOnClickListener(this);
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
            case R.id.tvDismiss:
                dismiss();
                break;
            case R.id.tvShareWechat:
                if (listener != null) {
                    listener.shareWechat();
                }
                break;
            case R.id.tvShareCircle:
                if (listener != null) {
                    listener.shareCircle();
                }
                break;
            case R.id.tvShareQQ:
                if (listener != null) {
                    listener.shareQQ();
                }
                break;
        }
    }

    public interface OnShareListener {
        void shareWechat();

        void shareCircle();

        void shareQQ();
    }

    private OnShareListener listener;

    public void setListener(OnShareListener listener) {
        this.listener = listener;
    }
}
