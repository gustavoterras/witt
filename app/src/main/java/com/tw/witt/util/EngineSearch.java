package com.tw.witt.util;

import android.util.Log;

import com.tw.witt.model.Day;
import com.tw.witt.model.Weather;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by gustavoterras on 26/01/18.
 */

public class EngineSearch {

    private static SimpleDateFormat sdf =
            new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    private static SimpleDateFormat sdff =
            new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());

    public static String formatDate (String date) {

        Date mDate = null;
        try {
            mDate = sdf.parse(date);
        } catch (ParseException e) {
            Log.e("TAG", "formatDate: ");
        }

        return mDate != null ? sdff.format(mDate) : "";
    }

    private static long getTimeMillisOfDay(List<Day> days, int position) throws ParseException {
        return sdf.parse(days.get(position).getDate()).getTime();
    }

    public static Map<String, List<Day>> getSequenceDays(List<Day> days, int nDays, int maxAvoid) throws ParseException {
        Map<String, List<Day>> mapDays = new HashMap<>();

        for (int i = 1; i < days.size(); i++) {

            long lastDatetime = getTimeMillisOfDay(days, i == 1 ? 0 : i - 1);
            long currentDateTime = getTimeMillisOfDay(days, i);
            long MILLISECONDS_PER_DAY = 1000L * 60 * 60 * 24;

            if (currentDateTime - lastDatetime <= (maxAvoid * MILLISECONDS_PER_DAY)) {

                List<Day> subGoodDays = days.subList(i, days.size() - 1);
                List<Day> bestDays = new ArrayList<>();

                for (int j = 1; j < subGoodDays.size(); j++) {

                    int index = j == 1 ? 0 : j - 1;
                    Day day = subGoodDays.get(index);

                    lastDatetime = getTimeMillisOfDay(subGoodDays, index);
                    currentDateTime = getTimeMillisOfDay(subGoodDays, j);

                    if (currentDateTime - lastDatetime <= (maxAvoid * MILLISECONDS_PER_DAY)) {
                        bestDays.add(day);

                        i = i + j;
                    } else {
                        if (bestDays.size() >= nDays)
                            mapDays.put("day_" + i, bestDays);

                        break;
                    }
                }

                if (bestDays.size() >= nDays)
                    mapDays.put("day_" + i, bestDays);
            }
        }

        return mapDays;
    }

    public static List<Day> getGoodDays(List<Weather> weatherList, Day... days) {
        List<Day> goodDays = new ArrayList<>();

        for (Day day : days) {
            for (Weather weather : weatherList) {
                if (day.getWeather().equalsIgnoreCase(weather.getName()))
                    goodDays.add(day);
            }
        }

        return goodDays;
    }

    public static List<Day> getGoodDays(List<Weather> weatherList, int maxTemp, int minTemp, Day... days) {
        List<Day> goodDays = new ArrayList<>();

        for (Day day : days) {
            for (Weather weather : weatherList) {
                if (day.getWeather().equalsIgnoreCase(weather.getName()) && day.getTemperature().getMax() <= maxTemp && day.getTemperature().getMin() >= minTemp)
                    goodDays.add(day);
            }
        }

        return goodDays;
    }


}
