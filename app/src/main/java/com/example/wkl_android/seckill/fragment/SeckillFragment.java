package com.example.wkl_android.seckill.fragment;

import android.view.View;

import com.example.wkl_android.R;
import com.example.wkl_android.base.all.BaseFragment;
import com.example.wkl_android.base.all.BasePresenter;
import com.example.wkl_android.seckill.adapter.SeckillDiscountAdapter;
import com.example.wkl_android.seckill.bean.SpikeBean;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class SeckillFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.rvSeckill)RecyclerView rvSeckill;
    @BindView(R.id.rvDiscount)RecyclerView rvDiscount;

    @Override
    public void onClick(View view) {

    }

    @Override
    protected void getid(View view) {

    }

    @Override
    protected int getResId() {
        return R.layout.fragment_seckill;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void getData() {
        GridLayoutManager manager = new GridLayoutManager(getContext(), 1);

        List<String> discountList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            discountList.add("");
        }
        SeckillDiscountAdapter seckillDiscountAdapter = new SeckillDiscountAdapter(getContext(),discountList);
        rvDiscount.setLayoutManager(manager);
        rvDiscount.setAdapter(seckillDiscountAdapter);


       /* rvSeckill.setAdapter(new SeckillAdapter(getActivity(), data));
        CustomDecoration customDecoration = new CustomDecoration(getActivity(),
                CustomDecoration.VERTICAL, R.drawable.shape_ll_divider_gray_10dp);
        rvSeckill.addItemDecoration(customDecoration);

        manager.setOrientation(RecyclerView.HORIZONTAL);*/
    }
}
