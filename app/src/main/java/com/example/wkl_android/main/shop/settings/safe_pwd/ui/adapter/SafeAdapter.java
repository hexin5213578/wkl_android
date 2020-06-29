package com.example.wkl_android.main.shop.settings.safe_pwd.ui.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wkl_android.R;
import com.example.wkl_android.main.shop.settings.safe_pwd.model.bean.FindList;
import com.example.wkl_android.main.shop.settings.safe_pwd.ui.activity.SetPwdSafeActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by szx
 * on 2020/1/7/007
 */
public class SafeAdapter extends RecyclerView.Adapter<SafeAdapter.ViewHolder> {
    private Context context;
    private List<FindList> list;

    public SafeAdapter(Context context, List<FindList> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pwd_safe, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvNumber.setText(String.valueOf(position + 1));
        holder.tvSpinner.setText(list.get(position).getPname());
        holder.etAnswer.setTag(position);

    }

    @Override
    public int getItemCount() {
        if (list == null || list.isEmpty()) {
            return 0;
        }
        return list.size();
    }

    public interface SetDataListener {
        void setText(String questionId, int position);

    }

    private SetDataListener listener;

    public void setListener(SetDataListener listener) {
        this.listener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvSpinner)
        TextView tvSpinner;
        @BindView(R.id.etAnswer)
        EditText etAnswer;
        @BindView(R.id.tvNumber)
        TextView tvNumber;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            etAnswer.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (s == null) {
                        return;
                    }
                    int position = (int) etAnswer.getTag();
                    ((SetPwdSafeActivity) context).setEditText(position, s.toString());
                }
            });
        }
    }
}
