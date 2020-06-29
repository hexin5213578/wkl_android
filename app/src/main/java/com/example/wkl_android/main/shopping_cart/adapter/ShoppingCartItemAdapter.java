package com.example.wkl_android.main.shopping_cart.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.bumptech.glide.Glide;
import com.example.wkl_android.R;
import com.example.wkl_android.base.app.BaseApp;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.databinding.ItemShoppingGoodsBinding;
import com.example.wkl_android.good.ui.GoodsActivity;
import com.example.wkl_android.http.HttpUtils;
import com.example.wkl_android.http.callback.impl.JsonCallBack;
import com.example.wkl_android.main.shopping_cart.bean.EditShopCarNumber;
import com.example.wkl_android.main.shopping_cart.bean.ShopListBean;
import com.example.wkl_android.utils.C;
import com.example.wkl_android.utils.StringUtil;
import com.example.wkl_android.widget.rv.adapter.BaseAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by szx
 * on 2020/1/2/002
 */
public class ShoppingCartItemAdapter extends BaseAdapter<ShopListBean.DataBean.GoodsShopCarVOSetBean, ShoppingCartItemAdapter.ViewHolder> {


    List<ShopListBean.DataBean.GoodsShopCarVOSetBean> selected;
    String shopId;

    ShoppingCartItemAdapter(Context context, List<ShopListBean.DataBean.GoodsShopCarVOSetBean> data) {
        super(context, data);
        selected = new ArrayList<>();
    }

    public void setAll(boolean isClick) {
        if (isClick) {
            selected.clear();
            selected.addAll(data);
        } else {
            selected.clear();

        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_shopping_goods, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ShopListBean.DataBean.GoodsShopCarVOSetBean item = data.get(position);

        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                holder.binding.cbGoods.setChecked(!holder.binding.cbGoods.isChecked());

                Intent intent = new Intent(context, GoodsActivity.class);
                intent.putExtra("id", item.getSkuId());
                intent.putExtra("type", "1");
                intent.putExtra("price", item.getSkuPrice());
                intent.putExtra("title", item.getSkuTitle());
                intent.putExtra("loadingImg", item.getSkuImage());

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        holder.binding.cbGoods.setChecked(selected.contains(item));
        holder.binding.cbGoods.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (!selected.contains(item)) {
                        selected.add(item);
                    }
                } else {
                    if (selected.contains(item)) {
                        selected.remove(item);
                    }
                }
                if (listener != null) {
                    listener.selectChange(selected);
                }
            }
        });
        holder.binding.name.setText(item.getSkuTitle() + "");
        holder.binding.price.setText(StringUtil.changeSizeByDot( item.getSkuPrice() + "" , 0.7f));
        holder.binding.tvNumber.setText(item.getSkuCount() + "");
        Glide.with(holder.itemView.getContext()).load(Common.getResizeImg(item.getSkuImage(), 60, 60)).into(holder.binding.icon);
        /*
        加减逻辑
         */
        holder.binding.tvReduce.setOnClickListener(v -> {
            if (item.getSkuCount() == 1) {
                BaseApp.APP.toast("该商品不能被减少了");
                holder.binding.tvNumber.setText(String.valueOf(item.getSkuCount()));
                return;
            }
            item.setSkuCount(item.getSkuCount() - 1);
            holder.binding.tvNumber.setText(String.valueOf(item.getSkuCount()));
            changeNumber(item);
        });
        holder.binding.tvAdd.setOnClickListener(v -> {
            item.setSkuCount(item.getSkuCount() + 1);
            holder.binding.tvNumber.setText(String.valueOf(item.getSkuCount()));
            changeNumber(item);
        });
    }

    public void changeNumber(ShopListBean.DataBean.GoodsShopCarVOSetBean item) {
        EditShopCarNumber bean = new EditShopCarNumber();
        bean.setCarId(Long.valueOf(item.getCarId()));
        bean.setSkuCount(item.getSkuCount());
        bean.setSkuId(Long.valueOf(item.getSkuId()));
        HttpUtils.getInstance().doPutJson(C.EDIT_SHOP_CARD, new Gson().toJson(bean), Common.getToken(), "", new JsonCallBack() {
            @Override
            public void onSuccess(String json) throws Exception {
                Log.e("ZYY", json);
                listener.selectChange(selected);
            }
        });

    }

    public interface CheckBoxCheckedChangeListener {
        /**
         * 设置全选状态下点击商品取消店铺勾选状态
         * checked同理
         */
        void selectChange(List<ShopListBean.DataBean.GoodsShopCarVOSetBean> selected);
    }

    private CheckBoxCheckedChangeListener listener;

    void setListener(CheckBoxCheckedChangeListener listener) {
        this.listener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ItemShoppingGoodsBinding binding;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);

        }
    }

}
