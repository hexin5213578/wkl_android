package com.example.wkl_android.order.detail;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.commentorder.ui.activity.CommentOrderActivity;
import com.example.wkl_android.dialog.CommDialog;
import com.example.wkl_android.dialog.DialogUtils;
import com.example.wkl_android.order.after_sell.AfterSellActivity;
import com.example.wkl_android.order.detail.adapter.OrderGoodsAdapter;
import com.example.wkl_android.order.detail.model.OrderDetailInfo;
import com.example.wkl_android.order.detail.presenter.OrderDetailPresenter;
import com.example.wkl_android.order.detail.ui.IOrderDetailView;
import com.example.wkl_android.order.evaluate.EvaluateActivity;
import com.example.wkl_android.order.logistics.LogisticsActivity;
import com.example.wkl_android.order.main.ui.bean.OrderInfo;
import com.example.wkl_android.order.refund.ApplyRefundActivity;
import com.example.wkl_android.order.remake.RemakeOrderActivity;
import com.example.wkl_android.orderpay.ui.activity.OrderPayActivity;
import com.example.wkl_android.orderpaycheck.ui.activity.OrderCheckActivity;
import com.example.wkl_android.shop_street.shop_home.ui.activity.ShopHomeActivity;

import java.util.UUID;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 订单详情
 * Created by szx
 * on 2020/1/22/022
 */
