package com.example.wkl_android.commentorder;

import com.example.wkl_android.base.view.IBaseView;
import com.example.wkl_android.good.model.GoodsListBean;
import com.example.wkl_android.order.detail.model.OrderDetailInfo;

/**
 * Created by szx
 * on 2019/12/30/030
 */
public interface ICommentOrderView extends IBaseView {
    public void upImg(String paht);
    public void setData(OrderDetailInfo data);
    public void commentSuccess();
}
