package com.example.wkl_android.wallet.withdraw;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.base.preseter.BasePresenter;

import butterknife.BindView;

/**
 * 提现
 */
public class WithdrawActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.ivLeft)
    View back;
    @BindView(R.id.tvTitle)
    TextView title;
    @BindView(R.id.tvSubmit)
    View tvSubmit;
    @BindView(R.id.etMoney)
    EditText etMoney;
    @BindView(R.id.tvEnableMoney)
    TextView tvEnableMoney;

    private double enableMoney = 1000.55;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_withdraw;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViews() {
        title.setText("提现");
        back.setOnClickListener(this);
        tvSubmit.setOnClickListener(this);
        tvEnableMoney.setText(String.format("可用余额：￥%s", enableMoney));
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

    /**
     * 提交
     */
    private void submit() {
        String money = etMoney.getText().toString().trim();
        if (TextUtils.isEmpty(money)) {
            toast("请输入提现金额");
            return;
        }
        if (Double.parseDouble(money) > enableMoney) {
            toast("提现金额不能超过可用余额");
            return;
        }
        toast("提现申请成功，请等待审核");
        finish();
    }
}
