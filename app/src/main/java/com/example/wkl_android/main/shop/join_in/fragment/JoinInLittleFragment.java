package com.example.wkl_android.main.shop.join_in.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseFragment;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.main.shop.address.select.SelectSiteActivity;

import butterknife.BindView;

/**
 * 小区合作
 */
public class JoinInLittleFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.tvAddress)
    TextView tvAddress;
    private String site1, site2, site3;
    @BindView(R.id.etLittle)
    EditText etLittle;

    public static JoinInLittleFragment newInstance() {

        Bundle args = new Bundle();

        JoinInLittleFragment fragment = new JoinInLittleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_join_in_little;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViews() {
        tvAddress.setOnClickListener(this);
    }

    /**
     * 设置地区
     *
     * @param sites 地区id
     * @param name  地区名
     */
    private void setLocation(String sites, String name) {
        if (!TextUtils.isEmpty(sites)) {
            String[] split = sites.split(",");
            if (split.length == 4) {
                for (String s : split) {
                    if (s != null) {
                        site1 = split[1];
                        site2 = split[2];
                        site3 = split[3];
                    }
                }
            } else {
                for (String s : split) {
                    if (s != null) {
                        site1 = split[1];
                        site2 = split[2];
                    }
                }
            }
            if (!TextUtils.isEmpty(name))
                tvAddress.setText(name.substring(5));
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvAddress:
                startActivityForResult(new Intent(APP, SelectSiteActivity.class)
                        .putExtra("pId", "0"), 1);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (Activity.RESULT_OK ==resultCode) {
            if (data == null) {
                return;
            }
            String sites = data.getStringExtra("sites");
            String name = data.getStringExtra("name");
            setLocation(sites, name);
        }

    }
}
