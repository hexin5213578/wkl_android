package com.example.wkl_android.select_city.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.main.shop.address.select.SelectSiteActivity;
import com.example.wkl_android.main.shop.address.select.model.bean.SelectSites;
import com.example.wkl_android.main.shop.address.select.ui.adapter.SiteAdapter;
import com.example.wkl_android.select_city.adapter.CityAreaAdapter;
import com.example.wkl_android.select_city.adapter.SelectCityAdapter;
import com.example.wkl_android.select_city.presenter.SelectCityPresenter;
import com.example.wkl_android.select_city.ui.ISelectCityView;
import com.example.wkl_android.utils.SPUtils;
import com.example.wkl_android.widget.rv.decoration.CustomDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 选择城市
 */
public class SelectCityActivity extends BaseActivity<ISelectCityView, SelectCityPresenter>
        implements ISelectCityView, View.OnClickListener {
    @BindView(R.id.tvTitle)
    TextView title;
    @BindView(R.id.ivLeft)
    View back;
    @BindView(R.id.tvLocation)
    TextView tvLocation;
    @BindView(R.id.rv)
    RecyclerView rv;

    View areaView;
    RecyclerView rv_area;
    String adCode;

    SelectCityAdapter mAdapter;

    List<SelectSites> city = new ArrayList<>();

    @Override
    public int getLayoutRes() {
        return R.layout.activity_select_city;
    }

    @Override
    protected SelectCityPresenter createPresenter() {
        return new SelectCityPresenter();
    }

    @Override
    protected void initViews() {
        setTitleBarWithe();
        title.setText("选择城市");
        back.setOnClickListener(this);
        String address = SPUtils.getInstance().getString("address", "");
        tvLocation.setText(String.format("当前定位：%s", address));

        areaView = View.inflate(this, R.layout.city_area_view, null);
        rv_area = areaView.findViewById(R.id.rv_area);
        mAdapter = new SelectCityAdapter(city);
        mAdapter.addHeaderView(areaView);
        rv.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(APP, SelectSiteActivity.class).putExtra("pId", city.get(position).getId())
                        .putExtra("name", city.get(position).getName()).putExtra("updateCity", true));
            }
        });

        GridLayoutManager manager = new GridLayoutManager(this, 3);
        rv_area.setLayoutManager(manager);

        SPUtils spUtils = SPUtils.getInstance();


        adCode = spUtils.getString("adcode", "");
        if (!TextUtils.isEmpty(adCode) && adCode.length() > 2) {
            adCode = adCode.substring(0, adCode.length() - 2);
            adCode += "00";
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getData();
        presenter.getProvince(adCode);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
        }
    }

    @Override
    public void handleSites(List<SelectSites> list) {

        city.clear();
        city.addAll(list);
        mAdapter.notifyDataSetChanged();

    }

    @Override
    public void setSide(List<SelectSites> list) {
        SPUtils spUtils = SPUtils.getInstance();
        String code = spUtils.getString("district", "");
        for (SelectSites side : list) {
            if (TextUtils.equals(side.getName(), code)) {
                side.setSelect(true);
            }
        }

        CityAreaAdapter areaAdapter = new CityAreaAdapter(list);
        rv_area.setAdapter(areaAdapter);

        areaAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                SelectSites item = list.get(position);
                String city = spUtils.getString("city", "");
                spUtils.putString("address", city + item.getName());
                spUtils.putString("district", item.getName());

                for (int i = 0 ; i < list.size() ;i++){
                    SelectSites sites = list.get(i);
                    if(i == position){
                        sites.setSelect(true);
                    }else {
                        sites.setSelect(false);
                    }
                }
                areaAdapter.notifyDataSetChanged();
                finish();

            }
        });
    }
}
