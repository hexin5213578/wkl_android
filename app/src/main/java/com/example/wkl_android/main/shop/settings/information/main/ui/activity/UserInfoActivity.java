package com.example.wkl_android.main.shop.settings.information.main.ui.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;
import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.main.MainActivity;
import com.example.wkl_android.main.shop.settings.information.main.model.bean.UserInfo;
import com.example.wkl_android.main.shop.settings.information.main.presenter.UserInfoPresenter;
import com.example.wkl_android.main.shop.settings.information.main.ui.IInfoView;
import com.example.wkl_android.main.shop.settings.information.main.ui.popup.DatePopup;
import com.example.wkl_android.main.shop.settings.information.main.ui.popup.SetHeadPopup;
import com.example.wkl_android.main.shop.settings.information.nick_name.ui.activity.UpdateNickNameActivity;
import com.example.wkl_android.utils.ImgPicker;
import com.example.wkl_android.utils.LogUtils;
import com.example.wkl_android.utils.PictureUtile;
import com.example.wkl_android.utils.SPUtils;
import com.example.wkl_android.utils.ToastUtil;
import com.example.wkl_android.widget.BitmapUtils;
import com.example.wkl_android.widget.CircleImageView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * 基本信息
 * Created by szx
 * on 2019/12/31/031
 */
