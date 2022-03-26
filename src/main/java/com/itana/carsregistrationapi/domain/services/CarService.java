package com.itana.carsregistrationapi.domain.services;

import com.itana.carsregistrationapi.domain.models.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CarService {

    Car createCar(Car car);

    List<Car>getAllCars();
    Car updateCar(Long carId, Car carRequest);
    ResponseEntity<?> deleteCar(Long carId);

}
