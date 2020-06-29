package com.example.wkl_android.charge.shop;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.charge.shop.adapter.ChargeAdapter;
import com.example.wkl_android.shop_street.main.SelectorPopup;
import com.example.wkl_android.shop_street.shop_detail.ui.activity.ShopMessageActivity;
import com.example.wkl_android.shop_street.shop_home.ui.activity.ShopHomeActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 活动店铺
 */
public class ChargeShopActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.ivLeft)
    View back;
    @BindView(R.id.tvTitle)
    TextView title;
    @BindView(R.id.rvStreet)
    RecyclerView rvStreet;
    @BindView(R.id.tvItem1)
    TextView tvItem1;
    @BindView(R.id.tvItem2)
    TextView tvItem2;
    @BindView(R.id.tvItem3)
    TextView tvItem3;
    @BindView(R.id.llShowPopup)
    View llShowPopup;
    private SelectorPopup popup;
    private int clickFlag;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_charge_shop;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViews() {
        title.setText("活动店铺");
        back.setOnClickListener(this);

        tvItem1.setOnClickListener(this);
        tvItem2.setOnClickListener(this);
        tvItem3.setOnClickListener(this);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("");
        }
        ChargeAdapter adapter = new ChargeAdapter(this, list);
        rvStreet.setAdapter(adapter);
        adapter.setListener(v -> startActivity(new Intent(APP, ShopMessageActivity.class)));

        List<String> parentList = new ArrayList<>();
        List<String> childList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            parentList.add("父布局Item" + i);
            childList.add("子布局Item" + i);
        }
        popup = new SelectorPopup(this, parentList, childList);
        popup.setListener(text -> {
            switch (clickFlag) {
                case 1:
                    tvItem1.setText(text);
                    break;
                case 2:
                    tvItem2.setText(text);
                    break;
                case 3:
                    tvItem3.setText(text);
                    break;
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvItem1:
                clickFlag = 1;
                popup.showAsDropDown(llShowPopup, 0, 0);
                break;
            case R.id.tvItem2:
                clickFlag = 2;
                popup.showAsDropDown(llShowPopup, 0, 0);
                break;
            case R.id.tvItem3:
                clickFlag = 3;
                popup.showAsDropDown(llShowPopup, 0, 0);
                break;
        }
    }
}
