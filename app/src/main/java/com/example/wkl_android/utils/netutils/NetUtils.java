package com.example.wkl_android.utils.netutils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;

import com.example.wkl_android.common.Common;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetUtils {
    private Apis apis;

    private NetUtils(){
        initOkHttp();
    }
    private static class SingleInstance{
        private static NetUtils utils = new NetUtils();
    }
    public static NetUtils getInstance(){
        return SingleInstance.utils;
    }
    private void initOkHttp() {
        //添加日志拦截器
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();

        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //设置缓存时间
        OkHttpClient build = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(new HeaderInterceptor())
                .build();

        Retrofit.Builder builder = new Retrofit.Builder();
        Retrofit retrofit = builder.client(build).baseUrl("39.100.87.173:30001/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apis = retrofit.create(Apis.class);
    }

    public Apis getApis() {
        return apis;
    }


    public class HeaderInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            String token = Common.getToken();

            if(TextUtils.isEmpty(token) ){
                return chain.proceed(request);
            }
            Request requestNew = request.newBuilder().addHeader("Content-Type","application/x-www-form-urlencoded")
                    .addHeader("token", token)
                    .build();;

            return chain.proceed(requestNew);
        }
    }
    //判断网络状态
    public boolean NetWork(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = cm.getActiveNetworkInfo();
        if(activeNetworkInfo!=null){
            return true;
        }
        return false;
    }
}
