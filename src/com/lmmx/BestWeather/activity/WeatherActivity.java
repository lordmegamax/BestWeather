package com.lmmx.BestWeather.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.lmmx.BestWeather.DBHelper;
import com.lmmx.BestWeather.R;
import com.lmmx.BestWeather.UpdateWeatherTask;
import com.lmmx.BestWeather.WeatherInfoListCursorAdapter;

public class WeatherActivity extends Activity {
    private ListView listView;
    private EditText etLocation;
    private static UpdateWeatherTask updateWeatherTask;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_PROGRESS);

        setContentView(R.layout.activity_weather);

        setProgressBarIndeterminate(Boolean.TRUE);

        listView = (ListView) findViewById(R.id.listView);
        etLocation = (EditText) findViewById(R.id.etLocation);

        updateWeatherList();
    }

    BroadcastReceiver weatherUpdatedReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(UpdateWeatherTask.ACTION_WEATHER_DATA_UPDATED)) {
                updateWeatherList();
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(weatherUpdatedReceiver, new IntentFilter(UpdateWeatherTask.ACTION_WEATHER_DATA_UPDATED));

        if (updateWeatherTask != null)
            updateWeatherTask.setActivity(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(weatherUpdatedReceiver);

        if (updateWeatherTask != null)
            updateWeatherTask.setActivity(null);
    }

    private void updateWeatherList() {
        SQLiteDatabase db = new DBHelper(this).getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DBHelper.TABLE_WEATHER_INFO, null);
        WeatherInfoListCursorAdapter adapter = new WeatherInfoListCursorAdapter(this, cursor);
        listView.setAdapter(adapter);
        Toast.makeText(getApplicationContext(), "List data was updated.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.menu_weather, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    if (updateWeatherTask == null || !updateWeatherTask.isRunning()) {
                        updateWeatherTask = new UpdateWeatherTask();
                        updateWeatherTask.setApplicationContext(getApplicationContext());
                        updateWeatherTask.setActivity(this);
                        updateWeatherTask.execute(etLocation.getText().toString());
                    } else {
                        Toast.makeText(getApplicationContext(), "In progress... Please wait.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Internet connection is unavailable.", Toast.LENGTH_SHORT).show();
                }


                return true;
            case R.id.action_about:
                // TODO: Show about screen.
                return true;
        }
        return super.onMenuItemSelected(featureId, item);
    }
}