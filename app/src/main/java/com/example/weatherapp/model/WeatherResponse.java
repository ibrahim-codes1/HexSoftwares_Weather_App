package com.example.weatherapp.model;

public class WeatherResponse {

    public Current current;

    public static class Current {
        public String time;
        public double temperature_2m;
        public double wind_speed_10m;
        public int weather_code;
    }
}