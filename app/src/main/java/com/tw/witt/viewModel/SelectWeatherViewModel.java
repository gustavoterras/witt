package com.tw.witt.viewModel;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;

import com.tw.witt.BR;
import com.tw.witt.R;
import com.tw.witt.adapter.RecyclerBindingAdapter;
import com.tw.witt.model.RecyclerViewConfiguration;
import com.tw.witt.model.Weather;
import com.tw.witt.network.ConsumerService;
import com.tw.witt.view.activity.MainActivity;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by gustavoterras on 27/01/18.
 */

public class SelectWeatherViewModel extends BaseViewModel implements ConsumerService.OnTaskCompleted<Weather[]>
        , RecyclerBindingAdapter.OnItemClickListener<Weather>{

    private ObservableField<String> maxTemp = new ObservableField<>("");
    private ObservableField<String> minTemp = new ObservableField<>("");

    //region --- ATTRIBUTES ---

    private static final int REQUEST_CODE_WEATHER = 238;
    private ObservableBoolean valid = new ObservableBoolean(false);

    private RecyclerBindingAdapter adapter;
    private RecyclerViewConfiguration recyclerViewConfiguration;

    //endregion

    //region --- CONSTRUCTOR ---

    public SelectWeatherViewModel(Context context) {
        super(context);

        initRecycler();
        getWeather();
    }

    //endregion

    //region --- PUBLIC METHODS ---

    public void nextStep(){
        if(getActivity() instanceof MainActivity){
            ((MainActivity) getActivity()).getMainViewModel().setMaxTemp(Integer.parseInt(maxTemp.get()));
            ((MainActivity) getActivity()).getMainViewModel().setMinTemp(Integer.parseInt(minTemp.get()));

            ((MainActivity) getActivity()).getMainViewModel().doNextStep();
        }
    }

    @Override
    public void onItemClick(int position, View view, Weather weather) {
        weather.setSelected(!weather.isSelected());
        ((MainActivity) getActivity()).getMainViewModel().setWeather(weather);

        getValid().set(checkSelectionWeather());
    }

    //endregion

    //region --- PRIVATE METHODS ---

    @SuppressWarnings("unchecked")
    private void initRecycler() {
        adapter = getCityAdapter();
        adapter.setOnItemClickListener(this);

        recyclerViewConfiguration = new RecyclerViewConfiguration(getContext());
        recyclerViewConfiguration.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerViewConfiguration.setAdapter(adapter);
    }

    private boolean checkSelectionWeather(){
        for (int i = 0; i < adapter.getItems().size(); i++) {
            if(((Weather) adapter.getItemAtPosition(i)).isSelected())
                return true;
        }
        return false;
    }

    //endregion

    //region --- GETTERS AND SETTERS ---


    public ObservableField<String> getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(ObservableField<String> maxTemp) {
        this.maxTemp = maxTemp;
    }

    public ObservableField<String> getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(ObservableField<String> minTemp) {
        this.minTemp = minTemp;
    }

    public RecyclerViewConfiguration getRecyclerViewConfiguration() {
        return recyclerViewConfiguration;
    }

    private RecyclerBindingAdapter<Weather> getCityAdapter() {
        return new RecyclerBindingAdapter<>(R.layout.content_item_list_weather, BR.weather, new ArrayList<Weather>());
    }

    public ObservableBoolean getValid() {
        return valid;
    }

    //endregion

    //region --- CALL API ---

    private void getWeather() {
        ConsumerService consumerService = new ConsumerService();
        consumerService.setOnTaskCompleted(this);
        consumerService.getWeather(REQUEST_CODE_WEATHER);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onSuccess(Weather[] response, int code, int requestCode) {
        switch (requestCode){
            case REQUEST_CODE_WEATHER:
                adapter.setList((AbstractList) Arrays.asList(response));
                break;
        }
    }

    @Override
    public void onFailure(Throwable error, int requestCode) {
        Log.e("TAG", "onFailure: ");
    }

    //endregion
}