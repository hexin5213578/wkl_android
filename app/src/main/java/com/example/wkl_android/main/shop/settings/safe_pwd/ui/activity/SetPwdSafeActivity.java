package com.example.wkl_android.main.shop.settings.safe_pwd.ui.activity;

import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.main.shop.settings.safe_pwd.model.bean.FindList;
import com.example.wkl_android.main.shop.settings.safe_pwd.presenter.PwdSafePresenter;
import com.example.wkl_android.main.shop.settings.safe_pwd.ui.IPwdSafeView;
import com.example.wkl_android.main.shop.settings.safe_pwd.ui.adapter.SafeAdapter;

import java.util.List;

import butterknife.BindView;

/**
 * 密保问题
 * Created by szx
 * on 2020/1/4/004
 */
public class SetPwdSafeActivity extends BaseActivity<IPwdSafeView, PwdSafePresenter>
        implements IPwdSafeView, View.OnClickListener {
    @BindView(R.id.ivLeft)
    View back;
    @BindView(R.id.tvTitle)
    TextView title;
    @BindView(R.id.tvSubmit)
    View tvSubmit;
    @BindView(R.id.rv)
    RecyclerView rv;
    private SparseArray<String> answerMap;
    private SparseArray<String> questionMap;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_set_safe_pwd;
    }

    @Override
    protected PwdSafePresenter createPresenter() {
        return new PwdSafePresenter();
    }

    @Override
    protected void initViews() {
        title.setText("密保问题");
        back.setOnClickListener(this);
        tvSubmit.setOnClickListener(this);

        answerMap = new SparseArray<>();
        questionMap = new SparseArray<>();

    }

    public void setEditText(int position, String input) {
        answerMap.put(position, input);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getFindList();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvSubmit:
                save();
                break;
        }
    }

    /**
     * 提交密保问题接口
     */
    private void save() {
        String problemIdOne = questionMap.get(0);
        String problemIdTwo = questionMap.get(1);
        String answerOne = answerMap.get(0);
        String answerTwo = answerMap.get(1);
        presenter.save(answerOne, answerTwo, problemIdOne, problemIdTwo);
    }

    /**
     * 处理问题列表
     *
     * @param list 问题列表数据
     */
    @Override
    public void handleFindList(List<FindList> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        rv.setAdapter(new SafeAdapter(this, list));
        for (int i = 0; i < list.size(); i++) {
            questionMap.put(i, list.get(i).getPid());
        }
    }

    /**
     * 处理保存成功
     *
     * @param message 返回信息
     */
    @Override
    public void handleSaveSuccess(String message) {
        toast(message);
        finish();
    }
}
