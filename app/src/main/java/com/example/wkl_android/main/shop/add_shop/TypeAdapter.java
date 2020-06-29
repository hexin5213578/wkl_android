package com.example.wkl_android.main.shop.add_shop;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wkl_android.R;
import com.example.wkl_android.main.new_category.TypeBean;

import java.util.List;

public abstract class TypeAdapter extends RecyclerView.Adapter<TypeAdapter.Holder> implements View.OnClickListener {
    List<TypeBean> typeBean;

    @Override
    public void onClick(View v) {
        click((TypeBean) v.getTag());
    }

    public void setTypeBean(List<TypeBean> typeBean) {
        this.typeBean = typeBean;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type_class, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.tv.setTag(typeBean.get(position));
        holder.tv.setOnClickListener(this::onClick);
        holder.tv.setText(typeBean.get(position).getClassifyName());
    }

    @Override
    public int getItemCount() {
        return typeBean == null ? 0 : typeBean.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        TextView tv;
        public Holder(@NonNull View itemView) {
            super(itemView);
            tv=itemView.findViewById(R.id.text);
        }
    }
    abstract public void click(TypeBean item);
}
