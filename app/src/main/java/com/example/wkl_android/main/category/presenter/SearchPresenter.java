package com.example.wkl_android.main.category.presenter;

import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.http.callback.impl.JsonCallBack;
import com.example.wkl_android.main.category.model.CategoryModel;
import com.example.wkl_android.main.category.model.bean.Category;
import com.example.wkl_android.main.category.ui.ISearchView;
import com.example.wkl_android.utils.ConvertUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by szx
 * on 2020/1/2/002
 */
public class SearchPresenter extends BasePresenter<ISearchView> {
    private CategoryModel model;

    public SearchPresenter() {
        model = new CategoryModel();
    }

    @Override
    public void cancel() {
        model.cancel();
    }

    /**
     * 获取分类数据
     */
    public void getData() {
        if (unViewAttachedOrNetworkDisconnected()) {
            return;
        }
        model.getCategory(new JsonCallBack() {
            @Override
            public void onSuccess(String json) {
                if (!isViewAttached()) {
                    return;
                }
                ConvertUtil<Category> util = new ConvertUtil<>();
                int convert = util.convert(new Category(), json);
                if (1 == convert) {
                    Type type = new TypeToken<List<Category>>() {
                    }.getType();
                    List<Category> list = new Gson().fromJson(json, type);
                    getView().handleCategoryList(list);
                    return;
                }
                if (2 == convert) {
                    handleConvert2(json);
                    return;
                }
                if (3 == convert) {
                    handleCovert3(json);
                }
            }
        });
    }
}
