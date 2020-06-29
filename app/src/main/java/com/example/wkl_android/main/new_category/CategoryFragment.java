package com.example.wkl_android.main.new_category;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wkl_android.R;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.databinding.LayoutCategoryBinding;
import com.example.wkl_android.http.HttpUtils;
import com.example.wkl_android.http.callback.impl.JsonCallBack;
import com.example.wkl_android.main.home.ui.GoodsListActivity;
import com.example.wkl_android.utils.C;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 */
public class CategoryFragment extends Fragment {
    LayoutCategoryBinding binding;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.layout_category, container, false);
        init();
        return binding.getRoot();
    }


    private void init() {

        binding.ivLeft.setVisibility(View.GONE);

        adapter = new TypeAdapter(getActivity()) {
            @Override
            public void selectItem(String text, String id) {

            }
        };

        SecondTypeAdapter secondTypeAdapter = new SecondTypeAdapter(getContext()) {
            @Override
            public void selectItem(String text, String id) {
                GoodsListActivity.toThisActivity(getContext(), id);

            }
        };
        HttpUtils.getInstance().doGet(C.GET_FIRST_TYPE, Common.getToken(), this, new JsonCallBack() {
            @Override
            public void onSuccess(String json) throws Exception {
                Log.e("ZYY", "请求数据成功");
                Gson g = new Gson();
                Type type = new TypeToken<List<TypeBean>>() {
                }.getType();
                List<TypeBean> typeBean = g.fromJson(json, type);

                adapter.setList(typeBean);

                binding.firstType.setAdapter(adapter);
                binding.firstType.setLayoutManager(new LinearLayoutManager(getActivity()));
                binding.secondType.setLayoutManager(new LinearLayoutManager(getActivity()));
                secondTypeAdapter.setList(typeBean);
                binding.secondType.setAdapter(secondTypeAdapter);
            }
        });

    }

    TypeAdapter adapter;

    @Override
    public void onResume() {
        super.onResume();
        if (adapter.getItemCount() == 0) {
            init();
        }
    }

    public static CategoryFragment newInstance() {

        Bundle args = new Bundle();
        CategoryFragment fragment = new CategoryFragment();
        fragment.setArguments(args);
        return fragment;
    }


}
