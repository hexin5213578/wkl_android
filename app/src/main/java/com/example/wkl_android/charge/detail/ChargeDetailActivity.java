package com.example.wkl_android.charge.detail;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.charge.consume.ConsumeRecordActivity;
import com.example.wkl_android.charge.detail.popup.PaySelectorPopup;
import com.example.wkl_android.charge.detail.popup.SharePopup;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.shop_street.shop_detail.ui.adapter.ShopImgAdapter;
import com.example.wkl_android.shop_street.shop_home.ui.activity.ShopHomeActivity;
import com.example.wkl_android.utils.MyIUiListener;
import com.example.wkl_android.utils.WxShareUtils;
import com.tencent.connect.share.QQShare;
import com.tencent.tauth.Tencent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 充值活动详情
 */
public class ChargeDetailActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.ivLeft)
    View back;
    @BindView(R.id.tvTitle)
    TextView title;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.rvChargeDetail)
    RecyclerView rvChargeDetail;
    @BindView(R.id.tvRight)
    TextView right;
    @BindView(R.id.tvBuy)
    View tvBuy;
    private PaySelectorPopup popup;
    private SharePopup sharePopup;
    private Tencent tencent;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_charge_detail;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViews() {
        tencent = Tencent.createInstance(Common.APP_ID_QQ, getApplicationContext());
        Intent intent = getIntent();
        if (intent != null) {
            int flag = intent.getIntExtra("flag", 0);
            if (flag == 1) {
                tvBuy.setVisibility(View.GONE);
                right.setVisibility(View.VISIBLE);
                right.setText("消费记录");
                right.setOnClickListener(this);
            } else {
                right.setVisibility(View.GONE);
                ivRight.setVisibility(View.VISIBLE);
                ivRight.setImageResource(R.mipmap.share);
                ivRight.setOnClickListener(this);
            }
        }
        title.setText("活动详情");
        back.setOnClickListener(this);
        tvBuy.setOnClickListener(this);

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("");
        }
        rvChargeDetail.setAdapter(new ShopImgAdapter(this, list, 1, 2));
        popup = new PaySelectorPopup(this);
        popup.setListener(() -> {
            toast("购买成功！");
            startActivity(new Intent(APP, ShopHomeActivity.class));
            finish();
        });
        sharePopup = new SharePopup(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvBuy:
                showSelectorPopup();
                break;
            case R.id.tvRight:
                startActivity(new Intent(APP, ConsumeRecordActivity.class));
                break;
            case R.id.ivRight:
                showSharePopup();
                break;
        }
    }

    /**
     * 分享弹框
     */
    private void showSharePopup() {
        sharePopup.show(getWindow().getDecorView());
        sharePopup.setListener(new SharePopup.OnShareListener() {
            @Override
            public void shareWechat() {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_point06);
                WxShareUtils.shareWeb(ChargeDetailActivity.this, 0, "www.baidu.com",
                        "分享标题", "这是分享内容", bitmap);
            }

            @Override
            public void shareCircle() {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_point06);
                WxShareUtils.shareWeb(ChargeDetailActivity.this, 1, "www.baidu.com",
                        "分享标题", "这是分享内容", bitmap);
            }

            @Override
            public void shareQQ() {
                shareToQQ();
            }
        });
    }

    private void shareToQQ() {
        Bundle params = new Bundle();
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
        params.putString(QQShare.SHARE_TO_QQ_TITLE, "标题");// 标题
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY, "要分享的摘要");// 摘要
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, "http://www.qq.com/news/1.html");// 内容地址
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, "http://imgcache.qq.com/qzone/space_item/pre/0/66768.gif");// 网络图片地址　　params.putString(QQShare.SHARE_TO_QQ_APP_NAME, "应用名称");// 应用名称
        params.putString(QQShare.SHARE_TO_QQ_EXT_INT, "其它附加功能");
// 分享操作要在主线程中完成
        tencent.shareToQQ(this, params, new MyIUiListener());

    }

    /**
     * 展示支付方式选择框
     */
    private void showSelectorPopup() {
        popup.show(getWindow().getDecorView());
    }
}
