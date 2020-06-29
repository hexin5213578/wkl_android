package com.example.wkl_android.main.shop.settings.information.certification.model;

import com.example.wkl_android.base.model.IModel;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.http.HttpUtils;
import com.example.wkl_android.http.callback.ICallBack;
import com.example.wkl_android.main.shop.settings.information.certification.model.bean.CertificationParams;
import com.example.wkl_android.utils.C;
import com.google.gson.Gson;

import java.io.File;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by szx
 * on 2020/1/3/003
 */
public class UploadImgModel implements IModel {
    @Override
    public void cancel() {
        HttpUtils.getInstance().cancel(this);
    }

    /**
     * 提交实名信息
     *
     * @param name     姓名
     * @param code     身份证号
     * @param callBack 回调
     */
    public void save(String name, String code, String frontUrl, String backUrl, ICallBack callBack) {
        String url = C.USER + "/certification/save";
        CertificationParams data = new CertificationParams();
        data.setCname(name);
        data.setCidCard(code);
        data.setCimageUrlTrue(frontUrl);
        data.setCimageUrlFalse(backUrl);
        String dataJson = new Gson().toJson(data);
        HttpUtils.getInstance().doPostJson(url, dataJson, Common.getToken(), this, callBack);
    }

    public void uploadImg(List<File> list, String fileName, String fileSize, ICallBack callBack) {
        String url = C.UP_FILE;
        ConcurrentHashMap<String, List<File>> files = new ConcurrentHashMap<>();
        files.put("file", list);
        ConcurrentHashMap<String, Object> params = new ConcurrentHashMap<>();
        params.put("fileName", fileName);
        params.put("fileSize", fileSize);
        params.put("token", Common.getToken());
        HttpUtils.getInstance().uploadImg(url, params, files, this, callBack);
    }
}
