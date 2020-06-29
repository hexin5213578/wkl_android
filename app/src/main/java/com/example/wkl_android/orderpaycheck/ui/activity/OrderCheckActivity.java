package com.example.wkl_android.orderpaycheck.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.base.Constant;
import com.example.wkl_android.good.model.GoodsListBean;
import com.example.wkl_android.main.home.ui.adapter.HomeGoodsItemAdapter;
import com.example.wkl_android.orderpay.ui.activity.OrderPayActivity;
import com.example.wkl_android.orderpaycheck.IOrderCheckView;
import com.example.wkl_android.orderpaycheck.adapter.CheckShopAdapter;
import com.example.wkl_android.orderpaycheck.bean.OrderCheckGroup;
import com.example.wkl_android.orderpaycheck.bean.OrderCheckVo;
import com.example.wkl_android.orderpaycheck.presenter.OrderCheckPresenter;
import com.example.wkl_android.searchinput.ui.activity.SearchInputActivity;
import com.example.wkl_android.utils.SPUtils;
import com.example.wkl_android.utils.ToastUtil;
import com.example.wkl_android.utils.ViewBgUtils;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 登录页面
 *
 * @author szx
 */
public class OrderCheckActivity extends BaseActivity<IOrderCheckView, OrderCheckPresenter>
        implements IOrderCheckView {

    @BindView(R.id.rv_order)
    RecyclerView rv_order;

    @BindView(R.id.tv_amount)
    TextView tv_amount;

    @BindView(R.id.tv_pay)
    TextView tv_pay;

    String orderID;

    CheckShopAdapter mAdapter;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_check_order;
    }

    @Override
    protected OrderCheckPresenter createPresenter() {
        return new OrderCheckPresenter();
    }

    @Override
    protected void initViews() {
        setTitleBarWithe();

        orderID = getIntent().getStringExtra("orderid");

        ViewBgUtils.setBg(tv_pay, "#ff4538", 30);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_order.setLayoutManager(manager);


        presenter.getData(orderID);
    }

    @OnClick(R.id.tv_pay)
    public void onPayClick() {

        Intent intent = new Intent(this, OrderPayActivity.class);
        intent.putExtra("id", orderID);
        intent.putExtra("price", "1000");
        startActivity(intent);

        finish();
    }

    @OnClick(R.id.iv_close)
    public void onCloseClick() {
        finish();
    }

    @Override
    public void setData(OrderCheckGroup data) {
        mAdapter = new CheckShopAdapter(data.getOrderListVOList());
        tv_amount.setText("￥" + data.getPayPrice());
        rv_order.setAdapter(mAdapter);
    }
}
