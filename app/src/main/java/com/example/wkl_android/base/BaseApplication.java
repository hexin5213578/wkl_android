package com.example.wkl_android.base;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.os.StrictMode;
import android.os.Vibrator;
import android.view.Window;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.example.wkl_android.BuildConfig;
import com.example.wkl_android.base.app.BaseApp;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.http.HttpUtils;
import com.example.wkl_android.http.processor.impl.OkHttpProcessor;
import com.example.wkl_android.service.LocationService;
import com.example.wkl_android.utils.LogUtils;
import com.example.wkl_android.utils.SPUtils;
import com.hjq.toast.ToastUtils;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


/**
 * @author li
 * @since 2019-05-20
 */
public class BaseApplication extends BaseApp {
    public LocationService locationService;
    public Vibrator mVibrator;

    private static BaseApplication instance;


    // IWXAPI 是第三方app和微信通信的openApi接口
    private IWXAPI api;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;


        setVersion(BuildConfig.VERSION_NAME);
        HttpUtils.getInstance().register(new OkHttpProcessor());
        SPUtils.getInstance().register(this);
        ToastUtils.init(this);


        // android 7.0系统解决拍照的问题
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();

        /**
         * 初始化定位sdk
         */
        locationService = new LocationService(getApplicationContext());
        mVibrator = (Vibrator) getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
        SDKInitializer.initialize(getApplicationContext());

        SDKInitializer.setCoordType(CoordType.BD09LL);

        initLifeCycle();
        regToWx();
    }

    public static BaseApplication getInstance() {
        return instance;
    }


    public BaseApplication() {
        super();
        instance = this;
    }

    private void regToWx() {
        String appId = Common.APP_ID;
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(this, appId, false);

        // 将应用的appId注册到微信
        api.registerApp(appId);

        //建议动态监听微信启动广播进行注册到微信
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                // 将该app注册到微信
                api.registerApp(appId);
            }
        }, new IntentFilter(ConstantsAPI.ACTION_REFRESH_WXAPP));

    }

    private void initLifeCycle() {
        BaseLifecycle.init(this).setOnTaskSwitchListener(new BaseLifecycle.OnTaskSwitchListener() {
            @Override
            public void onForeground() {
                LogUtils.d("前台");
            }

            @Override
            public void onBackground() {
                LogUtils.d("后台");
            }
        });
    }


}
