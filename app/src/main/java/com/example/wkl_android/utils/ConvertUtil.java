package com.example.wkl_android.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by szx
 * on 2020/1/16/016
 */
public class ConvertUtil<T> {

    public int convert(T t, String jsonData) {
        JSONObject jsonObject = null;
        try {
            jsonObject = JSON.parseObject(jsonData);
        } catch (JSONException e) {
            return 1;
        }
        if (jsonObject == null) {
            return 1;
        }
        String code = jsonObject.getString("code");
        if ("0x10035".equals(code)) {
            return 1;
        }
        if (code == null) {
            Object status = jsonObject.get("status");
            if (status == null) {
                //正常返回
                return 1;
            } else {
                //网关错误
                return 2;
            }
        } else {
            //普通错误
            return 3;
        }
    }

}
