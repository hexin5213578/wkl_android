package com.example.wkl_android.paysuccess.ui.activity;

import android.content.Context;
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
import com.example.wkl_android.main.MainActivity;
import com.example.wkl_android.main.home.ui.adapter.HomeGoodsItemAdapter;
import com.example.wkl_android.order.detail.OrderDetailActivity;
import com.example.wkl_android.paysuccess.IPaySuccessView;
import com.example.wkl_android.paysuccess.presenter.PaySuccessPresenter;
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
public class PaySuccessActivity extends BaseActivity<IPaySuccessView, PaySuccessPresenter>
        implements IPaySuccessView {

    @BindView(R.id.tv_check_order)
    TextView check_order;

    @BindView(R.id.tv_main)
    TextView tv_main;

    @BindView(R.id.tv_price)
    TextView tv_price;

    String id, price;


    public static void startPaySuccess(Context mContext, String id, String price) {
        Intent intent = new Intent(mContext, PaySuccessActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("price", price);
        mContext.startActivity(intent);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_pay_success;
    }

    @Override
    protected PaySuccessPresenter createPresenter() {
        return new PaySuccessPresenter();
    }

    @Override
    protected void initViews() {
        setTitleBarWithe();

        id = getIntent().getStringExtra("id");
        price = getIntent().getStringExtra("price");


        tv_price.setText(price);
        ViewBgUtils.setBg(check_order, "#ff4538", 5);
        ViewBgUtils.setBg(tv_main, "#ffffff", "#999999", 5);

    }

    @Override
    public void showData(GoodsListBean bean) {

    }

    @OnClick(R.id.tv_check_order)
    public void onCheckClick() {
        Intent intent = new Intent(this, OrderDetailActivity.class);
        intent.putExtra("id", id);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
     }

    @OnClick(R.id.tv_main)
    public void onMainClick() {
        startActivity(new Intent(this, MainActivity.class));
    }
}
