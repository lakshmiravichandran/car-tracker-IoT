package com.spring.cartracker.model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@Entity
public class Readings {
    @Id
    private String uniqueIdentifier;
    private String vin;
    private float latitude;
    private float longitude;
    private Timestamp timestamp;
    private float fuelVolume;
    private int speed;
    private int engineHp;
    private boolean checkEngineLightOn;
    private boolean engineCoolantLow;
    private boolean cruiseControlOn;
    private int engineRpm;
    @OneToOne(cascade = {CascadeType.ALL})
    private Tires tires;

    public Readings() {
        this.uniqueIdentifier = UUID.randomUUID().toString();
    }
}

/*
{
   "vin": "1HGCR2F3XFA027534",
   "latitude": 41.803194,
   "longitude": -88.144406,
   "timestamp": "2017-05-25T17:31:25.268Z",
   "fuelVolume": 1.5,
   "speed": 85,
   "engineHp": 240,
   "checkEngineLightOn": false,
   "engineCoolantLow": true,
   "cruiseControlOn": true,
   "engineRpm": 6300,
   "tires": {
      "frontLeft": 34,
      "frontRight": 36,
      "rearLeft": 29,
      "rearRight": 34
   }
}
 */
