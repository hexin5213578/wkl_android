package com.example.wkl_android.utils;

import android.app.Activity;

import java.util.LinkedList;
import java.util.List;

/**
 * @author li
 * @since 2018/11/12
 */
public class ActivityUtils {

    private List<Activity> list;
    private static volatile ActivityUtils _instance;

    private ActivityUtils() {
        list = new LinkedList<>();
    }

    public static ActivityUtils getInstance() {
        if (_instance == null) {
            synchronized (ActivityUtils.class) {
                if (_instance == null) {
                    _instance = new ActivityUtils();
                }
            }
        }
        return _instance;
    }

    /**
     * 创建新Activity时添加到栈中
     *
     * @param activity 新创建的Activity
     */
    public void addActivity(Activity activity) {
        list.add(activity);
    }

    /**
     * 关闭的Activity出栈
     *
     * @param activity 即将关闭的Activity
     */
    public void removeActivity(Activity activity) {
        list.remove(activity);
    }

    /**
     * 退出APP的方法
     * 这里使用遍历退出Activity的方式
     */
    public void exit() {
        for (Activity activity : list) {
            activity.finish();
        }
        list.clear();
    }
}
