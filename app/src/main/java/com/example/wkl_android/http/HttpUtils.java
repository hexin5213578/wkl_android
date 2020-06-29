package com.example.wkl_android.http;

import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.wkl_android.http.adapter.DoubleDefault0Adapter;
import com.example.wkl_android.http.adapter.IntegerDefault0Adapter;
import com.example.wkl_android.http.adapter.LongDefault0Adapter;
import com.example.wkl_android.http.callback.ICallBack;
import com.example.wkl_android.http.exception.HttpProcessorUnregisterException;
import com.example.wkl_android.http.processor.IHttpProcessor;
import com.example.wkl_android.http.utils.ParamUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Http代理类/工具类
 * <p>
 * 所有接口添加参数,键:<b>fromAPP</b>、值<b>jd</b>
 * <p>
 * 接口通过{@link ParamUtils}工具添加签名验签
 *
 * @author li
 * @since 2019/3/20
 */
public class HttpUtils implements IHttpProcessor {
    private static volatile HttpUtils _instance;
    public static Gson gson;
    private IHttpProcessor realProcessor;

    private HttpUtils() {
        buildGson();
    }

    public static HttpUtils getInstance() {
        if (_instance == null) {
            synchronized (HttpUtils.class) {
                if (_instance == null) {
                    _instance = new HttpUtils();
                }
            }
        }
        return _instance;
    }

    public void register(IHttpProcessor processor) {
        this.realProcessor = processor;
    }

    private void buildGson() {
        if (gson == null) {
            gson = new GsonBuilder()
                    .registerTypeAdapter(Integer.class, new IntegerDefault0Adapter())
                    .registerTypeAdapter(int.class, new IntegerDefault0Adapter())
                    .registerTypeAdapter(Double.class, new DoubleDefault0Adapter())
                    .registerTypeAdapter(double.class, new DoubleDefault0Adapter())
                    .registerTypeAdapter(Long.class, new LongDefault0Adapter())
                    .registerTypeAdapter(long.class, new LongDefault0Adapter())
                    .serializeNulls()
                    .create();
        }
    }

    @Override
    public void doGet(@NonNull String url, Object tag, ICallBack callBack) {
        if (realProcessor == null) {
            throw new HttpProcessorUnregisterException();
        }
        realProcessor.doGet(url, tag, callBack);
    }

    @Override
    public void doGet(@NonNull String url, String token, Object tag, ICallBack callBack) {
        if (realProcessor == null) {
            throw new HttpProcessorUnregisterException();
        }
        realProcessor.doGet(url, token, tag, callBack);
    }

    @Override
    public void doPost(@NonNull String url, @NonNull Map<String, String> param, Object tag, ICallBack callBack) {
        if (realProcessor == null) {
            throw new HttpProcessorUnregisterException();
        }
        realProcessor.doPost(url, param, tag, callBack);
    }

    @Override
    public void doPostJson(@NonNull String url, @NonNull String json, String token, Object tag, ICallBack callBack) {
        if (realProcessor == null) {
            throw new HttpProcessorUnregisterException();
        }
        realProcessor.doPostJson(url, json, token, tag, callBack);
    }

    @Override
    public void doPostJson(@NonNull String url, @NonNull String json, Object tag, ICallBack callBack) {
        if (realProcessor == null) {
            throw new HttpProcessorUnregisterException();
        }
        //  addCommonParam(param);

        //处理参数,添加签名
        // ParamUtils.addKeySignWithMethodPost(param);

        realProcessor.doPostJson(url, json, tag, callBack);
    }

    @Override
    public void doPutJson(@NonNull String url, @NonNull String json, String token, Object tag, ICallBack callBack) {
        if (realProcessor == null) {
            throw new HttpProcessorUnregisterException();
        }
        //  addCommonParam(param);

        //处理参数,添加签名
        // ParamUtils.addKeySignWithMethodPost(param);
        Log.d("wfs","请求参数："+json);
        realProcessor.doPutJson(url, json, token, tag, callBack);
    }

    @Override
    public void doSyncPost(@NonNull String url, @NonNull Map<String, String> param, Object tag, ICallBack callBack) {
        if (realProcessor == null) {
            throw new HttpProcessorUnregisterException();
        }


        // 处理参数,添加签名
        ParamUtils.addKeySignWithMethodPost(param);

        realProcessor.doSyncPost(url, param, tag, callBack);
    }

    @Override
    public void download(String url, Object tag, ICallBack callBack) {
        if (realProcessor == null) {
            throw new HttpProcessorUnregisterException();
        }
        realProcessor.download(url, tag, callBack);
    }

    @Override
    public void url2Bitmap(String url, Object tag, ICallBack callBack) {
        if (realProcessor == null) {
            throw new HttpProcessorUnregisterException();
        }
        realProcessor.url2Bitmap(url, tag, callBack);
    }

    @Override
    public void uploadImg(String url, Map<String, Object> params, Map<String, List<File>> files, Object tag, ICallBack callBack) {
        if (realProcessor == null) {
            throw new HttpProcessorUnregisterException();
        }

//        addCommonParam(params);
//        // 处理参数,添加签名
//        ParamUtils.addKeySignWithMethodPost(params);

        realProcessor.uploadImg(url, params, files, tag, callBack);
    }

    @Override
    public void uploadImg1(String url, Map<String, Object> params, File file, String is, ICallBack callBack) {
        if (realProcessor == null) {
            throw new HttpProcessorUnregisterException();
        }
        realProcessor.uploadImg1(url, params, file, is, callBack);
    }

    @Override
    public void uploadImgNoOtherParam(String url, Map<String, List<File>> files, Object tag, ICallBack callBack) {
        if (realProcessor == null) {
            throw new HttpProcessorUnregisterException();
        }

        realProcessor.uploadImgNoOtherParam(url, files, tag, callBack);
    }

    @Override
    public void cancel(Object tag) {
        if (realProcessor == null) {
            throw new HttpProcessorUnregisterException();
        }
        realProcessor.cancel(tag);
    }

    /**
     * 借助{@link Looper}判断当前线程是否为主线程
     *
     * @return true:主线程 false:非主线程
     */
    public static boolean isMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }
}