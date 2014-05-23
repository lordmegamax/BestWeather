package com.lmmx.BestWeather.dao;

import com.google.gson.annotations.Expose;

public class Coord {

    @Expose
    private double lon;
    @Expose
    private double lat;

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

}
