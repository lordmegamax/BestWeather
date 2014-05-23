package com.lmmx.BestWeather.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.lmmx.BestWeather.R;

public class SplashScreenActivity extends Activity {

    private static final long SPLASH_TIME = 3 * 1000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this, WeatherActivity.class));
                finish();
            }
        }, SPLASH_TIME);
    }
}
