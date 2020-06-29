package com.example.wkl_android.widget.adapter;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * @author li
 * @since 2019-05-23
 */
public class PagerAdapter extends FragmentStatePagerAdapter {
    private List<? extends Fragment> mList;

    public PagerAdapter(FragmentManager fm, List<? extends Fragment> mList) {
        super(fm);
        this.mList = null;
        this.mList = mList;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }
}
