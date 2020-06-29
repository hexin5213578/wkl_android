package com.example.wkl_android.main.category.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.wkl_android.R;
import com.example.wkl_android.main.category.model.bean.Category;
import com.example.wkl_android.widget.rv.adapter.BaseAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by szx
 * on 2020/1/2/002
 */
public class CategoryDetailAdapter extends BaseAdapter<Category.ChildrenListBeanX, CategoryDetailAdapter.ViewHolder> {

    public CategoryDetailAdapter(Context context, List<Category.ChildrenListBeanX> data) {
        super(context, data);
    }

    public void setList(List<Category.ChildrenListBeanX> list) {
        data = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_category_detail, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        Category.ChildrenListBeanX bean = data.get(position);
//        holder.tvName.setText(bean.getClassifyName());
//        Glide.with(context)
//                .asBitmap()
//                .load(bean.getClassifyImage())
//                .into(holder.iv);

    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.tvName)
        TextView tvName;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
