package com.example.wkl_android.commentorder.adapter;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wkl_android.R;
import com.example.wkl_android.order.detail.model.OrderGoodsInfo;
import com.example.wkl_android.utils.BitmapUtil;

 import java.util.List;


public class CommentGoodsAdapter extends BaseQuickAdapter<OrderGoodsInfo, BaseViewHolder> {
    public CommentGoodsAdapter(@Nullable List<OrderGoodsInfo> data, Activity activity) {
        super(R.layout.comment_goods_item, data);
        mActivity = activity;
    }

    Activity mActivity;

    @Override
    protected void convert(BaseViewHolder helper, OrderGoodsInfo item) {

        ImageView iv_img = helper.getView(R.id.iv_img);
        EditText et_comment = helper.getView(R.id.et_comment);
        RatingBar ratingBar = helper.getView(R.id.ratingBar);

        helper.setText(R.id.tv_title, item.getOrderSlaveCommodityTitle())
                .setText(R.id.tv_type, item.getOrderSlaveCommodityStandardValue());

        BitmapUtil.showImage(mContext, item.getOrderSlaveCommodityImage(), iv_img);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                item.setStar((int)rating);
            }
        });

        et_comment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                item.setCommentDetail(et_comment.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        GridImageAdapter imageAdapter;
        RecyclerView rv_img = helper.getView(R.id.rv_img);

        imageAdapter = new GridImageAdapter(mContext, new GridImageAdapter.onAddPicClickListener() {
            @Override
            public void onAddPicClick() {
                if (addClickListener != null) {
                    addClickListener.onAddClick(helper.getPosition());
                }
            }
        });
        imageAdapter.setList(item.getImgs());
        imageAdapter.setSelectMax(6);
        GridLayoutManager manager = new GridLayoutManager(mContext, 3);
        rv_img.setLayoutManager(manager);
        rv_img.setAdapter(imageAdapter);

    }

    onAddClickListener addClickListener;

    public void setAddClickListener(onAddClickListener addClickListener) {
        this.addClickListener = addClickListener;
    }

    public interface onAddClickListener {
        public void onAddClick(int position);
    }


}