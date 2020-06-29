package com.example.wkl_android.extension.discount;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.extension.discount.adapter.ExtensionDiscountAdapter;
import com.example.wkl_android.widget.rv.decoration.CustomDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 推广活动
 */
public class ExtensionDiscountActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.ivLeft)
    View back;
    @BindView(R.id.tvTitle)
    TextView title;
    @BindView(R.id.rvDiscount)
    RecyclerView rvDiscount;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_extension_discount;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViews() {
        title.setText("店铺活动");
        back.setOnClickListener(this);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("");
        }
        rvDiscount.setAdapter(new ExtensionDiscountAdapter(this, list));
        CustomDecoration customDecoration = new CustomDecoration(this,
                CustomDecoration.VERTICAL, R.drawable.shape_ll_divider_gray_10dp);
        rvDiscount.addItemDecoration(customDecoration);
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
