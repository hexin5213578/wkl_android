package com.example.wkl_android.main.shop.address.main.ui;

import com.example.wkl_android.base.view.IBaseView;
import com.example.wkl_android.main.shop.address.main.model.bean.Address;

import java.util.List;

/**
 * Created by szx
 * on 2020/1/6/006
 */
public interface IAddressView extends IBaseView {
    void handleAddressList(List<Address> list);
}
