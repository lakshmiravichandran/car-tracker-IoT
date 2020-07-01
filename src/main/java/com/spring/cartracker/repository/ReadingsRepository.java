package com.spring.cartracker.repository;


import com.spring.cartracker.model.Readings;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReadingsRepository extends CrudRepository<Readings, String> {
    List<Readings> findByVin(String vin);
}
