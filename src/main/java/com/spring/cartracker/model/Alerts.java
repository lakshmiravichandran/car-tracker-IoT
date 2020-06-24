package com.spring.cartracker.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity
public class Alerts {
    @Id
    private String uniqueIdentifier;
    private String priority;
    private String vin;

    public Alerts() {
        this.uniqueIdentifier = UUID.randomUUID().toString();
    }
}
