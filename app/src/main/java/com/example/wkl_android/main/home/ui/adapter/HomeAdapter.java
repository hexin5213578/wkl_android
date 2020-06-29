package com.example.wkl_android.main.home.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wkl_android.R;
import com.example.wkl_android.brand.BrandActivity;
import com.example.wkl_android.charge.shop.ChargeShopActivity;
import com.example.wkl_android.common.WebCommonActivity;
import com.example.wkl_android.good.model.GoodsListBean;
import com.example.wkl_android.main.home.model.bean.HomeActivityBean;
import com.example.wkl_android.main.home.model.bean.HomeAd;
import com.example.wkl_android.main.home.model.bean.HomeAdBean;
import com.example.wkl_android.main.home.model.bean.HomeBean;
import com.example.wkl_android.main.home.ui.LoopRecyclerViewPager;
import com.example.wkl_android.main.home.ui.ThreeMarketActivity;
import com.example.wkl_android.market.FruitsMarketActivity;
import com.example.wkl_android.reduce_price.ReducePriceActivity;
import com.example.wkl_android.seckill.SeckillActivity;
import com.example.wkl_android.shop_street.main.ShopStreetActivity;
import com.example.wkl_android.utils.BitmapUtil;
import com.example.wkl_android.utils.DisplayUtil;
import com.example.wkl_android.utils.StringUtil;
import com.example.wkl_android.utils.UIUtil;
import com.example.wkl_android.utils.ViewBgUtils;
import com.example.wkl_android.wholesale_market.ui.activity.WholesaleMarketActivity;
import com.example.wkl_android.widget.rv.ScrollRecyclerView;
import com.example.wkl_android.widget.rv.adapter.BaseAdapter;

