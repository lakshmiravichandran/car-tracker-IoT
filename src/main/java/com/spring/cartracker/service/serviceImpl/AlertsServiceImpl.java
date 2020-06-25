package com.spring.cartracker.service.serviceImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.cartracker.awsMessaging.AlertsSns;
import com.spring.cartracker.model.Alerts;
import com.spring.cartracker.model.Car;
import com.spring.cartracker.model.Readings;
import com.spring.cartracker.repository.AlertsRepository;
import com.spring.cartracker.service.AlertsService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AlertsServiceImpl implements AlertsService {
    AlertsRepository alertsRepository;
    private AlertsSns alertsSns;
    private ObjectMapper objectMapper;


    public AlertsServiceImpl(AlertsRepository alertsRepository, AlertsSns alertsSns, ObjectMapper objectMapper){
        this.alertsRepository = alertsRepository;
        this.alertsSns = alertsSns;
        this.objectMapper = objectMapper;
    }

    // rules to assign alerts with priority for cars
    @Override
    public boolean assignAlerts(Car car, Readings readings) throws JsonProcessingException {
        //Alerts alerts = new Alerts();
        if(readings.getEngineRpm() > car.getRedLineRpm()) {
            Alerts rpmAlerts = new Alerts();
            rpmAlerts.setPriority("HIGH");
            rpmAlerts.setVin(car.getVin());
            addAlerts(rpmAlerts);
            String message = objectMapper.writeValueAsString(rpmAlerts);
            alertsSns.send("Rule1 - HIGH priority",message);
            //System.out.println("HIGH");
        }
        if(readings.getFuelVolume() < car.getMaxFuelVolume()*0.10) {
            Alerts fuelAlerts = new Alerts();
            fuelAlerts.setPriority("MEDIUM");
            fuelAlerts.setVin(car.getVin());
            addAlerts(fuelAlerts);
            String message = objectMapper.writeValueAsString(fuelAlerts);
            alertsSns.send("Rule2 - MEDIUM priority",message);
            //System.out.println("MEDIUM");
        }
        if(readings.getTires().getFrontLeft() < 32 || readings.getTires().getFrontLeft() > 36 ||
                readings.getTires().getFrontRight() < 32 || readings.getTires().getFrontRight() > 36 ||
                readings.getTires().getRearLeft() < 32 || readings.getTires().getRearLeft() > 36 ||
                readings.getTires().getRearRight() < 32 || readings.getTires().getRearRight() > 36 ) {
            Alerts tireAlerts = new Alerts();
            tireAlerts.setPriority("LOW");
            tireAlerts.setVin(car.getVin());
            addAlerts(tireAlerts);
            String message = objectMapper.writeValueAsString(tireAlerts);
            alertsSns.send("Rule3 - LOW priority",message);
            //System.out.println("LOW");
        }
        if(readings.isEngineCoolantLow() == true || readings.isCheckEngineLightOn() == true) {
            Alerts engineAlerts = new Alerts();
            engineAlerts.setPriority("LOW");
            engineAlerts.setVin(car.getVin());
            addAlerts(engineAlerts);
            String message = objectMapper.writeValueAsString(engineAlerts);
            alertsSns.send("Rule4 - LOW priority",message);
            //System.out.println("LOW");
        }
        return true;
    }

    @Override
    public boolean addAlerts(Alerts alerts) {
        alertsRepository.save(alerts);
        return true;
    }

    @Override
    public List<Alerts> getAllAlerts() {
        return null;
    }
}
