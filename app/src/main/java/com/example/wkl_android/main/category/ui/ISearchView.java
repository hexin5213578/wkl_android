package com.example.wkl_android.main.category.ui;

import com.example.wkl_android.base.view.IBaseView;
import com.example.wkl_android.main.category.model.bean.Category;

import java.util.List;

/**
 * Created by szx
 * on 2020/1/2/002
 */
public interface ISearchView extends IBaseView {
    void handleCategoryList(List<Category> list);
}
