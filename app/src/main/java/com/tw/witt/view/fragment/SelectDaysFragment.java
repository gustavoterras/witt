package com.tw.witt.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tw.witt.R;
import com.tw.witt.databinding.FragmentSelectDaysBinding;
import com.tw.witt.viewModel.SelectDaysViewModel;

/**
 * Created by gustavoterras on 27/01/18.
 */

public class SelectDaysFragment extends Fragment {

    private SelectDaysViewModel selectDaysViewModel;

    public static SelectDaysFragment newInstance() {
        return new SelectDaysFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        setupViewModel();

        return setupBinding(inflater, container);
    }

    private void setupViewModel(){
        selectDaysViewModel = new SelectDaysViewModel(getContext());
    }

    private View setupBinding(LayoutInflater inflater, ViewGroup container){
        FragmentSelectDaysBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_select_days, container, false);
        binding.setViewModel(selectDaysViewModel);

        return binding.getRoot();
    }
}
