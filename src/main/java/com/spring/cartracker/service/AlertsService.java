package com.spring.cartracker.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.spring.cartracker.model.Alerts;
import com.spring.cartracker.model.Car;
import com.spring.cartracker.model.Readings;

import java.util.List;

public interface AlertsService {
    boolean assignAlerts(Car car, Readings readings) throws JsonProcessingException;
    boolean addAlerts(Alerts alerts);
    List<Alerts> getAllAlerts();
    List<Alerts> getHighAlerts();
}
