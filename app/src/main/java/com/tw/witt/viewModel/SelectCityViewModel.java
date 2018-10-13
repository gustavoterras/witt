package com.tw.witt.viewModel;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.util.Log;
import android.view.View;

import com.tw.witt.BR;
import com.tw.witt.view.activity.MainActivity;
import com.tw.witt.R;
import com.tw.witt.adapter.RecyclerBindingAdapter;
import com.tw.witt.model.City;
import com.tw.witt.model.RecyclerViewConfiguration;
import com.tw.witt.network.ConsumerService;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by gustavoterras on 27/01/18.
 */

public class SelectCityViewModel extends BaseViewModel implements ConsumerService.OnTaskCompleted<City[]>
        , RecyclerBindingAdapter.OnItemClickListener<City>{

    //region --- ATTRIBUTES ---

    private static final int REQUEST_CODE_CITIES = 119;
    private ObservableBoolean valid = new ObservableBoolean(false);

    private RecyclerBindingAdapter adapter;
    private RecyclerViewConfiguration recyclerViewConfiguration;

    //endregion

    //region --- CONSTRUCTOR ---

    public SelectCityViewModel(Context context) {
        super(context);

        initRecycler();
        getCities();
    }

    //endregion

    //region --- PUBLIC METHODS ---

    public void nextStep(){
        if(getActivity() instanceof MainActivity){
            ((MainActivity) getActivity()).getMainViewModel().doNextStep();
        }
    }

    @Override
    public void onItemClick(int position, View view, City city) {
        ((MainActivity) getActivity()).getMainViewModel().setCity(city);

        setCitySelected(position);

        getValid().set(true);
    }

    //endregion

    //region --- GETTERS AND SETTERS ---

    public RecyclerViewConfiguration getRecyclerViewConfiguration() {
        return recyclerViewConfiguration;
    }


    private RecyclerBindingAdapter<City> getCityAdapter() {
        return new RecyclerBindingAdapter<>(R.layout.content_item_list_city, BR.city, new ArrayList<City>());
    }

    public ObservableBoolean getValid() {
        return valid;
    }

    private void setCitySelected(int position){

        for (int i = 0; i < adapter.getItems().size(); i++) {
            ((City) adapter.getItemAtPosition(i)).setSelected(false);
        }

        ((City) adapter.getItemAtPosition(position)).setSelected(true);
    }

    //endregion

    //region --- PRIVATE METHODS ---

    @SuppressWarnings("unchecked")
    private void initRecycler() {
        adapter = getCityAdapter();
        adapter.setOnItemClickListener(this);

        recyclerViewConfiguration = new RecyclerViewConfiguration(getContext());
        recyclerViewConfiguration.setAdapter(adapter);
    }

    //endregion

    //region --- CALL API ---

    private void getCities() {
        ConsumerService consumerService = new ConsumerService();
        consumerService.setOnTaskCompleted(this);
        consumerService.getCities(REQUEST_CODE_CITIES);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onSuccess(City[] response, int code, int requestCode) {
        switch (requestCode){
            case REQUEST_CODE_CITIES:
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