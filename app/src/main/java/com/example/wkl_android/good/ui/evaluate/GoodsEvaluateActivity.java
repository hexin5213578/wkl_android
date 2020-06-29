package com.example.wkl_android.good.ui.evaluate;

import android.util.Log;
import android.view.View;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.good.adapter.EvaluateAdapter;
import com.example.wkl_android.good.model.CommentBean;
import com.example.wkl_android.good.model.GoodsCommentBean;
import com.example.wkl_android.good.model.GoodsListBean;
import com.example.wkl_android.http.HttpUtils;
import com.example.wkl_android.http.callback.impl.JsonCallBack;
import com.example.wkl_android.search.ISearchView;
import com.example.wkl_android.utils.C;
import com.example.wkl_android.utils.ToastUtil;
import com.example.wkl_android.widget.rv.decoration.CustomDecoration;
import com.google.gson.Gson;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 商品评价
 */
public class GoodsEvaluateActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.ivLeft)
    View back;
    @BindView(R.id.tvTitle)
    TextView title;
    @BindView(R.id.rvEvaluate)
    RecyclerView rvEvaluate;
    @BindView(R.id.svList)
    SpringView svList;

    int page = 1;
    String skuid;
    ArrayList<CommentBean> list =new ArrayList<>();
    EvaluateAdapter mAdapter;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_goods_evaluate;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViews() {
        title.setText("商品评价");
        back.setOnClickListener(this);
        setTitleBarWithe();

        skuid = getIntent().getExtras().getString("skuid");
        mAdapter = new EvaluateAdapter(this, list);
        rvEvaluate.setAdapter(mAdapter);
        CustomDecoration customDecoration = new CustomDecoration(this,
                CustomDecoration.VERTICAL, R.drawable.shape_ll_divider_gray_10dp);
        rvEvaluate.addItemDecoration(customDecoration);
        spList();
        getData();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
        }
    }


    private void spList() {
        //处理上下拉刷新
        svList.setHeader(new DefaultHeader(this));
        svList.setFooter(new DefaultFooter(this));

        svList.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                //加载板块数据
                getData();
            }

            @Override
            public void onLoadmore() {
                page++;
                getData();
            }
        });
    }


    private void getData() {
        HttpUtils.getInstance().doGet(C.GOODS_COMMENT + page + "/10" + "?skuId="+skuid, "", new JsonCallBack() {
            @Override
            public void onSuccess(String json) throws Exception {
                GoodsCommentBean bean = new Gson().fromJson(json , GoodsCommentBean.class);
                if(page == 1 && (bean.getList() == null || bean.getList().size() <=0)){
                    ToastUtil.show("没有相关评价");
                }else{
                    if(page == 1){
                        list.clear();
                    }

                    list.addAll(bean.getList());
                    mAdapter.notifyDataSetChanged();
                    svList.onFinishFreshAndLoad();
                }
            }

            @Override
            public void onFinished() {
                super.onFinished();
                svList.onFinishFreshAndLoad();
            }
        });
    }


}
