package com.weather.weatherapp.service;

import com.weather.weatherapp.dto.OpenStreetMapResponseDTO;
import com.weather.weatherapp.entity.CityCoordinates;
import com.weather.weatherapp.repo.CityCoordinatesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class GeocodingService {

    private final RestTemplate restTemplate;
    @Autowired
    private final CityCoordinatesRepo repo;

    public GeocodingService(RestTemplate restTemplate, CityCoordinatesRepo repo) {
        this.restTemplate = restTemplate;
        this.repo = repo;
    }

    public CityCoordinates getCoordinates(String city) {
        String url = UriComponentsBuilder.fromHttpUrl("https://nominatim.openstreetmap.org/search")
                .queryParam("q", city)
                .queryParam("format", "json")
                .queryParam("addressdetails", "1")
                .toUriString();

        OpenStreetMapResponseDTO[] response = restTemplate.getForObject(url, OpenStreetMapResponseDTO[].class);
        if(response!=null){
            CityCoordinates entity = CityCoordinates.builder()
                                                    .cityName(city)
                                                    .latitude( Double.parseDouble(response[0].getLat()))
                                                    .longitude(Double.parseDouble(response[0].getLon())).build();
            repo.save(entity);
            return entity;
        }
        else {
            return null;
        }
    }


}