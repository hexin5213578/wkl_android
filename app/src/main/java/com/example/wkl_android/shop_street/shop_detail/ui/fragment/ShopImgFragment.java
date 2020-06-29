package com.example.wkl_android.shop_street.shop_detail.ui.fragment;

import android.os.Bundle;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.wkl_android.Event.AddCarEvent;
import com.example.wkl_android.Event.ShopImgEvent;
import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseFragment;
import com.example.wkl_android.order.confirm.ConfirmOrderActivity;
import com.example.wkl_android.shop_street.shop_detail.presenter.ShopImgPresenter;
import com.example.wkl_android.shop_street.shop_detail.ui.IShopImgView;
import com.example.wkl_android.shop_street.shop_detail.ui.adapter.ShopImgAdapter;
import com.example.wkl_android.shop_street.shop_detail.ui.adapter.ShopLineImgAdapter;
import com.example.wkl_android.shop_street.shop_detail.ui.bean.ShopDetailVo;
import com.example.wkl_android.shop_street.shop_detail.ui.bean.ShopImg;
import com.example.wkl_android.utils.ViewBgUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 店铺照片
 * Created by szx
 * on 2020/1/18/018
 */
public class ShopImgFragment extends BaseFragment<IShopImgView, ShopImgPresenter>
        implements IShopImgView {

    @BindView(R.id.rvShopImg)
    RecyclerView rvShopImg;
    ShopDetailVo mDetail;


    @BindView(R.id.tv_photo)
    TextView tv_photo;

    @BindView(R.id.tv_img)
    TextView tv_img;

    StaggeredGridLayoutManager gridLayoutManager;
    LinearLayoutManager lineManger;

    ShopLineImgAdapter mAdapter;

    public static ShopImgFragment newInstance(ShopDetailVo detailVo) {

        Bundle args = new Bundle();
        args.putSerializable("detail", detailVo);

        ShopImgFragment fragment = new ShopImgFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @OnClick(R.id.tv_photo)
    public void onPhotoClick() {

        tv_photo.setTextColor(ContextCompat.getColor(getContext(), R.color.color_fe4537));
        tv_img.setTextColor(ContextCompat.getColor(getContext(), R.color.color_333333));

        rvShopImg.setLayoutManager(gridLayoutManager);
        mAdapter.setLine(false);
        mAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.tv_img)
    public void onImgClick() {
        tv_img.setTextColor(ContextCompat.getColor(getContext(), R.color.color_fe4537));
        tv_photo.setTextColor(ContextCompat.getColor(getContext(), R.color.color_333333));

        rvShopImg.setLayoutManager(lineManger);
        mAdapter.setLine(true);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shop_img;
    }

    @Override
    protected ShopImgPresenter createPresenter() {
        return new ShopImgPresenter();
    }

    @Override
    protected void initViews() {

        mDetail = (ShopDetailVo) getArguments().getSerializable("detail");

        ViewBgUtils.setBg(tv_img, "#f8f8f8", 20);
        ViewBgUtils.setBg(tv_photo, "#f8f8f8", 20);

        tv_photo.setText("照片 " + mDetail.getBusinessImageVOList().size());
        tv_img.setText("全景 " + mDetail.getBusinessImageVOList().size());


        lineManger = new LinearLayoutManager(getContext());
        lineManger.setOrientation(LinearLayoutManager.VERTICAL);

        gridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rvShopImg.setLayoutManager(gridLayoutManager);

        mAdapter = new ShopLineImgAdapter(mDetail.getBusinessImageVOList());
        rvShopImg.setAdapter(mAdapter);
    }

}
