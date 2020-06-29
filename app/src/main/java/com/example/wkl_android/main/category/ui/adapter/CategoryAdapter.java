package com.example.wkl_android.main.category.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wkl_android.R;
import com.example.wkl_android.main.category.model.bean.CategoryName;
import com.example.wkl_android.widget.rv.adapter.BaseAdapter;

import java.util.List;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by szx
 * on 2020/1/2/002
 */
public class CategoryAdapter extends BaseAdapter<CategoryName, CategoryAdapter.ViewHolder> {
    private int checkPosition = -1;

    public int getCheckPosition() {
        return checkPosition;
    }

    public void setCheckPosition(int checkPosition) {
        this.checkPosition = checkPosition;
    }

    public CategoryAdapter(Context context, List<CategoryName> data) {
        super(context, data);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        CategoryName bean = data.get(position);
//        holder.tvCategory.setText(bean.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCheckPosition(position);
                if (listener != null) {
                    listener.onClick(position);
                }
            }
        });
    }

    public interface OnClickListener {
        void onClick(int position);
    }

    private OnClickListener listener;

    public void setListener(OnClickListener listener) {
        this.listener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvCategory)
        TextView tvCategory;
        @BindView(R.id.llItemView)
        View llItemView;
        @BindColor(R.color.theme)
        int font_theme;
        @BindColor(R.color.font_black_3)
        int font_black_3;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
