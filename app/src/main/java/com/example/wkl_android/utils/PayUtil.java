package com.example.wkl_android.utils;

import com.example.wkl_android.orderpay.bean.WXPreOrderInfo;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;

public class PayUtil {

    public static boolean toWXPay(IWXAPI api, WXPreOrderInfo info) {
        PayReq payReq = new PayReq();
        payReq.appId = info.getAppid();
        payReq.partnerId = info.getPartnerid();
        payReq.packageValue = info.getPackage_value();
        payReq.prepayId = info.getPrepayid();
        payReq.timeStamp = info.getTimestamp();
        payReq.nonceStr = info.getNoncestr();
        payReq.sign = info.getSign();

//        Logger.d("微信", "prepayId = " + payReq.prepayId + "  timeStamp = " + payReq.timeStamp + "  nonceStr = " + payReq.nonceStr + "  sign = " + payReq.sign + "  packageValue = " + payReq.packageValue + "  appId = " + payReq.appId + "  partnerId = " + payReq.partnerId);

        return api.sendReq(payReq);
    }

//
//    public static boolean toWXPay(IWXAPI api, OrderWXOrderInfo info) {
//        PayReq payReq = new PayReq();
//        try {
//            payReq.appId = info.getAppid();
//            payReq.partnerId = info.getPartnerid();
//            payReq.packageValue = info.getPackageValue();
//            payReq.prepayId = info.getPrepayid();
//            payReq.timeStamp = info.getTimestamp();
//            payReq.nonceStr = info.getNoncestr();
//            payReq.sign = info.getPaysign();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
////        Logger.d("微信", "prepayId = " + payReq.prepayId + "  timeStamp = " + payReq.timeStamp + "  nonceStr = " + payReq.nonceStr + "  sign = " + payReq.sign + "  packageValue = " + payReq.packageValue + "  appId = " + payReq.appId + "  partnerId = " + payReq.partnerId);
//        return api.sendReq(payReq);
//    }
}
