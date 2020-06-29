package com.example.wkl_android.main.shop.address.location.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.baidu.mapapi.model.LatLng;
import com.example.wkl_android.R;
import com.example.wkl_android.main.shop.address.location.bean.PoiLocation;
import com.example.wkl_android.widget.rv.adapter.BaseAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PoiLittleAdapter extends BaseAdapter<PoiLocation, PoiLittleAdapter.ViewHolder> {
    public PoiLittleAdapter(Context context, List<PoiLocation> data) {
        super(context, data);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_poi_little, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PoiLocation bean = data.get(position);
        String name = bean.getName();
        holder.tvName.setText(name);
        String address = bean.getAddress();
        holder.tvAddress.setText(address);
        LatLng location = bean.getLocation();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onClick(name, address, location);
                }
            }
        });

    }

    public interface OnClickListener {
        void onClick(String name, String address, LatLng location);
    }

    private OnClickListener listener;

    public void setListener(OnClickListener listener) {
        this.listener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvAddress)
        TextView tvAddress;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
