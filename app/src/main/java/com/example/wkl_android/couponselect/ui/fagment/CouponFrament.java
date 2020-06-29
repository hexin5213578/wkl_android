package com.example.wkl_android.couponselect.ui.fagment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseFragment;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.brand.adapter.BrandAdapter;
import com.example.wkl_android.couponselect.adapter.CouponAdapter;
import com.example.wkl_android.couponselect.bean.CouponDataInfo;
import com.example.wkl_android.couponselect.bean.CouponInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CouponFrament extends BaseFragment implements BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.rv_coupon)
    RecyclerView rvBrand;

    CouponAdapter mAdapter;
    int postion;

    List<CouponInfo> data = new ArrayList<>();

    public static CouponFrament newInstance(int position , CouponDataInfo data  ) {

        Bundle args = new Bundle();
        args.putInt("position", position);
        args.putSerializable("data" , data);
        CouponFrament fragment = new CouponFrament();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_coupon;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        postion = getArguments().getInt("position");
        data = ((CouponDataInfo)getArguments().getSerializable("data")).getList();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void initViews() {

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rvBrand.setLayoutManager(manager);
        mAdapter = new CouponAdapter(data);
        rvBrand.setAdapter(mAdapter);
        if (postion == 0)
            mAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            for (CouponInfo info : data){
                info.setSelect(false);
            }

            data.get(position).setSelect(true);
            mAdapter.notifyDataSetChanged();
    }
}
