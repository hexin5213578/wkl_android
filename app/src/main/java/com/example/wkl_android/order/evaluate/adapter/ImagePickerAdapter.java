package com.example.wkl_android.order.evaluate.adapter;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.wkl_android.R;
import com.example.wkl_android.utils.LogUtils;
import com.example.wkl_android.widget.rv.adapter.BaseAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author li
 * @since 2019-09-06
 */
public class ImagePickerAdapter extends BaseAdapter<String, RecyclerView.ViewHolder> {

    private boolean readOnly;
    private int maxLength;

    public ImagePickerAdapter(Context context, List<String> data, boolean readOnly, int maxLength) {
        super(context, data);
        this.readOnly = readOnly;
        this.maxLength = maxLength;
    }

    public List<String> getData() {
        return data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case R.id.item_type_read_only:
                view = inflater.inflate(R.layout.item_appeal_show, parent, false);
                return new ShowHolder(view);
            case R.id.item_type_sub:
                view = inflater.inflate(R.layout.item_appeal_sub, parent, false);
                return new SubHolder(view);
            case R.id.item_type_add:
            default:
                view = inflater.inflate(R.layout.item_appeal_add, parent, false);
                return new AddHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof AddHolder) {
            // 添加图片
            AddHolder holder = (AddHolder) viewHolder;
            holder.ivAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onAdd(v, ImagePickerAdapter.this);
                    }
                }
            });
            return;
        }
        if (viewHolder instanceof SubHolder) {
            // 带删除按钮
            final SubHolder holder = (SubHolder) viewHolder;
            final String img = getItem(position);
            LogUtils.d("ImagePickerAdapter--"+img);
            Glide.with(context)
                    .load(img)
                    .into(holder.ivImg);
            holder.ivSub.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onDelete(v, ImagePickerAdapter.this, img);
                    }
                }
            });
            holder.ivImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onPreview(v, data, holder.getAdapterPosition());
                    }
                }
            });
            return;
        }
        if (viewHolder instanceof ShowHolder) {
            // 只展示
            final ShowHolder holder = (ShowHolder) viewHolder;
            final String img = getItem(position);
            Glide.with(context)
                    .load(img)
                    .placeholder(R.mipmap.icon_point06)
                    .into(holder.ivImg);
            holder.ivImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onPreview(v, data, holder.getAdapterPosition());
                    }
                }
            });
        }
    }

    private OnClickListener listener;

    @Override
    public int getItemCount() {
        if (data == null) {
            return 0;
        }
        if (readOnly) {
            return Math.min(data.size(), maxLength);
        }
        if (data.size() < maxLength) {
            return data.size() + 1;
        }
        return maxLength;
    }

    @Override
    public String getItem(int position) {
        if (data == null) {
            return "";
        }
        if (readOnly) {
            return super.getItem(position);
        }
        if (data.size() < maxLength) {
            if (position == getItemCount() - 1) {
                return "";
            } else {
                return super.getItem(position);
            }
        }
        return super.getItem(position);
    }

    @Override
    public int getItemViewType(int position) {
        if (readOnly) {
            return R.id.item_type_read_only;
        }
        if (data.size() >= maxLength || position != getItemCount() - 1) {
            return R.id.item_type_sub;
        }
        return R.id.item_type_add;
    }

    public interface OnClickListener {
        void onDelete(View view, ImagePickerAdapter adapter, String bean);

        void onAdd(View view, ImagePickerAdapter adapter);

        void onPreview(View view, List<String> data, int position);
    }

    public void setListener(OnClickListener listener) {
        this.listener = listener;
    }

    /**
     * 添加新图片的Holder
     */
    class AddHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivAdd)
        View ivAdd;

        AddHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    /**
     * 展示/删除 Holder
     */
    class SubHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ivImg)
        ImageView ivImg;
        @BindView(R.id.ivSub)
        View ivSub;

        SubHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    /**
     * 只展示的Holder
     */
    class ShowHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ivImg)
        ImageView ivImg;

        ShowHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
