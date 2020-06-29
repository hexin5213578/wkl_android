package com.example.wkl_android.main.home.presenter;

import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.main.category.model.CategoryModel;
import com.example.wkl_android.main.home.ui.IHomeView;

public class HomePresenter extends BasePresenter<IHomeView>{
    private CategoryModel model;
    public HomePresenter() {
        model = new CategoryModel();
    }

    @Override
    public void cancel() {

    }


}
