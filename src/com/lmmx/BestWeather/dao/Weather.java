package com.lmmx.BestWeather.dao;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

public class Weather {

    @Expose
    private String cod;
    @Expose
    private double message;
    @Expose
    private City city;
    @Expose
    private int cnt;
    @Expose
    private java.util.List<List> list = new ArrayList<List>();

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public double getMessage() {
        return message;
    }

    public void setMessage(double message) {
        this.message = message;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public java.util.List<List> getList() {
        return list;
    }

    public void setList(java.util.List<List> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "cod='" + cod + '\'' +
                ", message=" + message +
                ", city=" + city +
                ", cnt=" + cnt +
                ", list=" + list +
                '}';
    }
}
