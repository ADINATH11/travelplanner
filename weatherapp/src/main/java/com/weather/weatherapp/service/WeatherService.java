package com.weather.weatherapp.service;

import com.weather.weatherapp.dto.WeatherApiResponseDTO;
import com.weather.weatherapp.entity.CityCoordinates;
import com.weather.weatherapp.entity.WeatherData;
import com.weather.weatherapp.mapper.WeatherMapper;
import com.weather.weatherapp.repo.WeatherDataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.util.List;

@Service
public class WeatherService {

    private final RestTemplate restTemplate;
    @Autowired
    private final GeocodingService geocodingService;

    @Autowired
    private final WeatherDataRepo weatherDataRepo;

    public WeatherService(RestTemplate restTemplate, GeocodingService geocodingService, WeatherDataRepo weatherDataRepo) {
        this.restTemplate = restTemplate;
        this.geocodingService = geocodingService;
        this.weatherDataRepo = weatherDataRepo;
    }

    public WeatherApiResponseDTO getWeather(String city) {
        CityCoordinates coordinates = geocodingService.getCoordinates(city);

        double latitude = coordinates.getLatitude();
        double longitude = coordinates.getLongitude();

        String url = UriComponentsBuilder.fromHttpUrl("https://api.open-meteo.com/v1/forecast")
                .queryParam("latitude", latitude)
                .queryParam("longitude", longitude)
                .queryParam("daily", "temperature_2m_max,temperature_2m_min,precipitation_sum,windspeed_10m_max")
                .queryParam("timezone", "auto")
                .queryParam("start_date", LocalDate.now().toString())
                .queryParam("end_date", LocalDate.now().plusDays(7).toString())
                .toUriString();

        WeatherApiResponseDTO weatherData = restTemplate.getForObject(url, WeatherApiResponseDTO.class);
        if (weatherData != null) {
            List<WeatherData> entities = WeatherMapper.mapToWeatherData(weatherData, coordinates);
            weatherDataRepo.saveAll(entities);
        }

        return weatherData;
    }
}
