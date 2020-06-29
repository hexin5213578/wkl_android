package com.example.wkl_android.main.shop.address.remake;

import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.main.shop.address.main.model.bean.Address;
import com.example.wkl_android.main.shop.address.remake.presenter.RemakePresenter;
import com.example.wkl_android.main.shop.address.remake.ui.IRemakeView;
import com.example.wkl_android.main.shop.address.select.SelectSiteActivity;

import butterknife.BindColor;
import butterknife.BindView;

/**
 * 编辑收货地址
 * Created by szx
 * on 2020/1/6/006
 */
public class RemakeAddressActivity extends BaseActivity<IRemakeView, RemakePresenter>
        implements IRemakeView, View.OnClickListener {
    @BindView(R.id.tvTitle)
    TextView title;
    @BindView(R.id.ivLeft)
    View back;
    @BindView(R.id.tvRight)
    TextView right;
    @BindColor(R.color.theme)
    int font_theme;
    @BindColor(R.color.font_black_9)
    int font_black_9;
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.etPhone)
    EditText etPhone;
    @BindView(R.id.tvAddress)
    TextView tvAddress;
    @BindView(R.id.etDetail)
    EditText etDetail;
    @BindView(R.id.switch_address)
    Switch switch_address;
    @BindView(R.id.tvSubmit)
    View tvSubmit;
    @BindView(R.id.llAddress)
    View llAddress;

    private String id;
    private String site1, site2, site3;
    private String siteId1, siteId2, siteId3;
    private boolean swichChecked;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_remake_address;
    }

    @Override
    protected RemakePresenter createPresenter() {
        return new RemakePresenter();
    }

    @Override
    protected void initViews() {
        Intent intent = getIntent();
        if (intent != null) {
            id = intent.getStringExtra("id");
            Address bean = intent.getParcelableExtra("addressBean");
            Address.Site1Bean site1Bean = intent.getParcelableExtra("site1");
            Address.Site2Bean site2Bean = intent.getParcelableExtra("site2");
            Address.Site3Bean site3Bean = intent.getParcelableExtra("site3");
            if (site1Bean != null) {
                site1 = site1Bean.getName();
                siteId1 = site1Bean.getId();
            }
            if (site2Bean != null) {
                site2 = site2Bean.getName();
                siteId2 = site2Bean.getId();

            }
            if (site3Bean != null) {
                site3 = site3Bean.getName();
                siteId3 = site3Bean.getId();
            }
            if (bean != null) {
                etName.setText(bean.getConsignee());
                etPhone.setText(bean.getPhoneNumber());
                tvAddress.setText(String.format("%s %s %s", site1, site2, site3));
                etDetail.setText(bean.getDetailedAddress());
                swichChecked = bean.isIsDefault();
                switch_address.setChecked(swichChecked);
            }
            String sites = intent.getStringExtra("sites");
            String name = intent.getStringExtra("name");
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
        back.setOnClickListener(this);
        title.setText("编辑收货地址");
        right.setVisibility(View.VISIBLE);
        right.setText("删除");
        right.setTextColor(font_theme);
        right.setOnClickListener(this);
        tvSubmit.setOnClickListener(this);
        llAddress.setOnClickListener(this);

        switch_address.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                swichChecked = isChecked;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvRight:
                showConfirmDialog();
                break;
            case R.id.tvSubmit:
                submit();
                break;
            case R.id.llAddress:
                startActivity(new Intent(APP, SelectSiteActivity.class)
                        .putExtra("pId", "0"));
                finish();
                break;
        }
    }

    /**
     * 提交编辑信息
     */
    private void submit() {
        String name = etName.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            toast("请填写收货人");
            return;
        }
        String phone = etPhone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            toast("请填写手机号");
            return;
        }
        if (!Common.checkIsPhone(phone)) {
            toast("请填写正确格式的手机号");
            return;
        }
        String detail = etDetail.getText().toString().trim();
        if (TextUtils.isEmpty(detail)) {
            toast("请填写详细信息");
            return;
        }
        if (TextUtils.isEmpty(site3)) {
            toast("请选择地区");
            return;
        }

        presenter.save(name, phone, detail, siteId1, siteId2, siteId3, swichChecked, id);
    }

    /**
     * 展示确认删除弹框
     */
    private void showConfirmDialog() {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage("确认删除地址?")
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        presenter.deleteAddress(id);
                    }
                })
                .create();
        dialog.show();
        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(font_theme);
        dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(font_black_9);

    }

    /**
     * 删除成功返回处理
     *
     * @param message 返回信息
     */
    @Override
    public void handleDelete(String message) {
        toast(message);
        finish();
    }

    @Override
    public void handleSaveSuccess() {
        toast("保存成功");
        finish();
    }
}
