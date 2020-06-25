package com.spring.cartracker.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.spring.cartracker.model.Readings;

public interface ReadingsService {
    Readings loadReadings(Readings readingsData) throws JsonProcessingException;
}