public class OrderDetailActivity extends BaseActivity<IOrderDetailView, OrderDetailPresenter>
        implements IOrderDetailView, View.OnClickListener {
    @BindView(R.id.ivLeft)
    ImageView back;
    @BindView(R.id.vLine)
    View vLine;

    @BindView(R.id.tvTitle)
    TextView title;
    @BindView(R.id.llAddress)
    View llAddress;
    @BindView(R.id.tvCopy)
    View tvCopy;
    @BindView(R.id.tvShopHome)
    TextView tvShopHome;

    @BindView(R.id.llPayTime)
    View llPayTime;
    @BindView(R.id.tv_pay_time)
    TextView tv_pay_time;
    @BindView(R.id.tvStatus)
    TextView tvStatus;
    @BindView(R.id.ivStatus)
    ImageView ivStatus;
    @BindView(R.id.tvRemainTime)
    View tvRemainTime;

    @BindView(R.id.llWaitReceived)
    View llWaitReceived;


    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;

    @BindView(R.id.tv_username)
    TextView tv_username;

    @BindView(R.id.tv_phone)
    TextView tv_phone;

    @BindView(R.id.tv_order_id)
    TextView tv_order_id;

    @BindView(R.id.tv_create_time)
    TextView tv_create_time;

    @BindView(R.id.tv_goods_price)
    TextView tv_goods_price;

    @BindView(R.id.tv_feigth)
    TextView tv_feigth;

    @BindView(R.id.tv_pay_price)
    TextView tv_pay_price;

    @BindView(R.id.rv_goods)
    RecyclerView rv_goods;

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
    @BindView(R.id.tv_address)
    TextView tv_address;
    @BindView(R.id.ll_pay_type)
    LinearLayout ll_pay_type;
    @BindView(R.id.tv_pay_type)
    TextView tv_pay_type;
    @BindView(R.id.ll_type)
    LinearLayout ll_type;
    @BindView(R.id.tv_type)
    TextView tv_type;

    OrderGoodsAdapter goodsAdapter;

    String id;
    CommDialog dialog;

    OrderDetailInfo mDetailInfo;


    @BindColor(R.color.theme)
    int font_theme;
    @BindColor(R.color.font_black_9)
    int font_black_9;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_order_detail;
    }

    @Override
    protected OrderDetailPresenter createPresenter() {
        return new OrderDetailPresenter();
    }

    @Override
    protected void initViews() {

        setTitleBarWithe(ContextCompat.getColor(this, R.color.color_ff5438));
        rlTitle.setBackgroundColor(ContextCompat.getColor(this, R.color.color_ff5438));
        title.setTextColor(ContextCompat.getColor(this, R.color.white));
        back.setImageResource(R.mipmap.white_back);
        vLine.setVisibility(View.GONE);

        Intent intent = getIntent();
        if (intent != null) {
            id = intent.getStringExtra("id");
        }
        back.setOnClickListener(this);
        title.setText("订单详情");
        llAddress.setOnClickListener(this);
        tvCopy.setOnClickListener(this);
        tvShopHome.setOnClickListener(this);


        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_goods.setLayoutManager(manager);


        presenter.getOrderData(id);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvCopy:
                //获取剪贴板管理器：
                toast("复制成功");
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData mClipData = ClipData.newPlainText("Label", "这里是要复制的文字");
                if (cm != null) {
                    cm.setPrimaryClip(mClipData);
                }
                break;
            case R.id.tvShopHome:
                startActivity(new Intent(APP, ShopHomeActivity.class));
                break;
            case R.id.llGood:
                //跳转商品页面
                break;
            case R.id.tv_pay:

                 Intent intent = new Intent(this , OrderCheckActivity.class);
                intent.putExtra("orderid" , mDetailInfo.getOrderMasterPaymentId());
                startActivity(intent);

//                Intent intent = new Intent(this, OrderPayActivity.class);
//                intent.putExtra("id", mDetailInfo.getOrderMasterPaymentId());
//                intent.putExtra("price", mDetailInfo.getOrderMasterActualPayment());
//                startActivity(intent);
                break;

        }
    }


    @OnClick(R.id.tv_cancel)
    public void onCancelClick() {

        if(mDetailInfo == null)
            return;

        dialog = DialogUtils.showCommDialog(getSupportFragmentManager(), "提示", "确认取消订单？",
                "我在想想", v1 -> {
                    dialog.dismiss();
                }, "取消订单", v1 -> {

                    presenter.cancelOrder(mDetailInfo.getOrderMasterPaymentId());
                    dialog.dismiss();
                });
    }

    @OnClick(R.id.tv_comment)
    public void onCommentClick(){
        CommentOrderActivity.startActivity(this , mDetailInfo.getOrderMasterId());
    }

    @Override
    public void setData(OrderDetailInfo detailInfo) {

        mDetailInfo = detailInfo;

        tv_username.setText(detailInfo.getOrderMasterLinkmanName());
        tv_phone.setText(detailInfo.getOrderMasterLinkmanPhone());

        tv_order_id.setText(detailInfo.getOrderMasterId());
        tv_create_time.setText(detailInfo.getOrderMasterCreateTime());

        tv_goods_price.setText("￥" + detailInfo.getOrderMasterGoodsPrice());
        tv_feigth.setText("￥" + detailInfo.getOrderMasterFreight());
        tv_pay_price.setText(detailInfo.getOrderMasterActualPayment());

        tvShopHome.setText(detailInfo.getBusinessName());

        goodsAdapter = new OrderGoodsAdapter(detailInfo.getOrderSlaveVOList());
        rv_goods.setAdapter(goodsAdapter);
        tv_address.setText(detailInfo.getOrderMasterAddressDetail());

        tv_pay.setVisibility(View.GONE);
        tv_confirm.setVisibility(View.GONE);
        tv_look.setVisibility(View.GONE);
        tv_cancel.setVisibility(View.GONE);
        tv_comment.setVisibility(View.GONE);
        tv_pay.setOnClickListener(this);

        switch (detailInfo.getOrderMasterStatus()) {
            case 1://待发货
                tvStatus.setText("待发货");
                tv_cancel.setVisibility(View.VISIBLE);

                break;
            case 2://待收货
                tvStatus.setText("待收货");
                tv_look.setVisibility(View.VISIBLE);
                tv_confirm.setVisibility(View.VISIBLE);
                break;
            case 3://待评价
                tvStatus.setText("待评价");
                tv_comment.setVisibility(View.VISIBLE);
                break;
            case 4://已评价
                tvStatus.setText("已发货");
                tv_look.setVisibility(View.VISIBLE);
                break;
            case 5:  //待待退款
                tvStatus.setText("待退款");
                break;
            case 6:  //退款成功
                tvStatus.setText("退款成功");
                break;
            case 7:  //待商家确认
                tvStatus.setText("待商家确认");
                break;
            case 8:  //待用户付款
                tvStatus.setText("待付款");
                tv_pay.setVisibility(View.VISIBLE);
                tv_cancel.setVisibility(View.VISIBLE);
                break;
            case 9:  //已取消
                tvStatus.setText("已取消");
                break;
        }

        String payType = "";
        switch (detailInfo.getPayType()) {
            case 1:
                payType = "微信支付";
                break;
            case 2:
                payType = "支付宝支付";
                break;
            case 3:
                payType = "预存支付";
                break;
            case 4:
                payType = "余额支付";
                break;
        }

        if (TextUtils.isEmpty(payType)) {
            ll_pay_type.setVisibility(View.GONE);
            ll_type.setVisibility(View.GONE);
        } else {
            ll_pay_type.setVisibility(View.VISIBLE);
            tv_pay_type.setText(payType);

            ll_type.setVisibility(View.VISIBLE);
            if (detailInfo.isOrderMasterDistributionType()) {
                tv_type.setText("平台配送");
            } else {
                tv_type.setText("商家配送");
            }
        }


        if (TextUtils.isEmpty(detailInfo.getOrderMasterPaymentTime())) {
            llPayTime.setVisibility(View.GONE);
        } else {
            llPayTime.setVisibility(View.VISIBLE);
            tv_pay_time.setText(detailInfo.getOrderMasterPaymentTime());
        }


    }

    @Override
    public void cancelOrder() {
        presenter.getOrderData(mDetailInfo.getOrderMasterPaymentId());
    }
}
