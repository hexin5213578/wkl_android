package com.example.wkl_android.main.home.ui;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.http.HttpUtils;
import com.example.wkl_android.http.callback.impl.JsonCallBack;
import com.example.wkl_android.good.model.GoodsListBean;
import com.example.wkl_android.shop_street.shop_home.ui.adapter.GoodsAdapter;
import com.example.wkl_android.shop_street.shop_home.ui.adapter.ShopGoodsAdapter;
import com.example.wkl_android.utils.C;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;

/**
 * 商品列表
 */
public class GoodsListActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.ivLeft)
    View back;
    @BindView(R.id.tvTitle)
    TextView title;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.rvGoods)
    RecyclerView rvGoods;
    @BindView(R.id.ivSelectList)
    ImageView ivSelectList;

    private int flag, flag1;
    private GridLayoutManager gridLayoutManager;
    private ShopGoodsAdapter gridAdapter;
    private GoodsAdapter listAdapter;
    private LinearLayoutManager linearLayoutManager;
    private String name;
    private List<GoodsListBean.DataBean> list;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_goods_list;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    public static void toThisActivity(Context context, String id) {
        Intent intent = new Intent(context, GoodsListActivity.class);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }

    @Override
    protected void initViews() {
        Intent intent = getIntent();
        if (intent != null) {
            name = intent.getStringExtra("name");
        }
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("查看店铺");
        tvRight.setOnClickListener(this);
        title.setText("商品列表");
        back.setOnClickListener(this);
        ivSelectList.setOnClickListener(this);
        rvGoods.setAdapter(new GoodsAdapter(this, list));
        linearLayoutManager = new LinearLayoutManager(this);
        gridLayoutManager = new GridLayoutManager(this, 2);
        listAdapter = new GoodsAdapter(this, list, name);
        gridAdapter = new ShopGoodsAdapter(this, list, name);
        rvGoods.setLayoutManager(linearLayoutManager);
        rvGoods.setAdapter(listAdapter);
        initData();
    }

    private void initData() {
        HttpUtils.getInstance().doGet(C.SEARCH+"?classifyId="+getIntent().getStringExtra("id"), Common.getToken(), this, new JsonCallBack() {
            @Override
            public void onSuccess(String json) throws Exception {
                Log.e("ZYY", "请求数据成功");
                Gson g = new Gson();

                GoodsListBean goodsListBean = g.fromJson(json, GoodsListBean.class);
                listAdapter.setData(goodsListBean.getData());


            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.ivSelectList:
                if (flag == 0) {
                    rvGoods.setLayoutManager(gridLayoutManager);
                    rvGoods.setAdapter(gridAdapter);
                    ivSelectList.setImageResource(R.mipmap.goods_grid);
                    flag = 1;
                } else {
                    rvGoods.setLayoutManager(linearLayoutManager);
                    rvGoods.setAdapter(listAdapter);
                    ivSelectList.setImageResource(R.mipmap.goods_list);
                    flag = 0;
                }
                break;
            case R.id.tvRight:
                if (flag1 == 0) {
                    rvGoods.setLayoutManager(linearLayoutManager);
                 //   rvGoods.setAdapter(new StreetAdapter(this, list, 1));
                    flag1 = 1;
                    tvRight.setText("查看商品");

                } else {
                    rvGoods.setLayoutManager(linearLayoutManager);
                    rvGoods.setAdapter(listAdapter);
                    tvRight.setText("查看店铺");
                    flag1 = 0;
                }
                break;
        }
    }
}
