package com.spring.cartracker.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity
public class Tires {
    @Id
    private String uniqueIdentifier;
    private int frontLeft;
    private int frontRight;
    private int rearLeft;
    private int rearRight;
    public Tires() {
        this.uniqueIdentifier = UUID.randomUUID().toString();
    }

}

/*
"tires": {
      "frontLeft": 34,
      "frontRight": 36,
      "rearLeft": 29,
      "rearRight": 34
   }
 */
