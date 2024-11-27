package com.weather.weatherapp.repo;

import com.weather.weatherapp.entity.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherDataRepo extends JpaRepository<WeatherData,Integer> {
}
