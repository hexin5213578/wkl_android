package com.example.wkl_android.dialog;

import android.view.View;

import androidx.fragment.app.FragmentManager;

import com.example.wkl_android.base.Constant;

public class DialogUtils {
    /**
     * 通用提示对话框
     *
     * @param manager
     * @param title           标题
     * @param tip             提示文案
     * @param cancelDesc      取消按钮文案
     * @param cancelListener  取消按钮监听
     * @param confirmDesc     确定按钮文案
     * @param confirmListener 确定操作监听
     * @return
     */
    public static CommDialog showCommDialog(FragmentManager manager, String title, String tip, String cancelDesc, View.OnClickListener cancelListener, String confirmDesc, View.OnClickListener confirmListener) {
        CommDialog dialog = new CommDialog();
        dialog.setTitle(title);
        dialog.setTip(tip);
        dialog.setCancelDesc(cancelDesc);
        dialog.setCancelListener(cancelListener);
        dialog.setConfirmDesc(confirmDesc);
        dialog.setConfirmListener(confirmListener);
        try {
            dialog.show(manager, Constant.FRAGMENT_TAG.DIALOG_COMM);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dialog;
    }
}
