package com.spring.cartracker.repository;

import com.spring.cartracker.model.Alerts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertsRepository extends JpaRepository<Alerts, String> {

}

