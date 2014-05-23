package com.lmmx.BestWeather.dao;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

public class List {
    @Expose
    private int dt;
    @Expose
    private Temp temp;
    @Expose
    private double pressure;
    @Expose
    private int humidity;
    @Expose
    private java.util.List<Weather_> weather = new ArrayList<Weather_>();
    @Expose
    private double speed;
    @Expose
    private int deg;
    @Expose
    private int clouds;
    @Expose
    private double rain;

    public int getDt() {
        return dt;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public Temp getTemp() {
        return temp;
    }

    public void setTemp(Temp temp) {
        this.temp = temp;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public java.util.List<Weather_> getWeather() {
        return weather;
    }

    public void setWeather(java.util.List<Weather_> weather) {
        this.weather = weather;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getDeg() {
        return deg;
    }

    public void setDeg(int deg) {
        this.deg = deg;
    }

    public int getClouds() {
        return clouds;
    }

    public void setClouds(int clouds) {
        this.clouds = clouds;
    }

    public double getRain() {
        return rain;
    }

    public void setRain(double rain) {
        this.rain = rain;
    }

}
