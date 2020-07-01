package com.spring.cartracker.service.serviceImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.spring.cartracker.model.Alerts;
import com.spring.cartracker.model.Readings;
import com.spring.cartracker.repository.CarRepository;
import com.spring.cartracker.repository.ReadingsRepository;
import com.spring.cartracker.service.AlertsService;
import com.spring.cartracker.service.ReadingsService;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

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

    public Readings loadReadings(Readings readingsData) throws JsonProcessingException {
        System.out.println(readingsData);
        readingsRepository.save(readingsData);
        String carVin = readingsData.getVin();
        alertsService.assignAlerts(carRepository.findByVin(carVin), readingsData);
        return readingsData;
    }

    @Override
    public List<HashMap<String,Float>> getGeoInfoByVin(String vin) {
        List<Readings> readingsList = (List<Readings>) readingsRepository.findByVin(vin);
        List<HashMap<String,Float>> geoInfoLastHalfHour = new ArrayList();
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        DateTime now = new DateTime();
        DateTime lastHalfHour = formatter.parseDateTime(now.toString("yyyy-MM-dd HH:mm:ss"));
        lastHalfHour = lastHalfHour.minusMinutes(30);

        for (int i=0; i<readingsList.size(); i++) {
            DateTime readingTS = new DateTime(readingsList.get(i).getTimestamp());
            DateTime readingsTime = formatter.parseDateTime(readingTS.toString("yyyy-MM-dd HH:mm:ss"));
            HashMap<String, Float> geoInfo = new HashMap<>();
            if(readingsTime.isAfter(lastHalfHour)) {
                geoInfo.put("latitude",readingsList.get(i).getLatitude());
                geoInfo.put("longitude",readingsList.get(i).getLongitude());
                geoInfoLastHalfHour.add(geoInfo);
            }
        }
        return geoInfoLastHalfHour;
    }
}
