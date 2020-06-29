package com.example.wkl_android.main.new_category;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.wkl_android.R;

import java.util.List;

public abstract class SecondTypeAdapter extends RecyclerView.Adapter<SecondTypeAdapter.Holder> {
    List<TypeBean> list;
    Context context;

    public SecondTypeAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<TypeBean> list) {
        this.list = list;
    }



    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type_second, null,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        String classifyName = list.get(position).getClassifyName();
        holder.textView.setText(classifyName);
        holder.recyclerView.setLayoutManager(new GridLayoutManager(context,3));
        //holder.recyclerView.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));
        TypeItemAdapter adapter = new TypeItemAdapter(context) {
            @Override
            public void selectItem(String text, String id) {
                SecondTypeAdapter.this.selectItem(text,id);
            }
        };
        List<TypeBean.ChildrenListBeanX> childrenList = list.get(position).getChildrenList();

        if(childrenList == null || childrenList.size() <=0){
            holder.recyclerView.setVisibility(View.GONE);
        }else {
            holder.recyclerView.setVisibility(View.VISIBLE);
        }

        adapter.setList(childrenList);
        holder.recyclerView.setAdapter(adapter);


    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    class Holder extends RecyclerView.ViewHolder {
        TextView textView;
        RecyclerView recyclerView;

        public Holder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text);
            recyclerView = itemView.findViewById(R.id.list);
        }
    }

    public abstract void selectItem(String text, String id);
}
