package com.example.wkl_android.order.main.ui.adapter;

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
import com.example.wkl_android.order.detail.OrderDetailActivity;
import com.example.wkl_android.order.main.ui.bean.OrderGoodsInfo;
import com.example.wkl_android.order.main.ui.bean.OrderInfo;
import com.example.wkl_android.utils.BitmapUtil;
import com.example.wkl_android.utils.StringUtil;
import com.example.wkl_android.widget.rv.adapter.BaseAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by szx
 * on 2020/1/2/002
 */
public class OrderAdapter extends BaseAdapter<OrderInfo, OrderAdapter.ViewHolder> {


    public OrderAdapter(Context context, List<OrderInfo> data) {
        super(context, data);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_order, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        OrderInfo item = getItem(position);
        holder.tv_name.setText(item.getBusinessName());

        if (item.getOrderListSlaveVOList().size() == 1) {
            holder.single_goods.setVisibility(View.VISIBLE);
            holder.more_goods.setVisibility(View.GONE);

            OrderGoodsInfo goodsInfo = item.getOrderListSlaveVOList().get(0);

            BitmapUtil.showRadiusImage(context, goodsInfo.getOrderSlaveCommodityImage(), R.mipmap.ic_launcher, 3, holder.iv_single_img);
            holder.goods_name.setText(goodsInfo.getOrderSlaveCommodityTitle());
            holder.goods_type.setText(goodsInfo.getOrderSlaveCommodityStandardValue());
            holder.goods_price.setText("￥" + goodsInfo.getOrderSlaveCommodityPrices());

        } else {
            holder.single_goods.setVisibility(View.GONE);
            holder.more_goods.setVisibility(View.VISIBLE);

            holder.img1.setVisibility(View.GONE);
            holder.img2.setVisibility(View.GONE);
            holder.img3.setVisibility(View.GONE);
            holder.goods_num.setText("共" + item.getOrderListSlaveVOList().size() + "件");

            for (int i = 0; i < item.getOrderListSlaveVOList().size(); i++) {
                OrderGoodsInfo goodsInfo = item.getOrderListSlaveVOList().get(i);
                if (i == 0) {
                    BitmapUtil.showRadiusImage(context, goodsInfo.getOrderSlaveCommodityImage(), R.mipmap.ic_launcher, 3, holder.img1);
                    holder.img1.setVisibility(View.VISIBLE);

                } else if (i == 1) {
                    BitmapUtil.showRadiusImage(context, goodsInfo.getOrderSlaveCommodityImage(), R.mipmap.ic_launcher, 3, holder.img2);
                    holder.img2.setVisibility(View.VISIBLE);
                } else if (i == 2) {
                    BitmapUtil.showRadiusImage(context, goodsInfo.getOrderSlaveCommodityImage(), R.mipmap.ic_launcher, 3, holder.img3);
                    holder.img3.setVisibility(View.VISIBLE);
                }
            }

        }

        holder.tv_more_num.setText("共" + item.getOrderListSlaveVOList().size() + "件商品 合计：￥");
        holder.pay_price.setText(StringUtil.changeSizeByDot(item.getOrderMasterTotalPrice(), 0.7f));
        holder.tv_yunfei.setText("(含运费" + item.getOrderMasterFreight() + ")");

        int status = item.getOrderMasterStatus();
        holder.tv_pay.setVisibility(View.GONE);
        holder.tv_confirm.setVisibility(View.GONE);
        holder.tv_look.setVisibility(View.GONE);
        holder.tv_cancel.setVisibility(View.GONE);
        holder.tv_comment.setVisibility(View.GONE);

        switch (status) {
            case 1://待发货
                holder.tvStatus.setText("待发货");
                holder.tv_cancel.setVisibility(View.VISIBLE);

                break;
            case 2://待收货
                holder.tvStatus.setText("待收货");
                holder.tv_look.setVisibility(View.VISIBLE);
                holder.tv_confirm.setVisibility(View.VISIBLE);
                break;
            case 3://待评价
                holder.tvStatus.setText("待评价");
                holder.tv_comment.setVisibility(View.VISIBLE);
                break;
            case 4://已评价
                holder.tvStatus.setText("已发货");
                holder.tv_look.setVisibility(View.VISIBLE);
                break;
            case 5:  //待待退款
                holder.tvStatus.setText("待退款");
                break;
            case 6:  //退款成功
                holder.tvStatus.setText("退款成功");
                break;
            case 7:  //待商家确认
                holder.tvStatus.setText("待商家确认");
                break;
            case 8:  //待用户付款
                holder.tvStatus.setText("待付款");
                holder.tv_pay.setVisibility(View.VISIBLE);
                holder.tv_cancel.setVisibility(View.VISIBLE);
                break;
            case 9:  //已取消
                holder.tvStatus.setText("已取消");
                break;
        }
        holder.tv_pay.setOnClickListener(v -> {
            if (listener != null) {
                listener.onPayClick(position);
            }
        });
        holder.tv_cancel.setOnClickListener(v -> {
            if (listener != null) {
                listener.onCacelClick(position);
            }
        });
        holder.tv_confirm.setOnClickListener(v -> {
            if (listener != null) {
                listener.onConfirmClick(position);
            }
        });

        holder.tv_look.setOnClickListener(v -> {
            if (listener != null) {
                listener.onLookClick(position);
            }
        });

        holder.tv_comment.setOnClickListener(v -> {
            if (listener != null) {
                listener.onCommentClick(position);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OrderDetailActivity.class);
                intent.putExtra("id", item.getOrderMasterId());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    public interface OnClickListener {
        void onPayClick(int position);

        void onConfirmClick(int position);

        void onLookClick(int position);

        void onCacelClick(int position);

        void onCommentClick(int position);
    }

    private OnClickListener listener;

    public void setListener(OnClickListener listener) {
        this.listener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_pay)
        TextView tv_pay;
        @BindView(R.id.tv_confrim)
        TextView tv_confirm;
        @BindView(R.id.tv_comment)
        TextView tv_comment;
        @BindView(R.id.tv_look)
        TextView tv_look;
        @BindView(R.id.tv_cancel)
        TextView tv_cancel;

        @BindView(R.id.tvStatus)
        TextView tvStatus;

        @BindView(R.id.ll_more_goods)
        LinearLayout more_goods;
        @BindView(R.id.ll_single_goods)
        LinearLayout single_goods;
        @BindView(R.id.tv_name)
        TextView tv_name;

        @BindView(R.id.iv_single_img)
        ImageView iv_single_img;

        @BindView(R.id.tv_goods_name)
        TextView goods_name;
        @BindView(R.id.tv_goods_type)
        TextView goods_type;
        @BindView(R.id.tv_goods_price)
        TextView goods_price;


        @BindView(R.id.iv_img1)
        ImageView img1;
        @BindView(R.id.iv_img2)
        ImageView img2;
        @BindView(R.id.iv_img3)
        ImageView img3;
        @BindView(R.id.tv_goods_num)
        TextView goods_num;

        @BindView(R.id.tv_more_num)
        TextView tv_more_num;
        @BindView(R.id.tv_pay_price)
        TextView pay_price;
        @BindView(R.id.tv_yunfei)
        TextView tv_yunfei;


        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
