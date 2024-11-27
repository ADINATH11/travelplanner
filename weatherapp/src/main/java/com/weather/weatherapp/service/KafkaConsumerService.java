package com.weather.weatherapp.service;

import com.weather.weatherapp.config.AppConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumerService {
    @Autowired
    private WeatherService weatherService;
    @KafkaListener(topics = {AppConstants.LOCATION_TOPIC_NAME}, groupId = "topic-name")
    public void consume(String myMsg) {
        weatherService.getWeather(myMsg);
        log.info(String.format("Received: " + myMsg));
    }
}
