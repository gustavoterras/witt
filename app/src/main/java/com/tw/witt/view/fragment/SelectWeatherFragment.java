package com.tw.witt.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tw.witt.R;
import com.tw.witt.databinding.FragmentSelectWeatherBinding;
import com.tw.witt.viewModel.SelectWeatherViewModel;

/**
 * Created by gustavoterras on 27/01/18.
 */

public class SelectWeatherFragment extends Fragment {

    private SelectWeatherViewModel selectWeatherViewModel;

    public static SelectWeatherFragment newInstance() {
        return new SelectWeatherFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        setupViewModel();

        return setupBinding(inflater, container);
    }

    private void setupViewModel(){
        selectWeatherViewModel= new SelectWeatherViewModel(getContext());
    }

    private View setupBinding(LayoutInflater inflater, ViewGroup container){
        FragmentSelectWeatherBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_select_weather, container, false);
        binding.setViewModel(selectWeatherViewModel);

        return binding.getRoot();
    }
}
