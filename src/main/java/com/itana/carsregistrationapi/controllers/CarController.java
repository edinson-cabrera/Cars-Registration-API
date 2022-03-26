package com.itana.carsregistrationapi.controllers;

import com.itana.carsregistrationapi.domain.models.Car;
import com.itana.carsregistrationapi.domain.services.CarService;
import com.itana.carsregistrationapi.helper.CSVHelper;
import com.itana.carsregistrationapi.messages.ResponseMessage;
import com.itana.carsregistrationapi.resources.CarResource;
import com.itana.carsregistrationapi.resources.SaveCarResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@Transactional
@CrossOrigin(origins = {"http://localhost:3000","https://symphonious-froyo-8742d3.netlify.app"})
@Tag(name = "cars", description = "Cars API")
public class CarController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private CarService carService;

    @GetMapping("/cars")
    public ResponseEntity<List<Car>>getAllCars() {
        List<Car> cars = carService.getAllCars();
        return new ResponseEntity<List<Car>>(cars, HttpStatus.OK);
    }

    @GetMapping("/cars/{carId}")
    public CarResource getCarById(@PathVariable(name="carId") Long carId) {
        return convertToResource(carService.getCarById(carId));
    }

    @PostMapping("/cars")
    public CarResource createCar(@Valid @RequestBody SaveCarResource resource) {
        return convertToResource(carService.createCar(convertToEntity(resource)));
    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        if (CSVHelper.hasCSVFormat(file)) {
            try {
                carService.importByCSV(file);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }
        message = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }

    @PutMapping("/cars/{carId}")
    public CarResource updateCar(@PathVariable(name = "carId") Long carId, @Valid @RequestBody SaveCarResource resource){
        return convertToResource(carService.updateCar(carId, convertToEntity(resource)));
    }

    @DeleteMapping("/cars/{carId}")
    public ResponseEntity<?> deleteCar(@PathVariable(name="carId") Long carId){
        return carService.deleteCar(carId);
    }

    private Car convertToEntity(SaveCarResource resource) { return mapper.map(resource, Car.class);}
    private CarResource convertToResource(Car entity) { return mapper.map(entity, CarResource.class);}
}