import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeAdapter extends BaseAdapter<HomeBean, RecyclerView.ViewHolder> {
    private List<GoodsListBean.GoodsSlideshowVOList> bannerBeans;

    private int flag;
    private List<GoodsListBean.GoodsPlateVOList> menus;
    private HomeAd ad;
    private List<HomeActivityBean> activityBeans;


    public HomeAdapter(Context context, List<HomeBean> data, int flag) {
        super(context, data);
        this.flag = flag;
    }


    public void setAd(HomeAd ad) {
        this.ad = ad;
    }



    public List<GoodsListBean.GoodsSlideshowVOList> getBannerBeans() {
        return bannerBeans;
    }

    public void setBannerBeans(List<GoodsListBean.GoodsSlideshowVOList> bannerBeans) {
        this.bannerBeans = bannerBeans;
    }

    public void setMenus(List<GoodsListBean.GoodsPlateVOList> menus) {
        this.menus = menus;
    }

    public List<GoodsListBean.GoodsPlateVOList> getMenus() {
        return menus;
    }

    public void setActivityBeans(List<HomeActivityBean> activityBeans) {
        this.activityBeans = activityBeans;
    }

    public List<HomeActivityBean> getActivityBeans() {
        return activityBeans;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (R.id.type_header == viewType) {
            View view = inflater.inflate(R.layout.item_home_header, parent, false);
            return new HeaderViewHolder(view);
        }
        View view = inflater.inflate(R.layout.item_home_default, parent, false);
        return new DefaultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //头布局
        if (holder instanceof HeaderViewHolder) {
            HeaderViewHolder viewHolder = (HeaderViewHolder) holder;
            if (null != bannerBeans && bannerBeans.size() > 0) {
                viewHolder.vpBanner.setAdapter(new BannerAdapter(context, bannerBeans));
                viewHolder.vpBanner.setCurrentItem(Integer.MAX_VALUE / 2 - (Integer.MAX_VALUE / 2 % bannerBeans.size()));

//                viewHolder.vpBanner.setPageMargin(100);
                viewHolder.vpBanner.startAutoScroll();
                viewHolder.vpBanner.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {

                        position = position % bannerBeans.size();

                        viewHolder.ll_point.removeAllViews();
                        TextView point;
                        for (int i = 0; i < bannerBeans.size(); i++) {
                            point = new TextView(context);
                            if (i == position) {
                                ViewBgUtils.setBg(point, "#ff383b", 10);
                            } else {
                                ViewBgUtils.setBg(point, "#ffffff", 10);
                            }
                            viewHolder.ll_point.addView(point);
                            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) point.getLayoutParams();
                            params.width = DisplayUtil.dipToPixel(5);
                            params.height = DisplayUtil.dp2px(5);
                            params.rightMargin = DisplayUtil.dipToPixel(15);
                        }

                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
            }

            if(ad == null){
                viewHolder.iv_ad.setVisibility(View.GONE);
            }else {
                ViewGroup.LayoutParams layoutParams = viewHolder.iv_ad.getLayoutParams();
                layoutParams.width = UIUtil.getScreenWidth();
                layoutParams.height = UIUtil.getScreenWidth()*300/533;
                viewHolder.iv_ad.setScaleType(ImageView.ScaleType.FIT_XY);
                viewHolder.iv_ad.setVisibility(View.VISIBLE);
                Log.d("wfs",ad.getAdImage());
                BitmapUtil.showImage(context , ad.getAdImage() , viewHolder.iv_ad);
                viewHolder.iv_ad.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, WebCommonActivity.class);
                        intent.putExtra("title", "");
                        intent.putExtra("url", ad.getAdUrl());
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                });
            }

            if (null != menus && menus.size() > 0) {
                //主菜单

                HorizontalAdapter adapter = new HorizontalAdapter(context, menus, flag);
                viewHolder.rvHorizontal.setAdapter(adapter);
                adapter.setListener((v, position1) -> {
                    GoodsListBean.GoodsPlateVOList item = menus.get(position1);
                    if (TextUtils.equals(item.getPlateUrl() , "WHOLESALE")) {
                        //批发市场
                        Intent intent = new Intent(context, WholesaleMarketActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }

                    if (TextUtils.equals(item.getPlateUrl() , "PRESTORE")) {
                        //预存有礼
                        Intent intent = new Intent(context, ChargeShopActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                    if (TextUtils.equals(item.getPlateUrl() , "ADVERTISING")) {
                        //推广中心
                        Intent intent = new Intent(context, BrandActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }

                    if (TextUtils.equals(item.getPlateUrl() , "STREET")) {
                        //店铺街
                        Intent intent = new Intent(context, ShopStreetActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }


                    if (TextUtils.equals(item.getPlateUrl() , "LIMIT")) {

                        //限时购，特价秒杀
                        Intent intent = new Intent(context, SeckillActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }

                    if (TextUtils.equals(item.getPlateUrl() , "EXPIRE")) {

                        //临期处理
                        Intent intent = new Intent(context, ReducePriceActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }


                    if (TextUtils.equals(item.getPlateUrl() , "SELF")) {
                        //自营市场
                        Intent intent = new Intent(context, ThreeMarketActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
//                    if (position1 == 7) {
//                        //时令瓜果
//                        Intent intent = new Intent(context, FruitsMarketActivity.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        context.startActivity(intent);
//                    }
                });
                GridLayoutManager manager = new GridLayoutManager(context, 4);
//                manager.setOrientation(LinearLayoutManager.HORIZONTAL);
                viewHolder.rvHorizontal.setLayoutManager(manager);
            }

            if (null != activityBeans && activityBeans.size() > 0) {
                viewHolder.rv_ad.setVisibility(View.VISIBLE);
            } else {
                viewHolder.rv_ad.setVisibility(View.GONE);
            }


        }

        //首页活动列表
        if (holder instanceof DefaultViewHolder) {
            DefaultViewHolder viewHolder = (DefaultViewHolder) holder;
            //category表示分组,用HomeAdapter的数据
            LinearLayoutManager manager = new LinearLayoutManager(context);
            manager.setOrientation(LinearLayoutManager.VERTICAL);

            HomeCategoryAdapter goodsAdapter = new HomeCategoryAdapter(context, data);

            viewHolder.rv.setAdapter(goodsAdapter);


        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return R.id.type_header;
        }
        return R.id.type_default;
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.vpBanner)
        LoopRecyclerViewPager vpBanner;
        @BindView(R.id.ll_point)
        LinearLayout ll_point;

        @BindView(R.id.rvHorizontal)
        RecyclerView rvHorizontal;

        @BindView(R.id.rv_ad)
        RecyclerView rv_ad;

        @BindView(R.id.iv_ad)
        ImageView iv_ad;

        HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class DefaultViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.rv)
        RecyclerView rv;

        DefaultViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
