package com.example.wkl_android.good.model;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.wkl_android.Event.AddCarEvent;
import com.example.wkl_android.R;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.databinding.DialogGoodSelBinding;
import com.example.wkl_android.good.adapter.GoodsSpecificationAdapter;
import com.example.wkl_android.utils.StringUtil;
import com.example.wkl_android.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableField;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import org.greenrobot.eventbus.EventBus;


/**
 * 商品详情-选择商品
 * 作者：Mr.X
 * 时间：10:48
 */
public class GoodsSelDialog extends DialogFragment {

    private DialogGoodSelBinding binding;

    private String flag;
    public final String FLAG_ADD_TO_CAR = "1";
    public final String FLAG_PAY = "2";
    public final String FLAG_LOOK = "3";
    private Listener listener;
    public ObservableField<String> number;

    private GoodsBean.DataBean dataBean;
    private List<GoodsBean.DataBean.GoodsStaticPreviewSkuStandardSetVOListBean> skuPreviewData; //处理sku是否选中
    private List<GoodsBean.DataBean.GoodsStaticPreviewSkuVOListBean> skuDetailData; //处理sku详细信息

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public GoodsSelDialog(GoodsBean.DataBean data) {
        this.dataBean = data;
        this.skuPreviewData = data.getGoodsStaticPreviewSkuStandardSetVOList();
        this.skuDetailData = data.getGoodsStaticPreviewSkuVOList();
    }



    @Override
    public int show(@NonNull FragmentTransaction transaction, @Nullable String tag) {
        adapter.setData(skuPreviewData);
        return super.show(transaction, tag);
    }

    GoodsSpecificationAdapter adapter;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.dialog_good_sel, null);

        binding = DataBindingUtil.bind(view);
        number = new ObservableField<>();
        number.set("1");
        binding.setNumber(this);
        assert binding != null;

        //第一个sku价格和库存

        binding.mTvPrice.setText(StringUtil.changeSizeByDot(skuDetailData.get(0).getSkuPrice() , 0.7f));
        binding.mTvNum.setText(skuDetailData.get(0).getSkuStockNum() + "");
        //默认显示第一个sku图片，点击可切换
        Glide.with(getContext()).load(Common.getResizeImg(dataBean.getProductImage(), 60, 60)).into(binding.mIvIco);
        GoodsSpecificationAdapter.Listener listener = new GoodsSpecificationAdapter.Listener() {
            @Override
            public void setResult() {

            }
        };
        adapter = new GoodsSpecificationAdapter(skuPreviewData, getContext());
        binding.mRecycler.setAdapter(adapter);
        binding.mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.mRlBg.setOnClickListener(v -> dismiss());
        Dialog dialog = new Dialog(getActivity(), R.style.PXDialog);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        assert window != null;
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = -1;
        params.height = -1;
        window.setAttributes(params);
        initListener();
        binding.mTvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<String> ids = new ArrayList<>();
                for (GoodsBean.DataBean.GoodsStaticPreviewSkuStandardSetVOListBean item : skuPreviewData) {
                    for (GoodsBean.DataBean.GoodsStaticPreviewSkuStandardSetVOListBean.GoodsStaticPreviewSkuStandardValueSetVOSetBean sku : item.getGoodsStaticPreviewSkuStandardValueSetVOSet()) {
                        if (sku.isIsAt()) {
                            ids.add(sku.getSpuBuyStandardValue());
                        }
                    }
                }

                for (GoodsBean.DataBean.GoodsStaticPreviewSkuVOListBean sku : skuDetailData) {
                    int num = 0;
                    for (GoodsBean.DataBean.GoodsStaticPreviewSkuVOListBean.GoodsSkuStandardVOListBeanX item : sku.getGoodsSkuStandardVOList()) {
                        for (String id : ids) {
                            if (TextUtils.equals(item.getSkuStandardValue(), id)) {
                                num++;
                            }
                        }
                    }

                    if (num == sku.getGoodsSkuStandardVOList().size()) {
                        EventBus.getDefault().post(new AddCarEvent(sku.getSkuId() , binding.input.getText().toString()));
                        dismiss();
                        return;
                    }

                }

                dismiss();

            }

        });
        return dialog;
    }

    private void initListener() {
        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer integer = Integer.valueOf(number.get());
                number.set(String.valueOf(integer + 1));
                Log.e("ZYY", number.get());

            }
        });
        binding.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer integer = Integer.valueOf(number.get());
                if (integer > 1) {
                    number.set(String.valueOf(integer - 1));
                }
            }
        });

    }

    public interface Listener {
        void select(String count, String flag);
    }

}
