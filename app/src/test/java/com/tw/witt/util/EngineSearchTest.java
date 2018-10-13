package com.tw.witt.util;

import com.tw.witt.model.Day;
import com.tw.witt.model.Weather;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EngineSearchTest {

    @Test
    public void testGoodDays()  {
        List<Day> days = mockListOfDays();

        Weather weather = mock(Weather.class);
        when(weather.getName()).thenReturn("hot");

        assertEquals(10, EngineSearch.getGoodDays(Collections.singletonList(weather), days.toArray(new Day[days.size()])).size());
    }

    @Test
    public void testSequenceDays() throws ParseException {
        List<Day> days = mockListOfDays();

        Weather weather = mock(Weather.class);
        when(weather.getName()).thenReturn("hot");

        List<Day> goodDays = EngineSearch.getGoodDays(Collections.singletonList(weather), days.toArray(new Day[days.size()]));

        Map<String, List<Day>> sequenceDays = EngineSearch.getSequenceDays(goodDays, 7, 3);

        assertEquals(7, sequenceDays.values().iterator().next().size());
    }

    private List<Day> mockListOfDays(){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long MILLISECONDS_PER_DAY = 1000L * 60 * 60 * 24;
        List<Day> days = new ArrayList<>();

        for (int i = 1; i < 21; i++) {
            days.add(new Day(sdf.format((Calendar.getInstance().getTimeInMillis() + (MILLISECONDS_PER_DAY * i))), i > 10 ?  "cold" : "hot"));
        }

        return days;
    }
}