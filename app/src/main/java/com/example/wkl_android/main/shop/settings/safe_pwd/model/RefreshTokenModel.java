package com.example.wkl_android.main.shop.settings.safe_pwd.model;

import com.example.wkl_android.base.model.IModel;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.http.HttpUtils;
import com.example.wkl_android.http.callback.ICallBack;
import com.example.wkl_android.main.shop.settings.safe_pwd.model.bean.RefreshBean;
import com.example.wkl_android.main.shop.settings.safe_pwd.model.bean.SafePwdBean;
import com.example.wkl_android.utils.C;
import com.google.gson.Gson;

/**
 * Created by szx
 * on 2020/1/4/004
 */
public class RefreshTokenModel implements IModel {
    @Override
    public void cancel() {
        HttpUtils.getInstance().cancel(this);
    }

    public void findList(ICallBack callBack) {
        String url = C.USER + "/safetyProblem/findList";
        HttpUtils.getInstance().doPostJson(url, Common.getTokenJson(), Common.getToken(), this, callBack);
    }

    /**
     * 刷新token
     */
    public void refresh(ICallBack callBack) {
        String url = C.BASE_URL + "/logon/token/refresh";
        RefreshBean bean = new RefreshBean();
        bean.setData(Common.getRefreshToken());
        bean.setToken(Common.getToken());
        String json = new Gson().toJson(bean);
        HttpUtils.getInstance().doPostJson(url, json,  this, callBack);
    }

    /**
     * 保存密保问题
     *
     * @param answerOne    答案1
     * @param answerTwo    答案
     * @param problemIdOne 问题
     * @param problemIdTwo 问题
     * @param callBack     回调
     */
    public void save(String answerOne, String answerTwo, String problemIdOne, String problemIdTwo, ICallBack callBack) {
        String url = C.USER + "/safetyAnswer/save";
        SafePwdBean bean = new SafePwdBean();
        bean.setAnswerOne(answerOne);
        bean.setAnswerTwo(answerTwo);
        bean.setProblemIdOne(problemIdOne);
        bean.setProblemIdTwo(problemIdTwo);
        String json = new Gson().toJson(bean);
        HttpUtils.getInstance().doPostJson(url, json, Common.getToken(), this, callBack);
    }
}
