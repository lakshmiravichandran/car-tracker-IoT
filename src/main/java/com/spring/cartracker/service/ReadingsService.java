package com.spring.cartracker.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.spring.cartracker.model.Readings;

import java.util.HashMap;
import java.util.List;

public interface ReadingsService {
    Readings loadReadings(Readings readingsData) throws JsonProcessingException;

    List<HashMap<String, Float>> getGeoInfoByVin(String vin);
}
