package com.example.wkl_android.order.refund.complaint;

import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.main.MainActivity;

import java.util.Locale;

import butterknife.BindView;

/**
 * 投诉
 */
public class ComplaintActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.ivLeft)
    View back;
    @BindView(R.id.tvTitle)
    TextView title;
    @BindView(R.id.etComplaint)
    EditText etComplaint;
    @BindView(R.id.tvNum)
    TextView tvNum;
    @BindView(R.id.tvSubmit)
    View tvSubmit;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_complaint;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViews() {
        title.setText("投诉");
        back.setOnClickListener(this);
        tvSubmit.setOnClickListener(this);
        etComplaint.addTextChangedListener(new TextWatcher() {
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
                } else tvNum.setText("0/300");
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvSubmit:
                String complaint = etComplaint.getText().toString().trim();
                if(TextUtils.isEmpty(complaint)){
                    toast("请填写投诉原因");
                    return;
                }
                toast("提交成功");
                startActivity(new Intent(APP, MainActivity.class));
                break;
        }
    }
}

