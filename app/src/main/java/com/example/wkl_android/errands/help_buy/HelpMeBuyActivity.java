package com.example.wkl_android.errands.help_buy;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.DistanceUtil;
import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.errands.help_buy.adapter.GoodsCategoryAdapter;
import com.example.wkl_android.errands.help_buy.bean.SelectBean;
import com.example.wkl_android.errands.help_buy.popup.PayDetailPopup;
import com.example.wkl_android.errands.help_buy.popup.TimePopup;
import com.example.wkl_android.errands.order.ErrandsOrderActivity;
import com.example.wkl_android.errands.rules.HelpMeBuyRulesActivity;
import com.example.wkl_android.main.shop.address.add.ui.activity.AddAddressActivity;
import com.example.wkl_android.main.shop.address.location.LocationAddressActivity;
import com.example.wkl_android.main.shop.address.main.ui.activity.AddressActivity;
import com.example.wkl_android.order.confirm.popup.PayTypePopup;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindColor;
import butterknife.BindView;

/**
 * 帮我买
 */
public class HelpMeBuyActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.ivLeft)
    View back;
    @BindView(R.id.tvTitle)
    TextView title;
    @BindView(R.id.tvRight)
    TextView right;
    @BindView(R.id.rvGoodsCategory)
    RecyclerView rvGoodsCategory;
    //指定地点购买
    @BindView(R.id.tvDestination)
    TextView tvDestination;
    //就近购买
    @BindView(R.id.tvNearby)
    TextView tvNearby;
    @BindView(R.id.tvAddress)
    TextView tvAddress;
    @BindView(R.id.llTime)
    View llTime;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.tvToAddress)
    View tvToAddress;
    @BindView(R.id.tvAddress1)
    TextView tvAddress1;
    @BindView(R.id.llAddAddress)
    View llReceiveAddress;
    @BindView(R.id.etGoods)
    EditText etGoods;
    @BindView(R.id.tvNumber)
    TextView tvNumber;
    @BindView(R.id.tvPayDetail)
    View tvPayDetail;
    @BindView(R.id.tvSubmit)
    View tvSubmit;
    @BindView(R.id.llToLocationAddress)
    View llToLocationAddress;
    @BindView(R.id.tvDistance)
    TextView tvDistance;
    @BindColor(R.color.theme)
    int font_theme;
    @BindColor(R.color.font_black_6)
    int font_black_6;
    private TimePopup popup;
    private List<String> dateList;
    private List<String> timeList;
    private List<String> minuteList;
    private int flag;//0指定地点购买，可点击 1就近3公里购买
    private LatLng location1, location2;
    private DecimalFormat format = new DecimalFormat("0.00");
    private List<SelectBean> list;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_help_me_buy;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViews() {
        title.setText("帮我买");
        minuteList = new ArrayList<>();
        dateList = new ArrayList<>();
        timeList = new ArrayList<>();
        list = new ArrayList<>();
        back.setOnClickListener(this);
        right.setVisibility(View.VISIBLE);
        right.setText("规则");
        right.setOnClickListener(this);
        tvDestination.setOnClickListener(this);
        tvNearby.setOnClickListener(this);
        llTime.setOnClickListener(this);
        tvToAddress.setOnClickListener(this);
        llReceiveAddress.setOnClickListener(this);
        tvPayDetail.setOnClickListener(this);
        tvAddress.setOnClickListener(this);
        tvSubmit.setOnClickListener(this);
        llToLocationAddress.setOnClickListener(this);
        tvAddress1.setOnClickListener(this);
        initList();
        GoodsCategoryAdapter adapter = new GoodsCategoryAdapter(this, list);
        rvGoodsCategory.setAdapter(adapter);
        adapter.setListener(position -> {
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setSelect(false);
            }
            list.get(position).setSelect(true);
            adapter.notifyDataSetChanged();
        });
        etGoods.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s != null) {
                    tvNumber.setText(String.format(Locale.getDefault(), "%d/100", s.length()));
                }
            }
        });
    }

    private void initList() {
        dateList.add("今天");
        dateList.add("明天");
        dateList.add("后天");
        timeList.add("尽快送达");
        for (int i = 0; i < 25; i++) {
            timeList.add(i + "点");
        }
        for (int i = 0; i < 60; i++) {
            minuteList.add(i + "分");
        }
        popup = new TimePopup(this, dateList, timeList, minuteList);

        list.add(new SelectBean("小吃", "炸鸡鸭脖", false));
        list.add(new SelectBean("早餐", "豆浆油条", false));
        list.add(new SelectBean("酒水", "啤酒白酒", false));
        list.add(new SelectBean("药品", "退烧止疼", false));
        list.add(new SelectBean("水果", "苹果西瓜", false));
        list.add(new SelectBean("日用", "网线U盘", false));
        list.add(new SelectBean("香烟", "兰州云烟", false));
        list.add(new SelectBean("其它", "其它商品", false));
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvRight:
                startActivity(new Intent(APP, HelpMeBuyRulesActivity.class));
                break;
            case R.id.tvDestination:
                //指定地点购买
                flag = 0;
                tvDestination.setTextColor(font_theme);
                tvNearby.setTextColor(font_black_6);
                llToLocationAddress.setVisibility(View.VISIBLE);
                tvAddress.setVisibility(View.GONE);
                break;
            case R.id.tvNearby:
                //就近购买
                flag = 1;
                tvDestination.setTextColor(font_black_6);
                tvNearby.setTextColor(font_theme);
                llToLocationAddress.setVisibility(View.GONE);
                tvAddress.setVisibility(View.VISIBLE);
                tvAddress.setText("配送员就近3公里购买");
                break;
            case R.id.llTime:
                showPopup();
                break;
            case R.id.tvToAddress:
                startActivity(new Intent(APP, AddressActivity.class));
                break;
            //选择送达地址
            case R.id.tvAddress1:
            case R.id.llAddAddress:
                startActivityForResult(new Intent(APP, AddAddressActivity.class)
                        .putExtra("isErrands", true), 200);
                break;
            case R.id.tvPayDetail:
                showDetailPopup();
                break;
            case R.id.tvSubmit:
                showPayPopup();
                break;
            //选择取件地址
            case R.id.llToLocationAddress:
                startActivityForResult(new Intent(APP, LocationAddressActivity.class), 100);
                break;
            //选择取件地址
            case R.id.tvAddress:
                if (flag == 0) {
                    startActivityForResult(new Intent(APP, LocationAddressActivity.class), 100);
                }
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
            switch (requestCode) {
                case 100:
                    String name = data.getStringExtra("name");
                    double latitude = data.getDoubleExtra("latitude", 0);
                    double longitude = data.getDoubleExtra("longitude", 0);
                    location1 = new LatLng(latitude, longitude);
                    llToLocationAddress.setVisibility(View.GONE);
                    tvAddress.setVisibility(View.VISIBLE);
                    tvAddress.setText(name);
                    break;
                case 200:
                    name = data.getStringExtra("name");
                    latitude = data.getDoubleExtra("latitude", 0);
                    longitude = data.getDoubleExtra("longitude", 0);
                    location2 = new LatLng(latitude, longitude);
                    llReceiveAddress.setVisibility(View.GONE);
                    tvAddress1.setVisibility(View.VISIBLE);
                    tvAddress1.setText(name);
                    break;
            }
            //计算两地之间距离
            if (location1 != null && location2 != null) {
                String distance = format.format(DistanceUtil
                        .getDistance(location1, location2) / 1000);
                tvDistance.setText(String.format("%skm", distance));
            }
        }
    }

    private void showPayPopup() {
        PayTypePopup popup = new PayTypePopup(this);
        popup.show(getWindow().getDecorView());
        popup.setListener(() -> {
            toast("支付成功！");
            startActivity(new Intent(APP, ErrandsOrderActivity.class)
                    .putExtra("index", 2));
            finish();
        });
    }

    private void showDetailPopup() {
        PayDetailPopup popup = new PayDetailPopup(this);
        popup.show(getWindow().getDecorView());
    }

    private void showPopup() {
        popup.show(getWindow().getDecorView());
        popup.setListener((datePosition, timePosition, minutePosition) ->
                tvTime.setText(String.format("%s%s%s", dateList.get(datePosition),
                        timeList.get(timePosition), minuteList.get(minutePosition))));
    }
}
