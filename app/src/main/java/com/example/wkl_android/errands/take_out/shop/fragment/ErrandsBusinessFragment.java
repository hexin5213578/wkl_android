package com.example.wkl_android.errands.take_out.shop.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseFragment;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.errands.take_out.qualification.BusinessQualificationActivity;
import com.example.wkl_android.shop_street.shop_detail.ui.activity.ShopMessageActivity;

import butterknife.BindView;

/**
 * 店铺营业资质
 */
public class ErrandsBusinessFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.llToShop)
    View llToShop;
    @BindView(R.id.ivPhone)
    View ivPhone;
    @BindView(R.id.llBusinessQualification)
    View llBusinessQualification;
    public static ErrandsBusinessFragment newInstance() {

        Bundle args = new Bundle();

        ErrandsBusinessFragment fragment = new ErrandsBusinessFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_errands_business;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViews() {
        llToShop.setOnClickListener(this);
        ivPhone.setOnClickListener(this);
        llBusinessQualification.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.llToShop:
                startActivity(new Intent(APP, ShopMessageActivity.class));
                break;
            case R.id.ivPhone:
                callPhone("11111");
                break;
                //营业资质
            case R.id.llBusinessQualification:
                startActivity(new Intent(APP, BusinessQualificationActivity.class));
                break;
        }
    }

    /**
     * 拨打电话（跳转到拨号界面，用户手动点击拨打）
     *
     * @param phoneNum 电话号码
     */
    public void callPhone(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        startActivity(intent);
    }
}
