package com.example.wkl_android.base.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.view.DisplayCutout;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.wkl_android.Event.Event;
import com.example.wkl_android.R;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.base.view.IBaseView;
import com.example.wkl_android.utils.AllOpenActivity;
import com.jaeger.library.StatusBarUtil;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author szx
 * @since 2019-12-22
 */
public abstract class BaseMVPActivity<V extends IBaseView, P extends BasePresenter<V>>
        extends BaseAppCompatActivity implements IBaseView {

    protected P presenter;

    private static boolean isCheckNotch;
    public static List<Rect> notchRects;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P && !isCheckNotch) {
            getNotchParams();
            isCheckNotch = true;
        }

        setContentView(getLayoutRes());

        ButterKnife.bind(this);
        presenter = createPresenter();
        if (presenter != null) presenter.attachView((V) this);
        initViews();
        overridePendingTransition(0, 0);
        AllOpenActivity.getInstance().addActivity(this);
        EventBus.getDefault().register(this);

//        setTitleBarWithe();
    }


    /**
     * 获取状态栏高度
     *
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        return height;
    }


    /*
     * 获取刘海屏相关信息
     */
    @TargetApi(28)
    public void getNotchParams() {
        String tag = "notch";
        final View decorView = getWindow().getDecorView();
        decorView.post(new Runnable() {
            @Override
            public void run() {
                DisplayCutout displayCutout = decorView.getRootWindowInsets().getDisplayCutout();
                Logger.i(tag, "安全区域距离屏幕左边的距离 SafeInsetLeft:" + "null");

                if (displayCutout != null) {

                    Logger.i(tag, "安全区域距离屏幕左边的距离 SafeInsetLeft:" + displayCutout.getSafeInsetLeft());
                    Logger.i(tag, "安全区域距离屏幕右部的距离 SafeInsetRight:" + displayCutout.getSafeInsetRight());
                    Logger.i(tag, "安全区域距离屏幕顶部的距离 SafeInsetTop:" + displayCutout.getSafeInsetTop());
                    Logger.i(tag, "安全区域距离屏幕底部的距离 SafeInsetBottom:" + displayCutout.getSafeInsetBottom());

                    List<Rect> rects = displayCutout.getBoundingRects();
                    if (rects != null && rects.size() > 0) {
                        notchRects = rects;
                        for (Rect rect : rects) {
                        }
                        return;
                    }
                }
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void EventBusEvent(Event event) {

    }

    /**
     * 设置标题背景 红色
     */
    public void setTitleBarWithe() {
        StatusBarUtil.setLightMode(this);
        StatusBarUtil.setColor(this, ContextCompat.getColor(this, R.color.white), 0);

       setTitleHeight();

    }

    /**
     * 设置标题背景 红色
     */
    public void setTitleBarTrans() {
        StatusBarUtil.setLightMode(this);
        StatusBarUtil.setColor(this, ContextCompat.getColor(this, R.color.transparent), 0);

        setTitleHeight();

    }

    public void setTitleHeight(){
        try {
            FrameLayout flContent = findViewById(R.id.flContent);
            if (flContent != null) {
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) flContent.getLayoutParams();
                params.topMargin = getStatusBarHeight(this);

            }

        }catch (Exception e){

        }

        try {
            RelativeLayout rlTitle = findViewById(R.id.rlTitle);
            if (rlTitle != null) {
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) rlTitle.getLayoutParams();
                params.topMargin = getStatusBarHeight(this);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置标题背景 红色
     */
    public void setTitleBarWithe(int color) {

        setTitleHeight();
        StatusBarUtil.setLightMode(this);
        StatusBarUtil.setColor(this, color, 0);
    }

    /**
     * 获取页面布局的资源文件
     *
     * @return {@link LayoutRes}
     */
    @LayoutRes
    public abstract int getLayoutRes();

    /**
     * 创建Presenter
     *
     * @return {@link BasePresenter}的子类对象
     */
    protected abstract P createPresenter();

    /**
     * 初始化页面布局
     */
    protected abstract void initViews();


}
