package com.example.wkl_android.main.shop.add_shop.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.wkl_android.R;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.databinding.ActivityAddShopBinding;
import com.example.wkl_android.http.HttpUtils;
import com.example.wkl_android.http.callback.impl.JsonCallBack;
import com.example.wkl_android.main.new_category.TypeBean;
import com.example.wkl_android.main.shop.add_shop.TypeAdapter;
import com.example.wkl_android.main.shop.add_shop.model.bean.RequestAddShopBean;
import com.example.wkl_android.main.shop.add_shop.model.bean.ResultIsExist;
import com.example.wkl_android.main.shop.add_shop.model.bean.ResultShopMessage;
import com.example.wkl_android.main.shop.settings.information.certification.model.UploadImgModel;
import com.example.wkl_android.main.shop.settings.information.certification.model.bean.ImageBean;
import com.example.wkl_android.utils.C;
import com.example.wkl_android.utils.ImgPicker;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

/**
 * 店铺入驻
 * Created by szx
 * on 2020/1/9/009
 */
@RuntimePermissions
public class AddShopActivity extends AppCompatActivity {
    ActivityAddShopBinding binding;
    private RequestAddShopBean model;
    final int TYPE_ID_ONE = 1;
    final int TYPE_ID_TWO = 2;
    final int TYPE_ID_THREE = 3;
    final int TYPE_SHOP = 4;
    final int TYPE_HEALTH = 5;
    final int TYPE_TRANSPORTATION = 6;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_shop);
        model = new RequestAddShopBean();
        binding.setModel(model);

        binding.group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb1) {
                    model.setBusinessType(1);
                } else if (checkedId == R.id.rb2) {
                    model.setBusinessType(2);
                } else if (checkedId == R.id.rb3) {
                    model.setBusinessType(3);
                }
            }
        });
        binding.idOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddShopActivityPermissionsDispatcher.selectPictureWithPermissionCheck(AddShopActivity.this, TYPE_ID_ONE);
            }
        });
        binding.idTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddShopActivityPermissionsDispatcher.selectPictureWithPermissionCheck(AddShopActivity.this, TYPE_ID_TWO);
            }
        });
        binding.idThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddShopActivityPermissionsDispatcher.selectPictureWithPermissionCheck(AddShopActivity.this, TYPE_ID_THREE);
            }
        });
        binding.ivAddLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddShopActivityPermissionsDispatcher.selectPictureWithPermissionCheck(AddShopActivity.this, TYPE_SHOP);
            }
        });
        binding.health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddShopActivityPermissionsDispatcher.selectPictureWithPermissionCheck(AddShopActivity.this, TYPE_HEALTH);
            }
        });
        binding.transportation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddShopActivityPermissionsDispatcher.selectPictureWithPermissionCheck(AddShopActivity.this, TYPE_TRANSPORTATION);
            }
        });
        binding.type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSheetDialog();
            }
        });
        binding.tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(model.getBusinessCompanyName())) {
                    Toast.makeText(AddShopActivity.this, "请输入企业名称", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(model.getBusinessLinkman())) {
                    Toast.makeText(AddShopActivity.this, "请输入联系人姓名", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(model.getBusinessLinkmanPhoneNumber())) {
                    Toast.makeText(AddShopActivity.this, "请输入联系人电话", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(model.getBusinessIdCardOne())) {
                    Toast.makeText(AddShopActivity.this, "请上传身份证正面", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(model.getBusinessIdCardTwo())) {
                    Toast.makeText(AddShopActivity.this, "请上传身份证背面", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(model.getBusinessIdCardThree())) {
                    Toast.makeText(AddShopActivity.this, "请上传手持身份在身份证", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(model.getBusinessLicense())) {
                    Toast.makeText(AddShopActivity.this, "请上传营业执照", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(model.getBusinessName())) {
                    Toast.makeText(AddShopActivity.this, "请输入店铺名称", Toast.LENGTH_SHORT).show();
                    return;
                } else if (model.getBusinessClassify() == 0) {
                    Toast.makeText(AddShopActivity.this, "请选择店铺分类", Toast.LENGTH_SHORT).show();
                    return;
                } else if (model.getBusinessType() == 0) {
                    Toast.makeText(AddShopActivity.this, "请选择主体类型", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!binding.cb.isChecked()) {
                    Toast.makeText(AddShopActivity.this, "请先阅读协议", Toast.LENGTH_SHORT).show();
                    return;
                }
                HttpUtils.getInstance().doPostJson(C.ADD_SHOP, new Gson().toJson(model), Common.getToken(), "", new JsonCallBack() {
                    @Override
                    public void onSuccess(String json) throws Exception {

                    }
                });

            }
        });
        getData();
    }

    @NeedsPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void selectPicture(int type) {
        startActivityForResult(new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI), type);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RESULT_OK != resultCode) {
            return;
        }
        switch (requestCode) {
            case TYPE_ID_ONE:
                uploadImage(data, requestCode);
                break;
            case TYPE_ID_TWO:
                uploadImage(data, requestCode);
                break;
            case TYPE_ID_THREE:
                uploadImage(data, requestCode);
                break;
            case TYPE_SHOP:
                uploadImage(data, requestCode);
                break;
            case TYPE_HEALTH:
                uploadImage(data, requestCode);
                break;
            case TYPE_TRANSPORTATION:
                uploadImage(data, requestCode);
                break;
            default:
        }
    }

    public void uploadImage(Intent data, int requestCode) {
        Uri uri = data.getData();
        String imagePath = ImgPicker.getImagePathFromUri(AddShopActivity.this, uri);
        File file = new File(imagePath);
        List<File> files = new ArrayList<>();
        files.add(file);
        long fileSize = Common.getFileSize(file);
        UploadImgModel model = new UploadImgModel();
        model.uploadImg(files, file.getName(), fileSize + "", new JsonCallBack() {
            @Override
            public void onSuccess(String json) throws Exception {
                Log.e("ZYY", "图片上传成功" + json);
                ImageBean bean = new Gson().fromJson(json, ImageBean.class);
                switch (requestCode) {
                    case TYPE_ID_ONE:
                        AddShopActivity.this.model.setBusinessIdCardOne(bean.getNetWorkUrl());
                        Glide.with(AddShopActivity.this).load(C.BASE_IMAGE_URL + bean.getNetWorkUrl()).into(binding.oneImage);
                        break;
                    case TYPE_ID_TWO:
                        AddShopActivity.this.model.setBusinessIdCardTwo(bean.getNetWorkUrl());
                        Glide.with(AddShopActivity.this).load(C.BASE_IMAGE_URL + bean.getNetWorkUrl()).into(binding.twoImage);
                        break;
                    case TYPE_ID_THREE:
                        Glide.with(AddShopActivity.this).load(C.BASE_IMAGE_URL + bean.getNetWorkUrl()).into(binding.threeImage);
                        AddShopActivity.this.model.setBusinessIdCardThree(bean.getNetWorkUrl());
                        break;
                    case TYPE_SHOP:
                        Glide.with(AddShopActivity.this).load(C.BASE_IMAGE_URL + bean.getNetWorkUrl()).into(binding.ivAddLogo);
                        AddShopActivity.this.model.setBusinessLicense(bean.getNetWorkUrl());
                        break;
                    case TYPE_HEALTH:
                        Glide.with(AddShopActivity.this).load(C.BASE_IMAGE_URL + bean.getNetWorkUrl()).into(binding.health);
                        AddShopActivity.this.model.setBusinessMedicalCertificate(bean.getNetWorkUrl());
                        break;
                    case TYPE_TRANSPORTATION:
                        Glide.with(AddShopActivity.this).load(C.BASE_IMAGE_URL + bean.getNetWorkUrl()).into(binding.transportation);
                        AddShopActivity.this.model.setBusinessFoodCirculationPermit(bean.getNetWorkUrl());
                        break;
                    default:
                }
            }
        });
    }

    List<TypeBean> typeBean;

    public void getData() {
        HttpUtils.getInstance().doGet(C.GET_FIRST_TYPE, Common.getToken(), this, new JsonCallBack() {
            @Override
            public void onSuccess(String json) throws Exception {

                Gson g = new Gson();
                Type type = new TypeToken<List<TypeBean>>() {
                }.getType();
                typeBean = g.fromJson(json, type);
            }
        });
        HttpUtils.getInstance().doGet(C.IS_EXIST, Common.getToken(), this, new JsonCallBack() {
            @Override
            public void onSuccess(String json) throws Exception {
                Log.e("ZYY", "请求数据成功--" + json);
                ResultIsExist requestIsExist = new Gson().fromJson(json, ResultIsExist.class);
                if (!requestIsExist.getData().isBusinessIsExist() && requestIsExist.getData().isBusinessApplicationIsExist()) {
                    //申请过，但不是商家，---大概就是审核中吧

                    requestInputMessage();

                } else if (!requestIsExist.getData().isBusinessIsExist() && !requestIsExist.getData().isBusinessApplicationIsExist()) {
                    //没申请过


                } else if (requestIsExist.getData().isBusinessIsExist()) {
                    //已经是商家了
                    binding.tip.setText("恭喜您，审核通过，请下载商家版App");
                    binding.tvSubmit.setVisibility(View.GONE);

//                    WaitActivity.showResult(AddShopActivity.this, "审核通过,点击链接下载商家版APP");
//                    finish();
                }

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        AddShopActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    BottomSheetDialog bottomSheetDialog;

    private void showSheetDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_bottom_type, null);
        RecyclerView recyclerView = view.findViewById(R.id.dialog_recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        TypeAdapter adapter = new TypeAdapter() {
            @Override
            public void click(TypeBean item) {
                model.setBusinessType(item.getClassifyId());
                binding.typeValue.setText(item.getClassifyName());
                bottomSheetDialog.dismiss();
            }
        };
        adapter.setTypeBean(typeBean);
        recyclerView.setAdapter(adapter);

        bottomSheetDialog = new BottomSheetDialog(AddShopActivity.this);
        bottomSheetDialog.setContentView(view);
        BottomSheetBehavior<View> mDialogBehavior = BottomSheetBehavior.from((View) view.getParent());
        mDialogBehavior.setPeekHeight(getWindowHeight());//dialog的高度
        mDialogBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int i) {
                if (i == BottomSheetBehavior.STATE_HIDDEN) {
                    bottomSheetDialog.dismiss();
                    mDialogBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }

            @Override
            public void onSlide(@NonNull View view, float v) {

            }
        });
        bottomSheetDialog.show();
    }

    public void requestInputMessage() {
        HttpUtils.getInstance().doGet(C.BUSINESS_MESSAGE, Common.getToken(), this, new JsonCallBack() {
            @Override
            public void onSuccess(String json) throws Exception {
                Log.e("ZYY", "MESSAGE--" + json);
                ResultShopMessage resultShopMessage = new Gson().fromJson(json, ResultShopMessage.class);
                setData(resultShopMessage.getData());

            }
        });
    }

    public void setData(ResultShopMessage.DataBean data) {

        model.setBusinessLinkmanPhoneNumber(data.getBusinessLinkmanPhoneNumber());
        Glide.with(AddShopActivity.this).load(C.BASE_IMAGE_URL + data.getBusinessIdCardOne()).into(binding.oneImage);
        Glide.with(AddShopActivity.this).load(C.BASE_IMAGE_URL + data.getBusinessIdCardTwo()).into(binding.twoImage);
        Glide.with(AddShopActivity.this).load(C.BASE_IMAGE_URL + data.getBusinessIdCardThree()).into(binding.threeImage);
        Glide.with(AddShopActivity.this).load(C.BASE_IMAGE_URL + data.getBusinessLicense()).into(binding.ivAddLogo);
        Glide.with(AddShopActivity.this).load(C.BASE_IMAGE_URL + data.getBusinessMedicalCertificate()).into(binding.health);
        Glide.with(AddShopActivity.this).load(C.BASE_IMAGE_URL + data.getBusinessFoodCirculationPermit()).into(binding.transportation);

        switch (data.getBusinessType()) {
            case 1:
                binding.rb1.setChecked(true);
                break;
            case 2:
                binding.rb2.setChecked(true);
                break;
            case 3:
                binding.rb3.setChecked(true);
                break;
            default:
        }
        binding.cb.setChecked(true);
        binding.typeValue.setText(data.getBusinessClassifyDetail().getClassifyName());
        binding.etBusinessName.setText(data.getBusinessName());
        binding.etCompanyName.setText(data.getBusinessCompanyName());
        binding.etLinkman.setText(data.getBusinessLinkman());
        binding.etLinkmanPhone.setText(data.getBusinessLinkmanPhoneNumber());
        if (!TextUtils.isEmpty(data.getBusinessAuditDate())) {
            WaitActivity.showResult(AddShopActivity.this, "审核不通过\n"+data.getBusinessAuditDetail());
        }else {
            WaitActivity.showResult(AddShopActivity.this, "资料正在审核中,请耐心等待");
            finish();
        }
    }

    private int getWindowHeight() {
        Resources res = this.getResources();
        DisplayMetrics displayMetrics = res.getDisplayMetrics();
        return displayMetrics.heightPixels / 2;
    }
}
