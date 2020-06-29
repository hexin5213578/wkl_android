package com.example.wkl_android.login.register.ui.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.wkl_android.base.BaseFragment;

import java.util.List;

/**
 * @author li
 * @since 2019-07-25
 */
public class TabAdapter extends FragmentPagerAdapter {
    private List<BaseFragment> fragments;

    public TabAdapter(FragmentManager fm, List<BaseFragment> lists) {
        super(fm);
        this.fragments = lists;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragments.get(position).getTitle();
    }
}
