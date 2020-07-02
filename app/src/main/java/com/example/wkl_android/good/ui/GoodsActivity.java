package com.example.wkl_android.good.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.example.wkl_android.Event.AddCarEvent;
import com.example.wkl_android.Event.Event;
import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.charge.detail.ChargeDetailActivity;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.good.adapter.EvaluateAdapter;
import com.example.wkl_android.good.model.CommentBean;
import com.example.wkl_android.good.model.GoodsBean;
import com.example.wkl_android.good.model.GoodsListBean;
import com.example.wkl_android.good.model.GoodsSelDialog;
import com.example.wkl_android.good.presenter.GoodsDetailPresenter;
import com.example.wkl_android.good.ui.evaluate.GoodsEvaluateActivity;
import com.example.wkl_android.main.MainActivity;
import com.example.wkl_android.main.home.ui.LoopRecyclerViewPager;
import com.example.wkl_android.main.home.ui.adapter.BannerAdapter;
import com.example.wkl_android.order.confirm.ConfirmOrderActivity;
import com.example.wkl_android.shop_street.shop_home.ui.activity.ShopHomeActivity;
import com.example.wkl_android.utils.StringUtil;
import com.example.wkl_android.utils.ViewBgUtils;
import com.example.wkl_android.widget.rv.decoration.CustomDecoration;
import com.example.wkl_android.widget.web.NoScrollWebView;
import com.sunfusheng.util.Utils;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 商品详情
 */
public class GoodsActivity extends BaseActivity<IGoodsView, GoodsDetailPresenter> implements IGoodsView, View.OnClickListener {
     @BindView(R.id.ivLeft) View back;
    @BindView(R.id.vpBanner) LoopRecyclerViewPager vpBanner;
    @BindView(R.id.tvIndicator) TextView tvIndicator;
    @BindView(R.id.rvEvaluate) RecyclerView rvEvaluate;
    @BindView(R.id.tvMoreEvaluate) View tvMoreEvaluate;
    //    @BindView(R.id.llToShop)
//    View llToShop;
    @BindView(R.id.tvToCharge) View tvToCharge;
    @BindView(R.id.llEvaluate) View llEvaluate;
    @BindView(R.id.tvShop) View tvShop;
    @BindView(R.id.tvShoppingCart) View tvShoppingCart;
    @BindView(R.id.tvAddShoppingCart) View tvAddShoppingCart;
    @BindView(R.id.tvBuy) View tvBuy;
    @BindView(R.id.price) TextView tvPrice;
    @BindView(R.id.title) TextView goodsTitle;

    @BindView(R.id.webview) NoScrollWebView webView;
    @BindView(R.id.tvSkuEstimate) TextView tvSkuEstimate;
    @BindView(R.id.tvSkuGoodEstimate) TextView tvSkuGoodEstimate;

    //    @BindView(R.id.ivImg)
//    GlideImageView ivImg;
    private WebChromeClient.CustomViewCallback customViewCallback;
    private List<GoodsListBean.GoodsSlideshowVOList> goodsBanner;
    private String id;
    private GoodsBean mGoodsBean;
    private String type;
    private String price;
    private String mTitle;
    private String goodsImgForLoading; //加载时请求上一页的图片提高显示速度

    @Override
    public int getLayoutRes() {
        return R.layout.activity_goods;
    }

    @Override
    protected void onStop() {
        super.onStop();
        webView.reload();
    }

    @Override
    protected GoodsDetailPresenter createPresenter() {
        return new GoodsDetailPresenter();
    }

