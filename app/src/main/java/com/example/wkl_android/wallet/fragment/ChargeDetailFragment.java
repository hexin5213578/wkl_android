package com.example.wkl_android.wallet.fragment;

import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseFragment;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.wallet.adapter.WithdrawAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ChargeDetailFragment extends BaseFragment {
    @BindView(R.id.rv)
    RecyclerView rv;

    public static ChargeDetailFragment newInstance() {

        Bundle args = new Bundle();

        ChargeDetailFragment fragment = new ChargeDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_charge_detail;
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
        WithdrawAdapter adapter = new WithdrawAdapter(activity, list);
        rv.setAdapter(adapter);
    }
}
