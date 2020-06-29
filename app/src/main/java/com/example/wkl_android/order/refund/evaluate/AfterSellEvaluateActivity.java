package com.example.wkl_android.order.refund.evaluate;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.main.MainActivity;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;

/**
 * 售后评价
 */
public class AfterSellEvaluateActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.ivLeft)
    View back;
    @BindView(R.id.tvTitle)
    TextView title;
    @BindView(R.id.ratingBar)
    RatingBar ratingBar;
    @BindView(R.id.tvEvaluate)
    TextView tvEvaluate;
    @BindView(R.id.etServe)
    EditText etServe;
    @BindView(R.id.tvNum)
    TextView tvNum;
    @BindView(R.id.tvSubmit)
    View tvSubmit;

    private Map<Float, String> map;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_after_sell_evaluate;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViews() {
        title.setText("评价售后服务");
        back.setOnClickListener(this);
        tvSubmit.setOnClickListener(this);
        map = new HashMap<>();
        initMap();
        ratingBar.setOnRatingBarChangeListener((ratingBar, v, b) -> tvEvaluate.setText(map.get(v)));

        etServe.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s != null && s.length() > 0) {
                    tvNum.setText(String.format(Locale.getDefault(), "%d/300", s.length()));
                } else {
                    tvNum.setText("0/300");
                }
            }
        });
    }

    private void initMap() {
        map.put(1.0f, "非常不满意");
        map.put(2.0f, "不满意");
        map.put(3.0f, "一般");
        map.put(4.0f, "满意");
        map.put(5.0f, "非常满意");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvSubmit:
                submit();
                break;
        }
    }

    private void submit() {
        float num = ratingBar.getRating();
        if (0 == num) {
            toast("请先评分");
            return;
        }
        toast("提交成功");
        startActivity(new Intent(APP, MainActivity.class));
    }
}
