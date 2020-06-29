package com.example.wkl_android.collection;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.wkl_android.Event.CollectCancelOver;
import com.example.wkl_android.Event.CollectEvent;
import com.example.wkl_android.Event.Event;
import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.base.BaseFragment;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.collection.adapter.CollectionAdapter;
import com.example.wkl_android.collection.bean.CollectionGoods;
import com.example.wkl_android.collection.fragment.GoodsCollectionFragment;
import com.example.wkl_android.collection.fragment.ShopCollectionFragment;
import com.example.wkl_android.forget.ui.activity.ForgetActivity;
import com.example.wkl_android.login.register.ui.adapter.TabAdapter;
import com.example.wkl_android.widget.rv.decoration.CustomDecoration;
import com.google.android.material.tabs.TabLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 收藏
 */
public class CollectionActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.ivLeft)
    View back;
    @BindView(R.id.tvTitle)
    TextView title;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @BindView(R.id.tvRight)
    TextView tvRight;

    boolean edit = false;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_collection;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViews() {


        setTitleBarWithe();
        title.setText("我的收藏");
        back.setOnClickListener(this);

        List<BaseFragment> fragments = new ArrayList<>();
        GoodsCollectionFragment fragment = GoodsCollectionFragment.newInstance();
        fragment.setTitle("商品收藏");
        fragments.add(fragment);
        ShopCollectionFragment shopCollectionFragment = ShopCollectionFragment.newInstance();
        shopCollectionFragment.setTitle("店铺收藏");
        fragments.add(shopCollectionFragment);
        viewPager.setAdapter(new TabAdapter(getSupportFragmentManager(), fragments));
        tabLayout.setupWithViewPager(viewPager);

        tvRight.setText("编辑");
    }

    @OnClick(R.id.tvRight)
    public void onRightClick(){
        edit = !edit;
        showEdit();
        EventBus.getDefault().post(new CollectEvent(edit));
    }

    private void showEdit(){
        if(edit){
            tvRight.setText("完成");
        }else {
            tvRight.setText("编辑");
        }
    }

    //消息
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCollectEvent(CollectCancelOver msg) {
        edit = false;
        showEdit();
        EventBus.getDefault().post(new CollectEvent(false));
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
        }
    }
}
