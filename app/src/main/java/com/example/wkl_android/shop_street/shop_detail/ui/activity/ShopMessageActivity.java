package com.example.wkl_android.shop_street.shop_detail.ui.activity;

import android.app.job.JobScheduler;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.viewpager.widget.ViewPager;

import com.example.wkl_android.Event.Event;
import com.example.wkl_android.Event.ShopImgEvent;
import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.base.BaseFragment;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.charge.detail.ChargeDetailActivity;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.good.model.GoodsCommentBean;
import com.example.wkl_android.http.HttpUtils;
import com.example.wkl_android.http.callback.impl.JsonCallBack;
import com.example.wkl_android.login.register.ui.adapter.TabAdapter;
import com.example.wkl_android.shop_street.shop_detail.charge.ui.ShopChargeFragment;
import com.example.wkl_android.shop_street.shop_detail.ui.bean.ShopDetailVo;
import com.example.wkl_android.shop_street.shop_detail.ui.fragment.ShopDetailFragment;
import com.example.wkl_android.shop_street.shop_detail.ui.fragment.ShopImgFragment;
import com.example.wkl_android.utils.BitmapUtil;
import com.example.wkl_android.utils.C;
import com.example.wkl_android.utils.ToastUtil;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Comment;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;

/**
 * 店铺信息
 */
public class ShopMessageActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.ivLeft)
    View back;
    @BindView(R.id.tvTitle)
    TextView title;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tvFollow)
    TextView tvFollow;
    @BindView(R.id.filpper)
    ViewFlipper filpper;

    @BindView(R.id.iv_img)
    ImageView iv_img;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_fans)
    TextView tv_fans;
    @BindView(R.id.tv_num)
    TextView tv_num;

    String shopid;
    ShopDetailVo mDetail;


    public static void StartActivity(Context context, String id) {
        Intent intent = new Intent(context, ShopMessageActivity.class);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_shop_message;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViews() {

        setTitleBarWithe();
        shopid = getIntent().getStringExtra("id");

        back.setOnClickListener(this);
        title.setText("店铺信息");

        tvFollow.setOnClickListener(this);

        getData();
        for (int i = 0; i < 5; i++) {
            View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.layout_filpper, null);
            TextView tvFilpper = view.findViewById(R.id.tvFilpper);
            tvFilpper.setText(String.format(Locale.getDefault(),
                    "第%d波 店铺限时充值活动：充值1000赠100  查看详情>>", i));
            tvFilpper.setOnClickListener(view1 -> startActivity(new Intent(APP,
                    ChargeDetailActivity.class)));
            filpper.addView(view);
        }
    }

    private void initViewPager() {
        List<BaseFragment> fragments = new ArrayList<>();
        ShopChargeFragment shopChargeFragment = ShopChargeFragment.newInstance(mDetail);
        shopChargeFragment.setTitle("店铺活动");
        fragments.add(shopChargeFragment);
        ShopDetailFragment shopDetailFragment = ShopDetailFragment.newInstance(mDetail);
        shopDetailFragment.setTitle("店铺信息");
        fragments.add(shopDetailFragment);
        ShopImgFragment shopImgFragment = ShopImgFragment.newInstance(mDetail);
        shopImgFragment.setTitle("店铺图片");
        fragments.add(shopImgFragment);
        viewPager.setAdapter(new TabAdapter(getSupportFragmentManager(), fragments));
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvFollow:
                if (mDetail != null) {
                    if (mDetail.isBusinessAttentionStatus()) {
                        cancelSaveShop();
                    } else {
                        saveShop();
                    }
                }

                break;
        }
    }


    private void getData() {
        HttpUtils.getInstance().doGet(C.SHOP_DETAIL + shopid, "", new JsonCallBack() {
            @Override
            public void onSuccess(String json) throws Exception {
                JSONObject object = new JSONObject(json);
                mDetail = new Gson().fromJson(object.optString("data"), ShopDetailVo.class);
                setData();
                initViewPager();
            }

            @Override
            public void onFinished() {
                super.onFinished();
            }
        });
    }

    private void saveShop() {

        JSONObject object = new JSONObject();
        try {
            object.put("data", shopid);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        HttpUtils.getInstance().doPostJson(C.SHOP_SAVE, object.toString(), Common.getToken(), null,  new JsonCallBack() {
            @Override
            public void onSuccess(String json) throws Exception {
                mDetail.setBusinessAttentionStatus(true);
                showFoillw();
            }

            @Override
            public void onFinished() {
                super.onFinished();
            }
        });
    }

    private void cancelSaveShop() {

        JSONObject object = new JSONObject();
        try {
            object.put("data", shopid);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        HttpUtils.getInstance().doPostJson(C.CANCEL_SHOP_SAVE, object.toString(), Common.getToken(), null , new JsonCallBack() {
            @Override
            public void onSuccess(String json) throws Exception {
                mDetail.setBusinessAttentionStatus(false);
                showFoillw();
            }

            @Override
            public void onFinished() {
                super.onFinished();
            }
        });
    }


    private void setData() {

        tv_fans.setText("粉丝数 " + mDetail.getBusinessAttentionNumber());

        tv_name.setText(mDetail.getBusinessName());
        BitmapUtil.showImage(this, mDetail.getBusinessImageUrl(), iv_img);

        showFoillw();

    }

    private void showFoillw() {
        if (mDetail.isBusinessAttentionStatus()) {
            tvFollow.setText("取消关注");
        } else {
            tvFollow.setText("+ 关注");
        }
    }


}
