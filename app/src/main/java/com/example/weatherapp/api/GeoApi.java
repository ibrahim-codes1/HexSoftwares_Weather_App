package com.example.weatherapp.api;

import com.example.weatherapp.model.GeoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GeoApi {

    @GET("v1/search")
    Call<GeoResponse> searchCity(
            @Query("name") String cityName,
            @Query("count") int count,
            @Query("language") String language,
            @Query("format") String format
    );
}