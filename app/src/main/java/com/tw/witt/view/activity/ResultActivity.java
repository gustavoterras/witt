package com.tw.witt.view.activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tw.witt.R;
import com.tw.witt.databinding.ActivityResultBinding;
import com.tw.witt.viewModel.ResultViewModel;

/**
 * Created by gustavoterras on 24/01/18.
 */

public class ResultActivity extends AppCompatActivity {

    //region --- ATTRIBUTES ---

    private ResultViewModel resultViewModel;

    //endregion

    //region --- LIFE CYCLE ---

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupViewModel();
        setupBinding();
    }

    //endregion

    //region --- PRIVATE METHODS ---

    private void setupViewModel(){
        resultViewModel = new ResultViewModel(this, getIntent().getExtras());
    }

    private void setupBinding(){
        ActivityResultBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_result);
        binding.setViewModel(resultViewModel);
    }

    //endregion
}
