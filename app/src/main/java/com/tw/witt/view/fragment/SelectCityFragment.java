package com.tw.witt.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tw.witt.R;
import com.tw.witt.databinding.FragmentSelectCityBinding;
import com.tw.witt.viewModel.SelectCityViewModel;

/**
 * Created by gustavoterras on 27/01/18.
 */

public class SelectCityFragment extends Fragment {

    private SelectCityViewModel selectCityViewModel;

    public static SelectCityFragment newInstance() {
        return new SelectCityFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        setupViewModel();

        return setupBinding(inflater, container);
    }

    private void setupViewModel(){
        selectCityViewModel = new SelectCityViewModel(getContext());
    }

    private View setupBinding(LayoutInflater inflater, ViewGroup container){
        FragmentSelectCityBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_select_city, container, false);
        binding.setViewModel(selectCityViewModel);

        return binding.getRoot();
    }
}
