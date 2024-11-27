package com.weather.weatherapp.dto;

import lombok.Data;

@Data
public class OpenStreetMapResponseDTO {
    private String display_name;
    private String lat;
    private String lon;
}

