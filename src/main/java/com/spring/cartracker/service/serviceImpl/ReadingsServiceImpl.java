package com.spring.cartracker.service.serviceImpl;

import com.spring.cartracker.model.Readings;
import com.spring.cartracker.repository.ReadingsRepository;
import com.spring.cartracker.service.ReadingsService;
import org.springframework.stereotype.Service;

@Service
public class ReadingsServiceImpl implements ReadingsService {

    ReadingsRepository readingsRepository;

    public ReadingsServiceImpl(ReadingsRepository readingsRepository){
        this.readingsRepository = readingsRepository;
    }

    @Override
    public Readings loadReadings(Readings readingsData) {
        System.out.println(readingsData);
        readingsRepository.save(readingsData);
        return readingsData;
    }
}
