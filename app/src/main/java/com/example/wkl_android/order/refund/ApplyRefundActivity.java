package com.example.wkl_android.order.refund;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.main.shop.add_shop.ui.popup.WheelViewPopup;
import com.example.wkl_android.order.evaluate.adapter.ImagePickerAdapter;
import com.example.wkl_android.shop_street.shop_detail.ui.dialog.ImagePreviewDialog;
import com.example.wkl_android.utils.ImgPicker;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 申请退款
 */
public class ApplyRefundActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.ivLeft)
    View back;
    @BindView(R.id.tvTitle)
    TextView title;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.tvSelectCause)
    TextView tvSelectCause;
    @BindView(R.id.tvSelectState)
    TextView tvSelectState;
    @BindView(R.id.tvMoney)
    TextView tvMoney;
    @BindView(R.id.etMoney)
    EditText etMoney;
    @BindView(R.id.tvTips)
    TextView tvTips;
    @BindView(R.id.llState)
    View llState;
    @BindView(R.id.tvSubmit)
    View tvSubmit;

    private boolean isClickFlag;
    private ImagePickerAdapter adapter;
    private WheelViewPopup popup;
    //待发货状态的退款原因列表
    private List<String> waitPayList;
    //已发货状态-用户已收到货的退款原因列表
    private List<String> receivedList;
    //已发货状态-用户未收到货的退款原因列表
    private List<String> noReceivedList;
    //已发货状态-货物状态
    private List<String> stateList;
    private int orderState;
    private int statePosition;//0未收到货，1已收到货
    private boolean clickFlag;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_apply_refund;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViews() {
        Intent intent = getIntent();
        if (intent != null) {
            orderState = intent.getIntExtra("orderState", 0);
            if (orderState != 1) {
                tvTips.setText("最多￥100.00，含发货邮费￥0.00");
                llState.setVisibility(View.VISIBLE);
            }
        }
        title.setText("申请退款");
        back.setOnClickListener(this);
        tvSelectCause.setOnClickListener(this);
        tvSelectState.setOnClickListener(this);
        tvSubmit.setOnClickListener(this);
        adapter = new ImagePickerAdapter(this, new ArrayList<>(),
                false, 5);
        rv.setAdapter(adapter);
        adapter.setListener(new ImagePickerAdapter.OnClickListener() {
            @Override
            public void onDelete(View view, ImagePickerAdapter adapter, String bean) {
                adapter.getData().remove(bean);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onAdd(View view, ImagePickerAdapter adapter) {
                startActivityForResult(new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI), 1);
            }

            @Override
            public void onPreview(View view, List<String> data, int position) {
                ImagePreviewDialog dialog = new ImagePreviewDialog(ApplyRefundActivity.this,
                        data, position);
                dialog.show();
            }
        });
        popup = new WheelViewPopup(this, "退款原因");
        initPopList();

    }

    private void initPopList() {
        waitPayList = new ArrayList<>();
        waitPayList.add("拍错/多拍/不想要");
        waitPayList.add("协商一致退款");
        waitPayList.add("其他");

        receivedList = new ArrayList<>();
        receivedList.add("退运费");
        receivedList.add("大小/尺寸/重量与商品描述不符");
        receivedList.add("生产日期/保质期与商品描述不符");
        receivedList.add("品种/产地/规格与商品描述不符");
        receivedList.add("商品腐烂变质死亡");
        receivedList.add("少见/漏发");
        receivedList.add("包装/商品破损");
        receivedList.add("发票问题");
        receivedList.add("卖家发错货");

        noReceivedList = new ArrayList<>();
        noReceivedList.add("不喜欢/不想要");
        noReceivedList.add("空包裹");
        noReceivedList.add("快递/物流一直未送到");
        noReceivedList.add("快递/物流无跟踪记录");
        noReceivedList.add("货物破损已拒签");

        stateList = new ArrayList<>();
        stateList.add("未收到货");
        stateList.add("已收到货");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvSelectCause:
                if (1 == orderState) {
                    //待发货状态
                    showSelectCausePop(waitPayList);
                    break;
                }
                if (!clickFlag) {
                    toast("请先选择货物状态");
                    break;
                }
                tvSelectCause.setText("请选择  >");
                if (0 == statePosition) {
                    //未收到货
                    showSelectCausePop(noReceivedList);
                    break;
                }
                //已收到货
                showSelectCausePop(receivedList);
                break;
            case R.id.tvSelectState:
                showSelectStatePop(stateList);
                break;
            case R.id.tvSubmit:
                if (!isClickFlag) {
                    toast("请选择退货原因");
                    return;
                }
                toast("退款成功");
                finish();
                break;
        }
    }

    /**
     * 展示选择货物状态弹框
     */
    private void showSelectStatePop(List<String> list) {
        popup.setList(list);
        popup.setListener(position -> {
            clickFlag = true;
            statePosition = position;
            tvSelectState.setText(String.format("%s  >", list.get(position)));
            if (position == 0) {
                tvMoney.setVisibility(View.VISIBLE);
                etMoney.setVisibility(View.GONE);
                tvTips.setText("不可修改，最多￥100.00，含发货邮费￥0.00");
            } else {
                tvMoney.setVisibility(View.GONE);
                etMoney.setVisibility(View.VISIBLE);
                tvTips.setText("可修改，最多￥100.00，含发货邮费￥0.00");
            }
        });
        popup.show(getWindow().getDecorView());
    }

    /**
     * 展示选择退款原因弹框
     */
    private void showSelectCausePop(List<String> list) {
        popup.setList(list);
        popup.setListener(new WheelViewPopup.SetCategoryListener() {
            @Override
            public void setText(int position) {
                isClickFlag = true;
                tvSelectCause.setText(String.format("%s  >", list.get(position)));
            }
        });
        popup.show(getWindow().getDecorView());
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        processImagePicker(requestCode, resultCode, data);

    }

    /**
     * 处理选择图片页面返回信息
     *
     * @param requestCode 请求码
     * @param resultCode  结果码
     * @param data        数据
     */
    private void processImagePicker(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK || requestCode != 1) {
            return;
        }
        if (data == null) {
            return;
        }
        String imagePath;
        Uri uri = data.getData();
        if (uri == null) {
            return;
        }
        imagePath = ImgPicker.getImagePathFromUri(APP, uri);
        if (TextUtils.isEmpty(imagePath) || adapter == null) {
            return;
        }
        List<String> strings = adapter.getData();
        strings.add(imagePath);
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

}
