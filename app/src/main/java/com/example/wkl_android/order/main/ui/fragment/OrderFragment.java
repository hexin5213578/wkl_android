package com.example.wkl_android.order.main.ui.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseFragment;
import com.example.wkl_android.charge.detail.popup.PaySelectorPopup;
import com.example.wkl_android.commentorder.ui.activity.CommentOrderActivity;
import com.example.wkl_android.dialog.CommDialog;
import com.example.wkl_android.dialog.DialogUtils;
import com.example.wkl_android.main.shop.address.remake.RemakeAddressActivity;
import com.example.wkl_android.order.confirm.popup.PayTypePopup;
import com.example.wkl_android.order.evaluate.EvaluateActivity;
import com.example.wkl_android.order.main.presenter.OrderPresenter;
import com.example.wkl_android.order.main.ui.IOrderView;
import com.example.wkl_android.order.main.ui.adapter.OrderAdapter;
import com.example.wkl_android.order.main.ui.bean.OrderInfo;
import com.example.wkl_android.order.remake.RemakeOrderActivity;
import com.example.wkl_android.orderpay.ui.activity.OrderPayActivity;
import com.example.wkl_android.orderpaycheck.ui.activity.OrderCheckActivity;
import com.example.wkl_android.utils.ToastUtil;
import com.example.wkl_android.widget.rv.decoration.CustomDecoration;
import com.example.wkl_android.widget.rv.widget.EmptyRecyclerView;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindColor;
import butterknife.BindView;

/**
 * Created by szx
 * on 2020/1/2/002
 */
public class OrderFragment extends BaseFragment<IOrderView, OrderPresenter> implements IOrderView {
    @BindView(R.id.rvOrder)
    RecyclerView rv;

    @BindView(R.id.svList)
    SpringView svList;

    @BindColor(R.color.theme)
    int font_theme;
    @BindColor(R.color.font_black_9)
    int font_black_9;
    private FragmentActivity activity;
    private int status;
    int page = 1;

    ArrayList<OrderInfo> list = new ArrayList<>();
    OrderAdapter adapter;

    CommDialog dialog;

    public static OrderFragment newInstance(int status) {

        Bundle args = new Bundle();
        args.putInt("status", status);
        OrderFragment fragment = new OrderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_order;
    }

    @Override
    protected OrderPresenter createPresenter() {
        return new OrderPresenter();
    }

    @Override
    protected void initViews() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            status = arguments.getInt("status");
        }
        activity = weakReference.get();

        adapter = new OrderAdapter(activity, list);
        rv.setAdapter(adapter);


        CustomDecoration customDecoration = new CustomDecoration(activity,
                CustomDecoration.VERTICAL, R.drawable.shape_ll_divider_gray_10dp);
        rv.addItemDecoration(customDecoration);
        adapter.setListener(new OrderAdapter.OnClickListener() {

            @Override
            public void onPayClick(int position) {

                OrderInfo info = list.get(position);
                Intent intent = new Intent(getContext() , OrderCheckActivity.class);
                intent.putExtra("orderid" , info.getOrderMasterPaymentId());
                startActivity(intent);
//                OrderInfo info = list.get(position);
//
//                Intent intent = new Intent(getContext(), OrderPayActivity.class);
//                intent.putExtra("id", info.getOrderMasterPaymentId());
//                intent.putExtra("price", info.getOrderMasterTotalPrice());
//                startActivity(intent);
            }

            @Override
            public void onConfirmClick(int position) {
                ToastUtil.show("confirm");
            }

            @Override
            public void onLookClick(int position) {
                ToastUtil.show("look");
            }

            @Override
            public void onCacelClick(int position) {
                dialog = DialogUtils.showCommDialog(getFragmentManager(), "提示", "确认取消订单？",
                        "我在想想", v1 -> {
                            dialog.dismiss();
                        }, "取消订单", v1 -> {

                            presenter.cancelOrder(list.get(position).getOrderMasterPaymentId());
                            dialog.dismiss();
                        });
            }

            @Override
            public void onCommentClick(int position) {
                CommentOrderActivity.startActivity(getContext() , list.get(position).getOrderMasterId());

            }
        });
        spList();
        getData();

    }


    private void getData() {
        String state = "";
        switch (status) {
            case 0:
                state = "";
                break;
            case 1:
                state = "8";
                break;
            case 2:
                state = "1";
                break;
            case 3:
                state = "2";
                break;
            case 4:
                state = "3";
                break;
        }
        presenter.getData(page, state);
    }

    private void showPayPopup() {
        PayTypePopup popup = new PayTypePopup(activity);
        popup.show(activity.getWindow().getDecorView());
        popup.setListener(() -> toast("付款成功！"));
    }

    private void spList() {
        //处理上下拉刷新
        svList.setHeader(new DefaultHeader(getContext()));
        svList.setFooter(new DefaultFooter(getContext()));

        svList.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                //加载板块数据
                getData();
            }

            @Override
            public void onLoadmore() {
                page++;
                getData();
            }
        });
    }

    /**
     * 展示取消订单弹框
     */
    private void showCancelDialog() {
        AlertDialog dialog = new AlertDialog.Builder(activity)
                .setTitle("提示")
                .setMessage("订单取消后无法恢复,确定要取消订单吗?")
                .setPositiveButton("确定取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        toast("取消订单成功");
                    }
                })
                .setNegativeButton("暂不取消", null)
                .create();
        dialog.show();
        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(font_black_9);
        dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(font_theme);
    }

    /**
     * 展示延长收货弹框
     */
    private void showDelayDialog() {
        AlertDialog dialog = new AlertDialog.Builder(activity)
                .setTitle("确认延长收货时间？")
                .setMessage("每笔订单只能延长一次哦")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //距离结束时间3天才可以申请
                        toast("亲，距离结束时间3天才可以申请哦");
                    }
                })
                .setNegativeButton("取消", null)
                .create();
        dialog.show();
        dialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(font_theme);
        dialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(font_black_9);
    }


    @Override
    public void setData(ArrayList<OrderInfo> data) {
        svList.onFinishFreshAndLoad();
        if (page == 1) {
            list.clear();
        }
        list.addAll(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void cancelOrder() {
        page = 1;
        getData();
    }
}
