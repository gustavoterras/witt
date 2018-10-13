package com.tw.witt.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gustavoterras on 25/01/18.
 */

public class Day implements Parcelable {

    //region --- ATTRIBUTES ---

    @SerializedName("woeid")
    private int id;

    @SerializedName("date")
    private String date;

    @SerializedName("weather")
    private String weather;

    @SerializedName("temperature")
    private Temperature temperature;

    //region

    //region --- GETTERS AND SETTERS ---

    public Day(String date, String weather) {
        this.date = date;
        this.weather = weather;
    }

    private Day(Parcel in) {
        id = in.readInt();
        date = in.readString();
        weather = in.readString();
    }

    public static final Creator<Day> CREATOR = new Creator<Day>() {
        @Override
        public Day createFromParcel(Parcel in) {
            return new Day(in);
        }

        @Override
        public Day[] newArray(int size) {
            return new Day[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(date);
        parcel.writeString(weather);
    }

    //endregion
}
