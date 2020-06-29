package com.example.wkl_android.orderpay.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alipay.sdk.app.PayTask;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.base.Constant;
import com.example.wkl_android.orderpay.IOrderPayView;
import com.example.wkl_android.orderpay.abapter.PayTypeAdapter;
import com.example.wkl_android.orderpay.abapter.PayTypeInfo;
import com.example.wkl_android.orderpay.bean.WXPreOrderInfo;
import com.example.wkl_android.orderpay.presenter.OrderPayPresenter;
import com.example.wkl_android.pay.model.PayResult;
import com.example.wkl_android.paysuccess.ui.activity.PaySuccessActivity;
import com.example.wkl_android.utils.PayUtil;
import com.example.wkl_android.utils.StringUtil;
import com.example.wkl_android.utils.ToastUtil;
import com.example.wkl_android.utils.ViewBgUtils;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Map;


import butterknife.BindView;
import butterknife.OnClick;

/**
 * 登录页面
 *
 * @author szx
 */
public class OrderPayActivity extends BaseActivity<IOrderPayView, OrderPayPresenter>
        implements IOrderPayView {


    @BindView(R.id.rv_pay)
    RecyclerView rv_pay;

    @BindView(R.id.tv_pay)
    TextView tv_pay;

    @BindView(R.id.tv_price)
    TextView tv_price;

    ArrayList<PayTypeInfo> list = new ArrayList<>();
    PayTypeAdapter mAdapter;

    private IWXAPI api;


    String id;
    String price;

    int img[] = {R.mipmap.ic_wechat, R.mipmap.ic_zfb, R.mipmap.ic_cun, R.mipmap.ic_ling};
    String name[] = {"微信支付", "支付宝支付", "预存支付", "零钱支付"};

    @Override
    public int getLayoutRes() {
        return R.layout.activity_order_pay;
    }

    @Override
    protected OrderPayPresenter createPresenter() {
        return new OrderPayPresenter();
    }

    @Override
    protected void initViews() {
        setTitleBarWithe(ContextCompat.getColor(this, R.color.transparent));

        id = getIntent().getStringExtra("id");
        price = getIntent().getStringExtra("price");

        tv_price.setText(StringUtil.changeSizeByDot(price, 0.7f));

        ViewBgUtils.setBg(tv_pay, "#ff4538", 100);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_pay.setLayoutManager(manager);

        mAdapter = new PayTypeAdapter(list);
        rv_pay.setAdapter(mAdapter);

        PayTypeInfo info;
        for (int i = 0; i < img.length; i++) {
            info = new PayTypeInfo();
            info.setImg(img[i]);
            info.setName(name[i]);
            if (i == 0) {
                info.setSelect(true);
            } else {
                info.setSelect(false);
            }
            list.add(info);
        }

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                for (PayTypeInfo item : list) {
                    item.setSelect(false);
                }

                list.get(position).setSelect(true);
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    @OnClick(R.id.tv_pay)
    public void onPayClick() {

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isSelect()) {
                if (i == 0) {
                    presenter.getWeChat(id);
                } else if (i == 1) {
                    presenter.getAlipay(id);
                }
            }
        }


//        startActivity(new Intent(this, PaySuccessActivity.class));
    }


    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        ToastUtil.show("成功");
                        PaySuccessActivity.startPaySuccess(OrderPayActivity.this, id , price);
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
//                        showAlert(PayDemoActivity.this, getString(R.string.pay_success) + payResult);
                    } else {
                        ToastUtil.show("支付失败");
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
//                        showAlert(PayDemoActivity.this, getString(R.string.pay_failed) + payResult);
                    }
                    break;
                }
                case SDK_AUTH_FLAG: {

                    break;
                }
                default:
                    break;
            }
        }

        ;
    };

    @OnClick(R.id.iv_close)
    public void onCloseClick(){
        finish();
    }

    @Override
    public void alipay(String info) {

        final String orderInfo = info;   // 订单信息

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(OrderPayActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void WXPayEvent(BaseResp event) {
        if (event.errCode == 0) {
            PaySuccessActivity
                    .startPaySuccess(this, id , price);
        } else {
            ToastUtil.show("支付失败" + event.errCode);
        }
    }


    @Override
    public void wxPay(WXPreOrderInfo info) {
        if (api == null) {
            api = WXAPIFactory.createWXAPI(this, info.getAppid(), false);
            api.registerApp(info.getAppid());
        }
        PayUtil.toWXPay(api, info);

    }
}
