package com.example.wkl_android.main.shop.settings.information.nick_name.ui.activity;

import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.main.shop.settings.information.nick_name.presenter.UpdatePresenter;
import com.example.wkl_android.main.shop.settings.information.nick_name.ui.IUpdateView;

import butterknife.BindColor;
import butterknife.BindView;

/**
 * 修改用户昵称和签名
 * Created by szx
 * on 2019/12/31/031
 */
public class UpdateNickNameActivity extends BaseActivity<IUpdateView, UpdatePresenter>
        implements IUpdateView, View.OnClickListener {
    @BindView(R.id.tvTitle)
    TextView title;
    @BindView(R.id.ivLeft)
    View back;
    @BindView(R.id.tvRight)
    TextView right;
    @BindView(R.id.ivClear)
    View ivClear;
    @BindView(R.id.etNickName)
    EditText etNickName;
    @BindView(R.id.tvDescription)
    TextView tvDescription;
    @BindColor(R.color.theme)
    int font_theme;

    private int type;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_nick_name;
    }

    @Override
    protected UpdatePresenter createPresenter() {
        return new UpdatePresenter();
    }

    @Override
    protected void initViews() {
        Intent intent = getIntent();
        if (intent != null) {
            type = intent.getIntExtra("type", 0);
        }
        title.setText(type == 0 ? "修改昵称" : "修改签名");
        tvDescription.setText(type == 0 ? "请输入您的昵称" : "请输入您的签名");
        etNickName.setHint(type == 0 ? "请输入昵称" : "请输入签名");
        back.setOnClickListener(this);
        right.setVisibility(View.VISIBLE);
        right.setText("保存");
        right.setTextColor(font_theme);
        right.setOnClickListener(this);
        ivClear.setOnClickListener(this);
        etNickName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                ivClear.setVisibility(s != null && s.length() > 0 ? View.VISIBLE : View.GONE);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.ivClear:
                etNickName.setText("");
                ivClear.setVisibility(View.GONE);
                break;
            case R.id.tvRight:
                save();
                break;
        }
    }

    /**
     * 提交保存操作
     */
    private void save() {
        String nickName = etNickName.getText().toString().trim();
        if (TextUtils.isEmpty(nickName)) {
            toast("请输入昵称");
            return;
        }
        presenter.save(nickName);
    }

    /**
     * 处理保存成功操作
     *
     * @param message 返回信息
     */
    @Override
    public void handleSuccess(String message) {
        toast(message);
        finish();
    }
}
