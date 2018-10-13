package com.tw.witt.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.tw.witt.BR;
import com.tw.witt.view.custom.SeekArc;

/**
 * Created by gustavoterras on 25/01/18.
 */

public class SeekBarConfiguration extends BaseObservable {

    private int max = 0;
    private int min = 0;
    private int progress = 0;

    private SeekArc.OnSeekArcChangeListener onSeekChangeListener;

    @Bindable
    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
        notifyPropertyChanged(BR.max);
    }

    @Bindable
    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
        notifyPropertyChanged(BR.min);
    }

    @Bindable
    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
        notifyPropertyChanged(BR.progress);
    }

    @Bindable
    public SeekArc.OnSeekArcChangeListener getOnSeekChangeListener() {
        return onSeekChangeListener;
    }

    public void setOnSeekChangeListener(SeekArc.OnSeekArcChangeListener onSeekChangeListener) {
        this.onSeekChangeListener = onSeekChangeListener;
        notifyPropertyChanged(BR.onSeekChangeListener);
    }
}