package com.itana.carsregistrationapi.domain.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "cars")
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
