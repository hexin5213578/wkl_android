package com.example.wkl_android.main.home.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wkl_android.R;
import com.example.wkl_android.base.app.BaseApp;
import com.example.wkl_android.main.home.ui.CategoryActivity;
import com.example.wkl_android.main.home.ui.GoodsListActivity;
import com.example.wkl_android.main.home.ui.ThreeMarketActivity;
import com.example.wkl_android.utils.LogUtils;
import com.example.wkl_android.widget.rv.adapter.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryTopAdapter extends BaseAdapter<String, RecyclerView.ViewHolder> {

    private String allName;
    private List<String> list1 = new ArrayList<>();
    private List<String> list2 = new ArrayList<>();
    private List<String> list3 = new ArrayList<>();
    private List<String> list4 = new ArrayList<>();

    public CategoryTopAdapter(Context context, List<String> data) {
        super(context, data);
    }

    public CategoryTopAdapter(Context context, List<String> data, String name, int currentPosition) {
        super(context, data);
        this.allName = name;
        for (int i = 0; i < 9; i++) {
            list1.add("花生油");
            list2.add("白菜");
            list3.add("苹果");
            list4.add("红薯干");
        }
        if (currentPosition == 0) {
            this.data = list1;
        }
        if (currentPosition == 1) {
            this.data = list2;
        }
        if (currentPosition == 2) {
            this.data = list3;
        }
        if (currentPosition == 3) {
            this.data = list4;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == R.id.type_default) {
            View view = inflater.inflate(R.layout.item_category_top, parent, false);
            return new DefaultViewHolder(view);
        } else {
            View view = inflater.inflate(R.layout.item_category_bottom, parent, false);
            return new FooterViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof DefaultViewHolder) {
            DefaultViewHolder holder = (DefaultViewHolder) viewHolder;
            if (!TextUtils.isEmpty(allName)) {
                holder.tvName.setText(data.get(position));
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        toListActivity(data.get(position));
                    }
                });
                return;
            }
            String name = "";
            if (position == 0) {
                name = "粮油";
                holder.tvName.setText(name);

            }
            if (position == 1) {
                name = "蔬菜";
                holder.tvName.setText(name);

            }
            if (position == 2) {
                name = "水果";
                holder.tvName.setText(name);

            }
            if (position == 3) {
                name = "干条";
                holder.tvName.setText(name);
            }
            String finalName = name;
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    toActivity(finalName, position);
                }
            });
        }
        if (viewHolder instanceof FooterViewHolder) {
            FooterViewHolder holder = (FooterViewHolder) viewHolder;
            holder.itemView.setOnClickListener(view -> context.startActivity(new Intent(context,
                    CategoryActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)));
        }
    }

    private void toListActivity(String name) {
        context.startActivity(new Intent(context,
                GoodsListActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                .putExtra("name", name));
    }

    private void toActivity(String name, int position) {
        context.startActivity(new Intent(context,
                ThreeMarketActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                .putExtra("name", name).putExtra("position", position));
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 9) {
            return R.id.type_footer;
        } else {
            return R.id.type_default;
        }
    }

    class DefaultViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvName)
        TextView tvName;

        DefaultViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class FooterViewHolder extends RecyclerView.ViewHolder {
        FooterViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
