package com.example.wkl_android.main.shop.add_shop.ui;

import com.example.wkl_android.base.view.IBaseView;
import com.example.wkl_android.main.category.model.bean.Category;

import java.util.List;

/**
 * Created by szx
 * on 2020/1/9/009
 */
public interface IAddView extends IBaseView {
    void setImgUrl(String netWorkUrl);

    void handleSuccess(String message);

    void handleCategoryList(List<Category> list);
}
