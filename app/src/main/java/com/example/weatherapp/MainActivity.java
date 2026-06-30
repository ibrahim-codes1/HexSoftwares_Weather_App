package com.example.weatherapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.weatherapp.api.GeoApi;
import com.example.weatherapp.api.WeatherApi;
import com.example.weatherapp.model.GeoResponse;
import com.example.weatherapp.model.WeatherResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.example.weatherapp.R;

public class MainActivity extends AppCompatActivity {

    EditText cityEditText;
    Button searchButton;
    TextView locationTextView, tempTextView, windTextView, statusTextView;
    ProgressBar progressBar;

    GeoApi geoApi;
    WeatherApi weatherApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityEditText = findViewById(R.id.cityEditText);
        searchButton = findViewById(R.id.searchButton);
        locationTextView = findViewById(R.id.locationTextView);
        tempTextView = findViewById(R.id.tempTextView);
        windTextView = findViewById(R.id.windTextView);
        statusTextView = findViewById(R.id.statusTextView);
        progressBar = findViewById(R.id.progressBar);

        Retrofit geoRetrofit = new Retrofit.Builder()
                .baseUrl("https://geocoding-api.open-meteo.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Retrofit weatherRetrofit = new Retrofit.Builder()
                .baseUrl("https://api.open-meteo.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        geoApi = geoRetrofit.create(GeoApi.class);
        weatherApi = weatherRetrofit.create(WeatherApi.class);

        searchButton.setOnClickListener(v -> {
            String city = cityEditText.getText().toString().trim();

            if (city.isEmpty()) {
                Toast.makeText(this, "Please enter city name", Toast.LENGTH_SHORT).show();
            } else {
                searchCity(city);
            }
        });
    }

    private void searchCity(String cityName) {
        showLoading(true);

        geoApi.searchCity(cityName, 1, "en", "json").enqueue(new Callback<GeoResponse>() {
            @Override
            public void onResponse(Call<GeoResponse> call, Response<GeoResponse> response) {
                if (response.isSuccessful()
                        && response.body() != null
                        && response.body().results != null
                        && !response.body().results.isEmpty()) {

                    GeoResponse.Result result = response.body().results.get(0);

                    String locationName = result.name;
                    if (result.admin1 != null) {
                        locationName += ", " + result.admin1;
                    }
                    if (result.country != null) {
                        locationName += ", " + result.country;
                    }

                    getWeather(result.latitude, result.longitude, locationName);

                } else {
                    showLoading(false);
                    Toast.makeText(MainActivity.this, "City not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GeoResponse> call, Throwable t) {
                showLoading(false);
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getWeather(double latitude, double longitude, String locationName) {
        weatherApi.getWeather(
                latitude,
                longitude,
                "temperature_2m,wind_speed_10m,weather_code",
                "auto"
        ).enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                showLoading(false);

                if (response.isSuccessful()
                        && response.body() != null
                        && response.body().current != null) {

                    WeatherResponse.Current current = response.body().current;

                    locationTextView.setText(locationName);
                    tempTextView.setText(current.temperature_2m + "°C");
                    windTextView.setText("Wind Speed: " + current.wind_speed_10m + " km/h");
                    statusTextView.setText("Weather: " + getWeatherStatus(current.weather_code));

                } else {
                    Toast.makeText(MainActivity.this, "Weather data not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                showLoading(false);
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getWeatherStatus(int code) {
        if (code == 0) return "Clear Sky";
        if (code == 1 || code == 2 || code == 3) return "Partly Cloudy";
        if (code == 45 || code == 48) return "Foggy";
        if (code >= 51 && code <= 67) return "Drizzle / Rain";
        if (code >= 71 && code <= 77) return "Snow";
        if (code >= 80 && code <= 82) return "Rain Showers";
        if (code >= 95 && code <= 99) return "Thunderstorm";
        return "Unknown";
    }

    private void showLoading(boolean isLoading) {
        progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
        searchButton.setEnabled(!isLoading);
    }
}