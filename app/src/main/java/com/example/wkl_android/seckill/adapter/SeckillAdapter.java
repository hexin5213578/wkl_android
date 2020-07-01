package com.example.wkl_android.seckill.adapter;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.wkl_android.R;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.good.ui.GoodsActivity;
import com.example.wkl_android.seckill.SeckillDetailsActivity;
import com.example.wkl_android.seckill.bean.SpikeBean;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SeckillAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final FragmentActivity content;
    private final List<SpikeBean.DataBean> list;

    public SeckillAdapter(FragmentActivity content, List<SpikeBean.DataBean> list) {
        this.content = content;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(content, R.layout.item_seckill, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        SpikeBean.DataBean dataBean = list.get(position);
        String imgUrl = Common.getResizeImg(dataBean.getSkuImage(), 300, 300);

        ((ViewHolder) holder).tvName.setText(list.get(position).getSkuTitle());
        Glide.with(content).load(list.get(position).getSkuImage()).into(((ViewHolder) holder).iv);
        ((ViewHolder) holder).yuanjia.setText(list.get(position).getKillPriceRaw() + "");
        ((ViewHolder) holder).zhekoujia.setText(list.get(position).getKillPrice() + "");
        ((ViewHolder) holder).yuanjia.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        int killStockRaw = list.get(position).getKillStockRaw();
        int killStock = list.get(position).getKillStock();

        int a = killStockRaw - killStock;
        ((ViewHolder) holder).progressbar.setMax(killStockRaw);
        ((ViewHolder) holder).progressbar.setProgress(a);
        ((ViewHolder) holder).countYishou.setText("已售" + a);
        if (killStock == 0) {
            ((ViewHolder) holder).qianggou.setText("已售空");
        }
        if(killStock<=1){
            ((ViewHolder)holder).tvCountBaifenbi.setText("即将售罄");
        }
        float pressent = (float) killStock / killStockRaw * 100;
        ((ViewHolder)holder).tvCountBaifenbi.setText(pressent+"%");


        ((ViewHolder) holder).qianggou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = ((ViewHolder) holder).qianggou.getText().toString();
                if (s.equals("已售空")) {
                    Toast.makeText(content, "已售空,请选择别的商品", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Intent intent = new Intent(content, SeckillDetailsActivity.class);
                    intent.putParcelableArrayListExtra("list", (ArrayList<? extends Parcelable>) list);
                    content.startActivity(intent);
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
        @BindView(R.id.progressbar)
        ProgressBar progressbar;
        @BindView(R.id.count_yishou)
        TextView countYishou;
        @BindView(R.id.tv_count_baifenbi)
        TextView tvCountBaifenbi;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
