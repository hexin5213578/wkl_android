package com.example.wkl_android.reduce_price;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.reduce_price.adapter.ReducePriceAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 降价处理
 */
public class ReducePriceActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.ivLeft)
    View back;
    @BindView(R.id.tvTitle)
    TextView title;
    @BindView(R.id.rvReducePrice)
    RecyclerView rvReducePrice;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_reduce_price;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViews() {
        title.setText("降价处理");
        back.setOnClickListener(this);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("");
        }
        rvReducePrice.setAdapter(new ReducePriceAdapter(this, list));
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
