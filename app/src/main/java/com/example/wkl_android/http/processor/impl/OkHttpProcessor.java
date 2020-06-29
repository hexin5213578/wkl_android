package com.example.wkl_android.http.processor.impl;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.NetworkOnMainThreadException;
import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.example.wkl_android.common.Common;
import com.example.wkl_android.http.HttpUtils;
import com.example.wkl_android.http.callback.ICallBack;
import com.example.wkl_android.http.exception.InternalServerException;
import com.example.wkl_android.http.exception.OtherHttpException;
import com.example.wkl_android.http.exception.PageNotFoundException;
import com.example.wkl_android.http.exception.RequestErrorException;
import com.example.wkl_android.http.exception.ResponseIsEmptyException;
import com.example.wkl_android.http.exception.ServerException;
import com.example.wkl_android.http.processor.IHttpProcessor;
import com.example.wkl_android.http.utils.HttpLogger;
import com.example.wkl_android.http.utils.InterceptorLogger;
import com.example.wkl_android.utils.C;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Dispatcher;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * 使用OkHttp实现网络请求的代理类
 *
 * @author li
 * @since 2019/3/20
 */
public class OkHttpProcessor implements IHttpProcessor {

    /**
     * {@link OkHttpClient}
     * OkHttp客户端,调用接口
     */
    private OkHttpClient client;
    /**
     * 主线程Handler
     */
    private final Handler handler = new Handler(Looper.getMainLooper());

    /**
     * 连接超时时间,60秒
     */
    private static final int TIMEOUT_CONNECT = 60;
    /**
     * 读取超时时间,60秒
     */
    private static final int TIMEOUT_READ = 60;
    /**
     * 写入超时时间,60秒
     */
    private static final int TIMEOUT_WRITE = 60;

    /**
     * 下载路径文件夹名
     */
    private static final String DOWNLOAD_PATH = "dtbJDDownload";
    /**
     * 时间戳格式器
     */
    private final DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

    /**
     * 图片的MediaType
     */
    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");

    /**
     * 构造器,创建时初始化{@link #client}和{@link #handler}
     * <p>
     * 判断日志打印开关状态,如果处于开启状态,则需要添加日志拦截器
     */
    public OkHttpProcessor() {
        if (client == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder()
                    //设置连接超时
                    .connectTimeout(TIMEOUT_CONNECT, TimeUnit.SECONDS)
                    //设置读超时
                    .readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
                    //设置写超时
                    .writeTimeout(TIMEOUT_WRITE, TimeUnit.SECONDS)
                    //是否自动重连
                    .retryOnConnectionFailure(true);
            if (C.LOG_SWITCH) {
                //添加日志打印
                HttpLogger.init();
                builder.addInterceptor(new HttpLoggingInterceptor(new InterceptorLogger())
                        .setLevel(HttpLoggingInterceptor.Level.BODY));
            }
            client = builder.build();
        }
    }

    /**
     * 执行异步GET请求,参数处理和请求发生在不同线程
     * 1)先在参数集中添加经点APP的标记
     * 2)然后添加签名验签的参数
     * 3)创建请求体
     * 4)调用{@link #asyncCall(Request, ICallBack)}方法执行请求
     *
     * @param url      请求地址
     * @param tag      标记
     * @param callBack 回调
     */
    @Override
    public void doGet(@NonNull String url, Object tag, ICallBack callBack) {
        // 开始请求回调
        if (callBack != null) {
            callBack.onStartIO();
        }
        // 判断 URL为空,取消请求并执行失败回调
        if (TextUtils.isEmpty(url)) {
            if (callBack != null) {
                callBack.onFailedIO(new Exception("url 为空"));
            }
            return;
        }

        // 创建请求体
        Request request = new Request.Builder()
                .url(url)
                .addHeader("token",Common.getToken())
                .tag(tag)
                .build();
        // 执行异步请求
        asyncCall(request, callBack);
    }

