package com.example.wkl_android.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.wkl_android.R;
import com.example.wkl_android.base.Constant;
import com.orhanobut.logger.Logger;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;


public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, Constant.WX.APPID);//appid需换成商户自己开放平台appid
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {
        Logger.d("微信", "微信回调 resp type = " + resp.getType());
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            // resp.errCode == -1 原因：支付错误,可能的原因：签名错误、未注册APPID、项目设置APPID不正确、注册的APPID与设置的不匹配、其他异常等
            // resp.errCode == -2 原因 用户取消,无需处理。发生场景：用户不支付了，点击取消，返回APP
            // resp.errCode == 0  支付成功
            Logger.d("微信", "微信支付结果 code = " + resp.errCode);
            boolean isSuccess = resp.errCode == 0;

            EventBus.getDefault().post(resp);
            // 订单列表、订单详情 立即支付操作
//            if (isSuccess) {
//                EventBus.getDefault().post(new OrderEvent(OrderEvent.REFRESH_ORDER_WAIT_PAY));
//                EventBus.getDefault().post(new OrderEvent(OrderEvent.REFRESH_ORDER_DETAIL));
//            } else {
//            }
            finish();
        }
    }
}