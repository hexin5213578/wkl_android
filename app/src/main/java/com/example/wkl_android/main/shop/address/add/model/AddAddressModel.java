package com.example.wkl_android.main.shop.address.add.model;

import android.text.TextUtils;

import com.example.wkl_android.base.model.IModel;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.http.HttpUtils;
import com.example.wkl_android.http.callback.ICallBack;
import com.example.wkl_android.main.shop.address.add.model.bean.UploadAddress;
import com.example.wkl_android.utils.C;
import com.google.gson.Gson;

/**
 * Created by szx
 * on 2020/1/6/006
 */
public class AddAddressModel implements IModel {
    @Override
    public void cancel() {
        HttpUtils.getInstance().cancel(this);
    }

    /**
     * 保存收货地址
     *
     * @param name         收货人
     * @param phone        手机号
     * @param detail       详细地址
     * @param site1        省
     * @param site2        市
     * @param site3        区
     * @param swichChecked 开关状态
     * @param callBack     回调
     */
    public void save(String id , String name, String phone, String detail, String site1, String site2,
                     String site3, boolean swichChecked, ICallBack callBack) {
        String url = C.USER + "/address/save";
        if(TextUtils.isEmpty(id)){
            url = C.USER +"/address/edit";
        }
        UploadAddress bean = new UploadAddress();
        bean.setAddressId(id);
        bean.setConsignee(name);
        bean.setSite1(site1);
        bean.setSite2(site2);
        bean.setSite3(site3);
        bean.setDefault(swichChecked);
        bean.setDetailedAddress(detail);
        bean.setPhoneNumber(phone);
        String json = new Gson().toJson(bean);
        HttpUtils.getInstance().doPostJson(url, json, Common.getToken(), this, callBack);
    }
}
