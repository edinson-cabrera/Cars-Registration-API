package com.itana.carsregistrationapi.resources;

import lombok.Data;

@Data
public class SaveCarResource {

    private String month;
    private String make;
    private String fuel_type;
    private String vehicle_type;
    private Integer number;
}
