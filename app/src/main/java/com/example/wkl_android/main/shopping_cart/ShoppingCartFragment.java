package com.example.wkl_android.main.shopping_cart;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseFragment;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.http.HttpUtils;
import com.example.wkl_android.http.callback.impl.JsonCallBack;
import com.example.wkl_android.main.shopping_cart.adapter.ShoppingCartAdapter;
import com.example.wkl_android.main.shopping_cart.bean.CheckBean;
import com.example.wkl_android.main.shopping_cart.bean.ShopListBean;
import com.example.wkl_android.main.shopping_cart.presenter.ShoppingCartPresenter;
import com.example.wkl_android.order.confirm.ConfirmOrderActivity;
import com.example.wkl_android.utils.C;
import com.example.wkl_android.utils.StringUtil;
import com.example.wkl_android.utils.ToastUtil;
import com.google.gson.Gson;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindColor;
import butterknife.BindView;

/**
 * 购物车
 */
public class ShoppingCartFragment extends BaseFragment<IShoppingCartView, ShoppingCartPresenter>
        implements IShoppingCartView, View.OnClickListener {
    @BindView(R.id.ivLeft)
    View back;
    @BindView(R.id.tvTitle)
    TextView title;
    @BindView(R.id.tvRight)
    TextView right;
    @BindView(R.id.rvShoppingCart)
    RecyclerView rvShoppingCart;
    @BindView(R.id.llSettlement)
    View llSettlement;
    @BindView(R.id.tvDelete)
    View tvDelete;
    @BindView(R.id.tvMakeOrder)
    View tvMakeOrder;
    @BindColor(R.color.font_black_3)
    int font_black_3;
    @BindView(R.id.checkbox)
    CheckBox checkbox;
    @BindView(R.id.price)
    TextView tvPrice;

    private boolean clickFlag;
    private FragmentActivity activity;

    public static ShoppingCartFragment newInstance() {

        Bundle args = new Bundle();

        ShoppingCartFragment fragment = new ShoppingCartFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shoping_cart;
    }

    @Override
    protected ShoppingCartPresenter createPresenter() {
        return new ShoppingCartPresenter();
    }

    ShoppingCartAdapter shoppingCartAdapter;

    @Override
    protected void initViews() {

        setTitleBarWithe();

        back.setVisibility(View.GONE);
        title.setText("购物车");
        right.setText("管理");
        right.setTextColor(font_black_3);
        right.setVisibility(View.VISIBLE);
        tvMakeOrder.setOnClickListener(this);
        tvDelete.setOnClickListener(this);
        List<CheckBean> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new CheckBean());
        }
        activity = weakReference.get();
        shoppingCartAdapter = new ShoppingCartAdapter(activity, null) {
            @Override
            public void change(Map<String, List<ShopListBean.DataBean.GoodsShopCarVOSetBean>> select) {
                double price = 0;
                double allPrice = 0;
                for (String s : select.keySet()) {
                    List<ShopListBean.DataBean.GoodsShopCarVOSetBean> item = select.get(s);
                    for (ShopListBean.DataBean.GoodsShopCarVOSetBean goodsBean : item) {
                        price += goodsBean.getSkuPrice() * goodsBean.getSkuCount();
                    }
                }
                List<ShopListBean.DataBean> data = shopListBean.getData();
                for (ShopListBean.DataBean datum : data) {
                    for (ShopListBean.DataBean.GoodsShopCarVOSetBean goodsBean : datum.getGoodsShopCarVOSet()) {
                        allPrice += goodsBean.getSkuPrice() * goodsBean.getSkuCount();
                    }
                }


                DecimalFormat df = new DecimalFormat("#.00");
                String str = df.format(price);
                if (price < 1) {
                    str = "0" + str;
                }


                tvPrice.setText(StringUtil.changeSizeByDot("" + str, 0.7f));
                if (allPrice == price) {
                    checkbox.setChecked(true);
                } else {
                    checkbox.setChecked(false);
                }
            }
        };
        rvShoppingCart.setAdapter(shoppingCartAdapter);
        right.setOnClickListener(this);
        checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = checkbox.isChecked();
                shoppingCartAdapter.setSelect(checked);
                shoppingCartAdapter.change(shoppingCartAdapter.getSelect());
            }
        });

    }

    ShopListBean shopListBean;

    @Override
    public void onResume() {
        super.onResume();
        checkbox.setChecked(false);
        initCarData();
    }

    public void initCarData() {
        HttpUtils.getInstance().doGet(C.GET_SHOP_LIST, Common.getToken(), "", new JsonCallBack() {
            @Override
            public void onSuccess(String json) throws Exception {
                Log.e("ZYY", "json:" + json);
                shopListBean = new Gson().fromJson(json, ShopListBean.class);
                shoppingCartAdapter.setData(shopListBean.getData());
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvRight:
                if (clickFlag) {
                    tvDelete.setVisibility(View.VISIBLE);
                    llSettlement.setVisibility(View.GONE);
                    right.setText("完成");
                    clickFlag = false;
                } else {
                    tvDelete.setVisibility(View.GONE);
                    llSettlement.setVisibility(View.VISIBLE);
                    right.setText("管理");
                    clickFlag = true;
                }
                break;
            case R.id.tvMakeOrder:
                Map<String, List<ShopListBean.DataBean.GoodsShopCarVOSetBean>> adapterSelect = shoppingCartAdapter.getSelect();
                ArrayList<ShopListBean.DataBean> dataBeans = new ArrayList<>();
                for (String s : adapterSelect.keySet()) {
                    for (ShopListBean.DataBean datum : shopListBean.getData()) {
                        if (datum.getBusinessId().equals(s)) {
                            ShopListBean.DataBean dataBean = new ShopListBean.DataBean();
                            dataBean.setBusinessId(datum.getBusinessId());
                            dataBean.setBusinessName(datum.getBusinessName());
                            dataBean.setGoodsShopCarVOSet(adapterSelect.get(s));
                            dataBeans.add(dataBean);
                        }
                    }
                }
                if (dataBeans == null || dataBeans.size() == 0) {
                    ToastUtil.show("请选择要购买的商品");
                    return;
                }

                ConfirmOrderActivity.toThisActivity(getContext(), dataBeans);
                break;
            case R.id.tvDelete:
                List<String> deleteId = new ArrayList<>();
                Map<String, List<ShopListBean.DataBean.GoodsShopCarVOSetBean>> select = shoppingCartAdapter.getSelect();
                for (String s : select.keySet()) {
                    for (ShopListBean.DataBean.GoodsShopCarVOSetBean item : select.get(s)) {
                        deleteId.add(item.getCarId());
                    }
                }
                HashMap<String, List<String>> stringListHashMap = new HashMap<>();
                stringListHashMap.put("data", deleteId);
                HttpUtils.getInstance().doPutJson(C.DELETE_SHOP_CARD, new Gson().toJson(stringListHashMap), Common.getToken(), "", new JsonCallBack() {
                    @Override
                    public void onSuccess(String json) throws Exception {
                        initCarData();
                    }
                });
                break;
        }
    }
}
