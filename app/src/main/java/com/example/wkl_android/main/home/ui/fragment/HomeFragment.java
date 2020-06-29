package com.example.wkl_android.main.home.ui.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseFragment;
import com.example.wkl_android.http.callback.impl.JsonCallBack;
import com.example.wkl_android.login.register.ui.adapter.TabAdapter;
import com.example.wkl_android.main.category.model.CategoryModel;
import com.example.wkl_android.main.category.model.bean.Category;
import com.example.wkl_android.main.home.presenter.HomePresenter;
import com.example.wkl_android.main.home.ui.CategoryActivity;
import com.example.wkl_android.main.home.ui.IHomeView;
import com.example.wkl_android.pay.model.PayResult;
import com.example.wkl_android.search.ui.activity.SearchActivity;
import com.example.wkl_android.searchinput.ui.activity.SearchInputActivity;
import com.example.wkl_android.select_city.ui.activity.SelectCityActivity;
import com.example.wkl_android.utils.DisplayUtil;
import com.example.wkl_android.utils.LogUtils;
import com.example.wkl_android.utils.SPUtils;
import com.example.wkl_android.utils.ToastUtil;
import com.example.wkl_android.widget.NoScrollViewPager;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;

import butterknife.BindView;

/**
 * 首页或批发首页
 *
 * @author szx
 */
public class HomeFragment extends BaseFragment<IHomeView, HomePresenter> implements IHomeView, View.OnClickListener {
    @BindView(R.id.tvToSearch) View tvToSearch;
    @BindView(R.id.tvCity) TextView tvCity;
    @BindView(R.id.ll_city) LinearLayout ll_city;
    @BindView(R.id.tabLayout) TabLayout tabLayout;
    @BindView(R.id.viewPager) NoScrollViewPager viewPager;
    @BindView(R.id.ivToCategory) View ivToCategory;
    @BindView(R.id.ll_type) LinearLayout ll_type;
    @BindView(R.id.ivPay) View ivPay;
    @BindView(R.id.tvDistric) TextView tvDistric;


    private SPUtils spUtils;
    private String oldCity ,oldDis;
    private MyLocationListener myListener = new MyLocationListener();
    private LocationClient mLocationClient;
    private String info = "alipay_sdk=alipay-sdk-java-3.7.4.ALL&app_id=2021001141610805&biz_content=%7B%22out_trade_no%22%3A%2277700d84af504a81b483aba1a7d907b8%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22%E6%B4%9B%E5%AE%81%E8%8F%A0%E8%90%9D+5%E6%96%A4%E8%A3%85+%E8%B6%85%E5%AE%9E%E6%83%A0%22%2C%22total_amount%22%3A%220.01%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2F39.100.87.173%3A27001%2FpaymentOk&sign=s%2FNi2%2Bu01FQprh0lHlDa41qMu0zZgK73MIiRkMJ%2FFXB4Ayt2Q6P92MYhQadRoM8KDWQdZVt6dGCSNpQ5KwYh2nQukES3M%2FlQ6wL%2FbGPWOxm0da77QudYQ%2F6m5EONSfe6wir1QSlzqgEXBrPK8bo27x1NEfwLaQANuDxHA9%2BxBWoTp7WG9JiwBWOxWDawl1a%2FoE49rqbkwnX%2BBuhGpYEik247%2FVlx%2B3P5wS1aSDI2SruQEVQnE6M7A8dnmsNpNzCtxcp3xKSI6tlBEBGILqzQZYpuE2K7Tuh8Tz2%2Bv88iArDQSdFAgDyWWMLrHbWQdk5ykOmpPBsu%2Bfaj3IpnHMrQJg%3D%3D&sign_type=RSA2&timestamp=2020-03-11+11%3A26%3A54&version=1.0";
    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;

