package com.example.wkl_android.main.shop.address.add.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.model.LatLng;
import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.main.shop.address.add.model.bean.SaveAddressBean;
import com.example.wkl_android.main.shop.address.add.presenter.AddAddressPresenter;
import com.example.wkl_android.main.shop.address.add.ui.IAddView;
import com.example.wkl_android.main.shop.address.location.LocationAddressActivity;

import butterknife.BindColor;
import butterknife.BindView;

/**
 * 添加新地址
 * Created by szx
 * on 2019/12/31
 */
public class AddAddressActivity extends BaseActivity<IAddView, AddAddressPresenter>
        implements IAddView, View.OnClickListener {
    @BindView(R.id.tvTitle)
    TextView title;
    @BindView(R.id.ivLeft)
    View back;
    @BindView(R.id.tvRight)
    TextView right;
    @BindColor(R.color.theme)
    int font_theme;
    @BindView(R.id.llSelectAddress)
    View llSelectAddress;
    @BindView(R.id.tvAddress)
    TextView tvAddress;
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.etPhone)
    EditText etPhone;
    @BindView(R.id.etDetail)
    EditText etDetail;
    @BindView(R.id.switch_address)
    Switch switch_address;
    private boolean swichChecked;
    private String site1, site2, site3;
    private double latitude;
    private double longitude;
    private String name;
    LatLng latLng;
    @Override
    public int getLayoutRes() {
        return R.layout.activity_add_address;
    }

    @Override
    protected AddAddressPresenter createPresenter() {
        return new AddAddressPresenter();
    }

    @Override
    protected void initViews() {
        Intent intent = getIntent();
        if (intent != null) {
            boolean isErrands = intent.getBooleanExtra("isErrands", false);
            if (isErrands) {
                title.setText("添加送达地址");
            } else {
                title.setText("添加收货地址");
            }
        }
        back.setOnClickListener(this);
        right.setVisibility(View.VISIBLE);
        right.setText("保存");
        right.setTextColor(font_theme);
        right.setOnClickListener(this);

        llSelectAddress.setOnClickListener(this);
        switch_address.setOnCheckedChangeListener((buttonView, isChecked) -> swichChecked = isChecked);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvRight:
                Intent intent = new Intent();
                intent.putExtra("name", name);
                intent.putExtra("latitude", latitude);
                intent.putExtra("longitude", longitude);
                setResult(RESULT_OK, intent);
                finish();
                //save();
                break;
            case R.id.llSelectAddress:
                //跳转选择地区
                startActivityForResult(new Intent(APP, LocationAddressActivity.class), 1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RESULT_OK == resultCode) {
            if (data == null) {
                return;
            }
            name = data.getStringExtra("name");
            etDetail.setText(name);
            latitude = data.getDoubleExtra("latitude", 0);
            longitude = data.getDoubleExtra("longitude", 0);
            String province = data.getStringExtra("province");
            String city = data.getStringExtra("city");
            String district = data.getStringExtra("district");
            tvAddress.setText(String.format("%s%s%s", province, city, district));
        }

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

    /**
     * 保存
     */
    private void save() {
        SaveAddressBean bean=new SaveAddressBean();
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
        if (TextUtils.isEmpty(site2)) {
            toast("请选择地区");
            return;
        }
        bean.setConsignee(name);
        bean.setPhoneNumber(phone);
        bean.setDefault(swichChecked);
        bean.setAddressDetail(detail);
        presenter.save(bean);
    }

    /**
     * 保存成功处理
     */
    @Override
    public void handleSaveSuccess(String msg) {
        toast("保存成功");
        finish();
    }
}
