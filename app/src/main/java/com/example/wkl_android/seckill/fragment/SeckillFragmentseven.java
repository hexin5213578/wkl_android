package com.example.wkl_android.seckill.fragment;

import android.view.View;

import com.example.wkl_android.R;
import com.example.wkl_android.base.all.BaseFragment;
import com.example.wkl_android.base.all.BasePresenter;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class SeckillFragmentseven extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.rvSeckill)RecyclerView rvSeckill;

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


       /* rvSeckill.setAdapter(new SeckillAdapter(getActivity(), data));
        CustomDecoration customDecoration = new CustomDecoration(getActivity(),
                CustomDecoration.VERTICAL, R.drawable.shape_ll_divider_gray_10dp);
        rvSeckill.addItemDecoration(customDecoration);

        manager.setOrientation(RecyclerView.HORIZONTAL);*/
    }

    @Override
    public void onClick(View view) {

    }
}