    @Override
    public void doGet(@NonNull String url, String token, Object tag, ICallBack callBack) {
        // 开始请求回调
        if (callBack != null) {
            callBack.onStartIO();
        }
        // 判断 URL为空,取消请求并执行失败回调
        if (TextUtils.isEmpty(url)) {
            if (callBack != null) {
                callBack.onFailedIO(new Exception("url 为空"));
            }
            return;
        }

        // 创建请求体
        Request request = new Request.Builder()
                .url(url)
                .addHeader("token", token)
                .tag(tag)
                .build();
        // 执行异步请求
        asyncCall(request, callBack);
    }

    /**
     * 执行异步POST请求,参数处理和请求发生在不同线程
     * 1)先在参数集中添加经点APP的标记
     * 2)然后添加签名验签的参数
     * 3)创建请求体
     * 4)调用{@link #asyncCall(Request, ICallBack)}方法执行请求
     *
     * @param url      请求地址
     * @param param    参数
     * @param tag      标记
     * @param callBack 回调
     */
    @Override
    public void doPost(@NonNull String url, @NonNull Map<String, String> param, Object tag, ICallBack callBack) {
        // 开始请求回调
        if (callBack != null) {
            callBack.onStartIO();
        }

        // 判断URL为空则不请求
        if (TextUtils.isEmpty(url)) {
            if (callBack != null) {
                callBack.onFailedIO(new Exception("url 为空"));
            }
            return;
        }

        // 创建请求体
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, String> entry : param.entrySet()) {
            String key = entry.getKey();
            if (TextUtils.isEmpty(key)) continue;
            String val = entry.getValue();
            if (TextUtils.isEmpty(val)) continue;
            builder.add(key, val);
        }

        Request request = new Request.Builder()
                .url(url)
                .post(builder.build())
                .tag(tag)
                .build();

