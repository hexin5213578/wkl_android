package com.example.wkl_android.main.home.ui.fragment;

import android.os.Bundle;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseFragment;
import com.example.wkl_android.good.model.GoodsListBean;
import com.example.wkl_android.main.home.model.bean.HomeBean;
import com.example.wkl_android.main.home.presenter.HomeBlankPresenter;
import com.example.wkl_android.main.home.ui.IHomeBlankView;
import com.example.wkl_android.main.home.ui.adapter.HomeAdapter;
import com.example.wkl_android.utils.LogUtils;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;

/**
 * 首页板块内容
 */
public class HomeBlankFragment extends BaseFragment<IHomeBlankView, HomeBlankPresenter> implements IHomeBlankView {
    @BindView(R.id.rvHome)RecyclerView rvHome;
    private int flag;//0首页 1批发市场页面
    @BindView(R.id.svList)SpringView svList;
    private int currentPage = 1; //当前页码
    private List<GoodsListBean.DataBean> goodsList;
    private List<GoodsListBean.DataBean> appendList;
    private HomeAdapter adapter;
    private List<GoodsListBean.GoodsSlideshowVOList> mBannerBeans;
    private List<GoodsListBean.GoodsPlateVOList> mMenuBeans;

    public static HomeBlankFragment newInstance(int flag) {
        Bundle args = new Bundle();
        args.putInt("flag", flag);
        HomeBlankFragment fragment = new HomeBlankFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_top;
    }

    @Override
    protected HomeBlankPresenter createPresenter() {
        return new HomeBlankPresenter();
    }

    @Override
    protected void initViews() {
        LogUtils.d("-----------------------------------in HomeBlankFragment");

        //处理上下拉刷新
        svList.setHeader(new DefaultHeader(getContext()));
        svList.setFooter(new DefaultFooter(getContext()));

        svList.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                currentPage = 1;
                //加载板块数据
                presenter.getGoodsData(currentPage, true);
            }

            @Override
            public void onLoadmore() {
                currentPage++;
                presenter.getGoodsData(currentPage, false);
            }
        });

        Bundle arguments = getArguments();
        if (arguments != null) {
            flag = arguments.getInt("flag");
        }
        List<HomeBean> homeBeans = new ArrayList<>();
        HomeBean homeBean = new HomeBean();
        homeBean.setType(HomeBean.Type.GOODS);
        homeBeans.add(homeBean);
        adapter = new HomeAdapter(getContext(), homeBeans, 1);
        //adapter先加载设置空的数据源， 避免RecyclerView: No adapter attached; skipping layout
        GoodsListBean bean = new GoodsListBean();
        homeBean.setGoodsListBean(bean);
        goodsList = new ArrayList<>();
        goodsList.add(new GoodsListBean.DataBean());
        bean.setData(goodsList);

        mBannerBeans = new ArrayList<>();
        mBannerBeans.add(new GoodsListBean.GoodsSlideshowVOList());
        adapter.setBannerBeans(mBannerBeans);
        mMenuBeans = new ArrayList<>();
        mMenuBeans.add(new GoodsListBean.GoodsPlateVOList());
        adapter.setMenus(mMenuBeans);

//        presenter.getGoodsData(currentPage, true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                presenter.getGoodsData(currentPage, true);
            }
        }).start();
    }


    @Override
    public void onResume() {
        super.onResume();

        if (mBannerBeans == null || mBannerBeans.size() == 0) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    presenter.getGoodsData(currentPage, true);
                }
            }).start();
        }
    }

    /**
     * 处理商品列表显示
     *
     * @param isTop
     * @param bean
     */
    @Override
    public void handleGoodsList(boolean isTop, GoodsListBean bean, List<GoodsListBean.GoodsSlideshowVOList> bannerBeans, List<GoodsListBean.GoodsPlateVOList> menuBeans) {
        //homeAdapter一个商品分组,如果有其他区域在做区分
        //组合一个分组对象,包含分组类型和分类数据,用于处理首页
        //处理上下拉刷新
        LogUtils.d("----------------------------------------------handleGoodsList");


        goodsList = bean.getData();
        mMenuBeans = menuBeans;
        mBannerBeans = bannerBeans;

        if (null == goodsList) {
            goodsList = new ArrayList<>();
        }
        if (null == appendList) {
            appendList = new ArrayList<>();
        }

        List<HomeBean> homeBeans = new ArrayList<>();
        HomeBean homeBean = new HomeBean();
        homeBean.setType(HomeBean.Type.GOODS);
        homeBeans.add(homeBean);
        homeBean.setGoodsListBean(bean);
        if (currentPage > 1) {
//            if (appendList.size() > 0) {
//                appendList.clear();
//            }
            appendList.addAll(goodsList);
            bean.setData(appendList);
            if (adapter == null) {
                adapter = new HomeAdapter(activity, homeBeans, flag);
                rvHome.setAdapter(adapter);
            }
            if(bean.getGoodsAdvertisingVOList() != null && bean.getGoodsAdvertisingVOList().size() > 0){
                adapter.setAd(bean.getGoodsAdvertisingVOList().get(0));
            }
            adapter.setBannerBeans(mBannerBeans);
            adapter.setMenus(mMenuBeans);
        } else {
            appendList.clear();
            appendList.addAll(goodsList);
            bean.setData(appendList);
            adapter = new HomeAdapter(activity, homeBeans, flag);

            if(bean.getGoodsAdvertisingVOList() != null && bean.getGoodsAdvertisingVOList().size() > 0){
                adapter.setAd(bean.getGoodsAdvertisingVOList().get(0));
            }

            adapter.setBannerBeans(mBannerBeans);
            adapter.setMenus(mMenuBeans);
            rvHome.setAdapter(adapter);
        }
        svList.onFinishFreshAndLoad();
        adapter.notifyDataSetChanged();
    }
}
