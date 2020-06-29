package com.example.wkl_android.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/**
 * @author li
 * @since 2019-08-19
 */
public class BaseLifecycle implements Application.ActivityLifecycleCallbacks {

    private int mCount = 0;
    private static volatile BaseLifecycle sLifecycle;

    private OnTaskSwitchListener mOnTaskSwitchListener;

    private BaseLifecycle() {
    }

    static BaseLifecycle init(Application application) {
        if (null == sLifecycle) {
            synchronized (BaseLifecycle.class) {
                if (null == sLifecycle) {
                    sLifecycle = new BaseLifecycle();
                    application.registerActivityLifecycleCallbacks(sLifecycle);
                }
            }
        }
        return sLifecycle;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {
        if (mCount++ == 0) {
            mOnTaskSwitchListener.onForeground();
        }
    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {
        if (--mCount == 0) {
            mOnTaskSwitchListener.onBackground();
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }

    void setOnTaskSwitchListener(OnTaskSwitchListener listener) {
        this.mOnTaskSwitchListener = listener;
    }

    public interface OnTaskSwitchListener {
        void onForeground();

        void onBackground();
    }
}
