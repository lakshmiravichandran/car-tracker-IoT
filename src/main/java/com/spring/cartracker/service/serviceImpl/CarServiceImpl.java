package com.spring.cartracker.service.serviceImpl;

import com.spring.cartracker.model.Alerts;
import com.spring.cartracker.model.Car;
import com.spring.cartracker.repository.CarRepository;
import com.spring.cartracker.service.CarService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    CarRepository carRepository;
    public CarServiceImpl(CarRepository carRepository){
        this.carRepository = carRepository;
    }

    @Override
    public boolean loadCarData(Car carData) {
        System.out.println(carData);
        carRepository.save(carData);
        return true;
    }

    @Override
    public List<Car> getAllCarInfo() {
        List<Car> carList = (List<Car>) carRepository.findAll();
        return carList;
    }

}
