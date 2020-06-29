package com.example.wkl_android.errands.evaluate;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.base.preseter.BasePresenter;
import com.example.wkl_android.main.MainActivity;
import com.example.wkl_android.order.evaluate.adapter.ImagePickerAdapter;
import com.example.wkl_android.shop_street.shop_detail.ui.dialog.ImagePreviewDialog;
import com.example.wkl_android.utils.ImgPicker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindColor;
import butterknife.BindView;

/**
 * 跑腿评价
 */
public class ErrandsEvaluateActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.ivLeft)
    View back;
    @BindView(R.id.tvTitle)
    TextView title;
    @BindView(R.id.tvRight)
    TextView right;

    //商品评价
    @BindView(R.id.ratingBarGoods)
    RatingBar ratingBarGoods;
    @BindView(R.id.tvGoods)
    TextView tvGoods;
    //物流评价
    @BindView(R.id.ratingBarLogistics)
    RatingBar ratingBarLogistics;
    @BindView(R.id.tvLogistics)
    TextView tvLogistics;
    //服务评价
    @BindView(R.id.ratingBarService)
    RatingBar ratingBarService;
    @BindView(R.id.tvService)
    TextView tvService;
    @BindView(R.id.rv)
    RecyclerView rv;

    @BindColor(R.color.theme)
    int font_theme;
    Map<Float, String> map;
    private ImagePickerAdapter adapter;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_errands_evaluate;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initViews() {
        back.setOnClickListener(this);
        title.setText("发表评价");
        right.setVisibility(View.VISIBLE);
        right.setText("发布");
        right.setTextColor(font_theme);
        right.setOnClickListener(this);
        map = new HashMap<>();
        initMap();
        initListeners();
        adapter = new ImagePickerAdapter(this, new ArrayList<>(), false, 5);
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
                ImagePreviewDialog dialog = new ImagePreviewDialog(ErrandsEvaluateActivity.this,
                        data, position);
                dialog.show();
            }
        });
    }

    private void initListeners() {
        ratingBarGoods.setOnRatingBarChangeListener((ratingBar, v, b) -> tvGoods.setText(map.get(v)));
        ratingBarLogistics.setOnRatingBarChangeListener((ratingBar, v, b) -> tvLogistics.setText(map.get(v)));
        ratingBarService.setOnRatingBarChangeListener((ratingBar, v, b) -> tvService.setText(map.get(v)));
    }

    private void initMap() {
        map.put(1.0f, "非常差");
        map.put(2.0f, "差");
        map.put(3.0f, "一般");
        map.put(4.0f, "好");
        map.put(5.0f, "非常好");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvRight:
                toast("评价成功");
                startActivity(new Intent(APP, MainActivity.class));
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
