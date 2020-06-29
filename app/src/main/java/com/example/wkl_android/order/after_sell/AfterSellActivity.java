package com.example.wkl_android.order.after_sell;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.order.refund.ApplyRefundActivity;
import com.example.wkl_android.order.refund.goods.RefundGoodsActivity;

import butterknife.BindView;

/**
 * 售后
 */
public class AfterSellActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.tvTitle)
    TextView title;
    @BindView(R.id.ivLeft)
    View back;
    @BindView(R.id.llRefundMoney)
    View llRefundMoney;
    @BindView(R.id.llRefundGoods)
    View llRefundGoods;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_after_sell;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViews() {
        title.setText("选择服务类型");
        back.setOnClickListener(this);
        llRefundGoods.setOnClickListener(this);
        llRefundMoney.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.llRefundGoods:
                startActivity(new Intent(APP, RefundGoodsActivity.class));
                break;
            case R.id.llRefundMoney:
                startActivity(new Intent(APP, ApplyRefundActivity.class));
                break;
        }
    }
}
