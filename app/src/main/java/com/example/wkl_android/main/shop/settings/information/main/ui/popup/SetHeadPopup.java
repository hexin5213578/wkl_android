package com.example.wkl_android.main.shop.settings.information.main.ui.popup;

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

import butterknife.BindView;
import butterknife.ButterKnife;

public class SetHeadPopup extends PopupWindow implements View.OnClickListener {
    @BindView(R.id.tvCamera)
    View tvCamera;
    @BindView(R.id.tvPhoto)
    View tvPhoto;
    @BindView(R.id.tvCancel)
    View tvCancel;

    public SetHeadPopup(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.popup_set_head, null);
        setContentView(view);
        ButterKnife.bind(this, view);
        initView();
    }

    private void initView() {
        setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        setHeight(LinearLayout.LayoutParams.MATCH_PARENT);

        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        tvCamera.setOnClickListener(this);
        tvCancel.setOnClickListener(this);
        tvPhoto.setOnClickListener(this);
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
            case R.id.tvCamera:
                dismiss();
                if (listener != null) {
                    listener.toCamera();
                }
                break;
            case R.id.tvCancel:
                dismiss();
                break;
            case R.id.tvPhoto:
                dismiss();
                if (listener != null) {
                    listener.toPhoto();
                }
                break;
        }
    }

    public interface OnClickListener {
        void toCamera();

        void toPhoto();
    }

    private OnClickListener listener;

    public void setListener(OnClickListener listener) {
        this.listener = listener;
    }
}
