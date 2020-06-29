package com.spring.cartracker.service;

import com.spring.cartracker.model.Car;

import java.util.List;

public interface CarService {
    boolean loadCarData(Car carData);
    List<Car> getAllCarInfo();
}
