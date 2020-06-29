package com.example.wkl_android.charge.consume;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.charge.consume.adapter.ConsumeRecordAdapter;
import com.example.wkl_android.widget.rv.decoration.CustomDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 消费记录
 */
public class ConsumeRecordActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.ivLeft)
    View back;
    @BindView(R.id.tvTitle)
    TextView title;
    @BindView(R.id.rvConsumeRecord)
    RecyclerView rvConsumeRecord;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_consume_record;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViews() {
        title.setText("消费记录");
        back.setOnClickListener(this);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("");
        }
        ConsumeRecordAdapter adapter = new ConsumeRecordAdapter(this, list);
        rvConsumeRecord.setAdapter(adapter);
        CustomDecoration customDecoration = new CustomDecoration(this,
                CustomDecoration.VERTICAL, R.drawable.shape_ll_divider_gray_10dp);
        rvConsumeRecord.addItemDecoration(customDecoration);
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
