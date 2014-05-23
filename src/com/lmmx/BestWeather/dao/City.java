package com.lmmx.BestWeather.dao;

import com.google.gson.annotations.Expose;

public class City {

    @Expose
    private String id;
    @Expose
    private String name;
    @Expose
    private Coord coord;
    @Expose
    private String country;
    @Expose
    private int population;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

}
