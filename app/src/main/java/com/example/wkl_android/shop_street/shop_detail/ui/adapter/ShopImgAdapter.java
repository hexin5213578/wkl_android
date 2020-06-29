package com.example.wkl_android.shop_street.shop_detail.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wkl_android.R;
import com.example.wkl_android.charge.detail.ChargeDetailActivity;
import com.example.wkl_android.utils.BitmapUtil;
import com.example.wkl_android.widget.rv.adapter.BaseAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by szx
 * on 2020/2/1/001
 */
public class ShopImgAdapter extends BaseAdapter<String, ShopImgAdapter.ViewHolder> {
    private int flag;
    private int flag1;

    public ShopImgAdapter(Context context, List<String> data, int flag, int flag1) {
        super(context, data);
        this.flag = flag;
        this.flag1 = flag1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_shop_img, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        switch (flag) {
            case 1:
                holder.iv.setImageResource(R.mipmap.test_activity);
                if (flag1 == 2) {
                    return;
                }
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, ChargeDetailActivity.class);
                        intent.putExtra("flag", flag1);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                });
                break;
            case 2:
                BitmapUtil.showImage(context , data.get(position) , holder.iv);
//                holder.iv.setImageResource(R.mipmap.test_shop);
                break;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv)
        ImageView iv;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
