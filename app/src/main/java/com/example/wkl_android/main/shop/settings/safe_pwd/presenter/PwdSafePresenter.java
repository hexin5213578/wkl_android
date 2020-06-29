package com.example.wkl_android.main.shop.settings.safe_pwd.presenter;

import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.http.bean.BaseBean;
import com.example.wkl_android.http.callback.impl.JsonCallBack;
import com.example.wkl_android.http.callback.impl.StringCallBack;
import com.example.wkl_android.main.shop.settings.safe_pwd.model.RefreshTokenModel;
import com.example.wkl_android.main.shop.settings.safe_pwd.model.bean.FindList;
import com.example.wkl_android.main.shop.settings.safe_pwd.ui.IPwdSafeView;
import com.example.wkl_android.utils.ConvertUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by szx
 * on 2020/1/4/004
 */
public class PwdSafePresenter extends BasePresenter<IPwdSafeView> {
    private RefreshTokenModel model;

    public PwdSafePresenter() {
        model = new RefreshTokenModel();
    }

    @Override
    public void cancel() {
        model.cancel();
    }

    private List<FindList> list = new ArrayList<>();

    public void getFindList() {
        if (unViewAttachedOrNetworkDisconnected()) {
            return;
        }
        model.findList(new JsonCallBack() {

            @Override
            public void onSuccess(String json) {
                if(!isViewAttached()){
                    return;
                }
                ConvertUtil<FindList> util = new ConvertUtil<>();
                int convert = util.convert(new FindList(), json);
                if (convert == 1) {
                    Type listType = new TypeToken<List<FindList>>() {
                    }.getType();
                    list = new Gson().fromJson(json, listType);
                    getView().handleFindList(list);
                    return;
                }
                if (convert == 2) {
                    if (handleConvert2(json)) {
                        model.refresh(new JsonCallBack() {
                            @Override
                            public void onSuccess(String json) {
                                Common.handleTokenOverFiled(json);
                                getFindList();
                            }
                        });
                    }
                    return;
                }
                if (convert == 3) {
                    handleCovert3(json);
                }
            }
        });
    }

    public void save(String answerOne, String answerTwo, String problemIdOne, String problemIdTwo) {
        if (!isViewAttached()) {
            return;
        }
        model.save(answerOne, answerTwo, problemIdOne, problemIdTwo, new StringCallBack<BaseBean>() {
            @Override
            public void onSuccess(BaseBean bean) {
                if (!isViewAttached()) {
                    return;
                }
                if (bean.isState()) {
                    getView().handleSaveSuccess(bean.getMessage());
                    return;
                }
                if (bean.getTokenFailed()) {
                    model.refresh(new JsonCallBack() {
                        @Override
                        public void onSuccess(String json) {
                            Common.handleTokenOverFiled(json);
                            save(answerOne, answerTwo, problemIdOne, problemIdTwo);
                        }
                    });
                    return;
                }
                getView().toast(bean.getMessage());
            }
        });
    }
}
