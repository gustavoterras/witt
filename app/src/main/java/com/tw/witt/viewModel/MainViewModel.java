package com.tw.witt.viewModel;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableInt;
import android.os.Bundle;
import android.os.Parcelable;

import com.tw.witt.model.City;
import com.tw.witt.model.ViewPagerConfiguration;
import com.tw.witt.model.Weather;
import com.tw.witt.util.ConstantsUtil;
import com.tw.witt.view.activity.ResultActivity;
import com.tw.witt.view.fragment.SelectCityFragment;
import com.tw.witt.view.fragment.SelectDaysFragment;
import com.tw.witt.view.fragment.SelectWeatherFragment;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by gustavoterras on 26/01/18.
 */

public class MainViewModel extends BaseViewModel {

    //region --- ATTRIBUTES ---

    private HashSet<Weather> weatherSet = new HashSet<>();
    private int days;
    private City city;

    private int maxTemp, minTemp;

    private ViewPagerConfiguration viewPagerConfiguration;
    private ObservableInt currentPosition = new ObservableInt(0);

    //endregion

    //region --- CONSTRUCTOR ---

    public MainViewModel(Context context) {
        super(context);

        viewPagerConfiguration = new ViewPagerConfiguration(getActivity());

        initViewPager();
    }

    //endregion

    //region --- PRIVATE METHODS ---

    private void initViewPager(){
        getViewPagerConfiguration().getAdapter().add(SelectDaysFragment.newInstance());
        getViewPagerConfiguration().getAdapter().add(SelectCityFragment.newInstance());
        getViewPagerConfiguration().getAdapter().add(SelectWeatherFragment.newInstance());
    }

    private void assemblyPosition(boolean flag){
        int newPosition = getViewPagerConfiguration().getPosition() + (flag ? +1 : -1);

        if(newPosition < 0)
            getActivity().finish();
        else if(newPosition == getViewPagerConfiguration().getAdapter().getCount())
            goToResultActivity();
        else {
            getViewPagerConfiguration().setPosition(newPosition);
            getCurrentPosition().set(newPosition);
        }
    }

    private void goToResultActivity(){
        Bundle bundle = new Bundle();

        bundle.putInt("maxTemp", maxTemp);
        bundle.putInt("minTemp", minTemp);
        bundle.putInt(ConstantsUtil.KEY_DAYS, days);
        bundle.putParcelable(ConstantsUtil.KEY_CITY, city);
        bundle.putParcelableArrayList(ConstantsUtil.KEY_WEATHER, new ArrayList<Parcelable>(weatherSet));

        getActivity().startActivity(new Intent(getActivity(), ResultActivity.class).putExtras(bundle));
    }

    //endregion

    //region --- PUBLIC METHODS ---


    public int getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(int maxTemp) {
        this.maxTemp = maxTemp;
    }

    public int getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(int minTemp) {
        this.minTemp = minTemp;
    }

    public void doNextStep(){
        assemblyPosition(true);
    }

    public void doBackStep(){
        assemblyPosition(false);
    }

    //endregion

    //region --- GETTERS AND SETTERS ---

    public ObservableInt getCurrentPosition() {
        return currentPosition;
    }

    public ViewPagerConfiguration getViewPagerConfiguration() {
        return viewPagerConfiguration;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public void setWeather(Weather weather) {

        if(weather.isSelected())
            this.weatherSet.add(weather);
        else
            this.weatherSet.remove(weather);
    }

    //endregion
}