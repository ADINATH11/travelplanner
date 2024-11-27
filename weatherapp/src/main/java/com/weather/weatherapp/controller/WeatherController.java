package com.weather.weatherapp.controller;

import com.weather.weatherapp.dto.WeatherApiResponseDTO;
import com.weather.weatherapp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/")
    public String greetings(){
        return "Welcome to the Weather App";
    }
    @GetMapping("/weather")
    public ResponseEntity<WeatherApiResponseDTO> getWeather(@RequestParam(name = "city", defaultValue = "Berlin") String city) {
        WeatherApiResponseDTO weatherData = weatherService.getWeather(city);
        return ResponseEntity.ok().body(weatherData);
    }
}
