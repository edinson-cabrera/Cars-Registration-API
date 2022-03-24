package com.itana.carsregistrationapi.domain.services;

import com.itana.carsregistrationapi.domain.models.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface CarService {

    Car createCar(Car car);
    Page<Car>getAllCars(Pageable pageable);
    Car updateCar(Long carId, Car carRequest);
    ResponseEntity<?> deleteCar(Long carId);

}
