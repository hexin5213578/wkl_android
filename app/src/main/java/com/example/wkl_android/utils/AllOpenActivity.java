package com.example.wkl_android.utils;

import android.app.Activity;

import java.util.LinkedList;
import java.util.List;

/**
 * Activity 管理类
 * 使用单例设计模式,全局只有单个实例
 */
public class AllOpenActivity {

    /**
     * 手动保存的Activity栈
     */
    private List<Activity> activities;

    /**
     * 本类实例
     */
    private static volatile AllOpenActivity activityManager;

    /**
     * 私有化的构造器
     * 做初始化
     */
    private AllOpenActivity() {
        activities = new LinkedList<>();
    }

    /**
     * 获取实例
     * 使用synchronized,保证不会在多线程操作中初始化多个实例
     *
     * @return {@link AllOpenActivity}单例
     */
    public static AllOpenActivity getInstance() {
        if (activityManager == null) {
            synchronized (AllOpenActivity.class) {
                if (activityManager == null) {
                    activityManager = new AllOpenActivity();
                }
            }
        }
        return activityManager;
    }

    /**
     * 创建新Activity时添加到栈中
     *
     * @param activity 新创建的Activity
     */
    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    /**
     * 关闭的Activity出栈
     *
     * @param activity 即将关闭的Activity
     */
    public void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    /**
     * 退出APP的方法
     * 这里使用遍历退出Activity的方式
     */
    public void exit() {
        for (Activity activity : activities) {
            activity.finish();
        }
        activities.clear();
    }
}
