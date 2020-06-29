package com.example.wkl_android.main.shop.add_shop.presenter;

import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.http.bean.BaseBean;
import com.example.wkl_android.http.callback.impl.JsonCallBack;
import com.example.wkl_android.http.callback.impl.StringCallBack;
import com.example.wkl_android.main.category.model.CategoryModel;
import com.example.wkl_android.main.category.model.bean.Category;
import com.example.wkl_android.main.shop.add_shop.model.AddModel;
import com.example.wkl_android.main.shop.add_shop.ui.IAddView;
import com.example.wkl_android.main.shop.settings.information.certification.model.UploadImgModel;
import com.example.wkl_android.main.shop.settings.information.certification.model.bean.ImageBean;
import com.example.wkl_android.main.shop.settings.safe_pwd.model.RefreshTokenModel;
import com.example.wkl_android.utils.ConvertUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by szx
 * on 2020/1/9/009
 */
public class AddPresenter extends BasePresenter<IAddView> {
    private AddModel model;
    private UploadImgModel uploadImgModel;
    private RefreshTokenModel refreshTokenModel;
    private CategoryModel categoryModel;

    public AddPresenter() {
        model = new AddModel();
        uploadImgModel = new UploadImgModel();
        refreshTokenModel = new RefreshTokenModel();
        categoryModel = new CategoryModel();
    }

    @Override
    public void cancel() {
        model.cancel();
        uploadImgModel.cancel();
        refreshTokenModel.cancel();
        categoryModel.cancel();
    }


    /**
     * 上传图片
     *
     * @param files 图片文件
     * @param name  文件名
     * @param size  文件大小
     */
    public void uploadImg(List<File> files, String name, String size) {
        if (unViewAttachedOrNetworkDisconnected()) {
            return;
        }
        if (isTokenNull()) {
            getView().toLogin();
            return;
        }
        uploadImgModel.uploadImg(files, name, size, new JsonCallBack() {
            @Override
            public void onSuccess(String json) {
                if (!isViewAttached()) {
                    return;
                }
                ConvertUtil<ImageBean> util = new ConvertUtil<>();
                int convert = util.convert(new ImageBean(), json);
                if (convert == 1) {
                    ImageBean bean = new Gson().fromJson(json, ImageBean.class);
                    getView().setImgUrl(bean.getNetWorkUrl());
                    return;
                }
                if (convert == 2) {
                    if (handleConvert2(json)) {
                        refreshTokenModel.refresh(new JsonCallBack() {
                            @Override
                            public void onSuccess(String json) {
                                Common.handleTokenOverFiled(json);
                                uploadImg(files, name, size);
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
     * 店铺入驻提交信息
     * (参数太多了,在AddShop类自己找吧)
     */
    public void submit(String companyName, String companyPhone, String businessDepositBank,
                       String businessPaymentCode, String linkman, String linkmanPhone,
                       String taxpayerCode, String invoiceTitle, String mallBusinessNum,
                       String imgUrl, String businessName, String companyLocation,
                       String companyAddress, String fileName, String postCode, String companyUrl,
                       String companyIntroduce, String companyEmail, String businessGrade,
                       String businessClassify, String sellClassify) {
        if (unViewAttachedOrNetworkDisconnected()) {
            return;
        }
        if (isTokenNull()) {
            getView().toLogin();
            return;
        }
        model.submit(companyName, companyPhone, businessDepositBank, businessPaymentCode, linkman,
                linkmanPhone, taxpayerCode, invoiceTitle, mallBusinessNum, imgUrl, businessName,
                companyLocation, companyAddress, fileName, postCode, companyUrl, companyIntroduce,
                companyEmail, businessGrade, businessClassify, sellClassify, new StringCallBack<BaseBean>() {
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
                                        submit(companyName, companyPhone, businessDepositBank, businessPaymentCode, linkman,
                                                linkmanPhone, taxpayerCode, invoiceTitle, mallBusinessNum, imgUrl, businessName,
                                                companyLocation, companyAddress, fileName, postCode, companyUrl, companyIntroduce,
                                                companyEmail, businessGrade, businessClassify, sellClassify);
                                    } catch (Exception e) {
                                        getView().toast(bean.getMessage());
                                    }
                                }
                            });
                            return;
                        }
                        getView().toast(bean.getMessage());
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

    public void submit() {
        if (unViewAttachedOrNetworkDisconnected()) {
            return;
        }
        if (isTokenNull()) {
            getView().toLogin();
            return;
        }
        model.submit(new StringCallBack<BaseBean>() {
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
                                submit();
                            } catch (Exception e) {
                                getView().toast(bean.getMessage());
                            }
                        }
                    });
                    return;
                }
                getView().toast(bean.getMessage());
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


    /**
     * 获取店铺分类
     */
    public void getCategory() {
        if (unViewAttachedOrNetworkDisconnected()) {
            return;
        }
        if (isTokenNull()) {
            getView().toLogin();
            return;
        }
        categoryModel.getCategory(new JsonCallBack() {
            @Override
            public void onSuccess(String json) {
                ConvertUtil<Category> util = new ConvertUtil<>();
                int convert = util.convert(new Category(), json);
                if (convert == 1) {
                    Type type = new TypeToken<List<Category>>() {
                    }.getType();
                    List<Category> list = new Gson().fromJson(json, type);
                    getView().handleCategoryList(list);
                    return;
                }
                if (convert == 2) {
                    handleConvert2(json);
                    return;
                }
                if (convert == 3) {
                    handleCovert3(json);
                }
            }
        });
    }
}
