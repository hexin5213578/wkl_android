package com.example.wkl_android.extension.goods;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.extension.goods.adapter.ExtensionGoodsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 推广商品
 */
public class ExtensionGoodsActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.ivLeft)
    View back;
    @BindView(R.id.tvTitle)
    TextView title;
    @BindView(R.id.rvGoods)
    RecyclerView rvGoods;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_extension_goods;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViews() {
        title.setText("推广商品");
        back.setOnClickListener(this);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("");
        }
        rvGoods.setAdapter(new ExtensionGoodsAdapter(this, list));
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
