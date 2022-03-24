package com.itana.carsregistrationapi.resources;

import lombok.Data;

@Data
public class SaveCarResource {

    private String month;
    private String vehicleMake;
    private String fuelType;
    private String vehicleType;
    private Integer NumberOfVehicles;
}
