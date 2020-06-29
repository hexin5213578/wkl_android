package com.example.wkl_android.main.shop.address.select.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wkl_android.R;
import com.example.wkl_android.main.shop.address.select.model.bean.SelectSites;
import com.example.wkl_android.widget.rv.adapter.BaseAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by szx
 * on 2020/1/6/006
 */
public class SiteAdapter extends BaseAdapter<SelectSites, SiteAdapter.ViewHolder> {
    public SiteAdapter(Context context, List<SelectSites> data) {
        super(context, data);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_site, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SelectSites bean = data.get(position);
        holder.tvSite.setText(bean.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.toNext(bean.getId(),bean.getName());
                }
            }
        });
    }

    public interface SelectSitesListener {
        void toNext(String pid,String name);
    }

    private SelectSitesListener listener;

    public void setListener(SelectSitesListener listener) {
        this.listener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvSite)
        TextView tvSite;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
