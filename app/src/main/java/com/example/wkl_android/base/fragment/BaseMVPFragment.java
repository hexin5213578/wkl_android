package com.example.wkl_android.base.fragment;

import android.app.Activity;
import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.example.wkl_android.Event.AddCarEvent;
import com.example.wkl_android.Event.Event;
import com.example.wkl_android.R;
import com.example.wkl_android.base.app.BaseApp;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.base.view.IBaseView;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.order.confirm.ConfirmOrderActivity;
import com.example.wkl_android.widget.dialog.LoadingDialog;
import com.jaeger.library.StatusBarUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 生命周期：
 * onAttach -> onCreate -> onCreatedView -> onActivityCreated -> onStart -> onResume ->
 * onPause -> onStop -> onDestroyView -> onDestroy -> onDetach
 *
 * @author li
 * @since 2019-05-08
 */
public abstract class BaseMVPFragment<V extends IBaseView, P extends BasePresenter<V>>
        extends Fragment implements IBaseView {

    protected WeakReference<FragmentActivity> weakReference;
    protected P presenter;
    private Dialog loginTimeoutDialog;
    protected Application APP;

    private Unbinder unbinder;
    protected View contentView;

    private LoadingDialog loadingDialog;

    private boolean isViewCreated;
    private boolean currentVisibleState = false;
    private boolean mIsFirstVisible = true;

    private String title;
    protected FragmentActivity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        APP = (Application) context.getApplicationContext();
        presenter = createPresenter();
        if (presenter != null) presenter.attachView((V) this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        weakReference = new WeakReference<>(getActivity());
        activity = weakReference.get();
     }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (contentView == null) {
            contentView = inflater.inflate(getLayoutId(), container, false);

            isViewCreated = true;

            unbinder = ButterKnife.bind(this, contentView);

            initViews();

            if (!isHidden() && getUserVisibleHint()) {
                dispatchUserVisibleHint(true);
            }
        }
        return contentView;
    }

    protected abstract int getLayoutId();

    protected abstract P createPresenter();

    protected abstract void initViews();

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isViewCreated) {
            if (isVisibleToUser && !currentVisibleState) {
                dispatchUserVisibleHint(true);
            } else if (!isVisibleToUser && currentVisibleState) {
                dispatchUserVisibleHint(false);
            }
        }
    }

    /**
     * 统一处理用户可见信息分发
     *
     * @param isVisible 是否可见
     */
    private void dispatchUserVisibleHint(boolean isVisible) {
        if (isVisible && isParentInvisible()) {
            return;
        }
        //为了代码严谨
        if (currentVisibleState == isVisible) {
            return;
        }
        currentVisibleState = isVisible;
        if (isVisible) {
            if (mIsFirstVisible) {
                mIsFirstVisible = false;
                onFragmentFirstVisible();
            }
            onFragmentResume();
            dispatchChildVisibleState(true);
        } else {
            onFragmentPause();
            dispatchChildVisibleState(false);
        }
    }

    private void dispatchChildVisibleState(boolean visible) {
        FragmentManager fragmentManager = getChildFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        for (Fragment fragment : fragments) {
            if (fragment instanceof BaseMVPFragment &&
                    !fragment.isHidden() &&
                    fragment.getUserVisibleHint()) {
                ((BaseMVPFragment) fragment).dispatchUserVisibleHint(visible);
            }
        }
    }

    private boolean isParentInvisible() {
        Fragment parentFragment = getParentFragment();
        if (parentFragment instanceof BaseMVPFragment) {
            BaseMVPFragment fragment = (BaseMVPFragment) parentFragment;
            return !fragment.isSupportVisible();
        }
        return false;
    }

    private boolean isSupportVisible() {
        return currentVisibleState;
    }

    @CallSuper
    protected void onFragmentFirstVisible() {

    }

    @CallSuper
    protected void onFragmentResume() {

    }

    @CallSuper
    protected void onFragmentPause() {
        dismissLoading();
        if (presenter != null) presenter.cancel();
    }

    /**
     * 用FragmentTransaction来控制fragment的hide和show时，
     * 那么这个方法就会被调用。每当你对某个Fragment使用hide
     * 或者是show的时候，那么这个Fragment就会自动调用这个方法。
     * https://blog.csdn.net/u013278099/article/details/72869175
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            dispatchUserVisibleHint(false);
        } else {
            dispatchUserVisibleHint(true);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!mIsFirstVisible) {
            if (!isHidden() && !currentVisibleState && getUserVisibleHint()) {
                dispatchUserVisibleHint(true);
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (currentVisibleState && getUserVisibleHint()) {
            dispatchUserVisibleHint(false);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isViewCreated = false;
        mIsFirstVisible = false;
        if (unbinder != null) {
            unbinder.unbind();
        }
        contentView = null;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (presenter != null) presenter.detachView();
    }

    /**
     * 显示加载框
     */
    @Override
    public void showLoading() {
        Activity activity = weakReference.get();
        if (activity == null || activity.isFinishing()) {
            return;
        }
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(activity);
        }
        loadingDialog.show();
    }

    /**
     * 关闭加载框
     */
    @Override
    public void dismissLoading() {
        Activity activity = weakReference.get();
        if (activity == null || activity.isFinishing()) {
            return;
        }
        activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

    @Override
    public void toast(CharSequence msg) {
        BaseApp.APP.toast(msg);
    }

    @Override
    public void toast(@StringRes int stringRes) {
        BaseApp.APP.toast(stringRes);
    }

    public void loginTimeout() {
        Common.logout();
        showLoginTimeoutDialog();
    }

    @Override
    public void showCodeMessage(String code, String message) {
        Activity activity = weakReference.get();
        if (activity == null || activity.isFinishing()) {
            return;
        }
        if ("900".equals(code)) {
            loginTimeout();
            return;
        }
        toast(message);
    }

    /**
     * 展示登录超时的弹框
     */
    private void showLoginTimeoutDialog() {
        Activity activity = weakReference.get();
        if (activity == null || activity.isFinishing()) {
            return;
        }
        if (loginTimeoutDialog == null) {
            loginTimeoutDialog = new AlertDialog.Builder(activity)
                    .setTitle("温馨提示")
                    .setMessage("长时间无操作，请重新登录")
                    .setPositiveButton("重新登录", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            toLogin();
                        }
                    })
                    .create();
        }
        if (loginTimeoutDialog.isShowing()) {
            return;
        }
        loginTimeoutDialog.setCanceledOnTouchOutside(true);
        loginTimeoutDialog.show();
    }

    /**
     * 跳转登录页面
     */
    @Override
    public void toLogin() {
        throw new UnsupportedOperationException("登录方法未实现");
    }

    public boolean isPermissionAllow(String permission) {
        return ActivityCompat.checkSelfPermission(BaseApp.APP, permission) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * 隐藏键盘
     */
    protected void hideInput(View view) {
        InputMethodManager imm = (InputMethodManager) APP.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    /**
     * 设置标题背景 红色
     */
    public void setTitleBarWithe() {
        StatusBarUtil.setLightMode(getActivity());
        StatusBarUtil.setColor(getActivity(), ContextCompat.getColor(getContext(), R.color.white), 0);
    }

    /**
     * 设置标题背景 红色
     */
    public void setTitleBarWithe(int color) {
        StatusBarUtil.setLightMode(getActivity());
        StatusBarUtil.setColor(getActivity(), color, 0);
    }
}