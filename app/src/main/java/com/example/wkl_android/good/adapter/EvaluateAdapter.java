package com.example.wkl_android.good.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wkl_android.R;
import com.example.wkl_android.good.model.CommentBean;
import com.example.wkl_android.good.model.CommentImg;
import com.example.wkl_android.order.evaluate.adapter.ImagePickerAdapter;
import com.example.wkl_android.utils.BitmapUtil;
import com.example.wkl_android.widget.rv.adapter.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EvaluateAdapter extends BaseAdapter<CommentBean, EvaluateAdapter.ViewHolder> {
    public EvaluateAdapter(Context context, List<CommentBean> data) {
        super(context, data);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_evaluate, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        CommentBean bean = data.get(position);

        holder.tv_name.setText(bean.getGoodsEstimateUserName());
        BitmapUtil.showImage(context, bean.getOrderSlaveCommodityImage(), holder.iv_header);
        holder.rb_star.setNumStars(bean.getGoodsEstimateGrade());
        holder.tv_msg.setText(bean.getGoodsEstimateDetail());

        List<String> list = new ArrayList<>();

        if (bean.getGoodsEstimateImageVOList() != null && bean.getGoodsEstimateImageVOList().size() > 0) {
            for (CommentImg img : bean.getGoodsEstimateImageVOList()) {
                list.add(img.getGoodsEstimateImageUrl());
            }
        }
        GridLayoutManager manager = new GridLayoutManager(context, 3,
                RecyclerView.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        holder.rvEvaluateImg.setLayoutManager(manager);
        holder.rvEvaluateImg.setAdapter(new ImagePickerAdapter(context, list, true,
                5));
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.rvEvaluateImg)
        RecyclerView rvEvaluateImg;

        @BindView(R.id.iv_header)
        ImageView iv_header;

        @BindView(R.id.tv_name)
        TextView tv_name;

        @BindView(R.id.rb_star)
        RatingBar rb_star;

        @BindView(R.id.tv_msg)
        TextView tv_msg;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
