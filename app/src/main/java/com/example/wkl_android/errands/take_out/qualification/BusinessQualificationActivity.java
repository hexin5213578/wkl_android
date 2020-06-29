package com.example.wkl_android.errands.take_out.qualification;

import androidx.recyclerview.widget.RecyclerView;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.base.preseter.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 店铺资质
 */
public class BusinessQualificationActivity extends BaseActivity {
    @BindView(R.id.rvBusiness)
    RecyclerView rvBusiness;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_business_qualification;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViews() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("");
        }
        rvBusiness.setAdapter(new BusinessAdapter(this, list));
    }
}