    public static void toThisActivity(Context context, String type, String id, String price, String title, String img) {
        Intent intent = new Intent(context, GoodsActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("type", type);
        intent.putExtra("price", price);
        intent.putExtra("title", title);
        intent.putExtra("loadingImg", img);

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
     }

    GoodsSelDialog dialog;

    @Override
    protected void initViews() {
        back.setOnClickListener(this);

        setTitleBarWithe();

        if (getIntent().hasExtra("id")) {
            id = getIntent().getStringExtra("id");
        }
        if (getIntent().hasExtra("type")) {
            type = getIntent().getStringExtra("type");
        }
        if (getIntent().hasExtra("price")) {
            price = getIntent().getStringExtra("price");
        }
        if (getIntent().hasExtra("title")) {
            mTitle = getIntent().getStringExtra("title");
        }
        if (getIntent().hasExtra("loadingImg")) {
            goodsImgForLoading = getIntent().getStringExtra("loadingImg");
//            ivImg.load(goodsImgForLoading);
        }
        tvMoreEvaluate.setOnClickListener(this);
//        llToShop.setOnClickListener(this);
        tvToCharge.setOnClickListener(this);
        llEvaluate.setOnClickListener(this);
        tvShop.setOnClickListener(this);
        tvShoppingCart.setOnClickListener(this);
        tvAddShoppingCart.setOnClickListener(this);
        tvBuy.setOnClickListener(this);
        goodsBanner = new ArrayList<>();

        ViewBgUtils.setBg(tvAddShoppingCart , "#ff383b" , 50);
        ViewBgUtils.setBg(tvBuy , "#ffb802" , 50);

        //显示正在加载
        tvPrice.setText(StringUtil.changeSizeByDot(price , 0.7f));
        goodsTitle.setText(mTitle);
        showLoading();

        presenter.getDateil(type, id);


        //加载评价
        List<CommentBean> list = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            list.add(new CommentBean());
        }
        LinearLayoutManager manager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        rvEvaluate.setLayoutManager(manager);
        rvEvaluate.setAdapter(new EvaluateAdapter(this, list));
        CustomDecoration customDecoration = new CustomDecoration(this, CustomDecoration.VERTICAL, R.drawable.shape_ll_divider_gray);
        rvEvaluate.addItemDecoration(customDecoration);

        //webview加载商品详情
        //webview处理
        WebSettings webSettings = webView.getSettings();//获取webview设置属性
        //webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);//把html中的内容放大webview等宽的一列中
        webSettings.setJavaScriptEnabled(true);//支持js
        webSettings.setUseWideViewPort(true); // 关键点
        webSettings.setLoadWithOverviewMode(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            webSettings.setMediaPlaybackRequiresUserGesture(false);
        }
        webSettings.setAllowFileAccess(true); // 允许访问文件
        webSettings.setBuiltInZoomControls(false); // 不显示放大缩小
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE); // 不加载缓存内容
        webSettings.setSupportZoom(false); // 不可以缩放wv_detail.setVerticalScrollBarEnabled(false);
        webView.setVerticalScrollbarOverlay(false);
        webView.setHorizontalScrollBarEnabled(false);
        //默认字体，屏幕密度*18
        webSettings.setDefaultFontSize((int) (Utils.getDisplayMetrics(this).density * 18));
        webView.setHorizontalScrollbarOverlay(false);