        // 创建请求 并 发送请求
        asyncCall(request, callBack);
    }

    /**
     * 执行异步POST请求,参数处理和请求发生在不同线程
     * 1)先在参数集中添加标记
     * 2)然后添加签名验签的参数
     * 3)创建请求体
     * 4)调用{@link #asyncCall(Request, ICallBack)}方法执行请求
     *
     * @param url      请求地址
     * @param json     json参数
     * @param tag      标记
     * @param callBack 回调
     */
    @Override
    public void doPostJson(@NonNull String url, @NonNull String json, String token, Object tag, ICallBack callBack) {
        // 开始请求回调
        if (callBack != null) {
            callBack.onStartIO();
        }

        // 判断URL为空则不请求
        if (TextUtils.isEmpty(url)) {
            if (callBack != null) {
                callBack.onFailedIO(new Exception("url 为空"));
            }
            return;
        }

        Request request = new Request.Builder()
                .url(url)
                .addHeader("content-type", "application/json;charset:utf-8")
                .addHeader("token", token)
                .tag(tag)
                .post(RequestBody.create(
                        MediaType.parse("application/json; charset=utf-8"),
                        json))// post json提交
                .build();

        // 创建请求 并 发送请求
        asyncCall(request, callBack);
    }

    /**
     * 执行异步POST请求,参数处理和请求发生在不同线程
     * 1)先在参数集中添加标记
     * 2)然后添加签名验签的参数
     * 3)创建请求体
     * 4)调用{@link #asyncCall(Request, ICallBack)}方法执行请求
     *
     * @param url      请求地址
     * @param json     json参数
     * @param tag      标记
     * @param callBack 回调
     */
    @Override
    public void doPostJson(@NonNull String url, @NonNull String json, Object tag, ICallBack callBack) {
        // 开始请求回调
        if (callBack != null) {
            callBack.onStartIO();
        }

        // 判断URL为空则不请求
        if (TextUtils.isEmpty(url)) {
            if (callBack != null) {
                callBack.onFailedIO(new Exception("url 为空"));
            }
            return;
        }

        Request request = new Request.Builder()
                .url(url)
                .addHeader("content-type", "application/json;charset:utf-8")
                .tag(tag)
                .post(RequestBody.create(
                        MediaType.parse("application/json; charset=utf-8"),
                        json))// post json提交
                .build();

        // 创建请求 并 发送请求
        asyncCall(request, callBack);
    }

    /**
     * 执行异步POST请求,参数处理和请求发生在不同线程
     * 1)先在参数集中添加标记
     * 2)然后添加签名验签的参数
     * 3)创建请求体
     * 4)调用{@link #asyncCall(Request, ICallBack)}方法执行请求
     *
     * @param url      请求地址
     * @param json     json参数
     * @param tag      标记
     * @param callBack 回调
     */
    @Override
    public void doPutJson(@NonNull String url, @NonNull String json, String token, Object tag, ICallBack callBack) {
        // 开始请求回调
        if (callBack != null) {
            callBack.onStartIO();
        }

        // 判断URL为空则不请求
        if (TextUtils.isEmpty(url)) {
            if (callBack != null) {
                callBack.onFailedIO(new Exception("url 为空"));
            }
            return;
        }

        Request request = new Request.Builder()
                .url(url)
                .addHeader("content-type", "application/json;charset:utf-8")
                .addHeader("token", token)
                .tag(tag)
                .put(RequestBody.create(
                        MediaType.parse("application/json; charset=utf-8"),
                        json))// post json提交
                .build();

        // 创建请求 并 发送请求
        asyncCall(request, callBack);
    }

    /**
     * 执行同步POST请求,参数处理和请求发生在同一线程,使用时需要创建子线程
     * 1)先在参数集中添加经点APP的标记
     * 2)然后添加签名验签的参数
     * 3)创建请求体
     * 4)调用{@link #syncCall(Request, ICallBack)}方法执行请求
     *
     * @param url      请求地址
     * @param param    参数
     * @param tag      标记
     * @param callBack 回调
     */
    @Override
    public void doSyncPost(@NonNull String url, @NonNull Map<String, String> param, Object tag, ICallBack callBack) {
        // 执行开始请求回调
        if (callBack != null) {
            callBack.onStartIO();
        }
        // 判断,URL 为空取消请求,并执行失败回调
        if (TextUtils.isEmpty(url)) {
            if (callBack != null) {
                callBack.onFailedIO(new Exception("url 为空"));
            }
            return;
        }

        // 创建请求体
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, String> entry : param.entrySet()) {
            String key = entry.getKey();
            if (TextUtils.isEmpty(key)) continue;
            String val = entry.getValue();
            if (TextUtils.isEmpty(val)) continue;
            builder.add(key, val);
        }
        Request request = new Request.Builder()
                .url(url)
                .post(builder.build())
                .tag(tag)
                .build();
        // 执行同步请求
        syncCall(request, callBack);
    }

    /**
     * 下载实现方法
     *
     * @param url      下载地址, 就是下载路径
     * @param tag      请求标记, 用于取消下载
     * @param callBack 回调
     */
    @Override
    public void download(final String url, Object tag, final ICallBack callBack) {
        Request request = new Request.Builder()
                .url(url)
                .tag(tag)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                if (callBack != null) {
                    callBack.onDownloadFailed(e);
                }
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                InputStream is = null;
                OutputStream os = null;
                try {
                    // 操作流缓存
                    byte[] buf = new byte[1024];
                    int len;

                    File folder = new File(Environment.getExternalStorageDirectory(), DOWNLOAD_PATH);
                    if (!folder.exists() && !folder.mkdirs()) {
                        throw new IOException("路径创建失败");
                    }

                    // 储存下载文件的目录
                    final File file = new File(folder, getFileNameFromUrl(url));
                    if (file.exists() && !file.delete()) {
                        // 先将原来的删掉
                        throw new IOException("文件已存在或删除失败");
                    }
                    ResponseBody responseBody = response.body();
                    if (responseBody == null) {
                        throw new IOException("响应体为空");
                    }
                    is = responseBody.byteStream();
                    long total = responseBody.contentLength();

                    os = new FileOutputStream(file);
                    long sum = 0;
                    while ((len = is.read(buf)) != -1) {
                        os.write(buf, 0, len);
                        // 下载中
                        sum += len;
                        int progress = (int) (sum * 100 / total);
                        try {
                            callBack.onDownloading(progress);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    os.flush();
                    // 下载完成
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                callBack.onDownloadSuccess(file);
                            } catch (Exception e) {
                                e.printStackTrace();
                                callBack.onDownloadFailed(e);
                            }
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                    // 捕获到异常标识下载失败,执行失败回调
                    callBack.onDownloadFailed(e);
                } finally {
                    // 最终把输入流和输出流关闭
                    try {
                        if (is != null) is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        if (os != null) os.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /**
     * @param url      图片路径
     * @param tag      请求标记
     * @param callBack 回调
     */
    @Override
    public void url2Bitmap(final String url, Object tag, final ICallBack callBack) {
        Request request = new Request.Builder()
                .url(url)
                .tag(tag)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                if (callBack != null) {
                    callBack.onBitmapFailedIO(e);
                }
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                ResponseBody responseBody = response.body();
                if (responseBody == null) {
                    throw new IOException("响应体为空");
                }
                InputStream is = responseBody.byteStream();
                Bitmap bitmap;
                try {
                    bitmap = BitmapFactory.decodeStream(is);
                    if (bitmap != null) {
                        final Bitmap finalBitmap = Bitmap.createBitmap(bitmap);
                        // 下载完成
                        callBack.onBitmapSuccessIO(finalBitmap);
                    }
                } catch (Exception e) {
                    callBack.onBitmapFailedIO(e);
                }
            }
        });
    }

    @Override
    public void uploadImg(String url, Map<String, Object> params, Map<String, List<File>> files, Object tag, ICallBack callBack) {
        if (callBack != null) {
            callBack.onStartIO();
        }
        // 创建一个多类型的请求体
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);
        try {
            // 遍历文件集合，将文件添加到请求体
            for (Map.Entry<String, List<File>> entry : files.entrySet()) {
                String key = entry.getKey();
                if (TextUtils.isEmpty(key)) continue;
                List<File> fileList = entry.getValue();
                if (fileList == null || fileList.isEmpty()) continue;
                for (File file : fileList) {
                    if (file == null || !file.exists()) continue;
                    builder.addFormDataPart(key, file.getName(), RequestBody.create(MEDIA_TYPE_PNG, file));
                }
            }
            // 添加其他参数
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                String key = entry.getKey();
                if (TextUtils.isEmpty(key)) continue;
                String val = String.valueOf(entry.getValue());
                if (TextUtils.isEmpty(val)) continue;
                builder.addFormDataPart(key, val);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (callBack != null) {
                callBack.onFailedIO(e);
            }
        }

        // 创建请求
        Request request = new Request.Builder()
                .url(url)
                .addHeader("content-type", "application/octet-stream")
                .addHeader("token", Common.getToken())
                .tag(tag)
                .post(builder.build())
                .build();

        // 执行异步请求
        asyncCall(request, callBack);
    }

    @Override
    public void uploadImg1(String url, Map<String, Object> params, File file, String is, ICallBack callBack) {
        if (callBack != null) {
            callBack.onStartIO();
        }
        // 创建一个多类型的请求体
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);
        try {
            // 添加其他参数
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                String key = entry.getKey();
                if (TextUtils.isEmpty(key)) continue;
                String val = String.valueOf(entry.getValue());
                if (TextUtils.isEmpty(val)) continue;
                builder.addFormDataPart(key, val);
            }
            if (file != null) {
                RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), is);
                builder.addFormDataPart("file", file.getName(), fileBody);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (callBack != null) {
                callBack.onFailedIO(e);
            }
        }
        Request request = new Request.Builder()
                .url(url)
                .post(builder.build())
                .build();
        asyncCall(request, callBack);
    }

    @Override
    public void uploadImgNoOtherParam(String url, Map<String, List<File>> files, Object tag, ICallBack callBack) {
        if (callBack != null) {
            callBack.onStartIO();
        }
        // 创建一个多类型的请求体
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);
        try {
            // 遍历文件集合，将文件添加到请求体
            for (Map.Entry<String, List<File>> entry : files.entrySet()) {
                String key = entry.getKey();
                if (TextUtils.isEmpty(key)) continue;
                List<File> fileList = entry.getValue();
                if (fileList == null || fileList.isEmpty()) continue;
                for (File file : fileList) {
                    if (file == null || !file.exists()) continue;
                    builder.addFormDataPart(key, file.getName(), RequestBody.create(MEDIA_TYPE_PNG, file));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (callBack != null) {
                callBack.onFailedIO(e);
            }
        }

        // 创建请求
        Request request = new Request.Builder()
                .url(url)
                .tag(tag)
                .post(builder.build())
                .build();

        // 执行异步请求
        asyncCall(request, callBack);
    }

    @Override
    public void cancel(@NonNull Object tag) {
        Dispatcher dispatcher = client.dispatcher();
        // 先遍历任务队列,将任务队列里未执行的请求结束
        for (Call call : dispatcher.queuedCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
        // 再遍历正在请求的队列,将正在执行的请求结束
        for (Call call : dispatcher.runningCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
    }

    /**
     * 执行异步请求
     *
     * @param request  请求
     * @param callBack 回调
     */
    private void asyncCall(Request request, final ICallBack callBack) {
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                //请求失败或出现异常
                //执行回调失败方法
                if (callBack != null) {
                    callBack.onFailedIO(e);
                }
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                //请求成功
                //执行成功回调方法，进行数据解析
                if (callBack != null) {
                    if (response.isSuccessful()) {
                        ResponseBody body = response.body();
                        if (body != null) {
                            callBack.onSuccessIO(body.string());
                        } else {
                            throw new ResponseIsEmptyException();
                        }
                    } else {
                        int code = response.code();
                        if (code == 404) {
                            // 页面未找到
                            throw new PageNotFoundException();
                        } else if (code >= 400 && code < 500) {
                            throw new RequestErrorException();
                        } else if (code == 500) {
                            throw new InternalServerException();
                        } else if (code >= 500 && code <= 600) {
                            throw new ServerException();
                        } else {
                            throw new OtherHttpException();
                        }
                    }
                }
            }
        });
    }

    /**
     * 执行同步请求
     *
     * @param request  请求
     * @param callBack 回调
     */
    private void syncCall(Request request, final ICallBack callBack) {
        try {
            if (HttpUtils.isMainThread()) {
                // 主线程不能执行网络请求
                throw new NetworkOnMainThreadException();
            }
            Response response = client.newCall(request).execute();
            //请求成功
            //执行成功回调方法，进行数据解析
            if (callBack != null) {
                if (response.isSuccessful()) {
                    ResponseBody body = response.body();
                    if (body != null) {
                        callBack.onSuccessIO(body.string());
                    } else {
                        // 抛出响应体为空异常
                        throw new ResponseIsEmptyException();
                    }
                } else {
                    int code = response.code();
                    if (code == 404) {
                        throw new PageNotFoundException();
                    } else if (code >= 400 && code < 500) {
                        throw new RequestErrorException();
                    } else if (code == 500) {
                        throw new InternalServerException();
                    } else if (code >= 500 && code <= 600) {
                        throw new ServerException();
                    } else {
                        throw new OtherHttpException();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            //请求失败或出现异常
            //执行回调失败方法
            if (callBack != null) {
                callBack.onFailedIO(e);
            }
        }
    }

    /**
     * 通过URL获取文件名
     *
     * @param source 源URL
     * @return 如果url为空返回当前时间
     */
    private String getFileNameFromUrl(String source) {
        try {
            URL url = new URL(source);
            return url.getPath().replace("/", "");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return format.format(new Date());
    }
}