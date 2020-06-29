package com.example.wkl_android.order.confirm.popup;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.wkl_android.R;
import com.example.wkl_android.charge.detail.popup.PaySelectorPopup;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PayTypePopup extends PopupWindow implements View.OnClickListener {
    @BindView(R.id.ivCancel)
    View ivCancel;
    @BindView(R.id.tvWechatPay)
    View tvWechatPay;
    @BindView(R.id.tvAliPay)
    View tvAliPay;
    @BindView(R.id.tvStoragePay)
    View tvStoragePay;
    @BindView(R.id.tvBalancePay)
    View tvBalancePay;
    public PayTypePopup(Context context){
        View view = LayoutInflater.from(context).inflate(R.layout.popup_pay_type,null);
        setContentView(view);
        ButterKnife.bind(this, view);
        initView();
    }

    private void initView() {
        setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ivCancel.setOnClickListener(this);
        tvWechatPay.setOnClickListener(this);
        tvAliPay.setOnClickListener(this);
        tvStoragePay.setOnClickListener(this);
        tvBalancePay.setOnClickListener(this);
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
                IWXAPI api= WXAPIFactory.createWXAPI(tvWechatPay.getContext(), null);;
                PayReq request = new PayReq();
                request.appId = "wxd930ea5d5a258f4f";
                request.partnerId = "1900000109";
                request.prepayId= "1101000000140415649af9fc314aa427";
                request.packageValue = "Sign=WXPay";
                request.nonceStr= "1101000000140429eb40476f8896f4c9";
                request.timeStamp= "1398746574";
                request.sign= "7FFECB600D7157C5AA49810D2D8F28BC2811827B";
                api.sendReq(request);
            case R.id.tvAliPay:
            case R.id.tvBankPay:
            case R.id.tvBalancePay:
                if (listener != null) {
                    listener.onClick();
                }
                break;
        }
    }

    public interface OnClickListener {
        void onClick();
    }

    private PaySelectorPopup.OnClickListener listener;

    public void setListener(PaySelectorPopup.OnClickListener listener) {
        this.listener = listener;
    }
}
