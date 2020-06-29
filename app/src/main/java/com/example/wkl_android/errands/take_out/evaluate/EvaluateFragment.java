package com.example.wkl_android.errands.take_out.evaluate;

import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseFragment;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.errands.take_out.evaluate.adapter.ErrandsEvaluateAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class EvaluateFragment extends BaseFragment {
    @BindView(R.id.rvEvaluate)
    RecyclerView rvEvaluate;

    public static EvaluateFragment newInstance() {
        
        Bundle args = new Bundle();
        
        EvaluateFragment fragment = new EvaluateFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_evaluate;
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
        rvEvaluate.setAdapter(new ErrandsEvaluateAdapter(activity,list));
    }
}
