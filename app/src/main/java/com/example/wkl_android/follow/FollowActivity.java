package com.example.wkl_android.follow;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.collection.bean.CollectionShop;
import com.example.wkl_android.follow.adapter.FollowAdapter;
import com.example.wkl_android.shop_street.shop_detail.ui.activity.ShopMessageActivity;
import com.example.wkl_android.widget.rv.decoration.CustomDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class FollowActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.ivLeft)
    View back;
    @BindView(R.id.tvTitle)
    TextView title;
    @BindView(R.id.rvFollow)
    RecyclerView rvFollow;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_follow;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViews() {
        title.setText("关注店铺");
        back.setOnClickListener(this);
        List<CollectionShop> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new CollectionShop());
        }
        FollowAdapter adapter = new FollowAdapter(this, list);
        rvFollow.setAdapter(adapter);
//        adapter.setListener(() -> startActivity(new Intent(APP, ShopMessageActivity.class)));
        CustomDecoration customDecoration = new CustomDecoration(this,
                CustomDecoration.VERTICAL, R.drawable.shape_ll_divider_gray);
        rvFollow.addItemDecoration(customDecoration);
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
