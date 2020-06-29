package com.example.wkl_android.WholesaleMarketDetail.dapter;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.baidu.mapapi.search.core.PoiInfo;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wkl_android.R;
import com.example.wkl_android.WholesaleMarketDetail.bean.MarketPointVO;
import com.example.wkl_android.utils.BitmapUtil;

import java.util.List;

public class PointAdapter extends BaseQuickAdapter<MarketPointVO, BaseViewHolder> {
    public PointAdapter(@Nullable List<MarketPointVO> data) {
        super(R.layout.market_point_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MarketPointVO item) {

        helper.setText(R.id.tv_name , item.getClassifyName());

        ImageView iv_img = helper.getView(R.id.iv_img);
        BitmapUtil.showRadiusImage(mContext , item.getClassifyImage() , R.mipmap.ic_launcher , 50 , iv_img);

    }

}
