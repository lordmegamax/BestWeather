package com.lmmx.BestWeather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.lmmx.BestWeather.dao.Weather_;

import java.util.List;

public class WeatherInfoListAdapter extends BaseAdapter {
    private final LayoutInflater layoutInflater;
    private final List<Weather_> weatherList;

    public WeatherInfoListAdapter(Context context, List<Weather_> weather) {
        this.weatherList = weather;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return weatherList.size();
    }

    @Override
    public Weather_ getItem(int position) {
        return weatherList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = layoutInflater.inflate(R.layout.weather_list_row, null);



        return convertView;
    }
}
