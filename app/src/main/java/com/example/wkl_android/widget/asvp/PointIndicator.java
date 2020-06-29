package com.example.wkl_android.widget.asvp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.wkl_android.R;
import com.example.wkl_android.common.Common;
import com.example.wkl_android.widget.adapter.BaseInfinityPagerAdapter;

/**
 * @author li
 * @since 2018/12/6
 */
public class PointIndicator extends RadioGroup implements ViewPager.OnPageChangeListener {

    private int mCount;

    public PointIndicator(Context context) {
        super(context);
        init();
    }

    private void init() {
        setOrientation(HORIZONTAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        setLayoutParams(params);

    }

    public void bindViewPager(ViewPager viewPager) {
        PagerAdapter adapter = viewPager.getAdapter();
        if (adapter == null) {
            return;
        }
        if (adapter instanceof BaseInfinityPagerAdapter) {
            mCount = ((BaseInfinityPagerAdapter) adapter).getRealCount();
        } else {
            mCount = adapter.getCount();
        }
        requestLayout();

        if (getChildCount() != 0) {
            removeAllViews();
        }
        for (int i = 0; i < mCount; i++) {
            RadioButton rb = new RadioButton(getContext());
            rb.setButtonDrawable(R.drawable.selector_indicator_point);
            LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            int _3dp = Common.dip2px(getContext(), 3);
            params.setMargins(_3dp, 0, _3dp, 0);
            rb.setLayoutParams(params);
            addView(rb);
        }
        viewPager.addOnPageChangeListener(this);
        onPageSelected(0);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        View child = getChildAt(position % mCount);
        if (child == null) {
            return;
        }
        if (child instanceof RadioButton) {
            ((RadioButton) child).setChecked(true);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