    private List<BaseFragment> fragments;
    private int pageIndex = 1;
    private TabAdapter adapter;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        LogUtils.d(resultInfo);
                        toast("成功");
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        LogUtils.d(resultInfo);
                        toast("失败");
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter();
    }

    @Override
    protected void initViews() {
        spUtils = SPUtils.getInstance();
        tvCity.setText(oldCity);
        ll_city.setOnClickListener(this);
        tvToSearch.setOnClickListener(this);
        tvCity.setOnClickListener(this);
        ivToCategory.setOnClickListener(this);
        ll_type.setOnClickListener(this);

        ivPay.setOnClickListener(this);
        fragments = new ArrayList<>();
        //加载首页数据

        HomeBlankFragment fragment = HomeBlankFragment.newInstance(0);
        fragment.setTitle("首页");
        fragments.add(fragment);


//        String json = spUtils.getString("home_tab","");
//         if(!TextUtils.isEmpty(json)){
//            showTab(json);
//        }

        new CategoryModel().getFirstCategory(new JsonCallBack() {
            @Override
            public void onSuccess(String json) {
//                String tab = spUtils.getString("home_tab","");

                if (!TextUtils.isEmpty(json)) {
                    showTab(json);
                }
                spUtils.putString("home_tab", json);
            }
        });
        viewPager.setOffscreenPageLimit(15);
        adapter = new TabAdapter(getChildFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        initLocation();
        changeTextColor(tabLayout);
    }


    private void showTab(String json) {


            String innerJson = JSON.parseObject(json).getJSONObject("data").getJSONArray("list").toJSONString();
            Log.d("wfs","首页分类："+innerJson);
            List<Category> list = JSON.parseArray(innerJson,Category.class);

            for (int i = 0; i < list.size(); i++) {
                Log.d("wfs",""+list.get(i).getClassifyName());
                HomeCategoryFragment homeCategoryFragment = HomeCategoryFragment.newInstance();
                homeCategoryFragment.setTitle(list.get(i).getClassifyName());
                fragments.add(homeCategoryFragment);
            }
            adapter.notifyDataSetChanged();
            viewPager.setCurrentItem(0);


    }

    public void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }
    }


    private void changeTextColor(TabLayout tabLayout) {
        try {
            //拿到tabLayout的mTabStrip属性
            Field field = TabLayout.class.getDeclaredField("mTabStrip");
            field.setAccessible(true);
            //拿mTabStrip属性里面的值
            Object mTabStrip = field.get(tabLayout);
            //通过mTabStrip对象来获取class类，不能用field来获取class类，参数不能写Integer.class
            Method method = mTabStrip.getClass().getDeclaredMethod("setSelectedIndicatorColor", int.class);
            method.setAccessible(true);
            method.invoke(mTabStrip, Color.parseColor("#FF0C19D4"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setIsNeedAddress(true);
        mLocationClient = new LocationClient(APP);
        mLocationClient.registerLocationListener(myListener);
        mLocationClient.setLocOption(option);
        initPermission();
    }

    private void showChangeLocationDialog(String newCity , String newDis) {
        AlertDialog dialog = new AlertDialog.Builder(activity)
                .setTitle("定位到您在" + newCity)
                .setMessage("是否切换至该城市进行搜索？")
                .setNegativeButton("取消", null)
                .setPositiveButton("切换", (dialogInterface, i) -> {
                    spUtils.putString("city", newCity);
                    tvCity.setText(newCity);
                    tvDistric.setText(newDis);
                })
                .create();
        dialog.show();
    }

    @Override
    protected void onFragmentResume() {
        super.onFragmentResume();
        spUtils = SPUtils.getInstance();
        oldCity = spUtils.getString("city", "");
        oldDis = spUtils.getString("district", "");
        tvCity.setText(oldCity);
        tvDistric.setText(oldDis);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvToSearch:
                startActivity(new Intent(APP, SearchInputActivity.class));
                break;
            case R.id.ll_city:
            case R.id.tvCity:
                startActivity(new Intent(APP, SelectCityActivity.class));
                break;
            case R.id.ll_type:
            case R.id.ivToCategory:
                startActivity(new Intent(APP, CategoryActivity.class));
                break;
            case R.id.ivPay:
                pay();
                break;
            default:
                break;
        }
    }


    private void pay() {
       /* final String orderInfo = info;   // 订单信息

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(activity);
                Map<String, String> result = alipay.payV2(orderInfo, true);

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();*/

    }


    /**
     * 手动添加权限
     */
    private void initPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int hasPermission = activity.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION);
            //没有授权
            if (hasPermission != PackageManager.PERMISSION_GRANTED) {
                //Fragment中用requestPermissions，不能用ActivityCompat.requestPermissions，否则授权回调不执行
                requestPermissions(
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 200);
            } else {
                mLocationClient.start();
            }
        } else {
            mLocationClient.start();
        }
    }

    /**
     * 权限请求回调
     *
     * @param requestCode  请求码
     * @param permissions  权限列表
     * @param grantResults 权限列表对应的返回值--判断权限是否申请成功
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 200) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {//用户同意权限,执行操作
                mLocationClient.start();//开始定位
            } else {
                toast("未开启定位权限,请手动到设置去开启权限");
            }
        }
    }

//    @Override
//    public void handleCategoryList(List<Category> list) {
//
//        HomeBlankFragment fragment = HomeBlankFragment.newInstance(0);
//        fragment.setTitle("首页");
//        fragments.add(fragment);
//
//        for (int i = 0; i < list.size(); i++) {
//            HomeCategoryFragment homeCategoryFragment = HomeCategoryFragment.newInstance();
//            homeCategoryFragment.setTitle(list.get(i).getClassifyName());
//            fragments.add(homeCategoryFragment);
//        }
//        viewPager.setAdapter(new TabAdapter(getChildFragmentManager(), fragments));
//        tabLayout.setupWithViewPager(viewPager);
//
//    }


    /**
     * newCity是定位城市,oldCity是上一次启动app选择/定位的城市
     */
    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //获取城市
            String newCity = location.getCity();
            //获取区县
            String district = location.getDistrict();
            String street = location.getStreet();    //获取街道信息
            String errandsAddress = newCity + district + street;
            String adcode = location.getAdCode();

            spUtils.putString("district", district);
            spUtils.putString("address", errandsAddress);
            spUtils.putString("adcode", adcode);
            String oldCity = spUtils.getString("city", "");
            if (TextUtils.isEmpty(oldCity)) {
                tvCity.setText(newCity);
                spUtils.putString("city", newCity);
                return;
            }
            if (!TextUtils.isEmpty(newCity) && !oldCity.equals(newCity)) {
                showChangeLocationDialog(newCity , district);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();
    }

}
