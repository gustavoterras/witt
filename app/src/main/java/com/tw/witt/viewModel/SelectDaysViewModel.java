package com.tw.witt.viewModel;

import android.content.Context;
import android.databinding.ObservableField;

import com.tw.witt.model.SeekBarConfiguration;
import com.tw.witt.view.activity.MainActivity;
import com.tw.witt.view.custom.SeekArc;

/**
 * Created by gustavoterras on 27/01/18.
 */

public class SelectDaysViewModel extends BaseViewModel implements SeekArc.OnSeekArcChangeListener {

    //region --- ATTRIBUTES ---

    private ObservableField<String> selectedDays = new ObservableField<>("7");
    private SeekBarConfiguration seekBarConfiguration;

    //endregion

    //region --- CONSTRUCTOR ---

    public SelectDaysViewModel(Context context) {
        super(context);

        initSeekBarConfiguration();
    }

    //endregion

    //region --- PRIVATE METHODS ---

    private void initSeekBarConfiguration(){
        seekBarConfiguration = new SeekBarConfiguration();
        seekBarConfiguration.setProgress(7);
        seekBarConfiguration.setMax(30);
        seekBarConfiguration.setMin(7);
        seekBarConfiguration.setOnSeekChangeListener(this);

        ((MainActivity) getActivity()).getMainViewModel().setDays(7);
    }

    //endregion

    //region --- PUBLIC METHODS ---

    public void nextStep(){
        if(getActivity() instanceof MainActivity){
            ((MainActivity) getActivity()).getMainViewModel().doNextStep();
        }
    }

    public SeekBarConfiguration getSeekBarConfiguration() {
        return seekBarConfiguration;
    }

    public ObservableField<String> getSelectedDays() {
        return selectedDays;
    }

    @Override
    public void onProgressChanged(SeekArc seekArc, int progress, boolean fromUser) {
        getSelectedDays().set(String.valueOf(progress));

        ((MainActivity) getActivity()).getMainViewModel().setDays(progress);
    }

    @Override
    public void onStartTrackingTouch(SeekArc seekArc) {

    }

    @Override
    public void onStopTrackingTouch(SeekArc seekArc) {

    }

    //endregion
}