        webSettings.setDefaultTextEncodingName("UTF-8");
        webView.setWebViewClient(new MyWebViewClient());

    }

    public class MyWebViewClient extends WebViewClient {

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            imgReset();//重置webview中img标签的图片大小
            videoReset();
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    private void imgReset() {
        String javascript = "javascript:function ResizeImages() {" +
                "var maxwidth = document.body.clientWidth;" +
                "for(i=0;i <document.images.length;i++){" +
                " var myimg = document.images[i];" +
                "if(myimg.width > maxwidth){" +
                "myimg.width = maxwidth;" +
                "}" +
                "}" +
                "}";
        webView.loadUrl(javascript);
        webView.loadUrl("javascript:ResizeImages();");

    }

    private void videoReset() {
        webView.loadUrl("javascript:(function(){" +
                "var objs = document.getElementsByTagName('video'); " +
                "for(var i=0;i<objs.length;i++)  " +
                "{"
                + "var video = objs[i];   " +
                "    video.style.width = '100%'; video.style.height = 'auto';  " +
                "}" +
                "})()");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.llEvaluate:
            case R.id.tvMoreEvaluate:
                Intent intent =new Intent(APP, GoodsEvaluateActivity.class);
                intent.putExtra("skuid" , id);
                startActivity(intent);
                break;
            case R.id.tvShop:
            case R.id.llToShop:
                startActivity(new Intent(APP, ShopHomeActivity.class));
                break;
            case R.id.tvToCharge:
                startActivity(new Intent(APP, ChargeDetailActivity.class));
                break;
            case R.id.tvShoppingCart:
                startActivity(new Intent(APP, MainActivity.class).putExtra("index", 2));
                break;
            case R.id.tvAddShoppingCart:
                //打开商品属性选择
                addCar = true;
                dialog.show(getSupportFragmentManager(), "1");
               /* Map<String, Long> map = new HashMap<>();
                map.put("data", Long.valueOf(mGoodsBean.getData().getGoodsStaticPreviewSkuStandardSetVOList().get(0).getGoodsStaticPreviewSkuStandardValueSetVOSet().get(0).getSkuId()));
                String token = Common.getToken();
                Log.e("ZYY", token);
                HttpUtils.getInstance().doPostJson(C.ADD_TO_SHOP_CARD, new Gson().toJson(map), token, null, new JsonCallBack() {
                    @Override
                    public void onSuccess(String json) throws Exception {
                        Log.e("ZYY", json + "---");
                        JSONObject jsonObject = new JSONObject(json);
                        if (json.contains("保存成功")) {
                            Toast.makeText(GoodsActivity.this, "加入购物车成功", Toast.LENGTH_SHORT).show();
                        }
                    }
                });*/



                break;
            case R.id.tvBuy:
                addCar = false;
                dialog.show(getSupportFragmentManager(), "1");

                break;
        }
    }


    @Override
    public void handleGoodsDetail(GoodsBean goodsBean) {
        // ivImg.setVisibility(View.GONE);
        //  vpBanner.setVisibility(View.VISIBLE);

        if(!goodsBean.isStatus()){

        }

        this.mGoodsBean = goodsBean;
        tvPrice.setText(StringUtil.changeSizeByDot(goodsBean.getData().getGoodsStaticPreviewSkuVOList().get(0).getSkuPrice() + "" , 0.7f));
        goodsTitle.setText(goodsBean.getData().getProductTitle());
        //显示sku
        dialog = new GoodsSelDialog(goodsBean.getData());

        tvSkuEstimate.setText(goodsBean.getData().getMasterGoodsStaticPreviewSkuVO().getGoodsStaticPreviewSkuEstimateVO().getSkuEstimate() + "+");
        tvSkuGoodEstimate.setText(goodsBean.getData().getMasterGoodsStaticPreviewSkuVO().getGoodsStaticPreviewSkuEstimateVO().getSkuGoodEstimate() + "");
        // Glide.with(GoodsActivity.this).load(Common.getResizeImg(goodsBean.getData().getBusinessImage(), 60, 60)).into(shopIcon);
        List<GoodsBean.DataBean.GoodsStaticPreviewCarouselImageVOListBean> goodsStaticPreviewCarouselImageVOList = goodsBean.getData().getGoodsStaticPreviewCarouselImageVOList();
        for (GoodsBean.DataBean.GoodsStaticPreviewCarouselImageVOListBean listBean :
                goodsStaticPreviewCarouselImageVOList) {
            GoodsListBean.GoodsSlideshowVOList bannerBean = new GoodsListBean.GoodsSlideshowVOList();
            bannerBean.setSlideshowImage(Common.getResizeImg(listBean.getImageUrl(), 600, 600));
            goodsBanner.add(bannerBean);
        }
        if (goodsBanner.size() > 0) {
            vpBanner.setAdapter(new BannerAdapter(this, goodsBanner));
            vpBanner.setCurrentItem(0);
            tvIndicator.setText((vpBanner.getCurrentItem() + 1) + "/" + goodsBanner.size());
            vpBanner.setOffscreenPageLimit(5);
            vpBanner.setPageMargin(100);
            vpBanner.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    //localstring format导致卡顿
                    tvIndicator.setText((position % goodsBanner.size()) + 1 + "/" + goodsBanner.size());
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }
        webView.loadData(Common.translationHtml(goodsBean.getData().getSpuDetailRichText()), "text/html; charset=UTF-8", null);

        dismissLoading();
    }

    @Override
    public void collectSuccess() {

    }


    boolean addCar = true;
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void EventBusEvent(AddCarEvent event) {
        if(addCar){
            presenter.addCar(event.getSkuId());
        }else {
            ConfirmOrderActivity.toThisActivity(this, mGoodsBean , event.getSkuId() , event.getCount());
        }

    }

    @OnClick(R.id.ll_collect)
    public void onCollectClick(){
        presenter.collectGoods(mGoodsBean.getData().getGoodsStaticPreviewSkuVOList().get(0).getSkuId());
    }
}
