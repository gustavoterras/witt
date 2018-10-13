package com.tw.witt.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tw.witt.R;
import com.tw.witt.databinding.ActivityMainBinding;
import com.tw.witt.viewModel.MainViewModel;

/**
 * Created by gustavoterras on 24/01/18.
 */

public class MainActivity extends AppCompatActivity {

    //region --- ATTRIBUTES ---

    private MainViewModel mainViewModel;

    //endregion

    //region --- LIFE CYCLE ---

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupViewModel();
        setupBinding();
    }

    //endregion

    //region --- PUBLIC METHODS ---

    public MainViewModel getMainViewModel() {
        return mainViewModel;
    }

    @Override
    public void onBackPressed() {
        getMainViewModel().doBackStep();
    }

    //endregion

    //region --- PRIVATE METHODS ---

    private void setupViewModel(){
        mainViewModel = new MainViewModel(this);
    }

    private void setupBinding(){
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewModel(mainViewModel);
    }

    //endregion
}