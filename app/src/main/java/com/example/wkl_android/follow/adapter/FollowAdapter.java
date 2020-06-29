package com.example.wkl_android.follow.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wkl_android.R;
import com.example.wkl_android.collection.bean.CollectionShop;
import com.example.wkl_android.shop_discount.ShopDiscountActivity;
import com.example.wkl_android.utils.BitmapUtil;
import com.example.wkl_android.widget.rv.adapter.BaseAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FollowAdapter extends BaseAdapter<CollectionShop, FollowAdapter.ViewHolder> {
    public FollowAdapter(Context context, List<CollectionShop> data) {
        super(context, data);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_follow, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        CollectionShop item = data.get(position);
        holder.tv_name.setText(item.getBusinessName());
        holder.tv_num.setText("已有" + item.getBusinessCountNumber() + "人关注");

        BitmapUtil.showImage(context, item.getBusinessLogo(), holder.iv_img);

        if (item.isCheck()) {
            holder.iv_check.setVisibility(View.VISIBLE);
            if (item.isSelect()) {
                holder.iv_check.setImageResource(R.drawable.checked);
            } else {
                holder.iv_check.setImageResource(R.drawable.no_check);
            }

        } else {
            holder.iv_check.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(view -> {
            if (listener != null) {
                listener.onClick(position);
            }
        });

    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_check)
        ImageView iv_check;

        @BindView(R.id.iv_img)
        ImageView iv_img;

        @BindView(R.id.tv_name)
        TextView tv_name;

        @BindView(R.id.tv_num)
        TextView tv_num;


        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnClickListener {
        void onClick(int positon);
    }

    private OnClickListener listener;

    public void setListener(OnClickListener listener) {
        this.listener = listener;
    }
}
