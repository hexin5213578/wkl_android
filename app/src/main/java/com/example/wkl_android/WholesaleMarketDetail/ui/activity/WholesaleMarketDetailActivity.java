package com.example.wkl_android.WholesaleMarketDetail.ui.activity;

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

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.wkl_android.R;
import com.example.wkl_android.WholesaleMarketDetail.IWholesaleMarketDetailView;
import com.example.wkl_android.WholesaleMarketDetail.bean.BannerInfo;
import com.example.wkl_android.WholesaleMarketDetail.bean.MarketAd;
import com.example.wkl_android.WholesaleMarketDetail.bean.MarketDetailVo;
import com.example.wkl_android.WholesaleMarketDetail.bean.MarketPointVO;
import com.example.wkl_android.WholesaleMarketDetail.dapter.PointAdapter;
import com.example.wkl_android.WholesaleMarketDetail.presenter.WholesaleMarketDetailPresenter;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.base.Constant;
import com.example.wkl_android.common.WebCommonActivity;
import com.example.wkl_android.good.model.GoodsListBean;
import com.example.wkl_android.main.home.ui.adapter.HomeGoodsItemAdapter;
import com.example.wkl_android.search.ui.activity.SearchActivity;
import com.example.wkl_android.searchinput.ui.activity.SearchInputActivity;
import com.example.wkl_android.utils.BitmapUtil;
import com.example.wkl_android.utils.SPUtils;
import com.example.wkl_android.utils.ToastUtil;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 登录页面
 *
 * @author szx
 */
public class WholesaleMarketDetailActivity extends BaseActivity<IWholesaleMarketDetailView, WholesaleMarketDetailPresenter>
        implements IWholesaleMarketDetailView, XBanner.OnItemClickListener, XBanner.XBannerAdapter , BaseQuickAdapter.OnItemClickListener {


    @BindView(R.id.home_banner)
    XBanner bannerView;

    @BindView(R.id.svList)
    SpringView svList;


    @BindView(R.id.rv_goods)
    RecyclerView rv_list;

    List<GoodsListBean.DataBean> data = new ArrayList<>();
    HomeGoodsItemAdapter adapter;
    GridLayoutManager gridManager;


    GridLayoutManager pointManager;
    ArrayList<MarketPointVO> points = new ArrayList<>();
    PointAdapter pointAdapter;
    @BindView(R.id.rv_point)
    RecyclerView rv_point;

    @BindView(R.id.iv_ad)
    ImageView iv_ad;


    MarketDetailVo detailVo;
    int page = 1;
    String id;


    @Override
    public int getLayoutRes() {
        return R.layout.activity_market_detail;
    }

    @Override
    protected WholesaleMarketDetailPresenter createPresenter() {
        return new WholesaleMarketDetailPresenter();
    }

    @Override
    protected void initViews() {
        setTitleBarWithe(ContextCompat.getColor(this, R.color.color_ff383b));

        id = getIntent().getStringExtra("id");

        gridManager = new GridLayoutManager(this, 2);
        rv_list.setLayoutManager(gridManager);
        adapter = new HomeGoodsItemAdapter(this, data, HomeGoodsItemAdapter.TYPE_GRID);
        rv_list.setAdapter(adapter);


        pointManager = new GridLayoutManager(this, 5);
        rv_point.setLayoutManager(pointManager);
        pointAdapter = new PointAdapter(points);
        rv_point.setAdapter(pointAdapter);

        pointAdapter.setOnItemClickListener(this);

        spList();
        presenter.getData(id);

        presenter.search(page, 1, id);


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
                presenter.search(page, 1, id);
            }

            @Override
            public void onLoadmore() {
                page++;
                presenter.search(page, 1, id);
            }
        });
    }


    @Override
    public void showData(MarketDetailVo bean) {

        detailVo = bean;

        showBanner(bean.getWholesaleMarketSlideshowVOList());

        points.clear();
        points.addAll(bean.getGoodsClassifyVOList());
        pointAdapter.notifyDataSetChanged();

        if (bean.getWholesaleMarketAdvertisingVOList() == null || bean.getWholesaleMarketAdvertisingVOList().size() == 0) {
            iv_ad.setVisibility(View.GONE);
        } else {
            iv_ad.setVisibility(View.VISIBLE);
            MarketAd ad = bean.getWholesaleMarketAdvertisingVOList().get(0);
            BitmapUtil.showImage(this, ad.getMarketAdvertisingImage(), iv_ad);
        }

    }

    @Override
    public void setGoods(GoodsListBean bean) {
        svList.onFinishFreshAndLoad();
        if (page == 1) {
            data.clear();
        }
        data.addAll(bean.getData());
        adapter.notifyDataSetChanged();

    }

    @OnClick(R.id.tvToSearch)
    public void onSearchClick() {
        startActivity(new Intent(APP, SearchInputActivity.class));

    }

    @OnClick(R.id.iv_ad)
    public void onAdClick() {
        Intent intent = new Intent(this, WebCommonActivity.class);
        intent.putExtra("title", "");
        intent.putExtra("url", detailVo.getWholesaleMarketAdvertisingVOList().get(0).getMarketAdvertisingUrl());
        startActivity(intent);
    }


    private void showBanner(ArrayList<BannerInfo> list) {
        if (list != null && !list.isEmpty() && bannerView != null) {
            bannerView.setVisibility(View.VISIBLE);
            bannerView.setData(list, null);
            bannerView.setmAdapter(this);
            bannerView.setOnItemClickListener(this);
        } else {
            bannerView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onItemClick(XBanner banner, Object model, View view, int position) {
        BannerInfo info = (BannerInfo) model;

        Intent intent = new Intent(this, WebCommonActivity.class);
        intent.putExtra("title", "");
        intent.putExtra("url", info.getSlideshowUrl());
        startActivity(intent);
    }

    @Override
    public void loadBanner(XBanner banner, Object model, View view, int position) {
        BannerInfo info = (BannerInfo) model;

        BitmapUtil.showImage(this, info.getSlideshowImage(), (ImageView) view);

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        MarketPointVO pointVO = points.get(position);
        Intent intent = new Intent(this , SearchActivity.class);
        intent.putExtra("classifyid" , pointVO.getClassifyId());
        intent.putExtra("markid" , id);
        startActivity(intent);

    }
}
