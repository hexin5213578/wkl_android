package com.example.wkl_android.select_city.ui;

import com.example.wkl_android.base.view.IBaseView;
import com.example.wkl_android.main.shop.address.select.model.bean.SelectSites;

import java.util.List;

public interface ISelectCityView extends IBaseView {
    void handleSites(List<SelectSites> list);
    void setSide(List<SelectSites> list);
}
