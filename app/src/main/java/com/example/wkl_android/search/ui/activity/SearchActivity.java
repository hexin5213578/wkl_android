package com.example.wkl_android.search.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.CheckBox;
import android.widget.EditText;
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
import com.example.wkl_android.common.Common;
import com.example.wkl_android.good.model.GoodsListBean;
import com.example.wkl_android.login.register.ui.activity.RegisterActivity;
import com.example.wkl_android.main.MainActivity;
import com.example.wkl_android.main.home.ui.adapter.HomeCategoryAdapter;
import com.example.wkl_android.main.home.ui.adapter.HomeGoodsItemAdapter;
import com.example.wkl_android.search.ISearchView;
import com.example.wkl_android.search.presenter.SearchPresenter;
import com.example.wkl_android.searchinput.ui.activity.SearchInputActivity;
import com.example.wkl_android.searchshop.ShopAdapter;
import com.example.wkl_android.searchshop.bean.ShopVo;
import com.example.wkl_android.utils.KeyBoardUtils;
import com.example.wkl_android.utils.SPUtils;
import com.example.wkl_android.utils.ToastUtil;
import com.hjq.toast.BaseToast;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 登录页面
 *
 * @author szx
 */
public class SearchActivity extends BaseActivity<ISearchView, SearchPresenter>
        implements ISearchView {


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

    @BindView(R.id.iv_zh)
    ImageView iv_zh;

    @BindView(R.id.ll_sale)
    LinearLayout ll_sale;

    @BindView(R.id.tv_sale)
    TextView tv_sale;

    @BindView(R.id.iv_sale)
    ImageView iv_sale;

    @BindView(R.id.tv_search)
    TextView tv_search;

    @BindView(R.id.rv_shop)
    RecyclerView rv_shop;
    ShopAdapter shopAdapter;

    String msg;

    List<GoodsListBean.DataBean> data = new ArrayList<>();
    HomeGoodsItemAdapter adapter;
    LinearLayoutManager manager;
    GridLayoutManager gridManager;
    int page = 1;
    boolean zhsort = true;

    String classifyId;
    String marketId;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_search;
    }

    @Override
    protected SearchPresenter createPresenter() {
        return new SearchPresenter();
    }

    @Override
    protected void initViews() {
        setTitleBarWithe();
        setType();
        msg = getIntent().getStringExtra("msg");
        classifyId = getIntent().getStringExtra("classifyid");
        marketId = getIntent().getStringExtra("markid");


        et_search.setText(msg);
        if(!TextUtils.isEmpty(marketId)){
            et_search.setVisibility(View.INVISIBLE);
            tv_search.setVisibility(View.INVISIBLE);
        }

        manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        gridManager = new GridLayoutManager(this, 2);

        if (SPUtils.getInstance().getBoolean(Constant.GOODS_LIST_TYPE.GOODS_LIST_TYPE, false)) {
            rv_list.setLayoutManager(manager);
            adapter = new HomeGoodsItemAdapter(this, data, HomeGoodsItemAdapter.TYPE_LINE);
        } else {
            rv_list.setLayoutManager(gridManager);
            adapter = new HomeGoodsItemAdapter(this, data, HomeGoodsItemAdapter.TYPE_GRID);
        }

        rv_list.setAdapter(adapter);
        search();

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_shop.setLayoutManager(manager);


//        et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
//                    //点击搜索的时候隐藏软键盘
//                    KeyBoardUtils.closeKeyboard(et_search);
//                    // 在这里写搜索的操作,一般都是网络请求数据
//                    search();
//                    return true;
//                }
//
//                return false;
//            }
//        });

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

        int sales = 0;
        if (zhsort) {
            sales = 0;
        } else {
            sales = 1;
        }

        if(!TextUtils.isEmpty(marketId)){
            presenter.searchMarket(page , sales , classifyId , marketId);
            return;
        }

        if (TextUtils.isEmpty(msg)) {
            ToastUtil.show("请输入搜索内容");
            return;
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

    public void showData(GoodsListBean bean) {

        svList.onFinishFreshAndLoad();

        if (bean.getPage() == 1) {
            data.clear();
        }

        data.addAll(bean.getData());
        adapter.notifyDataSetChanged();

        if (bean.getBusinessSearchAppVO() != null) {
            ArrayList<ShopVo> shopVos = new ArrayList<>();
            shopVos.add(bean.getBusinessSearchAppVO());
            shopAdapter = new ShopAdapter(shopVos);
            rv_shop.setAdapter(shopAdapter);
        }

    }

    private void setType() {
        if (SPUtils.getInstance().getBoolean(Constant.GOODS_LIST_TYPE.GOODS_LIST_TYPE, false)) {
            iv_line.setImageResource(R.drawable.ic_goods_line);
        } else {
            iv_line.setImageResource(R.drawable.ic_goods_grid);
        }
    }

    @OnClick(R.id.iv_line)
    public void onLineClick() {

        if (rv_list.getLayoutManager() == manager) {
            rv_list.setLayoutManager(gridManager);
            adapter.setType(HomeGoodsItemAdapter.TYPE_GRID);
            SPUtils.getInstance().putBoolean(Constant.GOODS_LIST_TYPE.GOODS_LIST_TYPE, false);
        } else {
            rv_list.setLayoutManager(manager);
            adapter.setType(HomeGoodsItemAdapter.TYPE_LINE);
            SPUtils.getInstance().putBoolean(Constant.GOODS_LIST_TYPE.GOODS_LIST_TYPE, true);
        }

        rv_list.setAdapter(adapter);
        setType();

    }


    @OnClick(R.id.tv_search)
    public void onSearchClick() {
//        KeyBoardUtils.closeKeyboard(et_search);
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

    @OnClick(R.id.ll_area)
    public void onAreaClick() {

    }


    private void setSort(boolean zh) {

        if (zh == zhsort) {
            return;
        }

        zhsort = zh;
        if (zhsort) {
            tv_zh.setTextColor(ContextCompat.getColor(this, R.color.color_ff383b));
            iv_zh.setVisibility(View.VISIBLE);

            tv_sale.setTextColor(ContextCompat.getColor(this, R.color.color_666666));
            iv_sale.setVisibility(View.INVISIBLE);
        } else {
            tv_zh.setTextColor(ContextCompat.getColor(this, R.color.color_666666));
            iv_zh.setVisibility(View.INVISIBLE);

            tv_sale.setTextColor(ContextCompat.getColor(this, R.color.color_ff383b));
            iv_sale.setVisibility(View.VISIBLE);
        }

        page = 1;
        search();
    }

    @OnClick(R.id.et_search)
    public void onEtSearchClick() {
        startActivity(new Intent(this, SearchInputActivity.class));
    }
}
