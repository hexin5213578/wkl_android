package com.example.wkl_android.faceback.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.wkl_android.R;
import com.example.wkl_android.WholesaleMarketDetail.bean.MarketAd;
import com.example.wkl_android.base.BaseActivity;
import com.example.wkl_android.base.Constant;
import com.example.wkl_android.commentorder.adapter.GridImageAdapter;
import com.example.wkl_android.commentorder.ui.activity.CommentOrderActivity;
import com.example.wkl_android.faceback.IFaceBackView;
import com.example.wkl_android.faceback.adapter.FBTypeAdapter;
import com.example.wkl_android.faceback.bean.FadebackType;
import com.example.wkl_android.faceback.presenter.FaceBackPresenter;
import com.example.wkl_android.good.model.GoodsListBean;
import com.example.wkl_android.main.home.ui.adapter.HomeGoodsItemAdapter;
import com.example.wkl_android.searchinput.ui.activity.SearchInputActivity;
import com.example.wkl_android.searchshop.ShopAdapter;
import com.example.wkl_android.searchshop.bean.ShopVo;
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

import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 登录页面
 *
 * @author szx
 */
public class FaceBackActivity extends BaseActivity<IFaceBackView, FaceBackPresenter>
        implements IFaceBackView {

    @BindView(R.id.tvTitle)
    TextView tvTitle;

    @BindView(R.id.tv_send)
    TextView tv_send;

    @BindView(R.id.rv_list)
    RecyclerView rv_list;

    @BindView(R.id.rv_img)
    RecyclerView rv_img;
    GridImageAdapter run_imageAdapter;
    List<LocalMedia> run_imgs = new ArrayList<>();


    ArrayList<FadebackType> types = new ArrayList<>();
    FBTypeAdapter typeAdapter;
    String typeTitle[] = {"功能异常", "产品建议", "性能体验"};
    String typeMsg[] = {"不能正常使用现有功能", "用的不爽、建议都提过来吧", "白屏、卡顿、闪退、图片出不来"};

    @Override
    public int getLayoutRes() {
        return R.layout.activity_faceback;
    }

    @Override
    protected FaceBackPresenter createPresenter() {
        return new FaceBackPresenter();
    }

    @Override
    protected void initViews() {
        setTitleBarWithe();
        tvTitle.setText("意见反馈");
        ViewBgUtils.setBg(tv_send, "#ff453b", 50);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_list.setLayoutManager(manager);

        typeAdapter = new FBTypeAdapter(types);
        rv_list.setAdapter(typeAdapter);


        setType();
        initImg();
    }

    private void initImg() {
        run_imageAdapter = new GridImageAdapter(this, new GridImageAdapter.onAddPicClickListener() {
            @Override
            public void onAddPicClick() {
                PictureUtile.addPic(FaceBackActivity.this, run_imgs, 4);
            }
        });
        run_imageAdapter.setList(run_imgs);
        run_imageAdapter.setSelectMax(4);
        GridLayoutManager manager = new GridLayoutManager(this, 3);
        rv_img.setLayoutManager(manager);
        rv_img.setAdapter(run_imageAdapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    List<LocalMedia> pics = PictureSelector.obtainMultipleResult(data);
                    run_imgs.clear();
                    run_imgs.addAll(pics);
                    run_imageAdapter.notifyDataSetChanged();

                    break;
            }
        }
    }

    private void setType() {
        FadebackType type;
        for (int i = 0; i < typeTitle.length; i++) {
            type = new FadebackType();
            type.setTitle(typeTitle[i]);
            type.setMsg(typeMsg[i]);
            types.add(type);
        }

        typeAdapter.notifyDataSetChanged();
        typeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                for (FadebackType item : types) {
                    item.setSelect(false);
                }
                types.get(position).setSelect(true);
                typeAdapter.notifyDataSetChanged();
            }
        });
    }

    @OnClick(R.id.ivLeft)
    public void onBackClick() {
        finish();
    }

    @OnClick(R.id.tv_send)
    public void onSendClick() {

    }


    @Override
    public void showData(GoodsListBean bean) {

    }
}
