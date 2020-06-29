package com.example.wkl_android.widget.asvp;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

import androidx.viewpager.widget.PagerAdapter;

/**
 * 带指示器的轮播ViewPager
 *
 * @author li
 * @since 2018/12/06
 */
public class AutoScrollVPWithIndicator extends AutoScrollViewPager {

    public AutoScrollVPWithIndicator(Context paramContext) {
        super(paramContext);
    }

    public AutoScrollVPWithIndicator(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
    }

    public void setAdapter(PagerAdapter adapter, ViewGroup container) {
        super.setAdapter(adapter);
        if (container == null) {
            return;
        }
        if (container.getChildCount() != 0) {
            container.removeAllViews();
        }
        PointIndicator indicator = new PointIndicator(getContext());
        indicator.bindViewPager(this);
        container.addView(indicator);
    }
}
