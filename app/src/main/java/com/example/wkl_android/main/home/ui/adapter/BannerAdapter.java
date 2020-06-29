package com.example.wkl_android.main.home.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.example.wkl_android.R;
import com.example.wkl_android.good.model.GoodsListBean;
import com.example.wkl_android.widget.adapter.BaseInfinityPagerAdapter;
import com.sunfusheng.GlideImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author szx
 * @since 2019-07-28
 */
public class BannerAdapter extends BaseInfinityPagerAdapter<GoodsListBean.GoodsSlideshowVOList> {
    public BannerAdapter(Context mContext, List<GoodsListBean.GoodsSlideshowVOList> mData) {
        super(mContext, mData);
    }

    @Override
    protected View getView(View convertView, int position, GoodsListBean.GoodsSlideshowVOList bean) {
        BannerAdapter.Holder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_banner, null);
            holder = new Holder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.ivBanner.setScaleType(ImageView.ScaleType.CENTER_CROP);
        holder.ivBanner.load(bean.getSlideshowImage());
        return convertView;
    }

    class Holder {
        @BindView(R.id.ivBanner)
        GlideImageView ivBanner;

        Holder(View itemView) {
            ButterKnife.bind(this, itemView);
        }
    }
}
