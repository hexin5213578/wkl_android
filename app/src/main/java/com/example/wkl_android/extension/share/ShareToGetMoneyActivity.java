package com.example.wkl_android.extension.share;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.charge.detail.ChargeDetailActivity;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.shop_discount.DiscountRulesActivity;
import com.example.wkl_android.utils.MyIUiListener;
import com.example.wkl_android.utils.WxShareUtils;
import com.tencent.connect.share.QQShare;
import com.tencent.tauth.Tencent;

import butterknife.BindView;

/**
 * 分享获取佣金
 */
public class ShareToGetMoneyActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.ivLeft)
    View back;
    @BindView(R.id.tvTitle)
    TextView title;
    @BindView(R.id.tvShareWechat)
    View tvShareWechat;
    @BindView(R.id.tvShareCircle)
    View tvShareCircle;
    @BindView(R.id.tvShareQQ)
    View tvShareQQ;
    @BindView(R.id.tvRules)
    View tvRules;
    private Bitmap bitmap;
    private Tencent tencent;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_share;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViews() {
        tencent = Tencent.createInstance(Common.APP_ID_QQ, getApplicationContext());
        title.setText("分享获取佣金");
        back.setOnClickListener(this);
        tvShareQQ.setOnClickListener(this);
        tvShareCircle.setOnClickListener(this);
        tvShareWechat.setOnClickListener(this);
        tvRules.setOnClickListener(this);
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_point06);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvShareQQ:
                shareToQQ();
                break;
            case R.id.tvShareWechat:
                WxShareUtils.shareWeb(this, 0, "www.baidu.com",
                        "分享标题", "这是分享内容", bitmap);
                break;
            case R.id.tvShareCircle:
                WxShareUtils.shareWeb(this, 1, "www.baidu.com",
                        "分享标题", "这是分享内容", bitmap);
                break;
            case R.id.tvRules:
                startActivity(new Intent(APP, DiscountRulesActivity.class));
                break;
        }
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
}
