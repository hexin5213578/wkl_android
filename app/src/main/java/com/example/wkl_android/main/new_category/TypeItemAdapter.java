package com.example.wkl_android.main.new_category;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wkl_android.R;
import com.sunfusheng.GlideImageView;

import java.util.List;

public abstract class TypeItemAdapter extends RecyclerView.Adapter<TypeItemAdapter.Holder> {
    List<TypeBean.ChildrenListBeanX> list;
    Context context;

    public TypeItemAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<TypeBean.ChildrenListBeanX> list) {
        this.list = list;
    }



    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type_detail, null, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        TypeBean.ChildrenListBeanX listBean = list.get(position);
        String classifyName = listBean.getClassifyName();
        holder.textView.setText(classifyName);

        holder.imageView.load(listBean.getClassifyImage(), R.mipmap.ic_launcher, 20, null);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectItem(list.get(position).getClassifyName(), list.get(position).getClassifyId() + "");
            }
        });
        Log.e("ZYY", classifyName);
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    class Holder extends RecyclerView.ViewHolder {
        TextView textView;
        GlideImageView imageView;

        public Holder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.name);
            imageView = itemView.findViewById(R.id.icon);
        }
    }

    public abstract void selectItem(String text, String id);
}
