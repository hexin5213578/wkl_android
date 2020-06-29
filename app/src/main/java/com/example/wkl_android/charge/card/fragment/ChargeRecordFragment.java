package com.example.wkl_android.charge.card.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseFragment;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.charge.card.ChargeCardAdapter;
import com.example.wkl_android.charge.record.adapter.ChargeRecordAdapter;
import com.example.wkl_android.shop_street.shop_detail.ui.adapter.ShopImgAdapter;
import com.example.wkl_android.shop_street.shop_home.ui.activity.ShopHomeActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindColor;
import butterknife.BindView;

public class ChargeRecordFragment extends BaseFragment {
    @BindView(R.id.rvChargeRecord)
    RecyclerView rvChargeRecord;
    @BindColor(R.color.font_black_9)
    int font_black_9;
    @BindColor(R.color.theme)
    int font_theme;

    public static ChargeRecordFragment newInstance() {

        Bundle args = new Bundle();

        ChargeRecordFragment fragment = new ChargeRecordFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_charge_record;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViews() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("");
        }
        ChargeCardAdapter adapter = new ChargeCardAdapter(activity, list);
        rvChargeRecord.setAdapter(adapter);
        adapter.setListener(new ChargeCardAdapter.OnClickListener() {
            @Override
            public void onRefund() {
                showRefundDialog();
            }

            @Override
            public void onToShop() {
                startActivity(new Intent(APP, ShopHomeActivity.class));
            }
        });
    }

    private void showRefundDialog() {
        AlertDialog dialog = new AlertDialog.Builder(activity)
                .setTitle("提示")
                .setMessage("此操作不可恢复，请确定后再点击")
                .setPositiveButton("确定", (dialogInterface, i) -> {
                    toast("退款成功");
                })
                .setNegativeButton("取消", null)
                .create();
        dialog.show();
        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(font_black_9);
        dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(font_theme);

    }
}
