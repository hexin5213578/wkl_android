package com.example.wkl_android.main.category.model;

import com.example.wkl_android.base.model.IModel;
import com.example.wkl_android.http.HttpUtils;
import com.example.wkl_android.http.callback.ICallBack;
import com.example.wkl_android.utils.C;

/**
 * 处理分类数据
 * Created by szx
 * on 2020/1/13/013
 */
public class CategoryModel implements IModel {
    @Override
    public void cancel() {
        HttpUtils.getInstance().cancel(this);
    }

    /**
     * 获取分类数据
     *
     * @param callBack 回调
     */
    public void getCategory(ICallBack callBack) {
        String url = C.BASE_URL + "/goods/goodsclassify/findList";
        HttpUtils.getInstance().doGet(url, this, callBack);
    }

    /**
     * 获取一级分类数据
     *
     * @param callBack 回调
     */
    public void getFirstCategory(ICallBack callBack) {
        String url = C.GET_CATEGORY+"/1/50/0";

        HttpUtils.getInstance().doGet(url, this, callBack);
    }

    /**
     * 获取下级分类数据
     *
     * @param callBack 回调
     */
    public void getSecondCategory(String parentID, ICallBack callBack) {
        String url = C.GET_CATEGORY+"/1/50/"+parentID;
        HttpUtils.getInstance().doGet(url, this, callBack);
    }
}
