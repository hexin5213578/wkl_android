package com.example.wkl_android.Event;

public class CouponEvent {
    String id;
    int type;

    public CouponEvent(String id, int type){
        this.id = id;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public int getType() {
        return type;
    }
}
