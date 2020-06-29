package com.example.wkl_android.searchshop.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.base.Constant;
import com.example.wkl_android.good.model.GoodsListBean;
import com.example.wkl_android.main.home.ui.adapter.HomeGoodsItemAdapter;
import com.example.wkl_android.searchinput.ui.activity.SearchInputActivity;
import com.example.wkl_android.searchshop.ISearchShopView;
import com.example.wkl_android.searchshop.ShopAdapter;
import com.example.wkl_android.searchshop.bean.ShopVo;
import com.example.wkl_android.searchshop.presenter.SearchShopPresenter;
import com.example.wkl_android.utils.SPUtils;
import com.example.wkl_android.utils.ToastUtil;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 登录页面
 *
 * @author szx
 */
public class SearchShopActivity extends BaseActivity<ISearchShopView, SearchShopPresenter>
        implements ISearchShopView {


    @BindView(R.id.et_search)
    TextView et_search;

    @BindView(R.id.svList)
    SpringView svList;

    @BindView(R.id.rv_list)
    RecyclerView rv_list;

    @BindView(R.id.iv_line)
    ImageView iv_line;

    @BindView(R.id.ll_zh)
    LinearLayout ll_zh;

    @BindView(R.id.tv_zh)
    TextView tv_zh;


    @BindView(R.id.ll_sale)
    LinearLayout ll_sale;

    @BindView(R.id.tv_sale)
    TextView tv_sale;

    @BindView(R.id.iv_sale)
    ImageView iv_sale;

    String msg;

    List<ShopVo> data = new ArrayList<>();
    ShopAdapter adapter;
    LinearLayoutManager manager;
    int page = 1;
    boolean zhsort = true;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_search_shop;
    }

    @Override
    protected SearchShopPresenter createPresenter() {
        return new SearchShopPresenter();
    }

    @Override
    protected void initViews() {
        setTitleBarWithe();
        msg = getIntent().getStringExtra("msg");
        et_search.setText(msg);

        manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);


        rv_list.setLayoutManager(manager);
        adapter = new ShopAdapter(data);


        rv_list.setAdapter(adapter);
        search();

        spList();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        msg = intent.getStringExtra("msg");
        et_search.setText(msg);
        search();
    }

    private void search() {
//        String msg = et_search.getText().toString();

        if (TextUtils.isEmpty(msg)) {
            ToastUtil.show("请输入搜索内容");
            return;
        }


        int sales = 0;
        if (zhsort) {
            sales = 0;
        } else {
            sales = 1;
        }

        presenter.search(page, sales, msg);

    }

    private void spList() {
        //处理上下拉刷新
        svList.setHeader(new DefaultHeader(this));
        svList.setFooter(new DefaultFooter(this));

        svList.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                //加载板块数据
                search();
            }

            @Override
            public void onLoadmore() {
                page++;
                search();
            }
        });
    }

    public void showData(ArrayList<ShopVo> bean) {

        svList.onFinishFreshAndLoad();

        if (page == 1) {
            data.clear();
        }

        data.addAll(bean);
        adapter.notifyDataSetChanged();
    }


    @OnClick(R.id.tv_search)
    public void onSearchClick() {
        search();
    }

    @OnClick(R.id.iv_back)
    public void onBackClick() {
        finish();
    }


    @OnClick(R.id.ll_zh)
    public void onZhClick() {
        setSort(true);
    }

    @OnClick(R.id.ll_sale)
    public void onSaleClick() {
        setSort(false);
    }

    @OnClick(R.id.ll_brand)
    public void onBrandClick() {

    }


    private void setSort(boolean zh) {

        if (zh == zhsort) {
            return;
        }

        zhsort = zh;
        if (zhsort) {
            tv_zh.setTextColor(ContextCompat.getColor(this, R.color.color_ff383b));

            tv_sale.setTextColor(ContextCompat.getColor(this, R.color.color_666666));
            iv_sale.setVisibility(View.INVISIBLE);
        } else {
            tv_zh.setTextColor(ContextCompat.getColor(this, R.color.color_666666));

            tv_sale.setTextColor(ContextCompat.getColor(this, R.color.color_ff383b));
            iv_sale.setVisibility(View.VISIBLE);
        }

        page = 1;
        search();
    }

    @OnClick(R.id.et_search)
    public void onEtSearchClick() {
        Intent intent = new Intent(this, SearchInputActivity.class);
        intent.putExtra("type", 1);
        startActivity(intent);
    }
}
