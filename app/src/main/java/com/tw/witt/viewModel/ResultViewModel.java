package com.tw.witt.viewModel;

import android.content.Context;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.util.Log;

import com.tw.witt.R;
import com.tw.witt.model.City;
import com.tw.witt.model.Day;
import com.tw.witt.model.Weather;
import com.tw.witt.network.ConsumerService;
import com.tw.witt.util.ConstantsUtil;
import com.tw.witt.util.EngineSearch;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;

/**
 * Created by gustavoterras on 27/01/18.
 */

public class ResultViewModel extends BaseViewModel implements ConsumerService.OnTaskCompleted<Day[]> {

    //region --- ATTRIBUTES ---

    private static final int REQUEST_CODE_DAYS = 804;

    private ObservableField<String> result = new ObservableField<>("");

    private List<Weather> weatherList = new ArrayList<>();
    private City city;
    private int days;
    private int maxTemp, minTemp;

    //endregion

    //region --- CONSTRUCTOR ---

    public ResultViewModel(Context context, Bundle bundle) {
        super(context);

        extractBundle(bundle);
    }

    //endregion

    //region --- GETTERS AND SETTERS ---

    public ObservableField<String> getResult() {
        return result;
    }

    public List<Weather> getWeatherList() {
        return weatherList;
    }

    public City getCity() {
        return city;
    }

    public int getDays() {
        return days;
    }

    //endregion

    //region --- CALL API ---

    private void fetchDays(City city) {
        ConsumerService consumerService = new ConsumerService();
        consumerService.setOnTaskCompleted(this);
        consumerService.getDays(city.getId(), Calendar.getInstance().get(Calendar.YEAR), REQUEST_CODE_DAYS);
    }

    @Override
    public void onSuccess(Day[] response, int code, int requestCode) {
        searchResult(response, weatherList);
    }

    @Override
    public void onFailure(Throwable error, int requestCode) {
        Log.e(TAG, "onFailure: ");
    }

    //endregion

    //region --- PRIVATE METHODS ---

    private void extractBundle(Bundle bundle) {
        if (bundle != null) {
            maxTemp = bundle.getInt("maxTemp");
            minTemp = bundle.getInt("minTemp");
            days = bundle.getInt(ConstantsUtil.KEY_DAYS);
            city = bundle.getParcelable(ConstantsUtil.KEY_CITY);
            weatherList = bundle.getParcelableArrayList(ConstantsUtil.KEY_WEATHER);

            fetchDays(city);
        } else {
            Log.e(TAG, "extractBundle");
        }
    }

    private void searchResult(Day[] response, List<Weather> weathers){
        try {

            List<Day> goodDays = EngineSearch.getGoodDays(weathers, maxTemp, minTemp, response);

            Map<String, List<Day>> mapDays = EngineSearch.getSequenceDays(goodDays, getDays(), 1);

            if(mapDays.isEmpty())
                getResult().set(getString(R.string.result_fail));
            else
                for (String key : mapDays.keySet()) {

                    List<Day> days = mapDays.get(key);

                    String strResult = getResult().get();

                    strResult += EngineSearch.formatDate(days.get(0).getDate())
                            + " -> " + EngineSearch.formatDate(days.get(days.size() - 1).getDate()) + "\n";

                    getResult().set(strResult);
                }

        } catch (ParseException e) {
            Log.e(TAG, "ParseException: ", e);
        }
    }

    //endregion
}