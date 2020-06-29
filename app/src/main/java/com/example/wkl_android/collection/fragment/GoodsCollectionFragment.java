package com.example.wkl_android.collection.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.wkl_android.Event.CollectCancelOver;
import com.example.wkl_android.Event.CollectEvent;
import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseFragment;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.collection.adapter.CollectionAdapter;
import com.example.wkl_android.collection.bean.CollectionGoods;
import com.example.wkl_android.collection.bean.CollectionVo;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.good.ui.GoodsActivity;
import com.example.wkl_android.http.HttpUtils;
import com.example.wkl_android.http.callback.impl.JsonCallBack;
import com.example.wkl_android.order.detail.model.OrderDetailInfo;
import com.example.wkl_android.utils.C;
import com.example.wkl_android.utils.ViewBgUtils;
import com.example.wkl_android.widget.rv.decoration.CustomDecoration;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class GoodsCollectionFragment extends BaseFragment implements BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.rvCollection)
    RecyclerView rvCollection;

    @BindView(R.id.tv_cancel)
    TextView tv_cancel;

    int page = 1;

    ArrayList<CollectionGoods> data = new ArrayList<>();
    CollectionAdapter mAdapter;

    public static GoodsCollectionFragment newInstance() {

        Bundle args = new Bundle();

        GoodsCollectionFragment fragment = new GoodsCollectionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_goods_collection;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViews() {

        ViewBgUtils.setBg(tv_cancel, "#ff453b", 40);


        EventBus.getDefault().register(this);
        mAdapter = new CollectionAdapter(data);

        rvCollection.setAdapter(mAdapter);
        CustomDecoration customDecoration = new CustomDecoration(activity,
                CustomDecoration.VERTICAL, R.drawable.shape_ll_divider_gray);
        rvCollection.addItemDecoration(customDecoration);

        mAdapter.setOnItemClickListener(this);

        getData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void getData() {
        HttpUtils.getInstance().doGet(C.GET_SAVE_GOODS + page + "/10", Common.getToken(), this, new JsonCallBack() {
            @Override
            public void onSuccess(String json) throws Exception {


                JSONObject object = new JSONObject(json);
                CollectionVo collectionVo = new Gson().fromJson(object.optString("data"), CollectionVo.class);

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

    public void cancelCollect(ArrayList<String> ids) {
        HttpUtils.getInstance().doPutJson(C.DELECT_COLLECT, new Gson().toJson(ids), Common.getToken(), this, new JsonCallBack() {
            @Override
            public void onSuccess(String json) throws Exception {
                getData();

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
        for (CollectionGoods goods : data) {
            goods.setCheck(msg.isEdit());
        }
        mAdapter.notifyDataSetChanged();

        if (msg.isEdit()) {
            tv_cancel.setVisibility(View.VISIBLE);
        } else {
            tv_cancel.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.tv_cancel)
    public void onCancelClick() {
        ArrayList<String> ids = new ArrayList<>();
        for (CollectionGoods goods : data) {
            ids.add(goods.getUserGoodsFavoriteId());
        }

        if (ids.size() > 0) {
            cancelCollect(ids);
            EventBus.getDefault().post(new CollectCancelOver());
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        CollectionGoods goods = data.get(position);
        if (goods.isCheck()) {
            goods.setSelect(!goods.isSelect());
            mAdapter.notifyDataSetChanged();
        }else {
            GoodsActivity.toThisActivity(getContext(), "1", goods.getSkuId(), goods.getSkuPrice(), goods.getSkuTitle(), goods.getSkuImage());

        }
    }
}
