package com.example.wkl_android.seckill.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wkl_android.R;
import com.example.wkl_android.good.ui.GoodsActivity;
import com.example.wkl_android.seckill.bean.SpikeBean;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SeckillAdapter2 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final FragmentActivity content;
    private final List<SpikeBean.DataBean> list;
    private boolean check = true;
    public SeckillAdapter2(FragmentActivity content, List<SpikeBean.DataBean> list) {
        this.content = content;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(content, R.layout.item_seckill2, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).tvName.setText(list.get(position).getSkuTitle());
        Glide.with(content).load(list.get(position).getSkuImage()).into(((ViewHolder)holder).iv);
        ((ViewHolder)holder).yuanjia.setText(list.get(position).getKillPriceRaw()+"");
        ((ViewHolder)holder).zhekoujia.setText(list.get(position).getKillPrice()+"");
        ((ViewHolder)holder).yuanjia.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG);
        ((ViewHolder)holder).qianggou.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                if(check){
                    ((ViewHolder)holder).qianggou.setText("取消提醒");
                    ((ViewHolder)holder).qianggou.setBackgroundColor(R.color.white);
                    check=false;
                }else{
                    ((ViewHolder)holder).qianggou.setText("提醒我");
                    ((ViewHolder)holder).qianggou.setBackgroundColor(R.color.color_f23a3a);
                    check=true;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_discount)
        TextView tvDiscount;
        @BindView(R.id.zhekoujia)
        TextView zhekoujia;
        @BindView(R.id.yuanjia)
        TextView yuanjia;
        @BindView(R.id.qianggou)
        TextView qianggou;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
