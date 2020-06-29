package com.example.wkl_android.good.model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class GoodsCommentBean {
    int total;
    int size ;
    ArrayList<CommentBean> list; //	Array;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public ArrayList<CommentBean> getList() {
        return list;
    }

    public void setList(ArrayList<CommentBean> list) {
        this.list = list;
    }
}
