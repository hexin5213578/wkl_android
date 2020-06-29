package com.example.wkl_android.shop_street.shop_detail.ui.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wkl_android.R;
import com.example.wkl_android.searchshop.ShopGoodsAdapter;
import com.example.wkl_android.searchshop.bean.ShopVo;
import com.example.wkl_android.shop_street.shop_detail.ui.activity.ShopMessageActivity;
import com.example.wkl_android.shop_street.shop_detail.ui.bean.ShopImg;
import com.example.wkl_android.utils.BitmapUtil;
import com.example.wkl_android.utils.DisplayUtil;
import com.example.wkl_android.utils.UIUtil;
import com.example.wkl_android.utils.ViewBgUtils;

import java.util.List;

public class ShopLineImgAdapter extends BaseQuickAdapter<ShopImg, BaseViewHolder> {

    public ShopLineImgAdapter(@Nullable List<ShopImg> data) {
        super(R.layout.shop_img_item, data);
    }

    boolean line = false;

    public void setLine(boolean line) {
        this.line = line;
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopImg item) {

        ImageView iv_img = helper.getView(R.id.iv_img);
        BitmapUtil.showImage(mContext , item.getBusinessImageUrl() , iv_img);

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)iv_img.getLayoutParams();
        int widht =0;

        if(line){
            widht = UIUtil.getScreenWidth()  - DisplayUtil.dipToPixel(30);
        }else {
            widht = UIUtil.getScreenWidth() /2 - DisplayUtil.dipToPixel(20);
        }

        params.width = widht;
        params.height = (int)((double)widht/item.getBusinessWidth() * item.getBusinessHeight());



    }
}
