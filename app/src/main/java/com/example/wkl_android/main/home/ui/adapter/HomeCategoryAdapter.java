package com.example.wkl_android.main.home.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wkl_android.R;
import com.example.wkl_android.base.Constant;
import com.example.wkl_android.main.home.model.bean.HomeBean;
import com.example.wkl_android.utils.SPUtils;
import com.example.wkl_android.widget.rv.adapter.BaseAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 首页分类数据
 */
public class HomeCategoryAdapter extends BaseAdapter<HomeBean, HomeCategoryAdapter.ViewHolder> {

    HomeCategoryAdapter(Context context, List<HomeBean> data) {
        super(context, data);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //BANNER 横屏linearLayout Goods Model都是两列但尺寸不同
        if (R.id.home_module == viewType || R.id.home_goods == viewType) {
            View view = inflater.inflate(R.layout.item_home_category, parent, false);
            return new ViewHolder(view);
        } else {
            View view = inflater.inflate(R.layout.item_image_pre, parent, false);
            return new ViewHolder(view);
        }


    }

    @Override
    public int getItemViewType(int position) {
        if (data.get(position).getType() == HomeBean.Type.BANNER) {
            return R.id.home_banner;
        } else if (data.get(position).getType() == HomeBean.Type.GOODS) {
            return R.id.home_goods;
        } else if (data.get(position).getType() == HomeBean.Type.MODULE) {
            return R.id.home_module;
        }

        return R.id.type_default;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (data.get(position).getType() == HomeBean.Type.GOODS) {
            //表示商品列表
            HomeGoodsItemAdapter adapter ;
            LinearLayoutManager manager = new LinearLayoutManager(context);
            manager.setOrientation(LinearLayoutManager.VERTICAL);

            GridLayoutManager gridManager = new GridLayoutManager(context, 2);

            if (SPUtils.getInstance().getBoolean(Constant.GOODS_LIST_TYPE.GOODS_LIST_TYPE, false)) {
                holder.rvHomeItem.setLayoutManager(manager);
                adapter = new HomeGoodsItemAdapter(context, data.get(position).getGoodsListBean().getData(), HomeGoodsItemAdapter.TYPE_LINE);
            } else {
                holder.rvHomeItem.setLayoutManager(gridManager);
                adapter = new HomeGoodsItemAdapter(context, data.get(position).getGoodsListBean().getData(), HomeGoodsItemAdapter.TYPE_GRID);
            }

            setType(holder);

            holder.rvHomeItem.setAdapter(adapter);


            holder.iv_line.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (holder.rvHomeItem.getLayoutManager() == manager) {
                        holder.rvHomeItem.setLayoutManager(gridManager);
                        adapter.setType(HomeGoodsItemAdapter.TYPE_GRID);
                        SPUtils.getInstance().putBoolean(Constant.GOODS_LIST_TYPE.GOODS_LIST_TYPE  , false);
                    } else {
                        holder.rvHomeItem.setLayoutManager(manager);
                        adapter.setType(HomeGoodsItemAdapter.TYPE_LINE);
                        SPUtils.getInstance().putBoolean(Constant.GOODS_LIST_TYPE.GOODS_LIST_TYPE  , true);
                    }

                    holder.rvHomeItem.setAdapter(adapter);
                    setType(holder);
                }
            });
        }
    }

    private void setType(ViewHolder holder){
        if (SPUtils.getInstance().getBoolean(Constant.GOODS_LIST_TYPE.GOODS_LIST_TYPE, false)) {
            holder.iv_line.setImageResource(R.drawable.ic_goods_line);
        }else {
            holder.iv_line.setImageResource(R.drawable.ic_goods_grid);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.rvHomeItem)
        RecyclerView rvHomeItem;


        @BindView(R.id.iv_line)
        ImageView iv_line;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
