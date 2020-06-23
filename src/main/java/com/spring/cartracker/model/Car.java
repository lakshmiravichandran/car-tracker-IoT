package com.spring.cartracker.model;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;


@Data
@Entity
public class Car {
    @Id
    @Column(unique = true)
    private String vin;
    private String make;
    private String model;
    private int year;
    private int redLineRpm;
    private int maxFuelVolume;
    private Timestamp lastServiceDate;
}
/*
{
    "vin": "1HGCR2F3XFA027534",
    "make": "HONDA",
    "model": "ACCORD",
    "year": 2015,
    "redlineRpm": 5500,
    "maxFuelVolume": 15,
    "lastServiceDate": "2017-05-25T17:31:25.268Z"
 }
 */