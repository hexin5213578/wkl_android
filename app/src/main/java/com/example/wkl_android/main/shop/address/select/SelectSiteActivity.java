package com.example.wkl_android.main.shop.address.select;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.main.MainActivity;
import com.example.wkl_android.main.shop.address.select.model.bean.SelectSites;
import com.example.wkl_android.main.shop.address.select.presenter.SelectPresenter;
import com.example.wkl_android.main.shop.address.select.ui.ISelectView;
import com.example.wkl_android.main.shop.address.select.ui.adapter.SiteAdapter;
import com.example.wkl_android.utils.LogUtils;
import com.example.wkl_android.utils.SPUtils;
import com.example.wkl_android.widget.rv.decoration.CustomDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 选择地区
 * Created by szx
 * on 2020/1/6/006
 */
public class SelectSiteActivity extends BaseActivity<ISelectView, SelectPresenter>
        implements ISelectView, View.OnClickListener {
    @BindView(R.id.tvTitle)
    TextView title;
    @BindView(R.id.ivLeft)
    View back;
    @BindView(R.id.rv)
    RecyclerView rv;
    private String pId;
    private List<SelectSites> allList;
    private SiteAdapter adapter;
    private String sites;
    private String names;
    private boolean updateCity;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_select_site;
    }

    @Override
    protected SelectPresenter createPresenter() {
        return new SelectPresenter();
    }

    @Override
    protected void initViews() {
        Intent intent = getIntent();
        if (intent != null) {
            pId = intent.getStringExtra("pId");
            sites = intent.getStringExtra("sites");
            names = intent.getStringExtra("name");
            updateCity = intent.getBooleanExtra("updateCity", false);
        }
        title.setText("选择地区");
        back.setOnClickListener(this);
        allList = new ArrayList<>();
        adapter = new SiteAdapter(this, allList);
        rv.setAdapter(adapter);
        CustomDecoration customDecoration = new CustomDecoration(this,
                CustomDecoration.VERTICAL, R.drawable.shape_ll_divider_gray);
        rv.addItemDecoration(customDecoration);
        adapter.setListener(new SiteAdapter.SelectSitesListener() {
            @Override
            public void toNext(String pid, String name) {
                presenter.getProvince(pid);
                sites = sites + "," + pid;
                names = names + " " + name;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        allList.clear();
        presenter.getProvince(pId);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
        }
    }

    /**
     * 处理地区列表
     *
     * @param list 地区列表
     */
    @Override
    public void handleSites(List<SelectSites> list) {
        allList.clear();
        if (list == null || list.isEmpty()) {
            if (updateCity) {
                String[] split = names.split(" ");
                SPUtils.getInstance().putString("city", split[1]);
                startActivity(new Intent(APP, MainActivity.class));
                return;
            }
            Intent intent = new Intent();
            intent.putExtra("sites", sites);
            intent.putExtra("name", names);
            setResult(Activity.RESULT_OK, intent);
            finish();
            return;
        }
        allList.addAll(list);
        adapter.notifyDataSetChanged();
    }
}
