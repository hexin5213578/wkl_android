package com.example.wkl_android.errands.help_buy.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wkl_android.R;
import com.example.wkl_android.errands.help_buy.bean.SelectBean;
import com.example.wkl_android.widget.rv.adapter.BaseAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GoodsCategoryAdapter extends BaseAdapter<SelectBean, GoodsCategoryAdapter.ViewHolder> {

    public GoodsCategoryAdapter(Context context, List<SelectBean> data) {
        super(context, data);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_help_buy_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SelectBean bean = data.get(position);
        holder.tvName.setText(bean.getName());
        holder.tvNameDetail.setText(bean.getDetail());

        if (bean.isSelect()) {
            holder.ivChecked.setVisibility(View.VISIBLE);
        } else {
            holder.ivChecked.setVisibility(View.GONE);
        }
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onClick(position);
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivChecked)
        CheckBox ivChecked;
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvNameDetail)
        TextView tvNameDetail;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnClickListener {
        void onClick(int position);
    }

    private OnClickListener listener;

    public void setListener(OnClickListener listener) {
        this.listener = listener;
    }
}
