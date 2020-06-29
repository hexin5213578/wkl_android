package com.example.wkl_android.main.shop.address.main.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wkl_android.R;
import com.example.wkl_android.main.shop.address.add.ui.activity.AddOrderAddressActivity;
import com.example.wkl_android.main.shop.address.main.model.bean.Address;
import com.example.wkl_android.main.shop.address.remake.RemakeAddressActivity;
import com.example.wkl_android.widget.rv.adapter.BaseAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by szx
 * on 2019/12/31/031
 */
public abstract class AddressAdapter extends BaseAdapter<Address, AddressAdapter.ViewHolder> implements View.OnClickListener {
    public AddressAdapter(Context context, List<Address> data) {
        super(context, data);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_address, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Address bean = data.get(position);
        //姓名
        holder.tvName.setText("收货人：" + bean.getConsignee());
        //手机号
        holder.tvPhone.setText(bean.getPhoneNumber());
        //是否为默认地址
        holder.iv_check.setImageResource(bean.isIsDefault() ? R.drawable.checked : R.drawable.no_check);
//        holder.tvDefaultAddress.setVisibility(bean.isIsDefault() ? View.VISIBLE : View.GONE);

        holder.tvAddress.setText(bean.getAddressReDetail()+bean.getAddressArea());

        holder.tvRemakeAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),AddOrderAddressActivity.class);
                intent.putExtra("data",bean);
                v.getContext().startActivity(intent);
            }
        });
        holder.itemView.setTag(bean);
        holder.ll_check.setTag(bean);
        holder.ll_del.setTag(bean);
        holder.itemView.setOnClickListener(this);
        holder.ll_check.setOnClickListener(this);
        holder.ll_del.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Address tag = (Address) v.getTag();

        switch (v.getId()) {
            case R.id.ll_check:
                setDefault(tag);
                break;
            case R.id.ll_del:
                delAddress(tag);
                break;
            default:
                click(tag);
                break;
        }

    }

    public abstract void click(Address tag);

    public abstract void setDefault(Address tag);

    public abstract void delAddress(Address tag);


    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvRemakeAddress)
        View tvRemakeAddress;
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvPhone)
        TextView tvPhone;
        @BindView(R.id.tvDefaultAddress)
        View tvDefaultAddress;
        @BindView(R.id.tvAddress)
        TextView tvAddress;
        @BindView(R.id.ll_check)
        LinearLayout ll_check;
        @BindView(R.id.iv_check)
        ImageView iv_check;
        @BindView(R.id.ll_del)
        LinearLayout ll_del;
        @BindView(R.id.ll_edit)
        LinearLayout ll_edit;


        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
