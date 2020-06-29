package com.example.wkl_android.main.shopping_cart.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wkl_android.R;
import com.example.wkl_android.main.shopping_cart.bean.CheckBean;
import com.example.wkl_android.main.shopping_cart.bean.ShopListBean;
import com.example.wkl_android.utils.ViewBgUtils;
import com.example.wkl_android.widget.rv.adapter.BaseAdapter;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by szx
 * on 2020/1/2/002
 */
public abstract class ShoppingCartAdapter extends BaseAdapter<ShopListBean.DataBean, ShoppingCartAdapter.ViewHolder> {

    /**
     * 一个店铺内商品全选状态下，如果是取消点击商品，做个标记，设置取消店铺点击状态，而不改变其他商品点击状态；
     * 否则其他商品跟着店铺的点击全选或者全部取消
     */

    private Map<String, List<ShopListBean.DataBean.GoodsShopCarVOSetBean>> select;

    public ShoppingCartAdapter(Context context, List<ShopListBean.DataBean> data) {
        super(context, data);
        select = new HashMap<>();
    }

    public void setData(List<ShopListBean.DataBean> data) {
        this.data = data;
        select.clear();
        notifyDataSetChanged();

    }

    public Map<String, List<ShopListBean.DataBean.GoodsShopCarVOSetBean>> getSelect() {
        return select;
    }

    public void setSelect(boolean selectAll) {
        if (selectAll) {
            for (ShopListBean.DataBean datum : data) {
                select.put(datum.getBusinessId(), datum.getGoodsShopCarVOSet());
            }
        } else {
            select.clear();
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_shopping_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ShopListBean.DataBean dataBean = data.get(position);
        ShoppingCartItemAdapter itemAdapter = new ShoppingCartItemAdapter(context, dataBean.getGoodsShopCarVOSet());
        holder.rvShoppingCartItem.setAdapter(itemAdapter);
        holder.name.setText(dataBean.getBusinessName());
        ViewBgUtils.setBg(holder.tv_coupon, "#ffefef", 10);
        ViewBgUtils.setBg(holder.tv_sale_type , "f72607" , 3);
        holder.llName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isClick = !holder.cbShop.isChecked();
                Log.e("ZYY", isClick + "---");
              /*  if (isClick){
                    ArrayList<ShopListBean.DataBean.GoodsShopCarVOSetBean> goodsShopCarVOSetBeans = new ArrayList<>();
                    goodsShopCarVOSetBeans.addAll(dataBean.getGoodsShopCarVOSet());
                    select.put(dataBean.getBusinessId(), goodsShopCarVOSetBeans);
                }*/
                itemAdapter.setAll(isClick);
            }
        });
        if (select.containsKey(dataBean.getBusinessId())) {
            if (select.get(dataBean.getBusinessId()).size() == dataBean.getGoodsShopCarVOSet().size()) {
                itemAdapter.setAll(true);
                holder.cbShop.setChecked(true);
            }
        } else {
            itemAdapter.setAll(false);
            holder.cbShop.setChecked(false);
        }
        holder.cbShop.setOnCheckedChangeListener((buttonView, isChecked) -> {
            Log.e("ZYY", "变啦");
        });

        itemAdapter.setListener(new ShoppingCartItemAdapter.CheckBoxCheckedChangeListener() {
            @Override
            public void selectChange(List<ShopListBean.DataBean.GoodsShopCarVOSetBean> selected) {
                if (selected.size() == dataBean.getGoodsShopCarVOSet().size()) {
                    holder.cbShop.setChecked(true);
                } else {
                    holder.cbShop.setChecked(false);
                }
                select.put(dataBean.getBusinessId(), selected);
                change(select);
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.rvShoppingCartItem)
        RecyclerView rvShoppingCartItem;
        @BindView(R.id.cbShop)
        CheckBox cbShop;
        @BindView(R.id.llName)
        LinearLayout llName;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.tv_coupon)
        TextView tv_coupon;
        @BindView(R.id.rl_sale)
        RelativeLayout rl_sale;
        @BindView(R.id.tv_sale_type)
        TextView tv_sale_type;
        @BindView(R.id.tv_sale_msg)
        TextView tv_sale_msg;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public abstract void change(Map<String, List<ShopListBean.DataBean.GoodsShopCarVOSetBean>> select);
}
