package com.example.wkl_android.main.shop.settings.information.main.presenter;

import android.text.TextUtils;

import com.baidu.mapapi.map.MapView;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.http.bean.BaseBean;
import com.example.wkl_android.http.callback.impl.JsonCallBack;
import com.example.wkl_android.http.callback.impl.StringCallBack;
import com.example.wkl_android.main.shop.settings.information.certification.model.UploadImgModel;
import com.example.wkl_android.main.shop.settings.information.certification.model.bean.ImageBean;
import com.example.wkl_android.main.shop.settings.information.main.model.InfoModel;
import com.example.wkl_android.main.shop.settings.information.main.model.bean.UserInfo;
import com.example.wkl_android.main.shop.settings.information.main.ui.IInfoView;
import com.example.wkl_android.main.shop.settings.safe_pwd.model.RefreshTokenModel;
import com.example.wkl_android.utils.ConvertUtil;
import com.example.wkl_android.utils.SPUtils;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by szx
 * on 2020/1/7/007
 */
public class UserInfoPresenter extends BasePresenter<IInfoView> {
    private InfoModel model;
    private RefreshTokenModel refreshTokenModel;
    private UploadImgModel uploadImgModel;

    public UserInfoPresenter() {
        model = new InfoModel();
        refreshTokenModel = new RefreshTokenModel();
        uploadImgModel = new UploadImgModel();
    }

    @Override
    public void cancel() {
        model.cancel();
        refreshTokenModel.cancel();
        uploadImgModel.cancel();
    }

    /**
     * 获取用户信息
     */
    public void getUserInfo() {
        if (unViewAttachedOrNetworkDisconnected()) {
            return;
        }
        if (isTokenNull()) {
            getView().toLogin();
            return;
        }
        model.getUserInfo(new JsonCallBack() {
            @Override
            public void onSuccess(String json) {
                if (!isViewAttached()) {
                    return;
                }
                ConvertUtil<UserInfo> util = new ConvertUtil<>();
                int convert = util.convert(new UserInfo(), json);
                if (convert == 1) {
                    SPUtils.getInstance().putString(SPUtils.USER_INFO, json);
                    UserInfo bean = new Gson().fromJson(json, UserInfo.class);
                    getView().handleUserInfo(bean);

                    return;
                }
                if (convert == 2) {
                    if (handleConvert2(json)) {
                        refreshTokenModel.refresh(new JsonCallBack() {
                            @Override
                            public void onSuccess(String json) {
                                Common.handleTokenOverFiled(json);
                                getUserInfo();
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

    /**
     * 设置生日
     *
     * @param date 日期
     */
    public void setBirthday(String date) {
        if (unViewAttachedOrNetworkDisconnected()) {
            return;
        }
        if (TextUtils.isEmpty(Common.getToken())) {
            getView().toLogin();
            return;
        }
        model.setBirthday(date, new StringCallBack<BaseBean>() {
            @Override
            public void onStart() {
                super.onStart();
                if (!isViewAttached()) {
                    return;
                }
                getView().showLoading();
            }

            @Override
            public void onSuccess(BaseBean bean) {
                if (!isViewAttached()) {
                    return;
                }
                if (bean.isState()) {
                    getView().handleSuccess(bean.getMessage());
                    getUserInfo();
                    return;
                }
                if (bean.getTokenFailed()) {
                    refreshTokenModel.refresh(new JsonCallBack() {
                        @Override
                        public void onSuccess(String json) {
                            try {
                                Common.handleTokenOverFiled(json);
                                setBirthday(date);
                            } catch (Exception e) {
                                getView().toast(bean.getMessage());
                            }

                        }
                    });
                }
            }

            @Override
            public void onFinished() {
                super.onFinished();
                if (!isViewAttached()) {
                    return;
                }
                getView().dismissLoading();
            }
        });
    }


    public void upImg(String path) {

        if (uploadImgModel == null) {
            uploadImgModel = new UploadImgModel();
        }

        ArrayList<File> files = new ArrayList<>();
        files.add(new File(path));
        getView().showLoading();

        uploadImgModel.uploadImg(files, "file", "", new JsonCallBack() {
            @Override
            public void onSuccess(String json) {
                if (!isViewAttached()) {
                    return;
                }

                try {
                    JSONObject object = new JSONObject(json);
                    if (object.optBoolean("status")) {
                        String img = object.optString("data");
                        setHead(img);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }



    /**
     * 设置头像
     *
     * @param netWorkUrl 图片链接
     */
    private void setHead(String netWorkUrl) {
        if (unViewAttachedOrNetworkDisconnected()) {
            return;
        }
        if (isTokenNull()) {
            getView().toLogin();
            return;
        }
        model.setHead(netWorkUrl, new StringCallBack<BaseBean>() {
            @Override
            public void onStart() {
                super.onStart();
                if (!isViewAttached()) {
                    return;
                }
                getView().showLoading();
            }

            @Override
            public void onSuccess(BaseBean bean) {
                if (!isViewAttached()) {
                    return;
                }
                if (bean.isState()) {
                    getView().handleSuccess(bean.getMessage());
                    getUserInfo();
                    return;
                }
                if (bean.getTokenFailed()) {
                    refreshTokenModel.refresh(new JsonCallBack() {
                        @Override
                        public void onSuccess(String json) {
                            try {
                                Common.handleTokenOverFiled(json);
                                setHead(netWorkUrl);
                            } catch (Exception e) {
                                getView().toast(bean.getMessage());
                            }

                        }
                    });
                }
            }

            @Override
            public void onFinished() {
                super.onFinished();
                if (!isViewAttached()) {
                    return;
                }
                getUserInfo();
                getView().dismissLoading();
            }
        });
    }
}
