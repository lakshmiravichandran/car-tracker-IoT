package com.spring.cartracker.service.serviceImpl;

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

    public AlertsServiceImpl(AlertsRepository alertsRepository){
        this.alertsRepository = alertsRepository;
    }

    // rules to assign alerts with priority for cars
    @Override
    public boolean assignAlerts(Car car, Readings readings) {
        Alerts alerts = new Alerts();

        if(readings.getEngineRpm() > car.getRedLineRpm()) {
            alerts.setPriority("HIGH");
            alerts.setVin(car.getVin());
            addAlerts(alerts);
            //System.out.println("HIGH");
        }
        if(readings.getFuelVolume() < car.getMaxFuelVolume()*0.10) {
            alerts.setPriority("MEDIUM");
            alerts.setVin(car.getVin());
            addAlerts(alerts);
            //System.out.println("MEDIUM");
        }
        if(readings.getTires().getFrontLeft() < 32 || readings.getTires().getFrontLeft() > 36 ||
                readings.getTires().getFrontRight() < 32 || readings.getTires().getFrontRight() > 36 ||
                readings.getTires().getRearLeft() < 32 || readings.getTires().getRearLeft() > 36 ||
                readings.getTires().getRearRight() < 32 || readings.getTires().getRearRight() > 36 ) {
            alerts.setPriority("LOW");
            alerts.setVin(car.getVin());
            addAlerts(alerts);
            //System.out.println("LOW");
        }
        if(readings.isEngineCoolantLow() == true || readings.isCheckEngineLightOn() == true) {
            alerts.setPriority("LOW");
            alerts.setVin(car.getVin());
            addAlerts(alerts);
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
