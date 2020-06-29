package com.example.wkl_android.main.shop.settings.information.certification;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.main.shop.settings.information.certification.presenter.CertificationPresenter;
import com.example.wkl_android.main.shop.settings.information.certification.ui.ICertificationView;
import com.example.wkl_android.utils.ImgPicker;
import com.example.wkl_android.utils.LogUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 实名认证
 * Created by szx
 * on 2019/12/31/031
 */
public class CertificationActivity extends BaseActivity<ICertificationView, CertificationPresenter>
        implements ICertificationView, View.OnClickListener {
    @BindView(R.id.tvTitle)
    TextView title;
    @BindView(R.id.ivLeft)
    View back;
    @BindView(R.id.ivIdCardFront)
    ImageView ivIdCardFront;
    @BindView(R.id.ivIdCardBack)
    ImageView ivIdCardBack;
    @BindView(R.id.tvSubmit)
    View tvSubmit;
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.etCode)
    EditText etCode;
    private final int REQUEST_CODE_FRONT = 1;
    private boolean front;
    private String frontUrl;
    private String backUrl;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_certification;
    }

    @Override
    protected CertificationPresenter createPresenter() {
        return new CertificationPresenter();
    }

    @Override
    protected void initViews() {
        title.setText("实名认证");
        back.setOnClickListener(this);
        ivIdCardFront.setOnClickListener(this);
        ivIdCardBack.setOnClickListener(this);
        tvSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int REQUEST_CODE_BACK = 2;
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.ivIdCardFront:
                //身份证反面
                front = true;
                toCamera(REQUEST_CODE_FRONT);
                break;
            case R.id.ivIdCardBack:
                //身份证正面
                front = false;
                toCamera(REQUEST_CODE_BACK);
                break;
            case R.id.tvSubmit:
                submit();
                break;
        }
    }

    /**
     * 提交操作
     */
    private void submit() {
        String name = etName.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            toast("请输入您的姓名");
            return;
        }
        String code = etCode.getText().toString().trim();
        if (TextUtils.isEmpty(code)) {
            toast("请输入身份证号码");
            return;
        }
        if (TextUtils.isEmpty(frontUrl)) {
            toast("请选择身份证正面照片");
            return;
        }
        if (TextUtils.isEmpty(backUrl)) {
            toast("请选择身份证反面照片");
            return;
        }
        presenter.save(name, code, frontUrl, backUrl);
    }

    private void toCamera(int requestCode) {
        startActivityForResult(new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI), requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (data == null) {
                return;
            }
            Uri uri = data.getData();
            if (isPermissionAllow(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                upload(uri, requestCode, front);
            } else {
                int PERMISSION_WRITE_EXTERNAL_STORAGE = 0x1000;
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_WRITE_EXTERNAL_STORAGE);
            }
        }
    }

    /**
     * 通过Uri获取文件真实路径并上传
     *
     * @param uri 文件Uri
     * @author li
     * @since 2019-11-27 17:08
     */
    private void upload(Uri uri, int requestCode, boolean front) {
        if (uri == null) {
            return;
        }
        String imagePath = ImgPicker.getImagePathFromUri(this, uri);
        File file = new File(imagePath);
        List<File> files = new ArrayList<>();
        files.add(file);
        // 头像
        RequestBuilder<Drawable> builder = Glide.with(this)
                .load(file);
        if (REQUEST_CODE_FRONT == requestCode) {
            builder.into(ivIdCardFront);
        } else {
            builder.into(ivIdCardBack);
        }
        long fileSize = Common.getFileSize(file);
        presenter.uploadImg(files, file.getName(), String.valueOf(fileSize), front);
    }

    /**
     * 上传图片成功操作
     *
     * @param netWorkUrl 图片链接
     * @param front      是否为正面
     */
    @Override
    public void handleUploadSuccess(String netWorkUrl, boolean front) {
        if (front) {
            frontUrl = netWorkUrl;
            LogUtils.d("frontUrl:" + frontUrl);
        } else {
            backUrl = netWorkUrl;
            LogUtils.d("backUrl:" + backUrl);
        }
    }

    /**
     * 提交成功处理
     *
     * @param message 返回信息
     */
    @Override
    public void handleSaveSuccess(String message) {
        toast(message);
        finish();
    }
}
