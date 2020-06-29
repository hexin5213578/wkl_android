package com.example.wkl_android.invitation.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.base.Constant;
import com.example.wkl_android.good.model.GoodsListBean;
import com.example.wkl_android.invitation.IInvitationView;
import com.example.wkl_android.invitation.adapter.InviationAdapter;
import com.example.wkl_android.invitation.adapter.RuleAdapter;
import com.example.wkl_android.invitation.bean.InviationRule;
import com.example.wkl_android.invitation.bean.InviationUser;
import com.example.wkl_android.invitation.presenter.InvitationPresenter;
import com.example.wkl_android.main.home.ui.adapter.HomeGoodsItemAdapter;
import com.example.wkl_android.searchinput.ui.activity.SearchInputActivity;
import com.example.wkl_android.searchshop.ShopAdapter;
import com.example.wkl_android.searchshop.bean.ShopVo;
import com.example.wkl_android.utils.SPUtils;
import com.example.wkl_android.utils.ToastUtil;
import com.example.wkl_android.utils.ViewBgUtils;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 *
 *
 * @author szx
 */
public class InvitationActivity extends BaseActivity<IInvitationView, InvitationPresenter>
        implements IInvitationView {


    @BindView(R.id.tv_copy)
    TextView tv_copy;

    @BindView(R.id.tv_invitation)
    TextView tv_invitation;

    @BindView(R.id.rv_user)
    RecyclerView rv_user;

    ArrayList<InviationUser> users = new ArrayList<>();
    InviationAdapter userAdapetr;

    @BindView(R.id.rv_rule)
    RecyclerView rv_rule;
    ArrayList<InviationRule> rules = new ArrayList<>();
    RuleAdapter ruleAdapter;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_invitation;
    }

    @Override
    protected InvitationPresenter createPresenter() {
        return new InvitationPresenter();
    }

    @Override
    protected void initViews() {
        setTitleBarWithe();

        ViewBgUtils.setBg(tv_copy, "#f3c7b3", 4);
        ViewBgUtils.setBg(tv_invitation, "#F72607", 20);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_user.setLayoutManager(manager);
        userAdapetr = new InviationAdapter(users);
        rv_user.setAdapter(userAdapetr);

        LinearLayoutManager ruleManager = new LinearLayoutManager(this);
        ruleManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_rule.setLayoutManager(ruleManager);
        ruleAdapter = new RuleAdapter(rules);
        rv_rule.setAdapter(ruleAdapter);

        for (int i = 0; i < 10; i++) {
            users.add(new InviationUser());
        }
        userAdapetr.notifyDataSetChanged();

        for (int i = 0; i < 10; i++) {
            rules.add(new InviationRule());
        }
        ruleAdapter.notifyDataSetChanged();


    }

    @OnClick(R.id.ivLeft)
    public void onBackClick() {
        finish();
    }

    @OnClick(R.id.tv_copy)
    public void onCopyClick() {

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

    }


    @Override
    public void showData(GoodsListBean bean) {

    }
}
