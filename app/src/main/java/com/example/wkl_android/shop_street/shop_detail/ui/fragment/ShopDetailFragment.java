package com.example.wkl_android.shop_street.shop_detail.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseFragment;
import com.example.wkl_android.shop_street.shop_detail.presenter.ShopDetailPresenter;
import com.example.wkl_android.shop_street.shop_detail.ui.IShopDetailView;
import com.example.wkl_android.shop_street.shop_detail.ui.bean.ShopDetailVo;
import com.example.wkl_android.shop_street.shop_home.ui.activity.ShopHomeActivity;

import butterknife.BindView;

/**
 * Created by szx
 * on 2020/2/1/001
 */
public class ShopDetailFragment extends BaseFragment<IShopDetailView, ShopDetailPresenter> implements View.OnClickListener {
    @BindView(R.id.llToShop)
    View llToShop;
    private FragmentActivity activity;

    @BindView(R.id.tvShopDetail)
    TextView tvShopDetail;

    @BindView(R.id.tv_address)
    TextView tv_address;

    @BindView(R.id.tv_name)
    TextView tv_name;


    ShopDetailVo mDetail;

    public static ShopDetailFragment newInstance(ShopDetailVo detailVo) {

        Bundle args = new Bundle();
        args.putSerializable("detail", detailVo);

        ShopDetailFragment fragment = new ShopDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shop_detail;
    }

    @Override
    protected ShopDetailPresenter createPresenter() {
        return new ShopDetailPresenter();
    }

    @Override
    protected void initViews() {

        mDetail = (ShopDetailVo) getArguments().getSerializable("detail");

        activity = weakReference.get();
        llToShop.setOnClickListener(this);

        tv_address.setText(mDetail.getBusinessAddressDetail());
        tv_name.setText(mDetail.getBusinessName());
        tvShopDetail.setText(mDetail.getBusinessPresentation());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llToShop:
                startActivity(new Intent(activity, ShopHomeActivity.class));
                break;
        }
    }
}
