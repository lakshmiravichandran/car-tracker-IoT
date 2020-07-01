package com.spring.cartracker.service.serviceImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.cartracker.awsMessaging.AlertsSns;
import com.spring.cartracker.model.Alerts;
import com.spring.cartracker.model.Car;
import com.spring.cartracker.model.Readings;
import com.spring.cartracker.repository.AlertsRepository;
import com.spring.cartracker.service.AlertsService;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        //Alerts alerts = new Alerts();
        if(readings.getEngineRpm() > car.getRedLineRpm()) {
            Alerts rpmAlerts = new Alerts();
            rpmAlerts.setAlertMessage("High engine RPM alert");
            rpmAlerts.setPriority("HIGH");
            rpmAlerts.setVin(car.getVin());
            rpmAlerts.setTimestamp(timeStamp);
            addAlerts(rpmAlerts);
            String message = objectMapper.writeValueAsString(rpmAlerts);
            alertsSns.send("Rule1 - HIGH priority",message);
            //System.out.println("HIGH");
        }
        if(readings.getFuelVolume() < car.getMaxFuelVolume()*0.10) {
            Alerts fuelAlerts = new Alerts();
            fuelAlerts.setAlertMessage("Low fuel alert");
            fuelAlerts.setPriority("MEDIUM");
            fuelAlerts.setVin(car.getVin());
            fuelAlerts.setTimestamp(timeStamp);
            addAlerts(fuelAlerts);
            //String message = objectMapper.writeValueAsString(fuelAlerts);
            //alertsSns.send("Rule2 - MEDIUM priority",message);
            //System.out.println("MEDIUM");
        }
        if(readings.getTires().getFrontLeft() < 32 || readings.getTires().getFrontLeft() > 36 ||
                readings.getTires().getFrontRight() < 32 || readings.getTires().getFrontRight() > 36 ||
                readings.getTires().getRearLeft() < 32 || readings.getTires().getRearLeft() > 36 ||
                readings.getTires().getRearRight() < 32 || readings.getTires().getRearRight() > 36 ) {
            Alerts tireAlerts = new Alerts();
            tireAlerts.setAlertMessage("Check tire pressure alert");
            tireAlerts.setPriority("LOW");
            tireAlerts.setVin(car.getVin());
            tireAlerts.setTimestamp(timeStamp);
            addAlerts(tireAlerts);
            //String message = objectMapper.writeValueAsString(tireAlerts);
            //alertsSns.send("Rule3 - LOW priority",message);
            //System.out.println("LOW");
        }
        if(readings.isEngineCoolantLow() == true || readings.isCheckEngineLightOn() == true) {
            Alerts engineAlerts = new Alerts();
            engineAlerts.setAlertMessage("Engine coolant low or engine light on alert");
            engineAlerts.setPriority("LOW");
            engineAlerts.setVin(car.getVin());
            engineAlerts.setTimestamp(timeStamp);
            addAlerts(engineAlerts);
            //String message = objectMapper.writeValueAsString(engineAlerts);
            //alertsSns.send("Rule4 - LOW priority",message);
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
        List<Alerts> alertsList = (List<Alerts>) alertsRepository.findAll();
        return alertsList;
    }

    @Override
    public List<Alerts> getHighAlerts() {
        List<Alerts> alertsList = (List<Alerts>) alertsRepository.findAll();
        List<Alerts> alertsLastTwoHours = new ArrayList();
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        DateTime now = new DateTime();
        DateTime lastTwoHours = formatter.parseDateTime(now.toString("yyyy-MM-dd HH:mm:ss"));
        lastTwoHours = lastTwoHours.minusHours(2);

        System.out.println(lastTwoHours);
        for (int i=0; i<alertsList.size(); i++) {
            String alertTime = alertsList.get(i).getTimestamp();
            DateTime alertTS = formatter.parseDateTime(alertTime);
            if(alertsList.get(i).getPriority().equals("HIGH") &&  alertTS.isAfter(lastTwoHours)) {
                alertsLastTwoHours.add(alertsList.get(i));
            }
        }
        alertsLastTwoHours.sort(Comparator.comparing(Alerts::getTimestamp));
        return alertsLastTwoHours;
    }

    @Override
    public List<Alerts> getAlertsByVin(String vin) {
        List<Alerts> alertsList = (List<Alerts>) alertsRepository.findAll();
        List<Alerts> alertsForVin = new ArrayList();
        for (int i=0; i<alertsList.size(); i++) {
            if(alertsList.get(i).getVin().equals(vin)) {
                alertsForVin.add(alertsList.get(i));
            }
        }
        return alertsForVin;
    }
}
