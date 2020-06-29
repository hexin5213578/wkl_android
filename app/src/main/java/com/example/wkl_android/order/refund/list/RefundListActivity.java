package com.example.wkl_android.order.refund.list;

import android.view.View;
import android.widget.TextView;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.order.refund.list.adapter.RefundAdapter;
import com.example.wkl_android.widget.rv.decoration.CustomDecoration;
import com.example.wkl_android.widget.rv.widget.EmptyRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 退货列表
 * Created by szx
 * on 2020/1/6/006
 */
public class RefundListActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.tvTitle)
    TextView title;
    @BindView(R.id.ivLeft)
    View back;
    @BindView(R.id.rvRefund)
    EmptyRecyclerView rv;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_refund;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViews() {
        back.setOnClickListener(this);
        title.setText("退货/退款列表");
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("");
        }
        RefundAdapter adapter = new RefundAdapter(this, list);
        rv.setAdapter(adapter);
        CustomDecoration customDecoration = new CustomDecoration(APP, CustomDecoration.VERTICAL,
                R.drawable.shape_ll_divider_gray_10dp);
        rv.addItemDecoration(customDecoration);
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
