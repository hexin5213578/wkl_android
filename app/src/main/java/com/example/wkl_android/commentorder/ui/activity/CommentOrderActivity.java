package com.example.wkl_android.commentorder.ui.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.base.Constant;
import com.example.wkl_android.commentorder.ICommentOrderView;
import com.example.wkl_android.commentorder.adapter.CommentGoodsAdapter;
import com.example.wkl_android.commentorder.adapter.GridImageAdapter;
import com.example.wkl_android.commentorder.bean.CommentData;
import com.example.wkl_android.commentorder.bean.CommentGoods;
import com.example.wkl_android.commentorder.bean.CommentImg;
import com.example.wkl_android.commentorder.presenter.CommentOrderPresenter;
import com.example.wkl_android.good.model.GoodsListBean;
import com.example.wkl_android.main.home.ui.adapter.HomeGoodsItemAdapter;
import com.example.wkl_android.main.shop.settings.information.main.ui.popup.SetHeadPopup;
import com.example.wkl_android.order.detail.adapter.OrderGoodsAdapter;
import com.example.wkl_android.order.detail.model.OrderDetailInfo;
import com.example.wkl_android.order.detail.model.OrderGoodsInfo;
import com.example.wkl_android.searchinput.ui.activity.SearchInputActivity;
import com.example.wkl_android.utils.LogUtils;
import com.example.wkl_android.utils.PictureUtile;
import com.example.wkl_android.utils.SPUtils;
import com.example.wkl_android.utils.ToastUtil;
import com.example.wkl_android.utils.ViewBgUtils;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 评价订单
 *
 * @author szx
 */
