package com.weather.weatherapp.dto;

import lombok.Data;

import java.util.List;

@Data
public class DailyWeatherDTO {
    private List<String> time;
    private List<Double> temperature_2m_max;
    private List<Double> temperature_2m_min;
    private List<Double> precipitation_sum;
    private List<Double> windspeed_10m_max;
}
