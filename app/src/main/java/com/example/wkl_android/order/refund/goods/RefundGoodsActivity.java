package com.example.wkl_android.order.refund.goods;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
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

public class RefundGoodsActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.ivLeft)
    View back;
    @BindView(R.id.tvTitle)
    TextView title;
    @BindView(R.id.tvSelectCause)
    TextView tvSelectCause;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.tvSubmit)
    View tvSubmit;
    private WheelViewPopup popup;
    private ImagePickerAdapter adapter;
    private boolean clickFlag;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_refund_goods;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViews() {
        title.setText("申请退款");
        back.setOnClickListener(this);
        tvSelectCause.setOnClickListener(this);
        tvSubmit.setOnClickListener(this);


        adapter = new ImagePickerAdapter(this, new ArrayList<>(),
                false, 3);
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
                ImagePreviewDialog dialog = new ImagePreviewDialog(RefundGoodsActivity.this,
                        data, position);
                dialog.show();
            }
        });

        List<String> list = new ArrayList<>();
        list.add("7天无理由退换货");
        list.add("配送超时");
        list.add("未按约定时间发货");
        list.add("未送货上门");
        list.add("卖家发错货");
        list.add("少见/漏发");
        popup = new WheelViewPopup(this, "退款原因");
        popup.setList(list);
        popup.setListener(new WheelViewPopup.SetCategoryListener() {
            @Override
            public void setText(int position) {
                clickFlag = true;
                tvSelectCause.setText(String.format("%s  >", list.get(position)));
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvSelectCause:
                popup.show(getWindow().getDecorView());
                break;
            case R.id.tvSubmit:
                if (!clickFlag) {
                    toast("请选择退货原因");
                    return;
                }
                toast("申请成功");
                finish();
                break;
        }
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
