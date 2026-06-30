package com.example.weatherapp.model;

import java.util.List;

public class GeoResponse {

    public List<Result> results;

    public static class Result {
        public String name;
        public double latitude;
        public double longitude;
        public String country;
        public String admin1;
    }
}