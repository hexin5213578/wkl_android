package com.example.wkl_android.main.shop.address.add.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.baidu.mapapi.model.LatLng;
import com.example.wkl_android.R;
import com.example.wkl_android.address.MapActivity;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.main.shop.address.add.model.bean.SaveAddressBean;
import com.example.wkl_android.main.shop.address.add.presenter.AddAddressPresenter;
import com.example.wkl_android.main.shop.address.add.ui.IAddView;
import com.example.wkl_android.main.shop.address.main.model.bean.Address;

import butterknife.BindColor;
import butterknife.BindView;

/**
 * 添加订单地址
 */
public class AddOrderAddressActivity extends BaseActivity<IAddView, AddAddressPresenter>
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

    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.etPhone)
    EditText etPhone;
    @BindView(R.id.etDetail)
    EditText etDetail;
    @BindView(R.id.switch_address)
    CheckBox switch_address;

    @BindView(R.id.tv_loc)
    TextView tv_loc;
    private boolean swichChecked;
    double lat;
    double lng;
    private Address data;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_add_order_address;
    }

    @Override
    protected AddAddressPresenter createPresenter() {
        return new AddAddressPresenter();
    }

    @Override
    protected void initViews() {
        setTitleBarWithe();
        back.setOnClickListener(this);
        findViewById(R.id.submit).setOnClickListener(this);

        llSelectAddress.setOnClickListener(this);
        switch_address.setOnCheckedChangeListener((buttonView, isChecked) -> swichChecked = isChecked);
        if (getIntent().hasExtra("data")) {
            data = getIntent().getParcelableExtra("data");
        }

        if (data != null) {
            etName.setText(data.getConsignee());
            etPhone.setText(data.getPhoneNumber());
            etDetail.setText(data.getAddressArea());
            tv_loc.setText(data.getAddressReDetail());
            switch_address.setChecked(data.isDefault());
            String[] split = data.getAddressDetail().split(",");
            lat = Double.valueOf(split[0]);
            lng = Double.valueOf(split[1]);
            title.setText("编辑地址");
        } else {
            title.setText("添加地址");
        }


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.submit:
                save();
                break;
            case R.id.llSelectAddress:
                //跳转选择地区
              /*  Intent intent = new Intent(APP, SelectSiteActivity.class).putExtra("pId", "0");
                startActivityForResult(intent, 100);*/
                Intent intent = new Intent(APP, MapActivity.class);
                intent.putExtra("lat",lat);
                intent.putExtra("lng",lng);
                startActivityForResult(intent, 10);
                break;
            default:
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RESULT_OK == resultCode && requestCode == 100) {
            if (data == null) {
                return;
            }
            String sites = data.getStringExtra("sites");
            String name = data.getStringExtra("name");
            //  setLocation(sites, name);
        } else if (RESULT_OK == resultCode && requestCode == 10) {

            LatLng latLng = data.getParcelableExtra("data");
            String name = data.getStringExtra("name");
            String addressStr = data.getStringExtra("address");
//            etDetail.setText(addressStr);
            lat = Double.valueOf(latLng.latitude);
            lng = Double.valueOf(latLng.longitude);
            Log.d("wfs",lat+","+lng+"_"+name);
            tv_loc.setText(name);
        }

    }


    /**
     * 保存
     */
    private void save() {
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
            toast("请填写详细地址");
            return;
        }
        String selectAddress = tv_loc.getText().toString().trim();
        if (lat == 0 || TextUtils.isEmpty(selectAddress)) {
            toast("请选择地区");
            return;
        }
        SaveAddressBean bean = new SaveAddressBean();
        bean.setConsignee(name);
        bean.setPhoneNumber(phone);
        bean.setAddressReDetail(selectAddress);
        bean.setDefault(swichChecked);
        bean.setAddressDetail(lat + "," + lng);
        bean.setAddressArea(detail);
        if (data != null) {
            bean.setAddressId(data.getAddressId());
        }
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

    public static void toThisActivity(Context context, Address data) {
        Intent intent = new Intent(context, AddOrderAddressActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("data", data);
        context.startActivity(intent);
    }

}