public class UserInfoActivity extends BaseActivity<IInfoView, UserInfoPresenter>
        implements IInfoView, View.OnClickListener, EasyPermissions.PermissionCallbacks {
    @BindView(R.id.tvTitle)
    TextView title;
    @BindView(R.id.ivLeft)
    View back;
    @BindView(R.id.llHead)
    View llHead;
    @BindView(R.id.ivHead)
    CircleImageView ivHead;
    @BindView(R.id.llNickName)
    View llNickName;
    @BindView(R.id.llDate)
    View llDate;
    @BindView(R.id.llRecommend)
    View llRecomend;
    @BindView(R.id.tvDate)
    TextView tvDate;
    @BindView(R.id.tvNickName)
    TextView tvNickName;
    @BindView(R.id.tvPhone)
    TextView tvPhone;

    private final int PERMISSION_WRITE_EXTERNAL_STORAGE = 0x1000;
    private final int PERMISSION_CAMERA = 0x1001;
    private final int REQUEST_TAKE_PHOTO_CODE = 0x1005;
    private String[] permissions = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    public int getLayoutRes() {
        return R.layout.activity_basic_information;
    }

    @Override
    protected UserInfoPresenter createPresenter() {
        return new UserInfoPresenter();
    }

    @Override
    protected void initViews() {
        title.setText("基本信息");
        back.setOnClickListener(this);
        llHead.setOnClickListener(this);
        llNickName.setOnClickListener(this);
        llDate.setOnClickListener(this);
        llRecomend.setOnClickListener(this);
        tvPhone.setText(Common.getUserPhone());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.llHead:
                PictureUtile.getHead(UserInfoActivity.this, new ArrayList<>());
                break;
            case R.id.llNickName:
                startActivity(new Intent(APP, UpdateNickNameActivity.class).putExtra("type", 0));
                break;
            case R.id.llDate:
                showDatePopup();
                break;
            case R.id.llRecommend:
                startActivity(new Intent(APP, UpdateNickNameActivity.class).putExtra("type", 1));
                break;
        }
    }

    private void showSelectPopup() {
        SetHeadPopup popup = new SetHeadPopup(this);
        popup.show(getWindow().getDecorView());
        popup.setListener(new SetHeadPopup.OnClickListener() {
            @Override
            public void toCamera() {
                getPermission();
            }

            @Override
            public void toPhoto() {
                startActivityForResult(new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI), 1);
            }
        });
    }

    //获取权限
    private void getPermission() {
        if (EasyPermissions.hasPermissions(this, permissions)) {
            //已经打开权限
            Toast.makeText(this, "已经申请相关权限", Toast.LENGTH_SHORT).show();
            takePhoto();
        } else {
            //没有打开相关权限、申请权限
            EasyPermissions.requestPermissions(this, "需要获取您的相册、照相使用权限", REQUEST_TAKE_PHOTO_CODE, permissions);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getUserInfo();
    }

    private void showDatePopup() {
        DatePopup popup = new DatePopup(this);
        popup.show(getWindow().getDecorView());
        popup.setListener(date -> presenter.setBirthday(date));
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    List<LocalMedia> pics = PictureSelector.obtainMultipleResult(data);
                    presenter.upImg(pics.get(0).getCompressPath());
                    break;
            }
        }
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == Activity.RESULT_OK) {
//            switch (requestCode) {
//                case 1:
//                    if (data == null) {
//                        LogUtils.d("data为空");
//                        return;
//                    }
//                    Uri uri1 = data.getData();
//                    if (isPermissionAllow(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
//                        upload(uri1);
//                        String imagePath = ImgPicker.getImagePathFromUri(this, uri1);
//                        ToastUtil.show(imagePath);
//                        presenter.upImg(imagePath);
//                    } else {
//                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_WRITE_EXTERNAL_STORAGE);
//                    }
//                    break;
//                case REQUEST_TAKE_PHOTO_CODE:
//                    String photoPath = null;
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                        photoPath = String.valueOf(cameraSavePath);
//                    } else {
//                        photoPath = uri.getEncodedPath();
//                    }
//                    Bitmap bitmap = BitmapUtils.getBitmap(photoPath, 1024);
//                    LogUtils.d("拍照返回图片路径:--" + photoPath);
//                    presenter.upImg(photoPath);
//                    Glide.with(this).asBitmap().load(bitmap).into(ivHead);
//                    break;
//            }
//        }
//    }

    /**
     * 通过Uri获取文件真实路径并上传
     *
     * @param uri 文件Uri
     */
    private void upload(Uri uri) {
        if (uri == null) {
            return;
        }
        String imagePath = ImgPicker.getImagePathFromUri(this, uri);
        LogUtils.d("userInfo--" + imagePath);
        File file = new File(imagePath);
        List<File> files = new ArrayList<>();
        files.add(file);
        long fileSize = Common.getFileSize(file);
        Glide.with(this)
                .load(imagePath)
                .into(ivHead);
        // presenter.uploadHead(files, file.getName(), String.valueOf(fileSize));
    }

    private File cameraSavePath;//拍照照片路径
    private Uri uri;//照片uri

    /**
     * 调用系统相机拍照
     */
    private void takePhoto() {
        cameraSavePath = new File(Environment.getExternalStorageDirectory().getPath()
                + "/" + System.currentTimeMillis() + ".jpg");
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //第二个参数为 包名.fileprovider
            uri = FileProvider.getUriForFile(this, "com.example.wkl_android.fileprovider", cameraSavePath);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            uri = Uri.fromFile(cameraSavePath);
        }
        LogUtils.d("uri--" + uri);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, REQUEST_TAKE_PHOTO_CODE);
    }

    /**
     * 获取用户信息成功
     *
     * @param bean 用户信息
     */
    @Override
    public void handleUserInfo(UserInfo bean) {
        String userNickname = bean.getUserNickname();
        if (!TextUtils.isEmpty(userNickname)) {
            tvNickName.setText(userNickname);
        }
        String userBirthday = bean.getUserBirthday();
        if (!TextUtils.isEmpty(userBirthday)) {
            tvDate.setText(userBirthday);
        }

        try {
            JSONObject object = new JSONObject(bean.getUserImage());
            String img = object.optString("url");

            Glide.with(this)
                    .load(img)
                    .into(ivHead);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    /**
     * 修改信息成功
     *
     * @param message 返回信息
     */
    @Override
    public void handleSuccess(String message) {
        toast(message);
        presenter.getUserInfo();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //框架要求必须这么写
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        Toast.makeText(this, "相关权限获取成功", Toast.LENGTH_SHORT).show();
        takePhoto();
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        Toast.makeText(this, "请同意相关权限，否则功能无法使用", Toast.LENGTH_SHORT).show();
    }
}
