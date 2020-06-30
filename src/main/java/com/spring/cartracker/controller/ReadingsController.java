package com.spring.cartracker.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.spring.cartracker.model.Readings;
import com.spring.cartracker.service.ReadingsService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/vehicle")
public class ReadingsController {
    ReadingsService readingsService;

    @Autowired
    public ReadingsController(ReadingsService readingsService) {
        this.readingsService = readingsService;
    }

    @PostMapping("/addReadings")
    @ApiOperation(value="Endpoint to add car readings information")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public Readings postVehicleReadings(@RequestBody Readings readingsData) throws JsonProcessingException {
        return readingsService.loadReadings(readingsData);
    }
}
