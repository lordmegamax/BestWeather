package com.lmmx.BestWeather;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;
import com.google.gson.Gson;
import com.lmmx.BestWeather.dao.List;
import com.lmmx.BestWeather.dao.Weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import static com.lmmx.BestWeather.DBHelper.*;

public class UpdateWeatherTask extends AsyncTask<String, Void, Weather> {
    public static final String WEBSITE_API_URL = "http://api.openweathermap.org/data/2.5/forecast/daily?cnt=10&q=";
    public static final String ACTION_WEATHER_DATA_UPDATED = "action_weather_data_updated";

    private boolean isRunning;

    private Activity activity;
    private Context applicationContext;

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void setApplicationContext(Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    public boolean isRunning() {
        return isRunning;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        isRunning = true;

        if (activity != null)
            activity.setProgressBarVisibility(true);

        if (applicationContext == null)
            throw new IllegalArgumentException("You must set application context via setApplicationContext() method to use this task.");
    }

    @Override
    protected Weather doInBackground(String... params) {
        String query = params[0];
        String json = "";
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(WEBSITE_API_URL + URLEncoder.encode(query));
            urlConnection = (HttpURLConnection) url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line);
            }

            json = builder.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null)
                urlConnection.disconnect();
        }

        Weather weather = new Gson().fromJson(json, Weather.class);
        if (activity != null)
            Log.i(activity.getString(R.string.app_name), "New weather info available: " + weather);

        SQLiteDatabase db = new DBHelper(applicationContext).getWritableDatabase();
        java.util.List<List> list = weather.getList();
        for (List l : list) {
            ContentValues cv = new ContentValues();
            cv.put(WEATHER_INFO_DATE, l.getDt());
            cv.put(WEATHER_INFO_HUMIDITY, l.getHumidity());
            cv.put(WEATHER_INFO_PRESSURE, l.getPressure());
            cv.put(WEATHER_INFO_TEMP, l.getDeg());

            db.insert(TABLE_WEATHER_INFO, null, cv);
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        applicationContext.sendBroadcast(new Intent(ACTION_WEATHER_DATA_UPDATED));

        return weather;
    }

    @Override
    protected void onPostExecute(Weather weather) {
        super.onPostExecute(weather);
        if (activity != null)
            activity.setProgressBarVisibility(false);

        isRunning = false;
    }
}
