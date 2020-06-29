package com.example.wkl_android.main.shop.settings.information.certification.presenter;

import android.text.TextUtils;

import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.http.bean.BaseBean;
import com.example.wkl_android.http.callback.impl.JsonCallBack;
import com.example.wkl_android.http.callback.impl.StringCallBack;
import com.example.wkl_android.main.shop.settings.information.certification.model.UploadImgModel;
import com.example.wkl_android.main.shop.settings.information.certification.model.bean.ImageBean;
import com.example.wkl_android.main.shop.settings.information.certification.ui.ICertificationView;
import com.example.wkl_android.main.shop.settings.safe_pwd.model.RefreshTokenModel;
import com.example.wkl_android.utils.ConvertUtil;
import com.example.wkl_android.utils.LogUtils;
import com.google.gson.Gson;

import java.io.File;
import java.util.List;

/**
 * Created by szx
 * on 2020/1/3/003
 */
public class CertificationPresenter extends BasePresenter<ICertificationView> {
    private UploadImgModel model;
    private RefreshTokenModel refreshTokenModel;

    public CertificationPresenter() {
        model = new UploadImgModel();
        refreshTokenModel = new RefreshTokenModel();
    }

    @Override
    public void cancel() {
        model.cancel();
        refreshTokenModel.cancel();
    }

    /**
     * 保存实名信息
     *
     * @param name     姓名
     * @param code     身份证号
     * @param frontUrl 正面
     * @param backUrl  背面
     */
    public void save(String name, String code, String frontUrl, String backUrl) {
        if (unViewAttachedOrNetworkDisconnected()) {
            return;
        }
        if (isTokenNull()) {
            getView().toLogin();
            return;
        }
        model.save(name, code, frontUrl, backUrl, new StringCallBack<BaseBean>() {
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
                    refreshTokenModel.refresh(new JsonCallBack() {
                        @Override
                        public void onSuccess(String json) {
                            if (!isViewAttached()) {
                                return;
                            }
                            try {
                                Common.handleTokenOverFiled(json);
                                save(name, code, frontUrl, backUrl);
                            } catch (Exception e) {
                                getView().toast("json解析异常");
                            }
                        }
                    });
                    return;
                }
                getView().toast("异常错误");

            }
        });
    }

    /**
     * 上传图片
     *
     * @param files    图片文件
     * @param fileName 图片名字
     * @param fileSize 图片大小
     * @param front    是否为正面
     */
    public void uploadImg(List<File> files, String fileName, String fileSize, boolean front) {
        if (unViewAttachedOrNetworkDisconnected()) {
            return;
        }
        if (TextUtils.isEmpty(Common.getToken())) {
            getView().toLogin();
            return;
        }
        model.uploadImg(files, fileName, fileSize, new JsonCallBack() {
            @Override
            public void onSuccess(String json) {
                if (!isViewAttached()) {
                    return;
                }
                ConvertUtil<ImageBean> util = new ConvertUtil<>();
                int convert = util.convert(new ImageBean(), json);
                LogUtils.d("convert--"+convert);
                if (convert == 1) {
                    ImageBean bean = new Gson().fromJson(json, ImageBean.class);
                    getView().handleUploadSuccess(bean.getNetWorkUrl(), front);
                    return;
                }
                if (convert == 2) {
                    if (handleConvert2(json)) {
                        refreshTokenModel.refresh(new JsonCallBack() {
                            @Override
                            public void onSuccess(String json) {
                                Common.handleTokenOverFiled(json);
                                uploadImg(files, fileName, fileSize, front);
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
}
