package com.example.wkl_android.dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;


import com.example.wkl_android.R;
import com.example.wkl_android.utils.UIUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CommDialog extends DialogFragment {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_tip)
    TextView tvTip;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.tv_confirm)
    TextView tvConfirm;
    @BindView(R.id.view_space)
    View viewSpace;
    Unbinder unbinder;



    private String title, tip, cancelDesc, confirmDesc;
    private View.OnClickListener cancelListener, confirmListener;
    private boolean gravityCenter = true;

    public CommDialog() {
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_comm, null, false);
        unbinder = ButterKnife.bind(this, view);

        if (gravityCenter) {
            tvTip.setGravity(Gravity.CENTER);
        } else {
            tvTip.setGravity(Gravity.LEFT);
        }

        if (!TextUtils.isEmpty(tip)) {
            tvTip.setText(tip);
        }

        tvTip.setMovementMethod(ScrollingMovementMethod.getInstance());

        if (!TextUtils.isEmpty(title)) {
            tvTitle.setText(title);
        }

        if (cancelListener != null || !TextUtils.isEmpty(cancelDesc) || confirmListener != null) {
            if (cancelListener != null) {
                localCancelListener = cancelListener;
            }
            if (!TextUtils.isEmpty(cancelDesc)) {
                tvCancel.setText(cancelDesc);
            }
        } else {
            // 取消按钮文案没修改且功能和确定按钮一样，隐藏
            tvCancel.setVisibility(View.GONE);
            viewSpace.setVisibility(View.GONE);
        }
        tvCancel.setOnClickListener(localCancelListener);

        if (!TextUtils.isEmpty(confirmDesc)) {
            tvConfirm.setText(confirmDesc);
        }
        if (confirmListener != null) {
            localConfirmListener = confirmListener;
        }
        tvConfirm.setOnClickListener(localConfirmListener);
//        setCancelable(false);

        tvTip.getLayoutParams().width = (int) (UIUtil.getScreenWidth() * 0.824f);
        return view;
    }

    public void setGravityCenter(boolean gravityCenter) {
        this.gravityCenter = gravityCenter;
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        if (window != null)
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public void setCancelDesc(String cancelDesc) {
        this.cancelDesc = cancelDesc;
    }

    public void setConfirmDesc(String confirmDesc) {
        this.confirmDesc = confirmDesc;
    }

    public void setCancelListener(View.OnClickListener cancelListener) {
        this.cancelListener = cancelListener;
    }

    public void setConfirmListener(View.OnClickListener confirmListener) {
        this.confirmListener = confirmListener;
    }

    private View.OnClickListener localCancelListener = v -> CommDialog.this.dismiss();

    private View.OnClickListener localConfirmListener = v -> CommDialog.this.dismiss();

}
