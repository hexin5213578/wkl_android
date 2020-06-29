package com.example.wkl_android.searchinput.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.base.Constant;
import com.example.wkl_android.good.model.GoodsListBean;
import com.example.wkl_android.main.category.model.bean.Category;
import com.example.wkl_android.main.home.ui.adapter.HomeGoodsItemAdapter;
import com.example.wkl_android.search.ui.activity.SearchActivity;
import com.example.wkl_android.searchinput.ISearchInputView;
import com.example.wkl_android.searchinput.adapter.SearchHistoryAdapter;
import com.example.wkl_android.searchinput.presenter.SearchInputPresenter;
import com.example.wkl_android.searchshop.ui.activity.SearchShopActivity;
import com.example.wkl_android.utils.DisplayUtil;
import com.example.wkl_android.utils.KeyBoardUtils;
import com.example.wkl_android.utils.SPUtils;
import com.example.wkl_android.utils.ToastUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.library.flowlayout.FlowLayoutManager;
import com.library.flowlayout.SpaceItemDecoration;

import org.w3c.dom.ls.LSInput;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 登录页面
 *
 * @author szx
 */
public class SearchInputActivity extends BaseActivity<ISearchInputView, SearchInputPresenter>
        implements ISearchInputView, BaseQuickAdapter.OnItemClickListener {


    @BindView(R.id.et_search)
    EditText et_search;

    @BindView(R.id.rv_list)
    RecyclerView rv_list;

    SearchHistoryAdapter adapter;
    ArrayList<String> list = new ArrayList<>();

    int type = 0;   //0 商品 1店铺

    @Override
    public int getLayoutRes() {
        return R.layout.activity_search_input;
    }

    @Override
    protected SearchInputPresenter createPresenter() {
        return new SearchInputPresenter();
    }

    @Override
    protected void initViews() {
        setTitleBarWithe();
        KeyBoardUtils.openKeyBoard(et_search);

        type = getIntent().getIntExtra("type", 0);

        FlowLayoutManager manager = new FlowLayoutManager();
        rv_list.addItemDecoration(new SpaceItemDecoration(DisplayUtil.dipToPixel(10)));
        rv_list.setLayoutManager(manager);

        initList();
        adapter = new SearchHistoryAdapter(list);
        rv_list.setAdapter(adapter);
        adapter.setOnItemClickListener(this);


        et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    //点击搜索的时候隐藏软键盘
                    KeyBoardUtils.closeKeyboard(et_search);
                    // 在这里写搜索的操作,一般都是网络请求数据
                    search();
                    return true;
                }

                return false;
            }
        });

    }

    private void initList() {
        Type type = new TypeToken<List<String>>() {
        }.getType();
        String json = SPUtils.getInstance().getString("history", "");
        list = new Gson().fromJson(json, type);
    }

    private void search() {
        String msg = et_search.getText().toString();
        if (TextUtils.isEmpty(msg)) {
            ToastUtil.show("请输入搜索内容");
            return;
        }

        if (list == null) {
            list = new ArrayList<>();
        }

        int del = -1;
        for (int i = 0; i < list.size(); i++) {
            if (TextUtils.equals(msg, list.get(i))) {
                del = 1;
            }
        }

        try {
            if (del >= 0 && list.size() >= del - 1)
                list.remove(del);
            list.add(0, msg);
        } catch (Exception e) {
            e.printStackTrace();
        }


        SPUtils.getInstance().putString("history", new Gson().toJson(list));

        Intent intent;
        if (type == 1) {
            intent = new Intent(this, SearchShopActivity.class);
            intent.putExtra("msg", msg);
        } else {
            intent = new Intent(this, SearchActivity.class);
            intent.putExtra("msg", msg);

        }

        startActivity(intent);
        finish();

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        type = getIntent().getIntExtra("type", 0);
    }

    @OnClick(R.id.tv_search)
    public void onSearchClick() {
        search();
    }

    @OnClick(R.id.iv_del)
    public void onDelClick() {
        SPUtils.getInstance().putString("history", "");
        initList();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        et_search.setText(list.get(position));
        search();
    }


    @OnClick(R.id.iv_back)
    public void onBackClick() {
        finish();
    }

}
