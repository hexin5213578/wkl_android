package com.example.wkl_android.main.shop.address.remake.model;

import com.example.wkl_android.base.model.IModel;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.http.HttpUtils;
import com.example.wkl_android.http.callback.ICallBack;
import com.example.wkl_android.main.shop.address.remake.model.bean.UpdateAddress;
import com.example.wkl_android.main.shop.address.remake.model.bean.DataBean;
import com.example.wkl_android.utils.C;
import com.google.gson.Gson;

/**
 * Created by szx
 * on 2020/1/7/007
 */
public class RemakeModel implements IModel {
    @Override
    public void cancel() {
        HttpUtils.getInstance().cancel(this);
    }


    public void deleteAddress(String id, ICallBack callBack) {
        String url = C.USER + "/address/delete";
        DataBean bean = new DataBean();
        bean.setData(id);
        String json = new Gson().toJson(bean);
        HttpUtils.getInstance().doPutJson(url, json, Common.getToken(), this, callBack);
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
    public void save(String name, String phone, String detail, String site1, String site2,
                     String site3, boolean swichChecked, String id, ICallBack callBack) {
        String url = C.USER + "/address/edit";
        UpdateAddress bean = new UpdateAddress();
        bean.setSite1(site1);
        bean.setSite2(site2);
        bean.setSite3(site3);
        bean.setConsignee(name);
        bean.setDefault(swichChecked);
        bean.setDetailedAddress(detail);
        bean.setAddressId(id);
        bean.setPhoneNumber(phone);
        String json = new Gson().toJson(bean);
        HttpUtils.getInstance().doPutJson(url, json, Common.getToken(), this, callBack);
    }
}
