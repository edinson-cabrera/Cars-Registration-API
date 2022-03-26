package com.itana.carsregistrationapi.services;

import com.itana.carsregistrationapi.domain.models.Car;
import com.itana.carsregistrationapi.domain.repositories.CarRepository;
import com.itana.carsregistrationapi.domain.services.CarService;
import com.itana.carsregistrationapi.exceptions.ResourceNotFoundException;
import com.itana.carsregistrationapi.helper.CSVHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    public Car getCarById(Long carId) {
        return carRepository.findById(carId)
                .orElseThrow(() -> new ResourceNotFoundException("Car", "Id", carId));
    }

    @Override
    public Car updateCar(Long carId, Car carRequest) {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new ResourceNotFoundException("Car", "Id", carId));
        car.setMonth(carRequest.getMonth());
        car.setFuel_type(carRequest.getFuel_type());
        car.setMake(carRequest.getMake());
        car.setNumber(carRequest.getNumber());
        car.setVehicle_type(carRequest.getVehicle_type());
        return carRepository.save(car);
    }

    @Override
    public ResponseEntity<?> deleteCar(Long carId) {
        Car car = carRepository.findById(carId).orElseThrow(() -> new ResourceNotFoundException("Card", "Id", carId));
        carRepository.delete(car);
        return ResponseEntity.ok().build();
    }

    public void importByCSV(MultipartFile file) {
        try {
            List<Car> cars = CSVHelper.csvToCars(file.getInputStream());
            carRepository.saveAll(cars);
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }
}
