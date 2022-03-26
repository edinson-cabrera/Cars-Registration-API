package com.itana.carsregistrationapi.services;

import com.itana.carsregistrationapi.domain.models.Car;
import com.itana.carsregistrationapi.domain.repositories.CarRepository;
import com.itana.carsregistrationapi.domain.services.CarService;
import com.itana.carsregistrationapi.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;


    @Override
    public Car createCar( Car car) {
        return carRepository.save(car);
    }

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public Car updateCar(Long carId, Car carRequest) {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new ResourceNotFoundException("Car", "Id", carId));
        car.setMonth(carRequest.getMonth());
        car.setFuelType(carRequest.getFuelType());
        car.setVehicleMake(carRequest.getVehicleMake());
        car.setNumberOfVehicles(carRequest.getNumberOfVehicles());
        car.setVehicleType(carRequest.getVehicleType());
        return carRepository.save(car);
    }

    @Override
    public ResponseEntity<?> deleteCar(Long carId) {
        Car car = carRepository.findById(carId).orElseThrow(() -> new ResourceNotFoundException("Card", "Id", carId));
        carRepository.delete(car);
        return ResponseEntity.ok().build();
    }
}
