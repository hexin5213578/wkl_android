package com.example.wkl_android.order.detail.adapter;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wkl_android.R;
import com.example.wkl_android.good.model.GoodsModel;
import com.example.wkl_android.http.callback.impl.JsonCallBack;
import com.example.wkl_android.order.detail.model.OrderGoodsInfo;
import com.example.wkl_android.utils.BitmapUtil;
import com.example.wkl_android.utils.ToastUtil;
import com.example.wkl_android.wholesale_market.adapter.MrakAdapter;
import com.example.wkl_android.wholesale_market.bean.WholesaleMarketInfo;

import java.util.List;

public class OrderGoodsAdapter extends BaseQuickAdapter<OrderGoodsInfo, BaseViewHolder> {
    public OrderGoodsAdapter(@Nullable List<OrderGoodsInfo> data) {
        super(R.layout.order_goods_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderGoodsInfo item) {
        ImageView iv_img = helper.getView(R.id.iv_img);
        BitmapUtil.showRadiusImage(mContext, item.getOrderSlaveCommodityImage(), R.mipmap.ic_launcher, 5, iv_img);

        helper.setText(R.id.tv_name, item.getOrderSlaveCommodityTitle())
                .setText(R.id.tv_type, item.getOrderSlaveCommodityStandardValue())
                .setText(R.id.tv_num, "x" + item.getOrderSlaveCommodityCount())
                .setText(R.id.tv_price, "￥" + item.getOrderSlaveCommodityPrices());

        helper.getView(R.id.tv_add_car).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCar(item.getSkuId());
            }
        });
    }


    /**
     * 加购物车
     */
    public void addCar(String id) {
        GoodsModel goodsModel = new GoodsModel();

        goodsModel.addCar(id, new JsonCallBack() {
            @Override
            public void onSuccess(String json) throws Exception {

                ToastUtil.show("添加购物车成功");
            }

            @Override
            public void onFinished() {
                super.onFinished();

            }
        });

    }

}
