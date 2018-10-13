package com.tw.witt.viewModel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by gustavoterras on 25/01/18.
 */

public class BaseViewModel extends BaseObservable {

    private Context context;

    public BaseViewModel(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public AppCompatActivity getActivity() {
        return (AppCompatActivity) context;
    }

    public String getString(int res, Object... args){
        return getActivity().getString(res, args);
    }
}
