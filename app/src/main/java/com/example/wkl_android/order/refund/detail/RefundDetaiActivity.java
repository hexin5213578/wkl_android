package com.example.wkl_android.order.refund.detail;

import android.content.Intent;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.order.refund.complaint.ComplaintActivity;

import butterknife.BindColor;
import butterknife.BindView;

/**
 * 退款详情
 */
public class RefundDetaiActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.ivLeft)
    View back;
    @BindView(R.id.tvTitle)
    TextView title;
    @BindView(R.id.tvComplaint)
    TextView tvComplaint;

    @BindColor(R.color.theme)
    int font_theme;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_refund_detail;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViews() {
        title.setText("退款详情");
        back.setOnClickListener(this);
        initTips();
    }

    private void initTips() {
        String str1 = "如您对商家未按约定时间发货/缺货不满，您可以点";
        String str2 = "这里";
        String str3 = "进行投诉";
        SpannableStringBuilder ssb = new SpannableStringBuilder(str1);
        ssb.append(str2).append(str3);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(font_theme);
            }

            @Override
            public void onClick(@NonNull View view) {
                startActivity(new Intent(APP, ComplaintActivity.class));
            }
        };
        ssb.setSpan(clickableSpan, str1.length(), str1.length() + str2.length(),
                Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        tvComplaint.setHighlightColor(Color.TRANSPARENT);
        tvComplaint.setText(ssb);
        tvComplaint.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
        }
    }
}
