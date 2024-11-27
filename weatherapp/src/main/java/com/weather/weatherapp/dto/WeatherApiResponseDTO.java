package com.weather.weatherapp.dto;

import lombok.Data;

@Data
public class WeatherApiResponseDTO {
    private double latitude;
    private double longitude;
    private String timezone;
    private DailyWeatherDTO daily;
}
