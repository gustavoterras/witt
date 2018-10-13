package com.tw.witt.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.tw.witt.BR;
import com.tw.witt.adapter.ViewPagerAdapter;

/**
 * Created by gustavoterras on 25/01/18.
 */

public class ViewPagerConfiguration extends BaseObservable {

    private int position = 0;
    private ViewPagerAdapter adapter;
    private ViewPager.OnPageChangeListener onPageChangeListener;

    public ViewPagerConfiguration(AppCompatActivity activity) {
        this.adapter = new ViewPagerAdapter(activity.getSupportFragmentManager());
    }

    @Bindable
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
        notifyPropertyChanged(BR.position);
    }

    @Bindable
    public ViewPagerAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(ViewPagerAdapter adapter) {
        this.adapter = adapter;
        notifyPropertyChanged(BR.adapter);
    }

    @Bindable
    public ViewPager.OnPageChangeListener getOnPageChangeListener() {
        return onPageChangeListener;
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.onPageChangeListener = onPageChangeListener;
        notifyPropertyChanged(BR.onPageChangeListener);
    }
}