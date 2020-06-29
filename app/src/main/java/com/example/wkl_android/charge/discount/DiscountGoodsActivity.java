package com.example.wkl_android.charge.discount;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.charge.discount.adapter.DiscountGoodsAdapter;
import com.example.wkl_android.widget.rv.decoration.CustomDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 活动产品
 */
public class DiscountGoodsActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.ivLeft)
    View back;
    @BindView(R.id.tvTitle)
    TextView title;
    @BindView(R.id.rvGoods)
    RecyclerView rvGoods;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_discount_goods;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViews() {
        title.setText("活动产品");
        back.setOnClickListener(this);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("");
        }
        rvGoods.setAdapter(new DiscountGoodsAdapter(this, list));
        CustomDecoration customDecoration = new CustomDecoration(this,
                CustomDecoration.VERTICAL, R.drawable.shape_ll_divider_gray);
        rvGoods.addItemDecoration(customDecoration);
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
