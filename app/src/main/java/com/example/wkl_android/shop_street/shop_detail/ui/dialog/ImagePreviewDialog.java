package com.example.wkl_android.shop_street.shop_detail.ui.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.wkl_android.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author li
 * @since 2019-09-07
 */
public class ImagePreviewDialog extends Dialog {

    @BindView(R.id.vpImage)
    ViewPager viewPager;
    private List<String> picList;
    private int position;

    public ImagePreviewDialog(@NonNull Context context, List<String> picList, int postion) {
        super(context, R.style.ActivityDialogStyle);
        this.picList = picList;
        this.position = postion;
    }

    @Override
    @SuppressLint("InflateParams")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View contentView = inflater.inflate(R.layout.activity_image_pre, null);
        setContentView(contentView);

        ButterKnife.bind(this, contentView);

        setCanceledOnTouchOutside(true);

        List<View> list = new ArrayList<>();
        for (String str : picList) {
            View view = inflater.inflate(R.layout.item_image_pre, null);
            ImageView imageView = view.findViewById(R.id.ivPreview);

            Glide.with(getContext())
                    .load(str)
                    .fitCenter()
                    .into(imageView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cancel();
                }
            });
            list.add(view);
        }
        Adapter adapter = new Adapter(list);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(position);
    }

    class Adapter extends PagerAdapter {
        private List<View> mList;

        Adapter(List<View> mList) {
            this.mList = mList;
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = mList.get(position);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, @NonNull Object object) {
            container.removeView(mList.get(position));
        }
    }
}
