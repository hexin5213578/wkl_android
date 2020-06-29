package com.example.wkl_android.errands.help_buy.popup;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.wkl_android.R;
import com.example.wkl_android.errands.help_buy.PayRulesActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PayDetailPopup extends PopupWindow implements View.OnClickListener {
    View ivCancel;
    View tvRules;
    private Context context;

    public PayDetailPopup(Context context) {
        this.context = context;
        View view = LayoutInflater.from(context).inflate(R.layout.popup_pay_detail, null);
        setContentView(view);
        ivCancel = view.findViewById(R.id.ivCancel);
        tvRules = view.findViewById(R.id.tvRules);
        initView();
    }

    private void initView() {
        setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ivCancel.setOnClickListener(this);
        tvRules.setOnClickListener(this);
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
            case R.id.tvRules:
                context.startActivity(new Intent(context, PayRulesActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                break;
        }
    }
}
