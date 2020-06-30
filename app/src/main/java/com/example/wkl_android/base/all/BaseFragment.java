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
    private P presenter;
    private Unbinder bind;
    Dialog mLoadingDialog;
    public boolean mViewInflateFinished;
    private String title;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(),getResId(),null);
        presenter  = initPresenter();
        bind = ButterKnife.bind(this,view);
        getData();
        getid(view);
        mViewInflateFinished = true;
        return view;
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

    public P getPresenter() {
        return presenter;
    }


    protected abstract void getid(View view);
    protected abstract int getResId();
    protected abstract P initPresenter();
    protected abstract void getData();
    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.print("MusicFragment+onDestroy()");
        Log.i("xxx","MusicFragment+onDestroy()");
        if(presenter != null){
            presenter.detachView();
            presenter = null;
        }
        bind.unbind();
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
            getData();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        System.out.print("MusicFragment+onAttach()");
        Log.i("xxx","MusicFragment+onAttach()");
    }

    private void doNetWork() {
        if (getUserVisibleHint()) {
            getData();
        }
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
