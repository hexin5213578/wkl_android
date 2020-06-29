package com.example.wkl_android.utils;

 import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

 import androidx.annotation.StringRes;

 import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseApplication;
import com.hjq.toast.ToastUtils;

public class ToastUtil {

    private static CharSequence oldMsg = "";
    public static Toast toast;
    private static long oneTime = 0;
    private static long twoTime = 0;
    private static int toast_default_y = 0;
    private static View mView;
    private static TextView tv_toast;

    public static void show(CharSequence msg, int duration) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        if (toast_default_y == 0) {
            // com.android.internal.R.dimen.toast_y_offset
            toast_default_y = UIUtil.dp2px(24) + BaseApplication.getInstance().getResources().getDimensionPixelSize(R.dimen.tab_layout_height);
        }
        if (toast == null) {
            toast = Toast.makeText(BaseApplication.getInstance(), msg, duration);
            try {
                toast.show();
            } catch (Exception e) {

            }
            oneTime = System.currentTimeMillis();
        } else {
            toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, toast_default_y);
            twoTime = System.currentTimeMillis();
            if (TextUtils.equals(msg, oldMsg)) {
                if (twoTime - oneTime > Toast.LENGTH_SHORT) {
                    try {
                        toast.show();
                    } catch (Exception e) {

                    }
                }
            } else {
                oldMsg = msg;
                toast.setText(msg);
                try {
                    toast.show();
                } catch (Exception e) {

                }
            }
        }
        oneTime = twoTime;
    }

    public static void showCenter(CharSequence msg, int duration) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }

        View mView = View.inflate(BaseApplication.getInstance(), R.layout.toast_view, null);
        TextView tv_toast = mView.findViewById(R.id.tv_toast);
        ViewBgUtils.setBg(tv_toast, "#333333", 3);
        tv_toast.setText(msg);
        ToastUtils.setView(mView);
        ToastUtils.setGravity(Gravity.CENTER, 0, 0);
        ToastUtils.show(msg);

//        if (toast == null) {
//            toast = Toast.makeText(BaseApplication.getInstance(), msg, duration);
//            mView = View.inflate(BaseApplication.getInstance(), R.layout.toast_view, null);
//            tv_toast = (TextView) mView.findViewById(R.id.tv_toast);
//
//            ViewBgUtils.setBg(tv_toast, "#333333", 3);
//
//            toast.setView(mView);
//            tv_toast.setText(msg);
//            toast.setGravity(Gravity.CENTER, 0, 0);
//            try {
//                toast.show();
//            } catch (Exception e) {
//
//            }
//            oneTime = System.currentTimeMillis();
//        } else {
//            toast.setGravity(Gravity.CENTER, 0, 0);
////            toast.setText(msg);
//            tv_toast.setText(msg);
//            twoTime = System.currentTimeMillis();
//            if (TextUtils.equals(msg, oldMsg)) {
//                if (twoTime - oneTime > Toast.LENGTH_SHORT) {
//                    try {
//                        toast.show();
//                    } catch (Exception e) {
//
//                    }
//                }
//            } else {
//                oldMsg = msg;
////                toast.setText(msg);
//                tv_toast.setText(msg);
//                try {
//                    toast.show();
//                } catch (Exception e) {
//
//                }
//            }
//        }
//        oneTime = twoTime;
    }

    public static void show(CharSequence msg) {
//        show(msg, Toast.LENGTH_SHORT);
        showCenter(msg);
    }

    public static void showCenter(CharSequence msg) {
        showCenter(msg, Toast.LENGTH_SHORT);
    }

    public static void show(@StringRes int stringId) {
        showCenter(stringId);
//        show(BaseApplication.getInstance().getString(stringId), Toast.LENGTH_SHORT);
    }

    public static void showCenter(@StringRes int stringId) {
        showCenter(BaseApplication.getInstance().getString(stringId), Toast.LENGTH_SHORT);
    }

    public static void show(@StringRes int stringId, int duration) {
        show(BaseApplication.getInstance().getString(stringId), duration);
    }

    public static void showCenter(@StringRes int stringId, int duration) {
        showCenter(BaseApplication.getInstance().getString(stringId), duration);
    }

}
