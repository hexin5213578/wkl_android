package com.example.wkl_android.wholesale_market.ui.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.OnScrollListener;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.wkl_android.R;
import com.example.wkl_android.WholesaleMarketDetail.ui.activity.WholesaleMarketDetailActivity;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.base.Constant;
import com.example.wkl_android.good.model.GoodsListBean;
import com.example.wkl_android.main.home.ui.adapter.HomeGoodsItemAdapter;
import com.example.wkl_android.searchinput.ui.activity.SearchInputActivity;
import com.example.wkl_android.utils.SPUtils;
import com.example.wkl_android.utils.ToastUtil;
import com.example.wkl_android.wholesale_market.IWholesaleView;
import com.example.wkl_android.wholesale_market.adapter.MarkGourpAdapter;
import com.example.wkl_android.wholesale_market.adapter.MrakAdapter;
import com.example.wkl_android.wholesale_market.adapter.TypeAdapter;
import com.example.wkl_android.wholesale_market.bean.MarketInfo;
import com.example.wkl_android.wholesale_market.bean.WholesaleMarketInfo;
import com.example.wkl_android.wholesale_market.presenter.WholesaleMarketPresenter;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import q.rorbin.verticaltablayout.VerticalTabLayout;

/**
 * 批发市场列表
 *
 * @author szx
 */
public class WholesaleMarketActivity extends BaseActivity<IWholesaleView, WholesaleMarketPresenter>
        implements IWholesaleView {


    @BindView(R.id.rv_type)
    RecyclerView rv_type;

    @BindView(R.id.rv_list)
    RecyclerView rv_list;


    ArrayList<WholesaleMarketInfo> list = new ArrayList<>();
    ArrayList<MarketInfo> marketData = new ArrayList<>();
    TypeAdapter typeAdapter;
    MrakAdapter mrakAdapter;

    LinearLayoutManager manager;
    LinearLayoutManager list_manager;


    @Override
    public int getLayoutRes() {
        return R.layout.activity_wholesale_market;
    }

    @Override
    protected WholesaleMarketPresenter createPresenter() {
        return new WholesaleMarketPresenter();
    }

    @Override
    protected void initViews() {
        setTitleBarWithe();

        manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);


        list_manager = new LinearLayoutManager(this);
        list_manager.setOrientation(LinearLayoutManager.VERTICAL);


        rv_type.setLayoutManager(manager);
        rv_list.setLayoutManager(list_manager);


        typeAdapter = new TypeAdapter(list);
        mrakAdapter = new MrakAdapter(marketData);
        rv_type.setAdapter(typeAdapter);
        rv_list.setAdapter(mrakAdapter);

        presenter.getData();


        mrakAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MarketInfo info = marketData.get(position);
                Intent intent = new Intent(WholesaleMarketActivity.this, WholesaleMarketDetailActivity.class);
                intent.putExtra("id", info.getMarketId());
                startActivity(intent);

            }
        });

        typeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                rv_list.setOnScrollListener(null);
                rv_list.smoothScrollToPosition(list.get(position).getNum());
                showPostion(position);
//                setScrollListener();
                new asy().execute();
            }
        });
        setScrollListener();

    }

    class asy extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            Log.e("scroll", "........");

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            setScrollListener();


            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.e("scroll", "........////");

        }
    }

    private void setScrollListener() {
        rv_list.setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int positon = list_manager.findFirstCompletelyVisibleItemPosition();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getNum() >= positon) {
                        showPostion(i);
                        Log.e("scroll", i + "");
                        return;
                    }
                }
            }
        });

    }


    private void showPostion(int position) {
        for (WholesaleMarketInfo info : list) {
            info.setSelect(false);
        }
        list.get(position).setSelect(true);
        typeAdapter.notifyDataSetChanged();
    }


    public void showData(ArrayList<WholesaleMarketInfo> data) {
        list.clear();
        list.addAll(data);
        list.get(0).setSelect(true);

        for (WholesaleMarketInfo info : list) {
            info.setNum(marketData.size());
            for (MarketInfo item : info.getWholesaleMarketVOList()) {
                marketData.add(item);

            }
        }


        typeAdapter.notifyDataSetChanged();
        mrakAdapter.notifyDataSetChanged();

    }

    @OnClick(R.id.iv_search)
    public void onSearchClick() {
        Intent intent = new Intent(this, SearchInputActivity.class);
        intent.putExtra("type", 1);
        startActivity(intent);
    }


    @OnClick(R.id.ivLeft)
    public void onBackClick() {
        finish();
    }


}
