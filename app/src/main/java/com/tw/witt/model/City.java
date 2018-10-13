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

public class City extends BaseObservable implements Parcelable {

    //region --- ATTRIBUTES ---

    @SerializedName("woeid")
    private long id;

    @SerializedName("district")
    private String district;

    @SerializedName("province")
    private String province;

    @SerializedName("state_acronym")
    private String state;

    @SerializedName("country")
    private String country;

    private boolean selected;
    //endregion

    //region --- GETTER AND SETTERS ---

    private City(Parcel in) {
        id = in.readLong();
        district = in.readString();
        province = in.readString();
        state = in.readString();
        country = in.readString();
    }

    public static final Creator<City> CREATOR = new Creator<City>() {
        @Override
        public City createFromParcel(Parcel in) {
            return new City(in);
        }

        @Override
        public City[] newArray(int size) {
            return new City[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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
        parcel.writeLong(id);
        parcel.writeString(district);
        parcel.writeString(province);
        parcel.writeString(state);
        parcel.writeString(country);
    }

    //endregion
}
