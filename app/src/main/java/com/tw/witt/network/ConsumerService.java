package com.tw.witt.network;

import android.support.annotation.NonNull;

import com.tw.witt.BuildConfig;
import com.tw.witt.model.City;
import com.tw.witt.model.Day;
import com.tw.witt.model.Weather;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by gustavoterras on 24/01/18.
 */

@SuppressWarnings("unchecked")
public class ConsumerService {

    //region --- ATTRIBUTES ---

    private OnTaskCompleted listener;
    private IConsumerService service;

    //endregion

    //region --- LISTENER ---

    public interface OnTaskCompleted<T> {

        void onSuccess(T response, int code, int requestCode);

        void onFailure(Throwable error, int requestCode);
    }

    //endregion

    //region --- CONSTRUCTOR ---

    public ConsumerService() {
        service = ServiceGenerator.createService(BuildConfig.DEBUG, BuildConfig.HTTP_URL, IConsumerService.class);
    }

    //endregion

    //region --- GETTERS AND SETTERS ---

    public void setOnTaskCompleted(OnTaskCompleted onTaskCompleted) {
        listener = onTaskCompleted;
    }

    //endregion

    //region --- PUBLIC METHODS ---

    public void getCities(final int requestCode) {
        service.getCities().enqueue(new Callback<City[]>() {
            @Override
            public void onResponse(@NonNull Call<City[]> call, @NonNull Response<City[]> response) {
                listener.onSuccess(response.body(), response.code(), requestCode);
            }

            @Override
            public void onFailure(@NonNull Call<City[]> call, @NonNull Throwable throwable) {
                listener.onFailure(throwable, requestCode);
            }
        });
    }

    public void getWeather(final int requestCode) {
        service.getWeather().enqueue(new Callback<Weather[]>() {
            @Override
            public void onResponse(@NonNull Call<Weather[]> call, @NonNull Response<Weather[]> response) {
                listener.onSuccess(response.body(), response.code(), requestCode);
            }

            @Override
            public void onFailure(@NonNull Call<Weather[]> call, @NonNull Throwable throwable) {
                listener.onFailure(throwable, requestCode);
            }
        });
    }

    public void getDays(long cityId, int year, final int requestCode) {
        service.getDays(cityId, year).enqueue(new Callback<Day[]>() {
            @Override
            public void onResponse(@NonNull Call<Day[]> call, @NonNull Response<Day[]> response) {
                listener.onSuccess(response.body(), response.code(), requestCode);
            }

            @Override
            public void onFailure(@NonNull Call<Day[]> call, @NonNull Throwable throwable) {
                listener.onFailure(throwable, requestCode);
            }
        });
    }

    //endregion
}
