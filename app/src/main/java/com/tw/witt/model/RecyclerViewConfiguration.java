package com.tw.witt.model;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.tw.witt.BR;

/**
 * Created by gustavoterras on 25/01/18.
 */

public class RecyclerViewConfiguration extends BaseObservable {

    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.ItemAnimator itemAnimator;
    private RecyclerView.Adapter adapter;

    public RecyclerViewConfiguration(Context context) {
        this.layoutManager = new LinearLayoutManager(context);
        this.itemAnimator = new DefaultItemAnimator();
    }

    @Bindable
    public RecyclerView.LayoutManager getLayoutManager() {
        return layoutManager;
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        this.layoutManager = layoutManager;
        notifyPropertyChanged(BR.layoutManager);
    }

    @Bindable
    public RecyclerView.ItemAnimator getItemAnimator() {
        return itemAnimator;
    }

    public void setItemAnimator(RecyclerView.ItemAnimator itemAnimator) {
        this.itemAnimator = itemAnimator;
        notifyPropertyChanged(BR.itemAnimator);
    }

    @Bindable
    public RecyclerView.Adapter getAdapter() {
        return adapter;
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        this.adapter = adapter;
        notifyPropertyChanged(BR.adapter);
    }
}