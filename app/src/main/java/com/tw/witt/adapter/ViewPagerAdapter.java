package com.tw.witt.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gustavoterras on 25/01/18.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragments;

    public ViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        this.fragments = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        return getFragments().get(position);
    }

    @Override
    public int getCount() {
        return getFragments().size();
    }

    public List<Fragment> getFragments() {
        return fragments;
    }

    public void add(Fragment f) {
        getFragments().add(f);
    }
}
