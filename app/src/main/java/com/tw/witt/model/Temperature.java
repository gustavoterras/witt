package com.tw.witt.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by gustavoterras on 25/01/18.
 */

public class Temperature implements Serializable {

    //region --- ATTRIBUTES ---

    @SerializedName("max")
    private int max;

    @SerializedName("min")
    private int min;

    @SerializedName("unit")
    private String unit;

    //endregion

    //region --- GETTERS AND SETTERS ---

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    //endregion
}
