package com.tw.witt.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.tw.witt.BR;

/**
 * Created by gustavoterras on 25/01/18.
 */

public class Weather extends BaseObservable implements Parcelable {

    //region --- ATTRIBUTES ---

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    private boolean selected;
    //endregion

    public Weather() { }

    public Weather(int id, String name) {
        this.id = id;
        this.name = name;
    }

    //region --- GETTERS AND SETTERS ---

    private Weather(Parcel in) {
        id = in.readInt();
        name = in.readString();
    }

    public static final Creator<Weather> CREATOR = new Creator<Weather>() {
        @Override
        public Weather createFromParcel(Parcel in) {
            return new Weather(in);
        }

        @Override
        public Weather[] newArray(int size) {
            return new Weather[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Bindable
    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        notifyPropertyChanged(BR.selected);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
    }

    //region
}
