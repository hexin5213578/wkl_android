package com.example.wkl_android.main.shop.address.select.ui;

import com.example.wkl_android.base.view.IBaseView;
import com.example.wkl_android.main.shop.address.select.model.bean.SelectSites;

import java.util.List;

/**
 * Created by szx
 * on 2020/1/6/006
 */
public interface ISelectView extends IBaseView {
    void handleSites(List<SelectSites> list);
}
