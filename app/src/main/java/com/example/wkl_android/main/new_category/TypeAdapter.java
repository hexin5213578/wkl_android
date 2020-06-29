package com.example.wkl_android.main.new_category;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.wkl_android.R;

import java.util.List;

public abstract class TypeAdapter extends RecyclerView.Adapter<TypeAdapter.Holder> {
    List<TypeBean> list;
    Context context;

    public TypeAdapter(Context context) {
        this.context = context;
    }

    int position = 0;

    public void setList(List<TypeBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        if (position == this.position) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                holder.textView.setTextColor(context.getColor(R.color.color_fd1100));
                holder.textView.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
                holder.textView.setBackgroundColor(context.getColor(R.color.color_f3f3f3));
                holder.tag.setVisibility(View.VISIBLE);
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                holder.textView.setTextColor(context.getColor(R.color.color_999999));
                holder.textView.setTypeface(Typeface.DEFAULT);
                holder.textView.setBackgroundColor(context.getColor(R.color.white));
                holder.tag.setVisibility(View.GONE);
            }
        }
        String classifyName = list.get(position).getClassifyName();
        holder.textView.setText(classifyName);
        holder.textView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.e("ZYY", list.get(position).getClassifyId() + "__");
                TypeAdapter.this.position = position;
                selectItem(list.get(position).getClassifyName(), list.get(position).getClassifyId() + "");
                notifyDataSetChanged();
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
        View tag;

        public Holder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text);
            tag = itemView.findViewById(R.id.selected);
        }
    }

    public abstract void selectItem(String text, String id);
}
