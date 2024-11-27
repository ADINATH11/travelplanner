package com.programming.travelplanner.service;

import com.programming.travelplanner.config.AppConstants;
import com.programming.travelplanner.model.Itinerary;
import com.programming.travelplanner.repository.ItineraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItineraryService {

    @Autowired
    private ItineraryRepository itineraryRepository;

    @Autowired
    private KafkaProducerService kafkaProducerService;
    public List<Itinerary> getAllItineraries() {
        return itineraryRepository.findAll();
    }

    public Itinerary saveItinerary(Itinerary itinerary) {
        itinerary.getDestinations().forEach( destination -> kafkaProducerService.send(AppConstants.LOCATION_TOPIC_NAME, destination.getName()));
        return itineraryRepository.save(itinerary);
    }
}
