package com.example.wkl_android.main.shop.address.main.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.baidu.mapapi.map.MapView;
import com.example.wkl_android.R;
import com.example.wkl_android.address.MapActivity;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.main.shop.address.add.ui.activity.AddAddressActivity;
import com.example.wkl_android.main.shop.address.add.ui.activity.AddOrderAddressActivity;
import com.example.wkl_android.main.shop.address.main.model.bean.Address;
import com.example.wkl_android.main.shop.address.main.presenter.AddressPresenter;
import com.example.wkl_android.main.shop.address.main.ui.IAddressView;
import com.example.wkl_android.main.shop.address.main.ui.adapter.AddressAdapter;
import com.example.wkl_android.main.shop.join_in.JoinInLocationActivity;
import com.example.wkl_android.widget.rv.decoration.CustomDecoration;
import com.example.wkl_android.widget.rv.widget.EmptyRecyclerView;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 地址管理
 * Created by szx
 * on 2019/12/31/031
 */
public class AddressActivity extends BaseActivity<IAddressView, AddressPresenter> implements IAddressView, View.OnClickListener {
    @BindView(R.id.rvAddress)
    EmptyRecyclerView rvAddress;
    @BindView(R.id.tvTitle)
    TextView title;
    @BindView(R.id.tvRight)
    TextView right;
    @BindView(R.id.ivLeft)
    View back;
    @BindView(R.id.noData)
    View noData;
    @BindView(R.id.add)
    View add;


    private List<Address> allList;
    private AddressAdapter adapter;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_address;
    }

    public static void toThisActivity(Activity context) {
        Intent intent = new Intent(context, AddressActivity.class);
        intent.putExtra("needBack", true);
        context.startActivityForResult(intent, 10);
    }

    @Override
    protected AddressPresenter createPresenter() {
        return new AddressPresenter();
    }

    @Override
    protected void initViews() {
        title.setText("地址管理");
        setTitleBarWithe();

        right.setText("新增地址");
        right.setTextColor(ContextCompat.getColor(this, R.color.color_000000));
        right.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 17);
        add.setOnClickListener(this);
        back.setOnClickListener(this);
        allList = new ArrayList<>();
        adapter = new AddressAdapter(this, allList) {
            @Override
            public void click(Address tag) {
                if (getIntent().getBooleanExtra("needBack", false)) {
                    Intent data = new Intent();
                    data.putExtra("address", tag);
                    setResult(RESULT_OK, data);
                    finish();
                }
            }

            @Override
            public void setDefault(Address tag) {
                presenter.setDefault(tag.getAddressID());
            }

            @Override
            public void delAddress(Address tag) {
                presenter.delAddress(tag.getAddressID());
            }


        };
        rvAddress.setAdapter(adapter);
//        rvAddress.setEmptyView(noData);
        CustomDecoration customDecoration = new CustomDecoration(this,
                CustomDecoration.VERTICAL, R.drawable.shape_ll_divider_gray_10dp);
        rvAddress.addItemDecoration(customDecoration);
    }

    @Override
    protected void onResume() {
        super.onResume();
        allList.clear();
        presenter.getList();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                startActivity(new Intent(APP, AddOrderAddressActivity.class));
                break;
            case R.id.ivLeft:
                finish();
                break;
            default:
        }
    }


    /**
     * 处理地址列表数据
     * @param list 地址列表
     */
    @Override
    public void handleAddressList(List<Address> list) {
//        for(Address address :list){
//            Log.d("wfs",address.);
//        }

        right.setVisibility(list.size() >= 3 ? View.GONE : View.VISIBLE);
        allList.clear();
        allList.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @OnClick(R.id.tvRight)
    public void onRightClick() {
        startActivity(new Intent(APP, AddOrderAddressActivity.class));
    }
}
