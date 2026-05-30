package com.senprac.sto.car.service;

import com.senprac.sto.car.repository.CarRepository;
import com.senprac.sto.client.repository.ClientRepository;
import com.senprac.sto.entity.Car;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private final CarRepository carRepository;
    private final ClientRepository clientRepository;

    public CarService(CarRepository carRepository, ClientRepository clientRepository) {
        this.carRepository = carRepository;
        this.clientRepository = clientRepository;
    }

    public Car addCar(Car car, Long clientId) {
        return clientRepository.findById(clientId).map(client -> {
            car.setClient(client);
            return carRepository.save(car);
        }).orElseThrow(() -> new RuntimeException("Клиент не найден"));
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Optional<Car> getCarById(Long id) {
        return carRepository.findById(id);
    }

    public Optional<Car> getCarByPlate(String plateNumber) {
        return carRepository.findByPlateNumber(plateNumber);
    }

    public Optional<Car> getCarByVin(String vin) {
        return carRepository.findByVin(vin);
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }
}