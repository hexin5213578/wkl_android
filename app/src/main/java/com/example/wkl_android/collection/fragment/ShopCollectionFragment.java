package com.example.wkl_android.collection.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.wkl_android.Event.CollectCancelOver;
import com.example.wkl_android.Event.CollectEvent;
import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseFragment;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.collection.bean.CollectionGoods;
import com.example.wkl_android.collection.bean.CollectionShop;
import com.example.wkl_android.collection.bean.CollectionVo;
import com.example.wkl_android.collection.bean.ColletionShopGroup;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.follow.adapter.FollowAdapter;
import com.example.wkl_android.good.ui.GoodsActivity;
import com.example.wkl_android.http.HttpUtils;
import com.example.wkl_android.http.callback.impl.JsonCallBack;
import com.example.wkl_android.utils.C;
import com.example.wkl_android.utils.ViewBgUtils;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ShopCollectionFragment extends BaseFragment implements FollowAdapter.OnClickListener {
    @BindView(R.id.rvShopCollection)
    RecyclerView rvShopCollection;

    @BindView(R.id.tv_cancel)
    TextView tv_cancel;
    int page =1;

    List<CollectionShop> data = new ArrayList<>();
    FollowAdapter mAdapter;

    public static ShopCollectionFragment newInstance() {

        Bundle args = new Bundle();

        ShopCollectionFragment fragment = new ShopCollectionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shop_collection;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViews() {

        EventBus.getDefault().register(this);


        mAdapter = new FollowAdapter(activity, data);
        mAdapter.setListener(this);
        rvShopCollection.setAdapter(mAdapter);

        ViewBgUtils.setBg(tv_cancel, "#ff453b", 40);


        getData();
    }

    public void getData() {
        HttpUtils.getInstance().doGet(C.COLLECTION_SHOP + page + "/10", Common.getToken(), this, new JsonCallBack() {
            @Override
            public void onSuccess(String json) throws Exception {


                JSONObject object = new JSONObject(json);
                ColletionShopGroup collectionVo = new Gson().fromJson(object.optString("data"), ColletionShopGroup.class);

                if (page == 1) {
                    data.clear();
                }

                data.addAll(collectionVo.getList());
                mAdapter.notifyDataSetChanged();


            }

            @Override
            public void onFinished() {
                super.onFinished();

            }
        });
    }


    //消息
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCollectEvent(CollectEvent msg) {
        for (CollectionShop goods : data) {
            goods.setCheck(msg.isEdit());
        }
        mAdapter.notifyDataSetChanged();

        if (msg.isEdit()) {
            tv_cancel.setVisibility(View.VISIBLE);
        } else {
            tv_cancel.setVisibility(View.GONE);
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @OnClick(R.id.tv_cancel)
    public void onCancelClick() {
        ArrayList<String> ids = new ArrayList<>();
        for (CollectionShop goods : data) {
//            ids.add(goods.getUserGoodsFavoriteId());
        }

        if (ids.size() > 0) {
//            cancelCollect(ids);
            EventBus.getDefault().post(new CollectCancelOver());
        }
    }

    @Override
    public void onClick(int position) {
        CollectionShop goods = data.get(position);
        if (goods.isCheck()) {
            goods.setSelect(!goods.isSelect());
            mAdapter.notifyDataSetChanged();
        }else {
//            GoodsActivity.toThisActivity(getContext(), "1", goods.getSkuId(), goods.getSkuPrice(), goods.getSkuTitle(), goods.getSkuImage());

        }
    }
}
