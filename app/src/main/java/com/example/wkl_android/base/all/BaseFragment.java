package com.example.wkl_android.base.all;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.bumptech.glide.Glide;
import com.example.wkl_android.R;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseView {
    P basePresenter;
     String title;
//    private Unbinder bind;
    Dialog mLoadingDialog;
    public boolean mViewInflateFinished;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("asd","111111111111111111111111111111111111111111");
        initPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = View.inflate(getContext(), getLayout(), null);
        Log.d("asd","#3333333333333333333333333333333333333333333#################################");
       // bind = ButterKnife.bind(this,v);
        mViewInflateFinished = true;
        initView(v);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }
    public P getPresenter(){
        return basePresenter;
    }
    public void showDialog() {
        if (mLoadingDialog == null) {
            mLoadingDialog = new Dialog(getActivity());
            mLoadingDialog.setCancelable(false);
            View v = View.inflate(getContext(), R.layout.dialog_loading, null);

            mLoadingDialog.addContentView(v,
                    new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT));
        }

    }

    public void hideDialog() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }
    }
    protected abstract void initView(View v);
    protected abstract void initData();
    protected abstract int getLayout();
    protected abstract BasePresenter initPresenter();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(basePresenter != null){
            basePresenter.detachView();
            basePresenter = null;
        }
    }
    /**
     * fragment 提供的回调，回调当天fragment是否对用用户可见
     * 他是在当这个 fragment 是否对用户的可见发生变化的时候
     * @param isVisibleToUser false对用户不可见， true对用户可见
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        // 如果还没有加载过数据 && 用户切换到了这个fragment
        // 那就开始加载数据
        if (mViewInflateFinished && isVisibleToUser) {
            initData();
        }
    }
    private void doNetWork() {
        if (getUserVisibleHint()) {
            initData();
        }
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
