package com.example.wkl_android.order.confirm.model;

import com.example.wkl_android.couponselect.bean.CouponInfo;

import java.io.Serializable;
import java.util.ArrayList;

public class CouponSerializable implements Serializable {
    ArrayList<CouponInfo> list ;

    public ArrayList<CouponInfo> getList() {
        return list;
    }

    public void setList(ArrayList<CouponInfo> list) {
        this.list = list;
    }
}
