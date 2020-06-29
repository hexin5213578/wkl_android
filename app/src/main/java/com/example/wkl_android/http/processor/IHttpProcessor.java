package com.example.wkl_android.http.processor;


import androidx.annotation.NonNull;

import com.example.wkl_android.http.callback.ICallBack;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @author li
 * @since 2019/3/20
 */
public interface IHttpProcessor {

    /**
     * 异步GET
     *
     * @param url      请求地址
     * @param tag      标记
     * @param callBack 回调
     */
    void doGet(@NonNull String url, Object tag, ICallBack callBack);

    void doGet(@NonNull String url, String token, Object tag, ICallBack callBack);

    /**
     * 异步POST
     *
     * @param url      请求地址
     * @param param    请求参数
     * @param tag      标记
     * @param callBack 回调
     */
    void doPost(@NonNull String url, @NonNull Map<String, String> param, Object tag, ICallBack callBack);

    void doPostJson(@NonNull String url, @NonNull String json, String token, Object tag, ICallBack callBack);

    void doPostJson(@NonNull String url, @NonNull String json, Object tag, ICallBack callBack);

    void doPutJson(@NonNull String url, @NonNull String json, String token, Object tag, ICallBack callBack);

    /**
     * 同步POST
     *
     * @param url      请求地址
     * @param param    请求参数
     * @param tag      标记
     * @param callBack 回调
     */
    void doSyncPost(@NonNull String url, @NonNull Map<String, String> param, Object tag, ICallBack callBack);

    /**
     * 下载
     *
     * @param url      下载地址
     * @param tag      请求标记
     * @param callBack 回调
     */
    void download(String url, Object tag, ICallBack callBack);

    /**
     * @param url      图片路径
     * @param tag      请求标记
     * @param callBack 回调
     */
    void url2Bitmap(String url, Object tag, ICallBack callBack);

    /**
     * 上传文件
     *
     * @param url      地址
     * @param params   参数
     * @param files    文件集合,类型采用 Map<String, List<File>>
     * @param callBack 回调
     */
    void uploadImg(String url, Map<String, Object> params, Map<String, List<File>> files, Object tag, ICallBack callBack);

    void uploadImg1(String url, Map<String, Object> params, File file, String is, ICallBack callBack);

    /**
     * 上传文件(没有其他参数)
     *
     * @param url      地址
     * @param files    文件
     * @param tag      标记
     * @param callBack 回调
     */
    void uploadImgNoOtherParam(String url, Map<String, List<File>> files, Object tag, ICallBack callBack);

    /**
     * 取消网络请求
     *
     * @param tag 标记
     */
    void cancel(Object tag);
}
