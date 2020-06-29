package com.example.wkl_android.commentorder.presenter;

import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.commentorder.ICommentOrderView;
import com.example.wkl_android.commentorder.bean.CommentData;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.good.model.GoodsListBean;
import com.example.wkl_android.http.HttpUtils;
import com.example.wkl_android.http.callback.impl.JsonCallBack;
import com.example.wkl_android.login.login.model.LoginModel;
import com.example.wkl_android.main.shop.settings.information.certification.model.UploadImgModel;
import com.example.wkl_android.main.shop.settings.information.certification.model.bean.ImageBean;
import com.example.wkl_android.order.detail.model.OrderDetailInfo;
import com.example.wkl_android.utils.C;
import com.example.wkl_android.utils.ConvertUtil;
import com.example.wkl_android.utils.ToastUtil;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by szx
 * on 2019/12/30/030
 */
public class CommentOrderPresenter extends BasePresenter<ICommentOrderView> {

    private LoginModel model;
    private UploadImgModel uploadImgModel;

    @Override
    public void cancel() {
        model.cancel();
    }

    public void upImg(String path) {

        if (uploadImgModel == null) {
            uploadImgModel = new UploadImgModel();
        }

        ArrayList<File> files = new ArrayList<>();
        files.add(new File(path));

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
                        getView().upImg(img);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }


    public void getOrderData(String id) {
        HttpUtils.getInstance().doGet(C.ORDER_DETAIL + id, Common.getToken(), this, new JsonCallBack() {
            @Override
            public void onSuccess(String json) throws Exception {

                if (!isViewAttached()) {
                    return;
                }
                JSONObject object = new JSONObject(json);
                OrderDetailInfo data = new Gson().fromJson(object.optString("data"), OrderDetailInfo.class);

                getView().setData(data);


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

    public void commentOrder(CommentData data) {
        HttpUtils.getInstance().doPostJson(C.COMMENT_ORDER , new Gson().toJson(data) , Common.getToken(), this, new JsonCallBack() {
            @Override
            public void onSuccess(String json) throws Exception {

                if (!isViewAttached()) {
                    return;
                }

                JSONObject object = new JSONObject(json);
                boolean state = object.getBoolean("state");
                if(state){
                    getView().commentSuccess();

                }else {
                    ToastUtil.show(object.optString("message"));
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
}
