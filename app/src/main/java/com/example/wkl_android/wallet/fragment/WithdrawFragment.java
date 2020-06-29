package com.example.wkl_android.wallet.fragment;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseFragment;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.wallet.adapter.WithdrawAdapter;
import com.example.wkl_android.wallet.presenter.WithdrawPresenter;
import com.example.wkl_android.wallet.ui.IWithdrawView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class WithdrawFragment extends BaseFragment<IWithdrawView, WithdrawPresenter>
        implements IWithdrawView {
    @BindView(R.id.rv)
    RecyclerView rv;

    public static WithdrawFragment newInstance() {

        Bundle args = new Bundle();

        WithdrawFragment fragment = new WithdrawFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_withdraw;
    }

    @Override
    protected WithdrawPresenter createPresenter() {
        return new WithdrawPresenter();
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
