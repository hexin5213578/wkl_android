package com.example.wkl_android.main.shop.settings.main.presenter;

import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.http.bean.BaseBean;
import com.example.wkl_android.http.callback.impl.JsonCallBack;
import com.example.wkl_android.http.callback.impl.StringCallBack;
import com.example.wkl_android.main.shop.address.remake.model.bean.DataBean;
import com.example.wkl_android.main.shop.settings.main.model.SettingsModel;
import com.example.wkl_android.main.shop.settings.main.ui.ISettingsView;
import com.example.wkl_android.main.shop.settings.safe_pwd.model.RefreshTokenModel;
import com.example.wkl_android.utils.ConvertUtil;
import com.example.wkl_android.utils.SPUtils;
import com.google.gson.Gson;

/**
 * Created by szx
 * on 2020/1/4/004
 */
public class SettingsPresenter extends BasePresenter<ISettingsView> {
    private SettingsModel model;
    private RefreshTokenModel refresh;
    private int taskNum;

    public SettingsPresenter() {
        model = new SettingsModel();
        refresh = new RefreshTokenModel();
    }

    @Override
    public void cancel() {
        model.cancel();
        refresh.cancel();
    }

    public void loadPage() {
        isSafetyAnswer();
    }

    /**
     * 获取用户是否有密保问题
     */
    private void isSafetyAnswer() {
        if (unViewAttachedOrNetworkDisconnected()) {
            return;
        }
        if (isTokenNull()) {
            getView().toLogin();
            return;
        }
        model.isExist(new StringCallBack<BaseBean>() {
            @Override
            public void onStart() {
                super.onStart();
                if (!isViewAttached()) {
                    return;
                }
                taskNum++;
                //getView().showLoading();
            }

            @Override
            public void onSuccess(BaseBean bean) {
                if (!isViewAttached()) {
                    return;
                }
                if (bean.isState()) {
                    getView().handleIsExists(bean.isExist());
                    return;
                }
                if (bean.getTokenFailed()) {
                    refresh.refresh(new JsonCallBack() {
                        @Override
                        public void onSuccess(String json) {
                            try {
                                Common.handleTokenOverFiled(json);
                                isSafetyAnswer();
                            } catch (Exception e) {
                                getView().toast("解析异常");
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
                taskNum--;
                isCertification();
            }
        });
    }

    /**
     * 判断是否实名
     */
    private void isCertification() {
        if (taskNum > 0 || unViewAttachedOrNetworkDisconnected()) {
            return;
        }
        model.getIsCertification(new StringCallBack<BaseBean>() {
            @Override
            public void onStart() {
                super.onStart();
                taskNum++;
            }

            @Override
            public void onSuccess(BaseBean bean) {
                if (!isViewAttached()) {
                    return;
                }
                if (bean.isState()) {
                    getView().handleIsCertification(bean.isExist());
                    return;
                }
                if (bean.getTokenFailed()) {
                    refresh.refresh(new JsonCallBack() {
                        @Override
                        public void onSuccess(String json) {
                            try {
                                Common.handleTokenOverFiled(json);
                                isSafetyAnswer();
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
                    //getView().dismissLoading();
                }
                taskNum--;
                isPayPwd();
            }
        });
    }

    /**
     * 判断用户是否有支付密码
     */
    private void isPayPwd() {
        if (taskNum > 0 || unViewAttachedOrNetworkDisconnected()) {
            return;
        }
        model.getIsPayPwd(new StringCallBack<BaseBean>() {
            @Override
            public void onStart() {
                super.onStart();
                taskNum++;
            }

            @Override
            public void onSuccess(BaseBean bean) {
                if (!isViewAttached()) {
                    return;
                }
                if (bean.isState()) {
                    getView().handlePayPwdIsExist(bean.isExist());
                    return;
                }
                if (bean.getTokenFailed()) {
                    refresh.refresh(new JsonCallBack() {
                        @Override
                        public void onSuccess(String json) {
                            Common.handleTokenOverFiled(json);
                            isPayPwd();
                        }
                    });
                }
            }

            @Override
            public void onFinished() {
                super.onFinished();
                taskNum--;
                getUserPhone();
            }
        });
    }

    /**
     * 获取用户手机号
     */
    private void getUserPhone() {
        if (taskNum > 0 || unViewAttachedOrNetworkDisconnected()) {
            return;
        }
        model.getUserPhone(new JsonCallBack() {
            @Override
            public void onSuccess(String json) {
                if (!isViewAttached()) {
                    return;
                }
                ConvertUtil<DataBean> util = new ConvertUtil<>();
                int convert = util.convert(new DataBean(), json);
                if (convert == 1) {
                    DataBean bean = new Gson().fromJson(json, DataBean.class);
                    SPUtils.getInstance().putString(SPUtils.USER_PHONE, bean.getData());
                    return;
                }
                if (convert == 2) {
                    boolean isTokenFailed = handleConvert2(json);
                    if (isTokenFailed) {
                        refresh.refresh(new JsonCallBack() {
                            @Override
                            public void onSuccess(String json) {
                                Common.handleTokenOverFiled(json);
                                getUserPhone();
                            }
                        });
                    }
                    return;
                }
                if (convert == 3) {
                    handleCovert3(json);
                }
            }

            @Override
            public void onFinished() {
                super.onFinished();
                if (!isViewAttached()) {
                    return;
                }
                //getView().dismissLoading();
                taskNum--;
            }
        });
    }

}
