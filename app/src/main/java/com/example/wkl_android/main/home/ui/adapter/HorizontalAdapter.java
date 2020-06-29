package com.example.wkl_android.main.home.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wkl_android.R;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.good.model.GoodsListBean;
import com.example.wkl_android.utils.DisplayUtil;
import com.example.wkl_android.utils.UIUtil;
import com.example.wkl_android.widget.rv.adapter.BaseAdapter;
import com.sunfusheng.GlideImageView;
import com.sunfusheng.util.Utils;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 主菜单
 * Created by szx
 * on 2020/1/18/018
 */
public class HorizontalAdapter extends BaseAdapter<GoodsListBean.GoodsPlateVOList, HorizontalAdapter.ViewHolder> {

    private int flag;
    private Context mContext;

    public HorizontalAdapter(Context context, List<GoodsListBean.GoodsPlateVOList> data, int flag) {
        super(context, data);
        this.flag = flag;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_home_horizontal, parent, false);
        //重算宽度
        view.getLayoutParams().width = (Utils.getWindowWidth(mContext) - DisplayUtil.dipToPixel(30)) / 4;
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onClick(v, position);
            }
        });
        GoodsListBean.GoodsPlateVOList menuBean = data.get(position);
        holder.tvMainItem.setText(menuBean.getPlateName());
//        holder.icon.load(Common.getResizeImg(menuBean.getPlateImage(), 90, 90));

//        int width = UIUtil.getScreenWidth() / 4 - DisplayUtil.dipToPixel(35);
//
//        if (width > UIUtil.dp2px(60)) {
//            width = UIUtil.dp2px(60);
//        }
//
//        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) holder.icon.getLayoutParams();
//        params.width = width;
//        params.height = width;

        Glide.with(context)
                .load(menuBean.getPlateImage())
                .into(holder.icon);

    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvMainItem)
        TextView tvMainItem;
        @BindView(R.id.icon)
        ImageView icon;
        @BindView(R.id.layWrap)
        LinearLayout layWrap;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnClickListener {
        void onClick(View v, int position);
    }

    private OnClickListener listener;

    public void setListener(OnClickListener listener) {
        this.listener = listener;
    }
}
