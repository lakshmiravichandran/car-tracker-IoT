package com.spring.cartracker.service.serviceImpl;

import com.spring.cartracker.model.Readings;
import com.spring.cartracker.repository.CarRepository;
import com.spring.cartracker.repository.ReadingsRepository;
import com.spring.cartracker.service.AlertsService;
import com.spring.cartracker.service.ReadingsService;
import org.springframework.stereotype.Service;

@Service
public class ReadingsServiceImpl implements ReadingsService {

    ReadingsRepository readingsRepository;
    CarRepository carRepository;
    AlertsService alertsService;

    public ReadingsServiceImpl(ReadingsRepository readingsRepository, AlertsService alertsService, CarRepository carRepository){
        this.readingsRepository = readingsRepository;
        this.alertsService = alertsService;
        this.carRepository = carRepository;
    }

    public Readings loadReadings(Readings readingsData) {
        System.out.println(readingsData);
        readingsRepository.save(readingsData);
        String carVin = readingsData.getVin();
        alertsService.assignAlerts(carRepository.findByVin(carVin), readingsData);
        return readingsData;
    }
}
