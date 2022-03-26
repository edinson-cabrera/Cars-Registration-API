package com.itana.carsregistrationapi.resources;

import com.itana.carsregistrationapi.domain.models.AuditModel;
import lombok.Data;

@Data
public class CarResource extends AuditModel {
    private Long id;
    private String month;
    private String make;
    private String fuel_type;
    private String vehicle_type;
    private Integer number;
}
