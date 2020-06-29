package com.example.wkl_android.errands.order_detail;

import android.view.View;
import android.widget.TextView;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.base.preseter.BasePresenter;

import butterknife.BindView;

/**
 * 订单详情
 */
public class ErrandsOrderDetailActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.ivLeft)
    View back;
    @BindView(R.id.tvTitle)
    TextView title;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_errands_order_detail;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViews() {
        title.setText("订单详情");
        back.setOnClickListener(this);
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
