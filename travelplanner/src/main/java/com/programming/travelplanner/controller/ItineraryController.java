package com.programming.travelplanner.controller;

import com.programming.travelplanner.model.Itinerary;
import com.programming.travelplanner.service.ItineraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/itineraries")
public class ItineraryController {

    @Autowired
    private ItineraryService itineraryService;

    @PostMapping("/")
    public Itinerary createItinerary(@RequestBody Itinerary itinerary) {
        return itineraryService.saveItinerary(itinerary);
    }

    @GetMapping("/greeting")
    public String greeting(){
        return "hello from travelplanner";
    }

    @GetMapping("/")
    public List<Itinerary> getAllItineraries() {
        return itineraryService.getAllItineraries();
    }
}
