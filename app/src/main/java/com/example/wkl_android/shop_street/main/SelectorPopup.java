package com.example.wkl_android.shop_street.main;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.recyclerview.widget.RecyclerView;

import com.example.wkl_android.R;
import com.example.wkl_android.shop_street.main.adapter.SelectorChildAdapter;
import com.example.wkl_android.shop_street.main.adapter.SelectorParentAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectorPopup extends PopupWindow {
    @BindView(R.id.rvParent)
    RecyclerView rvParent;
    @BindView(R.id.rvChild)
    RecyclerView rvChild;

    private List<String> parentList;
    private List<String> childList;
    private SelectorParentAdapter parentAdapter;
    private SelectorChildAdapter childAdapter;

    public SelectorPopup(Context context, List<String> parentList, List<String> childList) {
        parentAdapter = new SelectorParentAdapter(context);
        childAdapter = new SelectorChildAdapter(context);
        View view = LayoutInflater.from(context).inflate(R.layout.popup_selector, null);
        setContentView(view);
        ButterKnife.bind(this, view);
        this.parentList = parentList;
        this.childList = childList;
        init();
    }

    private void init() {
        setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
        setOutsideTouchable(true);
        setFocusable(true);
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        parentAdapter.setList(parentList);
        rvParent.setAdapter(parentAdapter);
        parentAdapter.setListener(new SelectorParentAdapter.OnClicklistener() {
            @Override
            public void onClick(String text) {
                rvChild.setVisibility(View.VISIBLE);
                if (childList == null || childList.isEmpty()) {
                    if(listener!=null){
                        listener.onClick(text);
                    }
                    dismiss();
                    rvChild.setVisibility(View.GONE);
                } else {
                    childAdapter.setList(childList);
                    rvChild.setAdapter(childAdapter);
                }
            }
        });
        childAdapter.setListener(new SelectorChildAdapter.OnClickListener() {
            @Override
            public void onClick(String text) {
                if (listener != null) {
                    listener.onClick(text);
                    dismiss();
                    rvChild.setVisibility(View.GONE);
                }
            }
        });
    }

    public interface OnClickListener {
        void onClick(String text);
    }

    private OnClickListener listener;

    public void setListener(OnClickListener listener) {
        this.listener = listener;
    }
}
