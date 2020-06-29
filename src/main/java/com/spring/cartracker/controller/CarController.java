package com.spring.cartracker.controller;

import com.spring.cartracker.model.Car;
import com.spring.cartracker.service.CarService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {
    private CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PutMapping("/loadCarList")
    @ApiOperation(value="Endpoint to load car information")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public boolean putCarData(@RequestBody List<Car> carData){
        for(int i = 0; i < carData.size(); i++) {
            carService.loadCarData(carData.get(i));
        }
        return true;
    }

    @GetMapping("/getCarInfo")
    @ApiOperation(value="Endpoint to fetch all car details")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public List<Car> getAllCarInfo(){
        return carService.getAllCarInfo();
    }
}
