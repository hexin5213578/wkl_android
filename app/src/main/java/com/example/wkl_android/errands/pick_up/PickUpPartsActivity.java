package com.example.wkl_android.errands.pick_up;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.poi.PoiSortType;
import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.charge.detail.popup.PaySelectorPopup;
import com.example.wkl_android.errands.help_buy.popup.PayDetailPopup;
import com.example.wkl_android.errands.help_buy.popup.TimePopup;
import com.example.wkl_android.errands.order.ErrandsOrderActivity;
import com.example.wkl_android.errands.pick_up.adapter.PickUpGoodsAdapter;
import com.example.wkl_android.errands.pick_up.bean.PickUpBean;
import com.example.wkl_android.main.shop.add_shop.ui.popup.WheelViewPopup;
import com.example.wkl_android.main.shop.address.add.ui.activity.AddAddressActivity;
import com.example.wkl_android.main.shop.address.location.LocationAddressActivity;
import com.example.wkl_android.main.shop.address.main.ui.activity.AddressActivity;
import com.example.wkl_android.order.confirm.popup.PayTypePopup;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindColor;
import butterknife.BindView;

/**
 * 取送件
 */
public class PickUpPartsActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.ivLeft)
    View back;
    @BindView(R.id.tvTitle)
    TextView title;
    @BindView(R.id.rvGoodsCategory)
    RecyclerView rvGoodsCategory;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.llTime)
    View llTime;
    @BindView(R.id.tvToAddress)
    View tvToAddress;
    @BindView(R.id.tvToAddress1)
    View tvToAddress1;
    @BindView(R.id.llAddAddress)
    View llAddAddress;
    @BindView(R.id.llWeight)
    View llWeight;
    @BindView(R.id.tvWeight)
    TextView tvWeight;
    @BindView(R.id.mapView)
    MapView mapView;
    @BindView(R.id.tvPayDetail)
    View tvPayDetail;
    @BindView(R.id.tvSubmit)
    View tvSubmit;
    @BindView(R.id.llToLocationAddress)
    View llToLocationAddress;
    @BindView(R.id.tvAddress)
    TextView tvAddress;
    @BindView(R.id.etGoods)
    EditText etGoods;
    @BindView(R.id.tvNumber)
    TextView tvNumber;
    private TimePopup popup;
    private List<String> dateList;
    private List<String> timeList;
    private WheelViewPopup weightPopup;
    private List<String> weightList;
    private List<String> minuteList;
    private List<PickUpBean> list;
    private BaiduMap baiduMap;
    //防止每次定位都重新设置中心点和marker
    private boolean isFirstLocation = true;
    //初始化LocationClient定位类
    //BDAbstractLocationListener为7.2版本新增的Abstract类型的监听接口，原有BDLocationListener接口
    private BDAbstractLocationListener myListener = new MyLocationListener();
    private LocationClient mLocationClient;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_pick_up_parts;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViews() {
        title.setText("取送件");
        back.setOnClickListener(this);
        llTime.setOnClickListener(this);
        tvToAddress.setOnClickListener(this);
        llAddAddress.setOnClickListener(this);
        llWeight.setOnClickListener(this);
        tvToAddress1.setOnClickListener(this);
        tvPayDetail.setOnClickListener(this);
        tvSubmit.setOnClickListener(this);
        llToLocationAddress.setOnClickListener(this);
        tvAddress.setOnClickListener(this);
        minuteList = new ArrayList<>();
        dateList = new ArrayList<>();
        timeList = new ArrayList<>();
        weightList = new ArrayList<>();
        list = new ArrayList<>();
        weightPopup = new WheelViewPopup(this, "选择重量");
        initLocation();
        initData();
        initList();
        PickUpGoodsAdapter adapter = new PickUpGoodsAdapter(this, list);
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
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s == null) {
                    return;
                }
                tvNumber.setText(String.format(Locale.getDefault(), "%d/100", s.length()));
            }
        });
    }

    private void initLocation() {
        baiduMap = mapView.getMap();
        // 开启定位图层
        baiduMap.setMyLocationEnabled(true);
        //声明LocationClient类
        mLocationClient = new LocationClient(this);
        //注册监听函数
        mLocationClient.registerLocationListener(myListener);
        //开始定位
        mLocationClient.start();
    }

    private void initData() {
        LocationClientOption option = new LocationClientOption();
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认gcj02，设置返回的定位结果坐标系
        option.setCoorType("bd09ll");
        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        int span = 5000;
        option.setScanSpan(span);
        //可选，设置是否需要地址信息，默认不需要
        option.setIsNeedAddress(true);
        //可选，默认false,设置是否使用gps
        option.setOpenGps(true);
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setLocationNotify(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationDescribe(true);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIsNeedLocationPoiList(true);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.setIgnoreKillProcess(false);
        //可选，默认false，设置是否收集CRASH信息，默认收集
        option.SetIgnoreCacheException(false);
        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        option.setEnableSimulateGps(false);
        mLocationClient.setLocOption(option);
    }

    /**
     * 实现定位监听 位置一旦有所改变就会调用这个方法
     * 可以在这个方法里面获取到定位之后获取到的一系列数据
     *
     * registerLocationListener解决注册监听方法过期:extends BDAbstractLocationListener
     */
    public class MyLocationListener extends BDAbstractLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            //这个判断是为了防止每次定位都重新设置中心点和marker
            if (isFirstLocation) {
                isFirstLocation = false;
                //设置并显示中心点
                setPosition2Center(baiduMap, location, true);
            }
        }
    }

    /**
     * 设置中心点和添加marker
     *
     * @param map
     * @param bdLocation
     * @param isShowLoc
     */
    public void setPosition2Center(BaiduMap map, BDLocation bdLocation, Boolean isShowLoc) {
        MyLocationData locData = new MyLocationData.Builder()
                .accuracy(bdLocation.getRadius())
                .direction(bdLocation.getRadius()).latitude(bdLocation.getLatitude())
                .longitude(bdLocation.getLongitude()).build();
        map.setMyLocationData(locData);

        if (isShowLoc) {
            LatLng ll = new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude());
            MapStatus.Builder builder = new MapStatus.Builder();
            builder.target(ll).zoom(18.0f);
            map.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
        }
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
        for (int i = 1; i < 21; i++) {
            weightList.add(i + "kg");
        }
        weightPopup.setList(weightList);
        list.add(new PickUpBean("美食", R.mipmap.item_food));
        list.add(new PickUpBean("服饰", R.mipmap.item_clothes));
        list.add(new PickUpBean("证件", R.mipmap.item_card));
        list.add(new PickUpBean("报告", R.mipmap.item_report));
        list.add(new PickUpBean("文件", R.mipmap.item_file));
        list.add(new PickUpBean("蛋糕", R.mipmap.item_cake));
        list.add(new PickUpBean("钥匙", R.mipmap.item_key));
        list.add(new PickUpBean("鲜花", R.mipmap.item_flower));
        list.add(new PickUpBean("烟酒", R.mipmap.item_wine));
        list.add(new PickUpBean("其它", R.mipmap.item_other));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        // 退出时销毁定位
        mLocationClient.unRegisterLocationListener(myListener);
        mLocationClient.stop();
        // 关闭定位图层
        baiduMap.setMyLocationEnabled(false);
        mapView.onDestroy();
        mapView = null;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.llTime:
                showPopup();
                break;
            case R.id.tvToAddress1:
            case R.id.tvToAddress:
                startActivity(new Intent(APP, AddressActivity.class));
                break;
            case R.id.llAddAddress:
                startActivity(new Intent(APP, AddAddressActivity.class)
                        .putExtra("isErrands", true));
                break;
            case R.id.llWeight:
                showWeightPopup();
                break;
            case R.id.tvPayDetail:
                PayDetailPopup popup = new PayDetailPopup(this);
                popup.show(getWindow().getDecorView());
                break;
            case R.id.tvSubmit:
                showPayPopup();
                break;
            //选择取件地址
            case R.id.tvAddress:
            case R.id.llToLocationAddress:
                startActivityForResult(new Intent(APP, LocationAddressActivity.class), 100);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RESULT_OK == resultCode) {
            if (requestCode == 100) {
                if (data == null) {
                    return;
                }
                String name = data.getStringExtra("name");
                llToLocationAddress.setVisibility(View.GONE);
                tvAddress.setVisibility(View.VISIBLE);
                tvAddress.setText(name);
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


    private void showWeightPopup() {
        weightPopup.show(getWindow().getDecorView());
        weightPopup.setListener(new WheelViewPopup.SetCategoryListener() {
            @Override
            public void setText(int position) {
                tvWeight.setText(weightList.get(position));
            }
        });
    }

    private void showPopup() {
        popup.show(getWindow().getDecorView());
        popup.setListener((datePosition, timePosition, minutePosition) ->
                tvTime.setText(String.format("%s%s%s", dateList.get(datePosition),
                        timeList.get(timePosition), minuteList.get(minutePosition))));
    }
}
