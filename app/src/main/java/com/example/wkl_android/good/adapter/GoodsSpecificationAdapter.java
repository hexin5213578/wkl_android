package com.example.wkl_android.good.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.wkl_android.R;
import com.example.wkl_android.base.BaseAdapter;
import com.example.wkl_android.databinding.ItemGoodsSpecificationBinding;
import com.example.wkl_android.good.model.GoodsBean;
import com.example.wkl_android.utils.ViewBgUtils;
import com.example.wkl_android.widget.flowlayout.FlowLayout;
import com.example.wkl_android.widget.flowlayout.TagAdapter;
import com.example.wkl_android.widget.flowlayout.TagFlowLayout;

import org.w3c.dom.Text;

import java.util.List;

/**
 * @author zhangyangyang
 * @date 2019/5/31
 */

public class GoodsSpecificationAdapter extends BaseAdapter<GoodsSpecificationAdapter.Holder, GoodsBean.DataBean.GoodsStaticPreviewSkuStandardSetVOListBean> implements TagFlowLayout.OnTagClickListener {


    public GoodsSpecificationAdapter(List<GoodsBean.DataBean.GoodsStaticPreviewSkuStandardSetVOListBean> dataList, Context context) {
        super(dataList, context);
    }

    @Override
    protected void onBindViewHolder(Holder holder, int position, GoodsBean.DataBean.GoodsStaticPreviewSkuStandardSetVOListBean itemData) {
        //显示标题
        holder.binding.tvTitle.setText(itemData.getSkuStandardName());
        holder.binding.flSpecification.setTag(position);
        holder.binding.flSpecification.setOnTagClickListener(this);
        List<GoodsBean.DataBean.GoodsStaticPreviewSkuStandardSetVOListBean.GoodsStaticPreviewSkuStandardValueSetVOSetBean> a = itemData.getGoodsStaticPreviewSkuStandardValueSetVOSet();

        holder.binding.flSpecification.setAdapter(new TagAdapter<GoodsBean.DataBean.GoodsStaticPreviewSkuStandardSetVOListBean.GoodsStaticPreviewSkuStandardValueSetVOSetBean>
                (a) {
            @Override
            public View getView(FlowLayout parent, int position, GoodsBean.DataBean.GoodsStaticPreviewSkuStandardSetVOListBean.GoodsStaticPreviewSkuStandardValueSetVOSetBean o) {
                View view = LayoutInflater.from(context).inflate(R.layout.item_specification, parent, false);
                ((TextView) view).setText(o.getSpuBuyStandardValue());

                TextView tab = (TextView) view;
                if (o.isIsAt()) {

                    ViewBgUtils.setBg(tab , "#fef6f4" , "#eb2f2d" , 20);

//                    tab.setBackgroundResource(R.drawable.shape_specification);
                    tab.setTextColor(context.getResources().getColor(R.color.color_eb2fed));
                } else {
//                    tab.setBackgroundResource(R.drawable.shape_specification_default);
                    ViewBgUtils.setBg(tab , "#f6f6f6" , "#f6f6f6" , 20);
                    tab.setTextColor(context.getResources().getColor(R.color.color_666666));
                }
                return view;
            }

            @Override
            public boolean setSelected(int position, GoodsBean.DataBean.GoodsStaticPreviewSkuStandardSetVOListBean.GoodsStaticPreviewSkuStandardValueSetVOSetBean item) {
                return item.isIsAt();
            }
        });
    }

    @Override
    public int getLayout(int type) {
        return R.layout.item_goods_specification;
    }

    @Override
    public boolean onTagClick(View view, int position, FlowLayout parent) {
        int tag = (int) parent.getTag();
        Log.e("ZYY", "tag:" + tag);
        List<GoodsBean.DataBean.GoodsStaticPreviewSkuStandardSetVOListBean.GoodsStaticPreviewSkuStandardValueSetVOSetBean> list = dataList.get(tag).getGoodsStaticPreviewSkuStandardValueSetVOSet();
        GoodsBean.DataBean.GoodsStaticPreviewSkuStandardSetVOListBean.GoodsStaticPreviewSkuStandardValueSetVOSetBean item = list.get(position);
        for (int i = 0; i < list.size(); i++) {
            if (i != position) {
                list.get(i).setIsAt(false);
                Log.e("ZYY", "00000" + list.get(i).getSpuBuyStandardValue());
            }
        }
        boolean selected = item.isIsAt();
        item.setIsAt(!selected);
        Log.e("ZYY", "aaa");
        notifyDataSetChanged();
        return false;
    }

    class Holder extends BaseAdapter.BaseHolder<ItemGoodsSpecificationBinding> {
        protected Holder(View itemView) {
            super(itemView);
        }
    }

    public interface Listener {
        void setResult();
    }
}
