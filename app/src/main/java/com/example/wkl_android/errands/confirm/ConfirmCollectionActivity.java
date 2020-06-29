package com.example.wkl_android.errands.confirm;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.errands.evaluate.ErrandsEvaluateActivity;
import com.example.wkl_android.main.MainActivity;

import butterknife.BindView;

/**
 * 确认收货
 */
public class ConfirmCollectionActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.ivLeft)
    View back;
    @BindView(R.id.tvTitle)
    TextView title;
    @BindView(R.id.tvToMain)
    View tvToMain;
    @BindView(R.id.tvToEvaluate)
    View tvToEvaluate;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_confirm_collection;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViews() {
        title.setText("确认收货");
        back.setOnClickListener(this);
        tvToMain.setOnClickListener(this);
        tvToEvaluate.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvToMain:
                startActivity(new Intent(APP, MainActivity.class));
                break;
            case R.id.tvToEvaluate:
                startActivity(new Intent(APP, ErrandsEvaluateActivity.class));
                finish();
                break;
        }
    }
}
