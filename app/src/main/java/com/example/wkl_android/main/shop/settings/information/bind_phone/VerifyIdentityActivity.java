package com.example.wkl_android.main.shop.settings.information.bind_phone;

import android.view.View;
import android.widget.TextView;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.base.preseter.BasePresenter;

import butterknife.BindView;

/**
 * 验证身份-已实名
 * Created by szx
 * on 2019/12/31/031
 */
public class VerifyIdentityActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.tvTitle)
    TextView title;
    @BindView(R.id.ivLeft)
    View back;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_verify_identity;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViews() {
        title.setText("验证身份");
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
        }
    }
}
