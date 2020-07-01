package com.example.wkl_android.seckill.fragment;

import android.util.Log;
import android.view.View;

import com.example.wkl_android.R;
import com.example.wkl_android.base.all.BaseFragment;
import com.example.wkl_android.base.all.BasePresenter;
import com.example.wkl_android.seckill.adapter.SeckillAdapter;
import com.example.wkl_android.seckill.bean.SpikeBean;
import com.example.wkl_android.widget.rv.decoration.CustomDecoration;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

public class SeckillFragmentseven extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.rvSeckill)RecyclerView rvSeckill;
    private List<SpikeBean.DataBean> list;

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
        if(list!=null&&list.size()>0){
            GridLayoutManager manager = new GridLayoutManager(getContext(), 1);
            Log.d("hmy",list.size()+"");
            rvSeckill.setAdapter(new SeckillAdapter(getActivity(),list));
            CustomDecoration customDecoration = new CustomDecoration(getActivity(),
                    CustomDecoration.VERTICAL, R.drawable.shape_ll_divider_gray_10dp);
            rvSeckill.addItemDecoration(customDecoration);

            manager.setOrientation(RecyclerView.HORIZONTAL);
        }
    }
    @Override
    public void onClick(View view) {

    }
    public void setData(List<SpikeBean.DataBean> data) {
        list = new ArrayList<>();
        list.addAll(data);
       getData();
    }
}
