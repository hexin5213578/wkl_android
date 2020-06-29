package com.example.wkl_android.errands.order.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wkl_android.R;
import com.example.wkl_android.errands.evaluate.ErrandsEvaluateActivity;
import com.example.wkl_android.errands.order_detail.ErrandsOrderDetailActivity;
import com.example.wkl_android.widget.rv.adapter.BaseAdapter;

import java.util.List;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ErrandsOrderAdapter extends BaseAdapter<String, ErrandsOrderAdapter.ViewHolder> {

    private int status;

    public ErrandsOrderAdapter(Context context, List<String> data, int status) {
        super(context, data);
        this.status = status;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_errands_order, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        switch (status) {
            case 0://全部
                break;
            case 1://待支付
                holder.llWaitPay.setVisibility(View.VISIBLE);
                holder.tvEvaluate.setVisibility(View.GONE);
                holder.tvState.setText("待支付");
                holder.tvState.setTextColor(holder.font_red);
                break;
            case 2://待受理
                holder.tvState.setText("待受理");
                holder.llWaitPay.setVisibility(View.GONE);
                holder.tvEvaluate.setVisibility(View.VISIBLE);
                holder.tvEvaluate.setText("提醒发货");

                break;
            case 3://待收货
                holder.tvState.setText("待收货");
                holder.llWaitPay.setVisibility(View.GONE);
                holder.tvEvaluate.setVisibility(View.VISIBLE);
                holder.tvEvaluate.setText("确认收货");
                break;
            case 4://待评价
                holder.llWaitPay.setVisibility(View.GONE);
                holder.tvEvaluate.setVisibility(View.VISIBLE);
                holder.tvState.setText("待评价");
                holder.tvState.setTextColor(holder.font_theme);
                break;
            case 5:
                holder.tvState.setText("已退款");
                holder.tvState.setTextColor(holder.font_black_6);
                holder.llWaitPay.setVisibility(View.GONE);
                holder.tvEvaluate.setVisibility(View.GONE);
                break;
        }
        holder.itemView.setOnClickListener(view ->
                context.startActivity(new Intent(context, ErrandsOrderDetailActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)));
        holder.tvEvaluate.setOnClickListener(view -> {
            if (listener != null) {
                listener.onClick(status);
            }
        });
        holder.tvPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.pay();
                }
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.llWaitPay)
        View llWaitPay;
        @BindView(R.id.tvEvaluate)
        TextView tvEvaluate;
        @BindView(R.id.tvState)
        TextView tvState;
        @BindView(R.id.tvPay)
        View tvPay;
        @BindColor(R.color.theme)
        int font_theme;
        @BindColor(R.color.font_red)
        int font_red;
        @BindColor(R.color.font_black_6)
        int font_black_6;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnClickListener {
        void onClick(int state);

        void pay();
    }

    private OnClickListener listener;

    public void setListener(OnClickListener listener) {
        this.listener = listener;
    }
}
