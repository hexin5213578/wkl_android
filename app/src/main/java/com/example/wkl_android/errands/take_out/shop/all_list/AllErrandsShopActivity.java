package com.example.wkl_android.errands.take_out.shop.all_list;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.errands.take_out.shop.all_list.adapter.ShopErrandsGridAdapter;
import com.example.wkl_android.good.model.GoodsListBean;
import com.example.wkl_android.main.fragment.adapter.ShopErrandsAdapter;

import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * 全部商家
 */
public class AllErrandsShopActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.ivLeft)
    View back;
    @BindView(R.id.tvTitle)
    TextView title;
    @BindView(R.id.rvAllErrands)
    RecyclerView rvAllErrands;
    @BindView(R.id.ivChange)
    ImageView ivChange;
    private int flag;
    private ShopErrandsAdapter listAdapter;
    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager;
    private ShopErrandsGridAdapter gridAdapter;
    private List<GoodsListBean.GoodsPlateVOList> menuBeans;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_all_errands;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViews() {
        title.setText("全部商家");
        back.setOnClickListener(this);
        ivChange.setOnClickListener(this);

        linearLayoutManager = new LinearLayoutManager(this);
        if (null != menuBeans && menuBeans.size() > 0) {
            listAdapter = new ShopErrandsAdapter(this, menuBeans);
            rvAllErrands.setAdapter(listAdapter);

            gridLayoutManager = new GridLayoutManager(this, 2);
            gridAdapter = new ShopErrandsGridAdapter(this, menuBeans);
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.ivChange:
                if (flag == 0) {
                    rvAllErrands.setLayoutManager(gridLayoutManager);
                    rvAllErrands.setAdapter(gridAdapter);
                    ivChange.setImageResource(R.mipmap.goods_grid);
                    flag = 1;
                } else {
                    rvAllErrands.setLayoutManager(linearLayoutManager);
                    rvAllErrands.setAdapter(listAdapter);
                    ivChange.setImageResource(R.mipmap.goods_list);
                    flag = 0;
                }
                break;
        }
    }
}
