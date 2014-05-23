package com.lmmx.BestWeather;

import android.content.Context;
import android.database.Cursor;
import android.widget.SimpleCursorAdapter;

import static com.lmmx.BestWeather.DBHelper.*;

public class WeatherInfoListCursorAdapter extends SimpleCursorAdapter {

    private static final String[] from = new String[]{WEATHER_INFO_DATE, WEATHER_INFO_TEMP, WEATHER_INFO_PRESSURE, WEATHER_INFO_HUMIDITY};
    private static final int[] to = new int[]{R.id.tvDate, R.id.tvTemp, R.id.tvPressure, R.id.tvHumidity};

    public WeatherInfoListCursorAdapter(Context context, Cursor cursor) {
        super(context, R.layout.weather_list_row, cursor, from, to, 0);
    }
}
