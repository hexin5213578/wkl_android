package com.example.wkl_android.Event;

import com.example.wkl_android.http.utils.ParamUtils;

public class AddCarEvent {
    String skuId;
    String count;

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public AddCarEvent(String id , String count){
        this.skuId = id;
        this.count = count;
    }

    public String getSkuId() {
        return skuId;
    }
}
