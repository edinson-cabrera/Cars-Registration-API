package com.itana.carsregistrationapi.domain.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String month;

    private String vehicleMake;

    private String fuelType;

    private String vehicleType;

    private Integer NumberOfVehicles;
}
