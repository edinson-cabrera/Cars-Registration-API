package com.itana.carsregistrationapi.domain.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "cars")
public class Car extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String month;

    private String make;

    private String fuel_type;

    private String vehicle_type;

    private Integer number;


    public Car(String month, String vehicleMake, String fuelType, String vehicleType, Integer numberOfVehicles){
        this.month = month;
        this.make = vehicleMake;
        this.fuel_type = fuelType;
        this.vehicle_type = vehicleType;
        this.number = numberOfVehicles;
    } public Car() {

    }
}
