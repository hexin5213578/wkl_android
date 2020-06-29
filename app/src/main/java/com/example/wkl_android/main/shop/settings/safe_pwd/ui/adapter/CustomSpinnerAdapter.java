package com.example.wkl_android.main.shop.settings.safe_pwd.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wkl_android.R;
import com.example.wkl_android.main.shop.settings.safe_pwd.model.bean.FindList;
import com.example.wkl_android.widget.rv.adapter.BaseAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by szx
 * on 2020/1/4/004
 */
public class CustomSpinnerAdapter extends BaseAdapter<FindList, CustomSpinnerAdapter.ViewHolder> {

     public CustomSpinnerAdapter(Context context) {
        super(context);
    }

    public void setList(List<FindList> list) {
        this.data = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_spinner, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String name = data.get(position).getPname();
        holder.tvSpinner.setText(name);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.setQuestion(name, data.get(position).getPid());
                }
            }
        });
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvSpinner)
        TextView tvSpinner;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnClickListener {
        void setQuestion(String question, String id);
    }

    private OnClickListener listener;

    public void setListener(OnClickListener listener) {
        this.listener = listener;
    }
}
