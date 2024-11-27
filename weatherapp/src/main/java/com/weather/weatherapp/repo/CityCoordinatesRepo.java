package com.weather.weatherapp.repo;

import com.weather.weatherapp.entity.CityCoordinates;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityCoordinatesRepo extends JpaRepository<CityCoordinates, Integer> {
}
