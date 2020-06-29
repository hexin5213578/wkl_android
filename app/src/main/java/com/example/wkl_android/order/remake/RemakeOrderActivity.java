package com.example.wkl_android.order.remake;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.main.shop.address.main.ui.activity.AddressActivity;

import butterknife.BindView;

/**
 * 修改订单地址
 * Created by szx
 * on 2020/2/1/001
 */
public class RemakeOrderActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.ivLeft)
    View back;
    @BindView(R.id.tvTitle)
    TextView title;
    @BindView(R.id.llToAddress)
    View llToAddress;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_remake_order_address;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViews() {
        back.setOnClickListener(this);
        title.setText("修改地址");
        llToAddress.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.llToAddress:
                startActivity(new Intent(APP, AddressActivity.class));
                break;
        }
    }
}
