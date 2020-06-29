package com.example.wkl_android.errands.order.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseFragment;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.charge.detail.popup.PaySelectorPopup;
import com.example.wkl_android.errands.confirm.ConfirmCollectionActivity;
import com.example.wkl_android.errands.evaluate.ErrandsEvaluateActivity;
import com.example.wkl_android.errands.order.adapter.ErrandsOrderAdapter;
import com.example.wkl_android.order.confirm.popup.PayTypePopup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindColor;
import butterknife.BindView;

public class ErrandsOrderFragment extends BaseFragment {
    @BindView(R.id.rvOrder)
    RecyclerView rvOrder;
    @BindColor(R.color.theme)
    int font_theme;
    @BindColor(R.color.font_black_9)
    int font_black_9;
    private int status;//0全部，1待支付，2待受理，3待收货，4待评价，5退款/撤销

    public static ErrandsOrderFragment newInstance(int status) {

        Bundle args = new Bundle();
        args.putInt("status", status);
        ErrandsOrderFragment fragment = new ErrandsOrderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_errands_order;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViews() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            status = arguments.getInt("status");
        }
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("");
        }
        ErrandsOrderAdapter adapter = new ErrandsOrderAdapter(activity, list, status);
        rvOrder.setAdapter(adapter);
        adapter.setListener(new ErrandsOrderAdapter.OnClickListener() {
            @Override
            public void onClick(int state) {
                switch (state) {
                    case 2:
                        toast("提醒卖家成功");
                        break;
                    case 3://确认收货
                        showConfirmDialog();
                        break;
                    case 4:
                        startActivity(new Intent(APP, ErrandsEvaluateActivity.class));
                        break;
                }
            }

            @Override
            public void pay() {
                showPopup();
            }
        });
    }

    private void showPopup() {
        PayTypePopup popup = new PayTypePopup(activity);
        popup.show(activity.getWindow().getDecorView());
        popup.setListener(new PaySelectorPopup.OnClickListener() {
            @Override
            public void onClick() {
                toast("支付成功");
            }
        });
    }

    /**
     * 展示确认收货弹框
     */
    private void showConfirmDialog() {
        AlertDialog dialog = new AlertDialog.Builder(activity)
                .setTitle("提示")
                .setMessage("点击确认收货钱将即刻到商家账户中,请您确定收货后再点击")
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(APP, ConfirmCollectionActivity.class));
                    }
                })
                .create();
        dialog.show();
        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(font_theme);
        dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(font_black_9);
    }
}
