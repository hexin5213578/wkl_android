package com.example.wkl_android.couponselect.bean;

import java.io.Serializable;
import java.util.ArrayList;

public class CouponDataInfo implements Serializable {
    ArrayList<CouponInfo> list ;

    public ArrayList<CouponInfo> getList() {
        return list;
    }

    public void setList(ArrayList<CouponInfo> list) {
        this.list = list;
    }
}
