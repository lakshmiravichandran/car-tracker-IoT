package com.spring.cartracker.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@Entity
public class Alerts {
    @Id
    private String uniqueIdentifier;
    private String alertMessage;
    private String priority;
    private String vin;
    private String timestamp;

    public Alerts() {
        this.uniqueIdentifier = UUID.randomUUID().toString();
    }


}
