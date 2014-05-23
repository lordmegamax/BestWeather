package com.lmmx.BestWeather;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "weather.db";

    public static final String TABLE_WEATHER_INFO = "weather_info";

    public static final String WEATHER_INFO_DATE = "date";
    public static final String WEATHER_INFO_TEMP = "temp";
    public static final String WEATHER_INFO_PRESSURE = "pressure";
    public static final String WEATHER_INFO_HUMIDITY = "humidity";

    public static final String SQL_CREATE_WEATHER_INFO_TABLE = "CREATE TABLE " + TABLE_WEATHER_INFO + "("
            + "_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
            + WEATHER_INFO_DATE + " INTEGER(8) NOT NULL, "
            + WEATHER_INFO_TEMP + " INTEGER(8) NOT NULL, "
            + WEATHER_INFO_PRESSURE + " REAL NOT NULL, "
            + WEATHER_INFO_HUMIDITY + " INTEGER(8) NOT NULL, "
            + "UNIQUE ( " + WEATHER_INFO_DATE + " ) ON CONFLICT REPLACE)";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_WEATHER_INFO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
