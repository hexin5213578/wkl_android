package com.example.wkl_android.charge.card.fragment;

import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseFragment;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.charge.card.adapter.ChargeGiftAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ChargeGiftFragment extends BaseFragment {
    @BindView(R.id.rvChargeGift)
    RecyclerView rvChargeGift;

    public static ChargeGiftFragment newInstance() {
        
        Bundle args = new Bundle();
        
        ChargeGiftFragment fragment = new ChargeGiftFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_charge_gift;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViews() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("");
        }
        rvChargeGift.setAdapter(new ChargeGiftAdapter(activity, list));
    }
}