public class CommentOrderActivity extends BaseActivity<ICommentOrderView, CommentOrderPresenter>
        implements ICommentOrderView, GridImageAdapter.onAddPicClickListener, CommentGoodsAdapter.onAddClickListener {


    @BindView(R.id.tvTitle)
    TextView tvTitle;


    @BindView(R.id.tv_send)
    TextView tv_send;

    List<LocalMedia> imgs = new ArrayList<>();
    GridImageAdapter imageAdapter;
    @BindView(R.id.rv_shop_img)
    RecyclerView rv_img;
    ArrayList<String> imgUrls = new ArrayList<>();


    ArrayList<String> run_imgUrls = new ArrayList<>();
    List<LocalMedia> run_imgs = new ArrayList<>();
    GridImageAdapter run_imageAdapter;
    @BindView(R.id.rv_run_img)
    RecyclerView rv_run_img;

    @BindView(R.id.tv_shop_name)
    TextView tv_shop_name;

    @BindView(R.id.shop_ratingBar)
    RatingBar shop_ratingBar;

    @BindView(R.id.et_shop_comment)
    EditText et_shop_comment;

    @BindView(R.id.run_ratingBar)
    RatingBar run_ratingBar;

    @BindView(R.id.et_run_comment)
    EditText et_run_comment;

    String id;
    @BindView(R.id.rv_goods)
    RecyclerView rv_goods;

    CommentData commentData;

    ArrayList<OrderGoodsInfo> goods = new ArrayList<>();
    CommentGoodsAdapter goodsAdapter;

    OrderDetailInfo orderData;

    public static void startActivity(Context context, String id) {
        Intent intent = new Intent(context, CommentOrderActivity.class);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_comment_order;
    }

    @Override
    protected CommentOrderPresenter createPresenter() {
        return new CommentOrderPresenter();
    }

    @Override
    protected void initViews() {
        setTitleBarWithe();
        tvTitle.setText("发布评价");

        id = getIntent().getStringExtra("id");

        ViewBgUtils.setBg(tv_send, "#fd4141", 30);

        imageAdapter = new GridImageAdapter(this, this);
        imageAdapter.setList(imgs);
        imageAdapter.setSelectMax(6);
        GridLayoutManager manager = new GridLayoutManager(this, 3);
        rv_img.setLayoutManager(manager);
        rv_img.setAdapter(imageAdapter);

        initRun();

        LinearLayoutManager lineManager = new LinearLayoutManager(this);
        lineManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_goods.setLayoutManager(lineManager);

        goodsAdapter = new CommentGoodsAdapter(goods, this);
        goodsAdapter.setAddClickListener(this);
        rv_goods.setAdapter(goodsAdapter);

        presenter.getOrderData(id);

    }


    private void initRun() {
        run_imageAdapter = new GridImageAdapter(this, new GridImageAdapter.onAddPicClickListener() {
            @Override
            public void onAddPicClick() {
                addPicPosition = -3;
                PictureUtile.addPic(CommentOrderActivity.this, run_imgs, 6);
            }
        });
        run_imageAdapter.setList(run_imgs);
        run_imageAdapter.setSelectMax(6);
        GridLayoutManager manager = new GridLayoutManager(this, 3);
        rv_run_img.setLayoutManager(manager);
        rv_run_img.setAdapter(run_imageAdapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    List<LocalMedia> pics = PictureSelector.obtainMultipleResult(data);


                    if (addPicPosition == -3) {
                        run_imgs.clear();
                        run_imgs.addAll(pics);
                        run_imageAdapter.notifyDataSetChanged();

                    } else if (addPicPosition == -2) {
                        imgs.clear();
                        imgs.addAll(pics);
                        imageAdapter.notifyDataSetChanged();

                    } else if (addPicPosition >= 0) {
                        goods.get(addPicPosition).getImgs().clear();
                        goods.get(addPicPosition).getImgs().addAll(pics);
                        goodsAdapter.notifyDataSetChanged();
                    }


                    break;
            }
        }
    }


    @OnClick(R.id.tv_send)
    public void onSendClick() {

        if (haveMsg()) {
            showLoading();
            upImg();
        } else {
            ToastUtil.show("请将信息填写完整");
        }


    }

    private boolean haveMsg() {

        for (OrderGoodsInfo goodsInfo : goods) {
            if (goodsInfo.getStar() == 0 || TextUtils.isEmpty(goodsInfo.getCommentDetail())) {
                return false;
            }
        }

        if (shop_ratingBar.getRating() == 0 || TextUtils.isEmpty(et_shop_comment.getText().toString())) {
            return false;
        }

        if (run_ratingBar.getRating() == 0 || TextUtils.isEmpty(et_run_comment.getText().toString())) {
            return false;
        }

        return true;
    }

    int postion = -1;

    private void upImg() {
        for (int i = 0; i < goods.size(); i++) {
            OrderGoodsInfo goodsInfo = goods.get(i);
            if (goodsInfo.getImgs() != null && goodsInfo.getImgs().size() > 0 && goodsInfo.getImgUrls().size() < goodsInfo.getImgs().size()) {
                presenter.upImg(goodsInfo.getImgs().get(goodsInfo.getImgUrls().size()).getCompressPath());
                postion = i;
                return;
            }
        }

        if (imgUrls.size() < imgs.size()) {
            presenter.upImg(imgs.get(imgUrls.size()).getCompressPath());
            postion = -2;
            return;
        }

        if (run_imgUrls.size() < run_imgs.size()) {
            presenter.upImg(run_imgs.get(run_imgUrls.size()).getCompressPath());
            postion = -3;
            return;
        }

        dismissLoading();

        commentData = new CommentData();
        commentData.setOrderMasterId(orderData.getOrderMasterId());
        commentData.setBusinessEstimateDetail(et_shop_comment.getText().toString());
        commentData.setBusinessEstimateGrade((int) shop_ratingBar.getRating());
        CommentImg commentImg;
        for (String img : imgUrls) {
            commentImg = new CommentImg();
            commentImg.setBusinessEstimateImageUrl(img);
            commentData.getBusinessEstimateImageParamList().add(commentImg);
        }

        commentData.setDistributionEstimateDetail(et_run_comment.getText().toString());
        commentData.setDistributionEstimateGrade((int) run_ratingBar.getRating());
        for (String img : run_imgUrls) {
            commentImg = new CommentImg();
            commentImg.setBusinessEstimateImageUrl(img);
            commentData.getDistributionEstimateImageParamList().add(commentImg);
        }

        CommentGoods commentGoods;
        for (OrderGoodsInfo goodsInfo : goods) {
            commentGoods = new CommentGoods();
            commentGoods.setGoodsEstimateDetail(goodsInfo.getCommentDetail());
            commentGoods.setGoodsEstimateGrade(goodsInfo.getStar());
            commentGoods.setOrderSlaveId(goodsInfo.getOrderSlaveId());
            for (String img : goodsInfo.getImgUrls()) {
                commentImg = new CommentImg();
                commentImg.setBusinessEstimateImageUrl(img);
                commentGoods.getGoodsEstimateImageParamList().add(commentImg);
            }
            commentData.getGoodsEstimateParamList().add(commentGoods);
        }

        presenter.commentOrder(commentData);
    }

    @OnClick(R.id.ivLeft)
    public void onBackClick() {
        finish();
    }


    @Override
    public void onAddPicClick() {
        addPicPosition = -2;
        PictureUtile.addPic(this, imgs, 6);
    }

    @Override
    public void upImg(String paht) {
        Log.e("img", paht);
        if (postion >= 0) {
            goods.get(postion).getImgUrls().add(paht);
        } else if (postion == -2) {
            imgUrls.add(paht);
        } else if (postion == -3) {
            run_imgUrls.add(paht);
        }
        upImg();


//        imgUrls.add(paht);
//        if (imgUrls.size() < imgs.size()) {
//            presenter.upImg(imgs.get(imgUrls.size()).getPath());
//        } else {
//            ToastUtil.show("图片上传成功");
//        }
    }

    @Override
    public void setData(OrderDetailInfo data) {

        orderData = data;
        tv_shop_name.setText(data.getBusinessName());

        goods.clear();
        goods.addAll(data.getOrderSlaveVOList());
        goodsAdapter.notifyDataSetChanged();
    }

    @Override
    public void commentSuccess() {
        dismissLoading();
        finish();
    }


    int addPicPosition = -1;

    @Override
    public void onAddClick(int position) {
        addPicPosition = position;
        PictureUtile.addPic(this, goods.get(position).getImgs(), 6);

    }
}
