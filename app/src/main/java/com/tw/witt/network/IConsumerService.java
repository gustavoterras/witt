package com.tw.witt.network;

import com.tw.witt.model.City;
import com.tw.witt.model.Day;
import com.tw.witt.model.Weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by gustavoterras on 24/01/18.
 */

public interface IConsumerService {

    @GET("/cities/")
    Call<City[]> getCities();

    @GET("/cities/{cityId}/year/{year}")
    Call<Day[]> getDays(@Path("cityId") long cityId, @Path("year") int year);

    @GET("/weather/")
    Call<Weather[]> getWeather();

}
