package com.weather.weatherapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@Builder
public class WeatherData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    private double temperatureMax; // Daily maximum temperature
    private double temperatureMin; // Daily minimum temperature
    private double precipitation;  // Total precipitation for the day
    private double windSpeedMax;   // Maximum wind speed

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false) // Foreign key column
    private CityCoordinates cityCoordinates;
}

