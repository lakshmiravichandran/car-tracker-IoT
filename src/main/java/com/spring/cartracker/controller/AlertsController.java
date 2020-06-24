package com.spring.cartracker.controller;

import com.spring.cartracker.model.Alerts;
import com.spring.cartracker.service.AlertsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AlertsController {
    private AlertsService alertsService;

    @Autowired
    public AlertsController(AlertsService alertsService) {
        this.alertsService = alertsService;
    }

    @GetMapping("/getCarAlerts")
    public List<Alerts> getAlerts(){
        return alertsService.getAllAlerts();
    }
}
