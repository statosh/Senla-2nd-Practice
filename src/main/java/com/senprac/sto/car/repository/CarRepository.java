package com.senprac.sto.car.repository;

import com.senprac.sto.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {
    Optional<Car> findByPlateNumber(String plateNumber);
    Optional<Car> findByVin(String vin);
}