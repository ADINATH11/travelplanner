package com.weather.weatherapp.mapper;

import com.weather.weatherapp.dto.DailyWeatherDTO;
import com.weather.weatherapp.dto.WeatherApiResponseDTO;
import com.weather.weatherapp.entity.CityCoordinates;
import com.weather.weatherapp.entity.WeatherData;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class WeatherMapper {

    public static List<WeatherData> mapToWeatherData(WeatherApiResponseDTO responseDTO, CityCoordinates cityCoordinates) {
        List<WeatherData> weatherDataList = new ArrayList<>();
        DailyWeatherDTO daily = responseDTO.getDaily();

        List<String> dates = daily.getTime();
        List<Double> temperatureMax = daily.getTemperature_2m_max();
        List<Double> temperatureMin = daily.getTemperature_2m_min();
        List<Double> precipitation = daily.getPrecipitation_sum();
        List<Double> windSpeedMax = daily.getWindspeed_10m_max();

        for (int i = 0; i < dates.size(); i++) {
            WeatherData weatherData = WeatherData.builder()
                    .cityCoordinates(cityCoordinates)
                    .date(LocalDate.parse(dates.get(i)))
                    .temperatureMin(temperatureMin.get(i))
                    .temperatureMax(temperatureMax.get(i))
                    .precipitation(precipitation.get(i))
                    .windSpeedMax(windSpeedMax.get(i))
                    .build();

            weatherDataList.add(weatherData);
        }

        return weatherDataList;
    }
}
