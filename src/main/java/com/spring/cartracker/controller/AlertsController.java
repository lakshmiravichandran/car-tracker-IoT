package com.spring.cartracker.controller;

import com.spring.cartracker.model.Alerts;
import com.spring.cartracker.service.AlertsService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class AlertsController {
    private AlertsService alertsService;

    @Autowired
    public AlertsController(AlertsService alertsService) {
        this.alertsService = alertsService;
    }

    @GetMapping("/getCarAlerts")
    @ApiOperation(value="Endpoint to fetch all alerts")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public List<Alerts> getAlerts(){
        return alertsService.getAllAlerts();
    }

    @GetMapping("/getRecentHighAlerts")
    @ApiOperation(value="Endpoint to fetch high alerts within last 2 hours for all the cars (sorted by time)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public List<Alerts> getHighAlerts(){
        return alertsService.getHighAlerts();
    }

}
