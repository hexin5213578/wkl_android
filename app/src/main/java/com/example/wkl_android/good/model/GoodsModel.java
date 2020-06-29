package com.example.wkl_android.good.model;

import com.bumptech.glide.Glide;
import com.example.wkl_android.base.model.IModel;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.good.ui.GoodsActivity;
import com.example.wkl_android.http.HttpUtils;
import com.example.wkl_android.http.callback.impl.JsonCallBack;
import com.example.wkl_android.utils.C;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by max
 * on 2020/4/25/025
 */
public class GoodsModel implements IModel {
    @Override
    public void cancel() {

    }

    public void getDetail(String type, String id, JsonCallBack callBack) {
        HttpUtils.getInstance().doGet(C.GET_GOODS_DETAIL + id + "/"+type, Common.getToken(), callBack);
    }

    public void addCar(String id, JsonCallBack callBack) {
        Map<String , String> map = new HashMap<>();
        map.put("data" , id);
         HttpUtils.getInstance().doPostJson(C.ADD_TO_SHOP_CARD , new Gson().toJson(map), Common.getToken() , "", callBack);
    }

    public void collect(String id, JsonCallBack callBack) {
        Map<String , String> map = new HashMap<>();
        map.put("data" , id);
        HttpUtils.getInstance().doPostJson(C.COLLECT_GOODS , new Gson().toJson(map), Common.getToken() , "", callBack);
    }
}
