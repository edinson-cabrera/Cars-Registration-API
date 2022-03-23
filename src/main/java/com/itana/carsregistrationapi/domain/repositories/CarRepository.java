package com.itana.carsregistrationapi.domain.repositories;

import com.itana.carsregistrationapi.domain.models.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {

    Page<Car>findCarBy(long carId, Pageable pageable);

}
