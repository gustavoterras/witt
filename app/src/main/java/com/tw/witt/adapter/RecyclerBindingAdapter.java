package com.tw.witt.adapter;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.AbstractList;

/**
 * Created by gustavoterras on 26/01/18.
 */

public class RecyclerBindingAdapter<T> extends RecyclerView.Adapter<RecyclerBindingAdapter.BindingHolder> {

    //region --- ATTRIBUTES ---

    private AbstractList<T> items;
    private int holderLayout, variableId;
    private OnItemClickListener<T> onItemClickListener;

    //endregion

    //region --- LISTENER ---

    public interface OnItemClickListener<T> {
        void onItemClick(int position, View view, T item);
    }

    //endregion

    //region --- GETTERS AND SETTERS ---

    public void setOnItemClickListener(OnItemClickListener<T> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setList(AbstractList<T> items){
        this.items = items;
        notifyDataSetChanged();
    }

    public AbstractList<T> getItems() {
        return items;
    }

    public T getItemAtPosition(int position){
        return this.items.get(position);
    }

    //endregion

    //region --- CONSTRUCTOR ---

    public RecyclerBindingAdapter(int holderLayout, int variableId, AbstractList<T> items) {
        this.holderLayout = holderLayout;
        this.variableId = variableId;
        this.items = items;
    }

    //endregion

    //region --- PUBLIC METHODS ---

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(holderLayout, parent, false);
        return new BindingHolder(v);
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, @SuppressLint("RecyclerView") final int position) {
        final T item = items.get(position);
        holder.getBinding().getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null)
                    onItemClickListener.onItemClick(position, view, item);
            }
        });

        holder.getBinding().setVariable(variableId, item);
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class BindingHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding binding;

        BindingHolder(View v) {
            super(v);
            binding = DataBindingUtil.bind(v);
        }

        ViewDataBinding getBinding() {
            return binding;
        }
    }

    //endregion
}